// Facade Pattern - Travel Package Booking

// ── Subsystem Interface ────────────────────────────────────────────────────
interface Booking {
    void book();
}

// ── Subsystem Classes (complex internals the client shouldn't deal with) ───
class Hotel implements Booking {
    public void book() { System.out.println("Hotel booked successfully"); }
}

class Flight implements Booking {
    public void book() { System.out.println("Flight booked successfully"); }
}

class Cab implements Booking {
    public void book() { System.out.println("Cab booked successfully"); }
}

// ── Facade Interface ───────────────────────────────────────────────────────
interface PackageFacade {
    void book();
}

// ── Facade Implementation ──────────────────────────────────────────────────
// Hides all subsystem complexity behind a single book() call
class PackageFacadeImpl implements PackageFacade {
    private Booking cab    = new Cab();
    private Booking hotel  = new Hotel();
    private Booking flight = new Flight();

    public void book() {
        cab.book();
        hotel.book();
        flight.book();
        System.out.println("Travel package booked successfully");
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class PackageBookingDemo {
    public static void main(String[] args) {
        // With Facade — one call does everything
        PackageFacade facade = new PackageFacadeImpl();
        facade.book();

        System.out.println("\n--- Without Facade (client manages everything) ---");
        new Flight().book();
        new Hotel().book();
        new Cab().book();
    }
}
