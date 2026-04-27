// Adapter Pattern - Full Concrete Implementation Example

// ── Adaptee Interface ──────────────────────────────────────────────────────
interface Bird {
	public void fly();
	public void makeSound();
}

// ── Adaptee Concrete Class ─────────────────────────────────────────────────
class Sparrow implements Bird {
	public void fly() {
		System.out.println("Flying");
	}
	public void makeSound() {
		System.out.println("Chirp Chirp");
	}
}

// ── Target Interface ───────────────────────────────────────────────────────
interface ToyDuck {
	public void squeak();
}

// ── Target Concrete Class ──────────────────────────────────────────────────
class PlasticToyDuck implements ToyDuck {
	public void squeak() {
		System.out.println("Squeak");
	}
}

// ── Adapter ────────────────────────────────────────────────────────────────
// IS-A ToyDuck (implements Target) → so Client can use it as a ToyDuck
// HAS-A Bird (wraps the Adaptee) → delegates calls to the Bird inside
class BirdAdapter implements ToyDuck {
	Bird bird;

	public BirdAdapter(Bird bird) {
		this.bird = bird;
	}

	public void squeak() {
		bird.makeSound(); // Adapts squeak() → makeSound()
	}
}

// ── Client ─────────────────────────────────────────────────────────────────
class ConcreteImplementations {
	public static void main(String args[]) {

		// 1. Normal Sparrow (Adaptee) being used directly
		Sparrow sparrow = new Sparrow();
		System.out.println("Sparrow...");
		sparrow.fly();
		sparrow.makeSound();

		// 2. Normal PlasticToyDuck (Target) being used directly
		ToyDuck toyDuck = new PlasticToyDuck();
		System.out.println("\nToyDuck...");
		toyDuck.squeak();

		// 3. Sparrow wrapped in BirdAdapter → now behaves as a ToyDuck
		ToyDuck birdAdapter = new BirdAdapter(sparrow);
		System.out.println("\nBirdAdapter (Sparrow acting as ToyDuck)...");
		birdAdapter.squeak(); // Internally calls sparrow.makeSound()
	}
}
