// FACTORY METHOD PATTERN TEMPLATE
// Use when: A class cannot anticipate the class of objects it must create, 
// and wants its subclasses to specify the objects it creates.

// 1. Product Interface
interface Product {
    void doStuff();
}

// 2. Concrete Products
class ConcreteProductA implements Product {
    public void doStuff() { System.out.println("Product A"); }
}

class ConcreteProductB implements Product {
    public void doStuff() { System.out.println("Product B"); }
}

// 3. Creator (Abstract Factory Class)
abstract class Creator {
    // The Factory Method
    protected abstract Product createProduct();

    // Some operation that uses the product
    public void someOperation() {
        Product p = createProduct();
        p.doStuff();
    }
}

// 4. Concrete Creators (Subclasses decide what to instantiate)
class ConcreteCreatorA extends Creator {
    protected Product createProduct() { return new ConcreteProductA(); }
}

class ConcreteCreatorB extends Creator {
    protected Product createProduct() { return new ConcreteProductB(); }
}

// 5. Client
class FactoryTemplateDemo {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreatorA();
        creator.someOperation(); // Uses Product A under the hood
    }
}
