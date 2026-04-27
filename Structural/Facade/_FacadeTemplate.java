// FACADE PATTERN TEMPLATE
// Use when: You want to provide a simple interface to a complex subsystem.

// 1. Complex Subsystem Classes
class SubSystemA { public void operationA() { System.out.println("System A"); } }
class SubSystemB { public void operationB() { System.out.println("System B"); } }
class SubSystemC { public void operationC() { System.out.println("System C"); } }

// 2. Facade (Hides the complexity)
class Facade {
    private SubSystemA a = new SubSystemA();
    private SubSystemB b = new SubSystemB();
    private SubSystemC c = new SubSystemC();

    // Simplified method for the client
    public void doSimpleOperation() {
        a.operationA();
        b.operationB();
        c.operationC();
    }
}

// 3. Client
class FacadeTemplateDemo {
    public static void main(String[] args) {
        Facade facade = new Facade();
        // Client only talks to the Facade, knows nothing about A, B, or C
        facade.doSimpleOperation();
    }
}
