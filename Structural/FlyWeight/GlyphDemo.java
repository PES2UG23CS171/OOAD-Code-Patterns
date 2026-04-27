// Flyweight Pattern - Glyph Demo (Word Processor Characters)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ── Flyweight Interface ────────────────────────────────────────────────────
// context = extrinsic state (position, font) passed in at runtime
interface Glyph {
    void draw(String context);
    boolean intersects(String point, String context);
}

// ── Concrete Flyweight ─────────────────────────────────────────────────────
// One object per unique character — shared across all usages
class CharacterGlyph implements Glyph {
    private char c; // intrinsic state — stored inside, never changes

    public CharacterGlyph(char c) {
        System.out.println(">>> Creating glyph object for: '" + c + "'");
        this.c = c;
    }

    public void draw(String context) {
        System.out.println("Drawing '" + c + "' with context: " + context);
    }

    public boolean intersects(String point, String context) { return false; }
}

// ── Flyweight Factory ──────────────────────────────────────────────────────
// Cache ensures only one CharacterGlyph per unique character
class GlyphFactory {
    private static final Map<Character, Glyph> cache = new HashMap<>();

    public static Glyph getCharacter(char c) {
        if (!cache.containsKey(c)) {
            cache.put(c, new CharacterGlyph(c)); // create only if not cached
        }
        return cache.get(c); // return shared object
    }
}

// ── Composite Glyphs (Containers) ──────────────────────────────────────────
// Row and Column are NOT flyweights — they are containers that hold glyphs
// They also implement Glyph so they can be nested (Composite Pattern)
class Row implements Glyph {
    private List<Glyph> children = new ArrayList<>();

    public void add(Glyph g) { children.add(g); }

    public void draw(String context) {
        for (Glyph g : children) g.draw(context);
    }

    public boolean intersects(String point, String context) { return false; }
}

// ── Client ─────────────────────────────────────────────────────────────────
class GlyphDemo {
    public static void main(String[] args) {
        Row row = new Row();

        // "AAB" — 3 characters but only 2 objects created ('A' and 'B')
        String text = "AAB";
        for (char c : text.toCharArray()) {
            row.add(GlyphFactory.getCharacter(c)); // 'A' reused, not recreated
        }

        row.draw("SampleContext");
    }
}
