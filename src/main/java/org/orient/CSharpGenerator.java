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
        for (Ir.TypeDecl type : module.getTypes()) {
            appendTypeDecl(sb, type);
            sb.append(System.lineSeparator());
        }
        for (Ir.Statement statement : module.getTopLevelStatements()) {
            if (statement instanceof Ir.Comment comment) {
                sb.append(comment.getText()).append(System.lineSeparator());
            } else if (statement instanceof Ir.MethodDeclaration methodDecl) {
                appendMethod(sb, methodDecl.getMethod());
                sb.append(System.lineSeparator());
            } else if (statement instanceof Ir.VariableDeclaration decl) {
                appendVariableDeclaration(sb, decl);
                sb.append(System.lineSeparator());
            } else if (statement instanceof Ir.TupleDeconstruction tuple) {
                appendTupleDeconstruction(sb, tuple);
                sb.append(System.lineSeparator());
            } else if (statement instanceof Ir.Assignment assign) {
                appendExpression(sb, assign.getLeft());
                sb.append(" = ");
                appendExpression(sb, assign.getRight());
                sb.append(';').append(System.lineSeparator());
            } else if (statement instanceof Ir.ExpressionStatement exprStmt) {
                appendExpression(sb, exprStmt.getExpression());
                sb.append(';').append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    private void appendTypeDecl(StringBuilder sb, Ir.TypeDecl type) {
        if (type instanceof Ir.StructDecl st) {
            appendStructDecl(sb, st);
        } else if (type instanceof Ir.ClassDecl cls) {
            appendClassDecl(sb, cls);
        }
    }

    private void appendStructDecl(StringBuilder sb, Ir.StructDecl st) {
        sb.append("struct ").append(st.getName()).append(System.lineSeparator());
        sb.append('{').append(System.lineSeparator());
        for (Ir.Member m : st.getMembers()) {
            if (m instanceof Ir.Field f) {
                sb.append("    ");
                if (f.isPublic()) sb.append("public ");
                if (f.isConst()) sb.append("const ");
                sb.append(Util.SymbolType2Str(f.getType())).append(' ').append(f.getName());
                if (f.getInitializer() != null) {
                    sb.append(" = ");
                    appendExpression(sb, f.getInitializer());
                }
                sb.append(';').append(System.lineSeparator());
            }
        }
        sb.append('}').append(System.lineSeparator());
    }

    private void appendClassDecl(StringBuilder sb, Ir.ClassDecl cls) {
        sb.append("class ").append(cls.getName()).append(System.lineSeparator());
        sb.append('{').append(System.lineSeparator());
        // minimal: fields only (extend later)
        for (Ir.Member m : cls.getMembers()) {
            if (m instanceof Ir.Field f) {
                sb.append("    ");
                if (f.isPublic()) sb.append("public ");
                if (f.isConst()) sb.append("const ");
                sb.append(Util.SymbolType2Str(f.getType())).append(' ').append(f.getName());
                if (f.getInitializer() != null) {
                    sb.append(" = ");
                    appendExpression(sb, f.getInitializer());
                }
                sb.append(';').append(System.lineSeparator());
            }
        }
        sb.append('}').append(System.lineSeparator());
    }

    private void appendVariableDeclaration(StringBuilder sb, Ir.VariableDeclaration decl) {
        String typeName = Util.SymbolType2Str(decl.getType());
        sb.append(typeName);
        if (decl.getInitializer() instanceof Ir.Literal lit && "null".equals(lit.getValue())) {
            sb.append('?');
        }
        sb.append(' ')
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
        } else if (expr instanceof Ir.Call call) {
            appendExpression(sb, call.getTarget());
            sb.append('(');
            for (int i = 0; i < call.getArguments().size(); i++) {
                if (i > 0) sb.append(", ");
                appendExpression(sb, call.getArguments().get(i));
            }
            sb.append(')');
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

    private void appendTupleDeconstruction(StringBuilder sb, Ir.TupleDeconstruction tuple) {
        sb.append('(');
        for (int i = 0; i < tuple.getVariables().size(); i++) {
            if (i > 0) sb.append(", ");
            Ir.VariableDeclaration v = tuple.getVariables().get(i);
            String name = v.getName() != null ? v.getName() : "_";
            // 对占位符 "_" 不输出类型，只输出 "_"
            if ("_".equals(name)) {
                sb.append("_");
            } else {
                String typeName = Util.SymbolType2Str(v.getType());
                sb.append(typeName)
                  .append(' ')
                  .append(name);
            }
        }
        sb.append(") = ");
        appendExpression(sb, tuple.getCall());
        sb.append(';');
    }

    private void appendMethod(StringBuilder sb, Ir.Method method) {
        // return type
        if (method.getReturnTypes().isEmpty()) {
            sb.append("void");
        } else if (method.getReturnTypes().size() == 1) {
            sb.append(Util.SymbolType2Str(method.getReturnTypes().getFirst()));
        } else {
            sb.append('(');
            for (int i = 0; i < method.getReturnTypes().size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(Util.SymbolType2Str(method.getReturnTypes().get(i)));
            }
            sb.append(')');
        }
        sb.append(' ')
          .append(method.getName())
          .append('(');

        // parameters
        for (int i = 0; i < method.getParameters().size(); i++) {
            if (i > 0) sb.append(", ");
            Ir.Parameter p = method.getParameters().get(i);
            sb.append(Util.SymbolType2Str(p.getType()))
              .append(' ')
              .append(p.getName());
        }
        sb.append(')').append(System.lineSeparator())
          .append('{').append(System.lineSeparator());

        for (Ir.Statement stmt : method.getBody().getStatements()) {
            if (stmt instanceof Ir.Comment comment) {
                sb.append(comment.getText()).append(System.lineSeparator());
            } else if (stmt instanceof Ir.VariableDeclaration decl) {
                appendVariableDeclaration(sb, decl);
                sb.append(System.lineSeparator());
            } else if (stmt instanceof Ir.Return ret) {
                appendReturn(sb, ret);
                sb.append(System.lineSeparator());
            } else if (stmt instanceof Ir.ExpressionStatement exprStmt) {
                appendExpression(sb, exprStmt.getExpression());
                sb.append(';').append(System.lineSeparator());
            }
        }

        sb.append('}').append(System.lineSeparator());
    }

    private void appendReturn(StringBuilder sb, Ir.Return ret) {
        sb.append("return");
        if (!ret.getValues().isEmpty()) {
            sb.append(' ');
            if (ret.getValues().size() == 1) {
                appendExpression(sb, ret.getValues().getFirst());
            } else {
                sb.append('(');
                for (int i = 0; i < ret.getValues().size(); i++) {
                    if (i > 0) sb.append(", ");
                    appendExpression(sb, ret.getValues().get(i));
                }
                sb.append(')');
            }
        }
        sb.append(';');
    }
}

