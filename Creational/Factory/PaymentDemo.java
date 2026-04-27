// Factory Pattern - Payment Method (Simple Factory / Static Factory)
// Key idea: Client asks a Factory for an object by NAME — doesn't need to know which class to instantiate.

// ── Product Interface ──────────────────────────────────────────────────────
interface PaymentMethod {
    void pay(double amount);
}

// ── Concrete Products ──────────────────────────────────────────────────────
class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) { System.out.println("Paid ₹" + amount + " using Credit Card"); }
}

class PayPalPayment implements PaymentMethod {
    public void pay(double amount) { System.out.println("Paid ₹" + amount + " using PayPal"); }
}

class UpiPayment implements PaymentMethod {
    public void pay(double amount) { System.out.println("Paid ₹" + amount + " using UPI"); }
}

// ── Factory ────────────────────────────────────────────────────────────────
// Static method decides which product class to instantiate
class PaymentFactory {
    public static PaymentMethod getPaymentMethod(String type) {
        switch (type.toLowerCase()) {
            case "creditcard": return new CreditCardPayment();
            case "paypal":     return new PayPalPayment();
            case "upi":        return new UpiPayment();
            default: throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class PaymentDemo {
    public static void main(String[] args) {
        PaymentMethod p1 = PaymentFactory.getPaymentMethod("creditcard");
        p1.pay(500);

        PaymentMethod p2 = PaymentFactory.getPaymentMethod("upi");
        p2.pay(300);

        PaymentMethod p3 = PaymentFactory.getPaymentMethod("paypal");
        p3.pay(700);
    }
}
