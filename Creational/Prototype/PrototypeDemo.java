// Prototype Pattern - Shape Cloning
// Key idea: Instead of creating a new object from scratch, CLONE an existing one.
// Useful when object creation is expensive or complex.
// Each class knows how to clone itself via a clone() method.
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// ── Prototype (abstract base) ──────────────────────────────────────────────
// Declares clone() — every subclass must implement it
abstract class Shape {
    public int x, y;
    public String color;

    public Shape() {}

    // Copy constructor — copies base fields from an existing shape
    public Shape(Shape source) {
        if (source != null) { this.x = source.x; this.y = source.y; this.color = source.color; }
    }

    public abstract Shape clone(); // ← every subclass clones itself

    public boolean equals(Object o) {
        if (!(o instanceof Shape)) return false;
        Shape s = (Shape) o;
        return s.x == x && s.y == y && Objects.equals(s.color, color);
    }
}

// ── Concrete Prototypes ────────────────────────────────────────────────────
class Circle extends Shape {
    public int radius;
    public Circle() {}
    public Circle(Circle source) { super(source); if (source != null) this.radius = source.radius; }
    public Shape clone() { return new Circle(this); } // clones itself
    public boolean equals(Object o) {
        if (!(o instanceof Circle) || !super.equals(o)) return false;
        return ((Circle) o).radius == radius;
    }
}

class Rectangle extends Shape {
    public int width, height;
    public Rectangle() {}
    public Rectangle(Rectangle source) { super(source); if (source != null) { this.width = source.width; this.height = source.height; } }
    public Shape clone() { return new Rectangle(this); } // clones itself
    public boolean equals(Object o) {
        if (!(o instanceof Rectangle) || !super.equals(o)) return false;
        Rectangle r = (Rectangle) o;
        return r.width == width && r.height == height;
    }
}

// ── Prototype Registry (optional) ─────────────────────────────────────────
// Stores pre-built prototypes — returns CLONES on request (not the originals)
class ShapeCache {
    private Map<String, Shape> cache = new HashMap<>();

    public ShapeCache() {
        Circle circle = new Circle();
        circle.x = 5; circle.y = 7; circle.radius = 45; circle.color = "Green";

        Rectangle rect = new Rectangle();
        rect.x = 6; rect.y = 9; rect.width = 8; rect.height = 10; rect.color = "Blue";

        cache.put("circle", circle);
        cache.put("rectangle", rect);
    }

    public Shape get(String key) { return cache.get(key).clone(); } // always returns a clone
}

// ── Client ─────────────────────────────────────────────────────────────────
class PrototypeDemo {
    public static void main(String[] args) {
        // Direct cloning
        Circle original = new Circle();
        original.x = 10; original.y = 20; original.radius = 15; original.color = "Red";

        Circle cloned = (Circle) original.clone();

        System.out.println("Same object? " + (original == cloned));    // false
        System.out.println("Same data?   " + original.equals(cloned)); // true

        System.out.println("---");

        // Using registry
        ShapeCache cache = new ShapeCache();
        Shape c1 = cache.get("circle");
        Shape c2 = cache.get("circle");

        System.out.println("Same object? " + (c1 == c2));    // false — different clones
        System.out.println("Same data?   " + c1.equals(c2)); // true — same values
    }
}
