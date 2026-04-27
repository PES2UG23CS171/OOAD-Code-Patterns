// Interpreter Pattern - Postfix Expression Evaluator
import java.util.Stack;

// ── Expression Interface ───────────────────────────────────────────────────
interface Expression {
    int evaluate();
}

// ── Terminal Expressions (leaf nodes — hold actual numbers) ────────────────
class WholeNumber implements Expression {
    private int number;
    public WholeNumber(int number) { this.number = number; }
    public WholeNumber(String str) { this.number = Integer.parseInt(str); }
    public int evaluate() { return number; }
}

// ── Non-Terminal Expressions (operations — combine two expressions) ─────────
class Addition implements Expression {
    private Expression left, right;
    public Addition(Expression left, Expression right) { this.left = left; this.right = right; }
    public int evaluate() { return left.evaluate() + right.evaluate(); }
}

class Subtraction implements Expression {
    private Expression left, right;
    public Subtraction(Expression left, Expression right) { this.left = left; this.right = right; }
    public int evaluate() { return left.evaluate() - right.evaluate(); }
}

class Multiply implements Expression {
    private Expression left, right;
    public Multiply(Expression left, Expression right) { this.left = left; this.right = right; }
    public int evaluate() { return left.evaluate() * right.evaluate(); }
}

class Divide implements Expression {
    private Expression numerator, denominator;
    public Divide(Expression numerator, Expression denominator) {
        this.numerator = numerator; this.denominator = denominator;
    }
    public int evaluate() {
        try { return numerator.evaluate() / denominator.evaluate(); }
        catch (ArithmeticException e) { System.out.println("Division by Zero"); throw e; }
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class InterpreterDemo {
    public static void main(String[] args) {
        Stack<Expression> stack = new Stack<>();
        String postFix = "5 3 * 2 + 1 - 4 /"; // = (5*3 + 2 - 1) / 4 = 4
        for (String token : postFix.split(" ")) {
            if (isOperator(token)) {
                Expression right = stack.pop();
                Expression left  = stack.pop();
                stack.push(getOperator(token, left, right));
            } else {
                stack.push(new WholeNumber(token));
            }
        }
        System.out.println("Result: " + stack.pop().evaluate()); // 4
    }

    static boolean isOperator(String s) { return "+-*/".contains(s); }

    static Expression getOperator(String s, Expression left, Expression right) {
        switch (s) {
            case "+": return new Addition(left, right);
            case "-": return new Subtraction(left, right);
            case "*": return new Multiply(left, right);
            case "/": return new Divide(left, right);
            default:  return null;
        }
    }
}
