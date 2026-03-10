package org.orient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A language-neutral intermediate representation (IR) used between
 * the Lua parse/semantic model (AnnotatedTree/Scope/Symbol)
 * and the C# code generator.
 *
 * The design aims to:
 * - Represent modules, types, members, statements and expressions
 * - Carry over essential Lua semantic information (multiple returns, nilability)
 * - Reference semantic symbols where applicable, to leverage existing analysis
 */
public final class Ir {

    private Ir() {
    }

    // region Common infrastructure

    public interface Node {
    }

    public interface Statement extends Node {
    }

    public interface Expression extends Node {
        Symbol.Type getStaticType();
    }

    // endregion

    // region Module / type level

    public static final class Module implements Node {
        private final String name;
        private final List<TypeDecl> types;
        private final List<Statement> topLevelStatements;
        private final List<Method> topLevelMethods;

        public Module(String name, List<TypeDecl> types, List<Statement> topLevelStatements) {
            this(name, types, topLevelStatements, List.of());
        }

        public Module(String name, List<TypeDecl> types, List<Statement> topLevelStatements, List<Method> topLevelMethods) {
            this.name = Objects.requireNonNull(name, "name");
            this.types = defensiveCopy(types);
            this.topLevelStatements = defensiveCopy(topLevelStatements);
            this.topLevelMethods = defensiveCopy(topLevelMethods);
        }

        public String getName() {
            return name;
        }

        public List<TypeDecl> getTypes() {
            return types;
        }

        public List<Statement> getTopLevelStatements() {
            return topLevelStatements;
        }

        public List<Method> getTopLevelMethods() {
            return topLevelMethods;
        }
    }

    public sealed interface TypeDecl extends Node
            permits ClassDecl, StructDecl {
        String getName();
    }

    public static final class ClassDecl implements TypeDecl {
        private final String name;
        private final List<Member> members;

        public ClassDecl(String name, List<Member> members) {
            this.name = Objects.requireNonNull(name, "name");
            this.members = defensiveCopy(members);
        }

        @Override
        public String getName() {
            return name;
        }

        public List<Member> getMembers() {
            return members;
        }
    }

    public static final class StructDecl implements TypeDecl {
        private final String name;
        private final List<Member> members;

        public StructDecl(String name, List<Member> members) {
            this.name = Objects.requireNonNull(name, "name");
            this.members = defensiveCopy(members);
        }

        @Override
        public String getName() {
            return name;
        }

        public List<Member> getMembers() {
            return members;
        }
    }

    public sealed interface Member extends Node
            permits Field, Method {
        String getName();
    }

    public static final class Field implements Member {
        private final String name;
        private final Symbol.Type type;
        private final boolean isPublic;

        public Field(String name, Symbol.Type type, boolean isPublic) {
            this.name = Objects.requireNonNull(name, "name");
            this.type = Objects.requireNonNull(type, "type");
            this.isPublic = isPublic;
        }

        @Override
        public String getName() {
            return name;
        }

        public Symbol.Type getType() {
            return type;
        }

        public boolean isPublic() {
            return isPublic;
        }
    }

    public static final class Method implements Member {
        private final String name;
        private final List<Parameter> parameters;
        private final List<Symbol.Type> returnTypes;
        private final boolean isPublic;
        private final boolean isConstructor;
        private final Block body;

        public Method(
                String name,
                List<Parameter> parameters,
                List<Symbol.Type> returnTypes,
                boolean isPublic,
                boolean isConstructor,
                Block body) {
            this.name = Objects.requireNonNull(name, "name");
            this.parameters = defensiveCopy(parameters);
            this.returnTypes = defensiveCopy(returnTypes);
            this.isPublic = isPublic;
            this.isConstructor = isConstructor;
            this.body = Objects.requireNonNull(body, "body");
        }

        @Override
        public String getName() {
            return name;
        }

        public List<Parameter> getParameters() {
            return parameters;
        }

        public List<Symbol.Type> getReturnTypes() {
            return returnTypes;
        }

        public boolean isPublic() {
            return isPublic;
        }

        public boolean isConstructor() {
            return isConstructor;
        }

        public Block getBody() {
            return body;
        }
    }

    public static final class Parameter implements Node {
        private final String name;
        private final Symbol.Type type;
        private final Symbol symbol; // optional back reference

        public Parameter(String name, Symbol.Type type, Symbol symbol) {
            this.name = Objects.requireNonNull(name, "name");
            this.type = Objects.requireNonNull(type, "type");
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public Symbol.Type getType() {
            return type;
        }

        public Symbol getSymbol() {
            return symbol;
        }
    }

    // endregion

    // region Statements

    public static final class Block implements Statement {
        private final List<Statement> statements;
        private final Scope scope; // corresponding Lua scope, if any

        public Block(List<Statement> statements, Scope scope) {
            this.statements = defensiveCopy(statements);
            this.scope = scope;
        }

        public List<Statement> getStatements() {
            return statements;
        }

        public Scope getScope() {
            return scope;
        }
    }

    public static final class VariableDeclaration implements Statement {
        private final String name;
        private final Symbol.Type type;
        private final Expression initializer;
        private final Symbol symbol; // backing semantic symbol, if available

        public VariableDeclaration(String name, Symbol.Type type, Expression initializer, Symbol symbol) {
            this.name = Objects.requireNonNull(name, "name");
            this.type = Objects.requireNonNull(type, "type");
            this.initializer = initializer;
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public Symbol.Type getType() {
            return type;
        }

        public Expression getInitializer() {
            return initializer;
        }

        public Symbol getSymbol() {
            return symbol;
        }
    }

    public static final class Assignment implements Statement {
        private final Expression left;
        private final Expression right;

        public Assignment(Expression left, Expression right) {
            this.left = Objects.requireNonNull(left, "left");
            this.right = Objects.requireNonNull(right, "right");
        }

        public Expression getLeft() {
            return left;
        }

        public Expression getRight() {
            return right;
        }
    }

    public static final class Return implements Statement {
        private final List<Expression> values;

        public Return(List<Expression> values) {
            this.values = defensiveCopy(values);
        }

        public List<Expression> getValues() {
            return values;
        }
    }

    public static final class ExpressionStatement implements Statement {
        private final Expression expression;

        public ExpressionStatement(Expression expression) {
            this.expression = Objects.requireNonNull(expression, "expression");
        }

        public Expression getExpression() {
            return expression;
        }
    }

    public static final class TupleDeconstruction implements Statement {
        private final List<VariableDeclaration> variables;
        private final Call call;

        public TupleDeconstruction(List<VariableDeclaration> variables, Call call) {
            this.variables = defensiveCopy(variables);
            this.call = Objects.requireNonNull(call, "call");
        }

        public List<VariableDeclaration> getVariables() {
            return variables;
        }

        public Call getCall() {
            return call;
        }
    }

    // Additional statement kinds (if/for/while) can be added later.

    // endregion

    // region Expressions

    public static final class Literal implements Expression {
        private final Object value;
        private final Symbol.Type type;

        public Literal(Object value, Symbol.Type type) {
            this.value = value;
            this.type = Objects.requireNonNull(type, "type");
        }

        public Object getValue() {
            return value;
        }

        @Override
        public Symbol.Type getStaticType() {
            return type;
        }
    }

    public static final class VariableRef implements Expression {
        private final String name;
        private final Symbol symbol;
        private final Symbol.Type type;

        public VariableRef(String name, Symbol symbol, Symbol.Type type) {
            this.name = Objects.requireNonNull(name, "name");
            this.symbol = symbol;
            this.type = Objects.requireNonNull(type, "type");
        }

        public String getName() {
            return name;
        }

        public Symbol getSymbol() {
            return symbol;
        }

        @Override
        public Symbol.Type getStaticType() {
            return type;
        }
    }

    public static final class Call implements Expression {
        private final Expression target;
        private final List<Expression> arguments;
        private final List<Symbol.Type> returnTypes;

        public Call(Expression target, List<Expression> arguments, List<Symbol.Type> returnTypes) {
            this.target = Objects.requireNonNull(target, "target");
            this.arguments = defensiveCopy(arguments);
            this.returnTypes = defensiveCopy(returnTypes);
        }

        public Expression getTarget() {
            return target;
        }

        public List<Expression> getArguments() {
            return arguments;
        }

        public List<Symbol.Type> getReturnTypes() {
            return returnTypes;
        }

        @Override
        public Symbol.Type getStaticType() {
            if (returnTypes.isEmpty()) {
                return Symbol.Type.SYMBOL_TYPE_UNKNOWN;
            }
            return returnTypes.getFirst();
        }
    }

    public static final class Binary implements Expression {
        public enum Operator {
            ADD, SUBTRACT, MULTIPLY, DIVIDE, CONCAT,
            EQUALS, NOT_EQUALS, LESS_THAN, LESS_OR_EQUAL, GREATER_THAN, GREATER_OR_EQUAL
        }

        private final Operator operator;
        private final Expression left;
        private final Expression right;
        private final Symbol.Type type;

        public Binary(Operator operator, Expression left, Expression right, Symbol.Type type) {
            this.operator = Objects.requireNonNull(operator, "operator");
            this.left = Objects.requireNonNull(left, "left");
            this.right = Objects.requireNonNull(right, "right");
            this.type = Objects.requireNonNull(type, "type");
        }

        public Operator getOperator() {
            return operator;
        }

        public Expression getLeft() {
            return left;
        }

        public Expression getRight() {
            return right;
        }

        @Override
        public Symbol.Type getStaticType() {
            return type;
        }
    }

    public static final class TableInit implements Expression {
        public enum Kind {
            LIST,
            DICTIONARY,
            OBJECT
        }

        private final Kind kind;
        private final List<TableField> fields;
        private final Symbol.Type type;

        public TableInit(Kind kind, List<TableField> fields, Symbol.Type type) {
            this.kind = Objects.requireNonNull(kind, "kind");
            this.fields = defensiveCopy(fields);
            this.type = Objects.requireNonNull(type, "type");
        }

        public Kind getKind() {
            return kind;
        }

        public List<TableField> getFields() {
            return fields;
        }

        public List<TableField> getFieldsByName(String name) {
            if (fields.isEmpty()) {
                return Collections.emptyList();
            }
            List<TableField> result = new ArrayList<>();
            for (TableField field : fields) {
                if (name.equals(field.getName())) {
                    result.add(field);
                }
            }
            return result;
        }

        @Override
        public Symbol.Type getStaticType() {
            return type;
        }
    }

    public static final class TableField implements Node {
        private final String name; // may be null for pure list-style fields
        private final Expression value;

        public TableField(String name, Expression value) {
            this.name = name;
            this.value = Objects.requireNonNull(value, "value");
        }

        public String getName() {
            return name;
        }

        public Expression getValue() {
            return value;
        }
    }

    // endregion

    private static <T> List<T> defensiveCopy(List<T> list) {
        if (list == null || list.isEmpty()) {
            return List.of();
        }
        return List.copyOf(list);
    }
}

