// INTERPRETER PATTERN TEMPLATE
// Use when: You have a grammar/language to evaluate and want to represent its rules as classes.

// 1. Abstract Expression
interface Expression {
    boolean interpret(String context);
}

// 2. Terminal Expression (Leaf node in syntax tree, checks literal values)
class TerminalExpression implements Expression {
    private String data;
    
    public TerminalExpression(String data) { this.data = data; }

    public boolean interpret(String context) {
        return context.contains(data);
    }
}

// 3. Non-Terminal Expression (Operator node, combines two expressions)
class OrExpression implements Expression {
    private Expression expr1, expr2;
    
    public OrExpression(Expression expr1, Expression expr2) { 
        this.expr1 = expr1; this.expr2 = expr2; 
    }

    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

// 4. Client
class InterpreterTemplateDemo {
    public static void main(String[] args) {
        Expression isMale = new OrExpression(
            new TerminalExpression("John"), 
            new TerminalExpression("Robert")
        );

        System.out.println("Is John male? " + isMale.interpret("John"));
    }
}
