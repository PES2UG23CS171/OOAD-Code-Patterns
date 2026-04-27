// SINGLETON PATTERN TEMPLATE
// Use when: You need exactly one instance of a class, and global access to it.

class Singleton {
    // 1. Private static instance (holds the single object)
    private static Singleton instance;

    // 2. Private constructor (prevents instantiation from outside)
    private Singleton() { }

    // 3. Public static method (provides global access)
    public static Singleton getInstance() {
        // Lazy initialization
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("Singleton is working.");
    }
}

// 4. Client
class SingletonTemplateDemo {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        
        System.out.println(s1 == s2); // true (same instance)
    }
}
