// Proxy Pattern - Virtual Proxy Demo (Lazy Image Loading)

// ── Subject Interface ──────────────────────────────────────────────────────
interface Image {
    void display();
}

// ── Real Subject (expensive to create — loads from disk) ───────────────────
class RealImage implements Image {
    private String fileName;
    public RealImage(String fileName) {
        this.fileName = fileName;
        System.out.println("Loading image from disk: " + fileName); // expensive!
    }
    public void display() { System.out.println("Displaying image: " + fileName); }
}

// ── Proxy (delays creation of RealImage until actually needed) ─────────────
class ProxyImage implements Image {
    private RealImage realImage; // null until first display() call
    private String fileName;

    public ProxyImage(String fileName) { this.fileName = fileName; }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // lazy load — only on first call
        }
        realImage.display();
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class VirtualProxyDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("photo.jpg");

        System.out.println("First call:");
        image.display(); // loads from disk + displays

        System.out.println("\nSecond call:");
        image.display(); // only displays — no reload
    }
}
