// BUILDER PATTERN TEMPLATE
// Use when: Creating a complex object step-by-step.

// 1. Product (The complex object)
class Product {
    // fields
}

// 2. Builder Interface (The steps)
interface Builder {
    void buildPartA();
    void buildPartB();
    Product getResult();
}

// 3. Concrete Builder (Implementation of steps)
class ConcreteBuilder implements Builder {
    private Product product = new Product();
    public void buildPartA() { /* build A */ }
    public void buildPartB() { /* build B */ }
    public Product getResult() { return product; }
}

// 4. Director (Controls the building process sequence)
class Director {
    private Builder builder;
    public void setBuilder(Builder builder) { this.builder = builder; }
    public void construct() {
        builder.buildPartA();
        builder.buildPartB();
    }
}

// 5. Client
class BuilderTemplateDemo {
    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new ConcreteBuilder();
        director.setBuilder(builder);
        director.construct();
        Product p = builder.getResult();
    }
}
