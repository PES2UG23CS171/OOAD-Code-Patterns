/*
GRASP polymorphism - When behavior varies based on type, 
assign responsibility to the types for which the behavior varies.

--------------------------------------------------------------
--- The Four Types of Polymorphism (Cheat Sheet Examples)  ---
--------------------------------------------------------------

1. Ad-hoc (Overloading)
   Same method name, different parameter types.
   class Calculator {
       int add(int a, int b) { return a + b; }
       double add(double a, double b) { return a + b; }
   }

2. Parametric (Early Binding / Generics)
   Writing ONE piece of code that works with ANY type using `<T>`.
   "Early Binding" means the compiler checks the types BEFORE the program runs.
   
  // A generic blueprint (Parametric Polymorphism)
class Box<T> {
    private T item;
    
    public void set(T item) { this.item = item; }
    public T get() { return item; }
}

public class Main {
    public static void main(String[] args) {
        // The compiler binds this Box strictly to Integers
        Box<Integer> intBox = new Box<>();
        intBox.set(100); 
        // intBox.set("Hello"); // The compiler catches this error immediately!

        // The compiler binds this Box strictly to Strings
        Box<String> strBox = new Box<>();
        strBox.set("Java");
    }
}

3. Inclusion (Subtyping / Runtime Polymorphism)
   Derived via base class pointers; resolved at runtime.
   (This is what the active code below demonstrates!)
   Payment p = new CreditCardPayment();
   p.processPayment(); // Calls CreditCardPayment's version

4. Coercion (Casting)
   Object cast to another type (implicit or explicit).
   double d = 100;       // Implicit coercion: int 100 becomes double 100.0
   int i = (int) 100.9;  // Explicit coercion: double 100.9 is cast to int 100


 GRASP polymorphism - When behavior varies based on type,
 assign responsibility to the types for which the behavior varies.
 
 */

// common interface
interface Payment {
    void processPayment();
}

// Different payment types implement behavior
class CreditCardPayment implements Payment {
    public void processPayment() {
        System.out.println("Processing Credit Card payment");
    }
}

class UPIPayment implements Payment {
    public void processPayment() {
        System.out.println("Processing UPI payment");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        Payment payment = new CreditCardPayment();
        payment.processPayment();

        payment = new UPIPayment();
        payment.processPayment();
    }
}