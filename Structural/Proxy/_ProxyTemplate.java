// PROXY PATTERN TEMPLATE
// Use when: You want to control access to an object (lazy loading, access control, logging).

// 1. Subject Interface
interface Subject {
    void request();
}

// 2. Real Subject (The actual object that does the real work, maybe expensive to create)
class RealSubject implements Subject {
    public void request() {
        System.out.println("RealSubject: Handling request.");
    }
}

// 3. Proxy (Controls access to RealSubject)
class Proxy implements Subject {
    private RealSubject realSubject;

    public void request() {
        // Proxy can add logic BEFORE delegating (e.g., access control, lazy initialization)
        if (realSubject == null) {
            realSubject = new RealSubject(); // Lazy load
        }
        
        System.out.println("Proxy: Logging request before delegation.");
        realSubject.request(); // Delegate work to real subject
    }
}

// 4. Client
class ProxyTemplateDemo {
    public static void main(String[] args) {
        Subject proxy = new Proxy();
        proxy.request(); // Client thinks it's talking to the real object
    }
}
