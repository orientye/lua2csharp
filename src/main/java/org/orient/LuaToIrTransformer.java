package org.orient;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Lua AST + semantic information (AnnotatedTree) to IR (Ir.Module) transformer.
 *
 * 当前版本专门支持最基础的一类语句：
 *   - 顶层的 `local name = <exp>` 形式
 *   - 表达式包括：字面量、变量引用、简单二元运算（+ - * /）
 * 这已经足以覆盖 Exp.lua 示例，后续可以按需要扩展更多规则。
 */
public class LuaToIrTransformer {

    private final AnnotatedTree annotatedTree;

    public LuaToIrTransformer(AnnotatedTree annotatedTree) {
        this.annotatedTree = annotatedTree;
    }

    public Ir.Module transform(ParseTree root, String moduleName) {
        List<Ir.Statement> topLevelStatements = new ArrayList<>();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new LuaParserBaseListener() {
            @Override
            public void exitStat(LuaParser.StatContext ctx) {
                // 'local' attnamelist '=' explist
                LuaParser.AttnamelistContext attnamelistContext = ctx.attnamelist();
                LuaParser.ExplistContext explistContext = ctx.explist();
                if (ctx.LOCAL() != null && attnamelistContext != null && explistContext != null) {
                    List<TerminalNode> names = attnamelistContext.NAME();
                    if (names.size() == 1 && explistContext.exp().size() == 1) {
                        String varName = names.getFirst().getText();
                        Symbol.Type varType = Util.GetExpContextTypeInList(0, explistContext, annotatedTree);
                        LuaParser.ExpContext expContext = explistContext.exp().getFirst();
                        Ir.Expression initializer = toIrExpression(expContext);
                        Symbol symbol = annotatedTree.symbols.get(expContext);
                        Ir.VariableDeclaration decl = new Ir.VariableDeclaration(varName, varType, initializer, symbol);
                        topLevelStatements.add(decl);
                    }
                }
            }
        }, root);

        String name = moduleName != null ? moduleName : "LuaModule";
        return new Ir.Module(name, List.of(), topLevelStatements);
    }

    private Ir.Expression toIrExpression(LuaParser.ExpContext ctx) {
        // Literal numbers, booleans, strings
        if (ctx.number() != null || ctx.string() != null || ctx.TRUE() != null || ctx.FALSE() != null) {
            String text = ctx.getText();
            Symbol.Type type = Util.GetExpContextTypeInTree(ctx, annotatedTree);
            return new Ir.Literal(text, type);
        }

        // Simple variable reference: in the generated parser, a bare identifier
        // is represented as an ExpContext whose single child is a NAME terminal.
        if (ctx.getChildCount() == 1 && ctx.getChild(0) instanceof TerminalNode) {
            String name = ctx.getText();
            Symbol symbol = annotatedTree.symbols.get(ctx);
            Symbol.Type type = symbol != null ? symbol.getType() : Symbol.Type.SYMBOL_TYPE_UNKNOWN;
            return new Ir.VariableRef(name, symbol, type);
        }

        // Simple binary expressions: exp op exp
        if (ctx.getChildCount() == 3
                && ctx.getChild(0) instanceof LuaParser.ExpContext leftCtx
                && ctx.getChild(2) instanceof LuaParser.ExpContext rightCtx
                && ctx.getChild(1) instanceof TerminalNode opNode) {
            Ir.Expression left = toIrExpression(leftCtx);
            Ir.Expression right = toIrExpression(rightCtx);

            Token opToken = opNode.getSymbol();
            String opText = opToken.getText();
            Ir.Binary.Operator op;
            switch (opText) {
                case "+" -> op = Ir.Binary.Operator.ADD;
                case "-" -> op = Ir.Binary.Operator.SUBTRACT;
                case "*" -> op = Ir.Binary.Operator.MULTIPLY;
                case "/" -> op = Ir.Binary.Operator.DIVIDE;
                default -> {
                    // fallback: treat as unknown literal of original text
                    Symbol.Type type = Util.GetExpContextTypeInTree(ctx, annotatedTree);
                    return new Ir.Literal(ctx.getText(), type);
                }
            }

            Symbol.Type resultType = Util.GetExpContextTypeInTree(ctx, annotatedTree);
            return new Ir.Binary(op, left, right, resultType);
        }

        // Fallback: use raw text as a literal of unknown type
        Symbol.Type type = Util.GetExpContextTypeInTree(ctx, annotatedTree);
        return new Ir.Literal(ctx.getText(), type);
    }
}

