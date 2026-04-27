// PROTOTYPE PATTERN TEMPLATE
// Use when: Creating an object is expensive or complex; clone an existing one instead.

// 1. Prototype Interface (Must have clone method)
interface Prototype {
    Prototype clone();
}

// 2. Concrete Prototype
class ConcretePrototype implements Prototype {
    private String state;

    public ConcretePrototype(String state) { this.state = state; }

    // Copy Constructor (optional but good practice)
    public ConcretePrototype(ConcretePrototype source) {
        this.state = source.state;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(this); // Clone itself
    }
}

// 3. Client
class PrototypeTemplateDemo {
    public static void main(String[] args) {
        ConcretePrototype original = new ConcretePrototype("Initial State");
        
        // Clone instead of using 'new'
        ConcretePrototype copy = (ConcretePrototype) original.clone();
    }
}
