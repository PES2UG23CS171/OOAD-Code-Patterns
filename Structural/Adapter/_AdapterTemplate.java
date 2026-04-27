// ADAPTER PATTERN TEMPLATE (Object Adapter via Composition)
// Use when: You need to make two incompatible interfaces work together.

// 1. Target Interface (What the client expects)
interface Target {
    void request();
}

// 2. Adaptee (The existing, incompatible class we want to use)
class Adaptee {
    public void specificRequest() { 
        System.out.println("Adaptee doing work"); 
    }
}

// 3. Adapter (Wraps the Adaptee to match the Target interface)
class Adapter implements Target {
    private Adaptee adaptee;

    // Pass the adaptee via constructor (Composition)
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        // Translate the call
        adaptee.specificRequest();
    }
}

// 4. Client
class AdapterTemplateDemo {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        
        // Target obj = new Adapter(adaptee)
        Target target = new Adapter(adaptee);
        
        target.request(); // Client uses Target interface, but Adaptee does the work
    }
}
