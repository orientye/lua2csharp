package org.orient;

/**
 * Very small C# code generator that consumes an {@link Ir.Module}
 * and produces C# source code. The initial implementation is focused
 * on supporting the Exp.lua example: top-level variable declarations
 * and simple expressions.
 */
public class CSharpGenerator {

    private final Ir.Module module;

    public CSharpGenerator(Ir.Module module) {
        this.module = module;
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();
        for (Ir.Statement statement : module.getTopLevelStatements()) {
            if (statement instanceof Ir.VariableDeclaration decl) {
                appendVariableDeclaration(sb, decl);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    private void appendVariableDeclaration(StringBuilder sb, Ir.VariableDeclaration decl) {
        String typeName = Util.SymbolType2Str(decl.getType());
        sb.append(typeName)
                .append(' ')
                .append(decl.getName())
                .append(" = ");
        appendExpression(sb, decl.getInitializer());
        sb.append(';');
    }

    private void appendExpression(StringBuilder sb, Ir.Expression expr) {
        if (expr instanceof Ir.Literal literal) {
            Object value = literal.getValue();
            sb.append(value != null ? value.toString() : "null");
        } else if (expr instanceof Ir.VariableRef varRef) {
            sb.append(varRef.getName());
        } else if (expr instanceof Ir.Binary binary) {
            appendBinary(sb, binary);
        } else {
            // Fallback to ToString representation
            sb.append(expr.toString());
        }
    }

    private void appendBinary(StringBuilder sb, Ir.Binary binary) {
        appendExpression(sb, binary.getLeft());
        sb.append(switch (binary.getOperator()) {
            case ADD -> "+";
            case SUBTRACT -> "-";
            case MULTIPLY -> "*";
            case DIVIDE -> "/";
            case CONCAT -> "+";
            case EQUALS -> "==";
            case NOT_EQUALS -> "!=";
            case LESS_THAN -> "<";
            case LESS_OR_EQUAL -> "<=";
            case GREATER_THAN -> ">";
            case GREATER_OR_EQUAL -> ">=";
        });
        appendExpression(sb, binary.getRight());
    }
}

