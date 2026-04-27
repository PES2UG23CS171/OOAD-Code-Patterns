// Flyweight Pattern - Drawing App
import java.util.HashMap;
import java.util.Map;

// ── Flyweight Interface ────────────────────────────────────────────────────
// Defines the shared operation — color is the INTRINSIC (shared) state
// shape is the EXTRINSIC (per-call) state passed in at runtime
interface Color {
    void applyColor(String shape); // shape = extrinsic state
}

// ── Concrete Flyweight ─────────────────────────────────────────────────────
// This object is SHARED — created only once per color
class PenColor implements Color {
    private final String color; // intrinsic state — stored inside, never changes

    public PenColor(String color) {
        this.color = color;
        System.out.println(">>> Creating new PenColor object: " + color);
    }

    public void applyColor(String shape) {
        System.out.println("Drawing " + shape + " with color " + color);
    }
}

// ── Flyweight Factory ──────────────────────────────────────────────────────
// Caches and reuses PenColor objects — never creates duplicates
class ColorFactory {
    private static final Map<String, Color> cache = new HashMap<>();

    public static Color getColor(String colorName) {
        if (!cache.containsKey(colorName)) {
            cache.put(colorName, new PenColor(colorName)); // create only if not cached
        }
        return cache.get(colorName); // return the shared object
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class DrawingApp {
    public static void main(String[] args) {
        // "Red" is requested twice — only ONE PenColor object is created
        Color red1 = ColorFactory.getColor("Red");
        red1.applyColor("Circle");

        Color red2 = ColorFactory.getColor("Red"); // reuses the same object
        red2.applyColor("Square");

        // "Blue" is new — a new object is created
        Color blue = ColorFactory.getColor("Blue");
        blue.applyColor("Triangle");

        // Proof: red1 and red2 point to the exact same object
        System.out.println("\nred1 == red2? " + (red1 == red2)); // true
    }
}
