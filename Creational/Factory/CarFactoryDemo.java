// Factory Method Pattern - Car Factory
// Key idea: The ABSTRACT Factory defines a factory method (retrieveCar).
// SUBCLASSES decide which specific Car class to instantiate.
// The base class handles the common "prepare" steps — subclass handles the "which car" step.

// ── Product (abstract base) ────────────────────────────────────────────────
abstract class Car {
    protected int horsePower;
    protected String color;
    protected String fuelSource;

    public Car(int horsePower, String color, String fuelSource) {
        this.horsePower = horsePower;
        this.color = color;
        this.fuelSource = fuelSource;
    }

    public void cleanCar()     { System.out.println(getClass().getSimpleName() + " cleaned"); }
    public void mechanicCheck(){ System.out.println(getClass().getSimpleName() + " mechanic checked"); }
    public void fuelCar()      { System.out.println(getClass().getSimpleName() + " fueled with " + fuelSource); }
    public void startEngine()  { System.out.println(getClass().getSimpleName() + " engine started — " + horsePower + " HP"); }
}

// ── Concrete Products ──────────────────────────────────────────────────────
class Audi       extends Car { public Audi()       { super(300, "Black",  "Petrol"); } }
class Tesla      extends Car { public Tesla()      { super(400, "White",  "Electric"); } }
class Toyota     extends Car { public Toyota()     { super(200, "Silver", "Hybrid"); } }
class Volkswagen extends Car { public Volkswagen() { super(180, "Blue",   "Diesel"); } }

// ── Abstract Creator (Factory Method Pattern) ──────────────────────────────
// create() is the TEMPLATE METHOD — it calls retrieveCar() which subclasses override
abstract class CarCreator {
    public Car create(String type) {
        Car car = retrieveCar(type);   // factory method — subclass decides which car
        car.cleanCar();
        car.mechanicCheck();
        car.fuelCar();
        return car;
    }
    protected abstract Car retrieveCar(String type); // ← the factory method
}

// ── Concrete Creators (Subclasses decide what to make) ─────────────────────
class LuxuryCarFactory extends CarCreator {
    protected Car retrieveCar(String type) {
        switch (type.toLowerCase()) {
            case "tesla": return new Tesla();
            case "audi":  return new Audi();
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}

class CompanyCarFactory extends CarCreator {
    protected Car retrieveCar(String type) {
        switch (type.toLowerCase()) {
            case "toyota":     return new Toyota();
            case "volkswagen": return new Volkswagen();
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class CarFactoryDemo {
    public static void main(String[] args) {
        CarCreator luxury = new LuxuryCarFactory();
        Car tesla = luxury.create("tesla");
        tesla.startEngine();

        System.out.println("---");

        CarCreator company = new CompanyCarFactory();
        Car toyota = company.create("toyota");
        toyota.startEngine();
    }
}
