// Builder Pattern - Burger Example
// Key idea: Separate the CONSTRUCTION of a complex object from its REPRESENTATION.
// The same Director (Chef) can build different products (Burgers) using different Builders.

// ── Product ────────────────────────────────────────────────────────────────
// The complex object being built step by step
class Burger {
    private String bun, meat, cheese, salad, sauce;
    public void setBun(String bun)       { this.bun = bun; }
    public void setMeat(String meat)     { this.meat = meat; }
    public void setCheese(String cheese) { this.cheese = cheese; }
    public void setSalad(String salad)   { this.salad = salad; }
    public void setSauce(String sauce)   { this.sauce = sauce; }
    public void show() {
        System.out.println("Burger: " + bun + " | " + meat + " | " + cheese + " | " + salad + " | " + sauce);
    }
}

// ── Builder Interface ──────────────────────────────────────────────────────
// Defines the steps — each concrete builder fills them differently
interface BurgerBuilder {
    void buildBun();
    void buildMeat();
    void buildCheese();
    void buildSalad();
    void buildSauce();
    Burger getBurger();
}

// ── Concrete Builders ──────────────────────────────────────────────────────
class CheeseBurgerBuilder implements BurgerBuilder {
    private Burger burger = new Burger();
    public void buildBun()    { burger.setBun("White Bun"); }
    public void buildMeat()   { burger.setMeat("Beef Patty"); }
    public void buildCheese() { burger.setCheese("Cheddar Cheese"); }
    public void buildSalad()  { burger.setSalad("Lettuce"); }
    public void buildSauce()  { burger.setSauce("Mayo"); }
    public Burger getBurger() { return burger; }
}

class VegBurgerBuilder implements BurgerBuilder {
    private Burger burger = new Burger();
    public void buildBun()    { burger.setBun("Whole Wheat Bun"); }
    public void buildMeat()   { burger.setMeat("Veg Patty"); }
    public void buildCheese() { burger.setCheese("No Cheese"); }
    public void buildSalad()  { burger.setSalad("Tomato & Lettuce"); }
    public void buildSauce()  { burger.setSauce("Mint Sauce"); }
    public Burger getBurger() { return burger; }
}

// ── Director ───────────────────────────────────────────────────────────────
// Knows the ORDER of steps — doesn't know WHAT is built
class Chef {
    private BurgerBuilder builder;
    public void setBuilder(BurgerBuilder builder) { this.builder = builder; }
    public void constructBurger() {
        builder.buildBun();
        builder.buildMeat();
        builder.buildCheese();
        builder.buildSalad();
        builder.buildSauce();
    }
    public Burger getBurger() { return builder.getBurger(); }
}

// ── Client ─────────────────────────────────────────────────────────────────
class BurgerDemo {
    public static void main(String[] args) {
        Chef chef = new Chef();

        chef.setBuilder(new CheeseBurgerBuilder());
        chef.constructBurger();
        chef.getBurger().show();

        chef.setBuilder(new VegBurgerBuilder());
        chef.constructBurger();
        chef.getBurger().show();
    }
}
