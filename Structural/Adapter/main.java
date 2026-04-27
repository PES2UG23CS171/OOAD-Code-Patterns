//Java implementation of Adapter pattern

interface Bird {
	// birds implement Bird interface that allows
	// them to fly and make sounds adaptee interface
	public void fly();

	public void makeSound();
}

interface ToyDuck {
	// target interface
	// toyducks dont fly they just make
	// squeaking sound
	public void squeak();
}

class BirdAdapter implements ToyDuck {
	// You need to implement the interface your
	// client expects to use.
	Bird bird;

	public BirdAdapter(Bird bird) {
		// we need reference to the object we
		// are adapting
		this.bird = bird;
	}

	public void squeak() {
		// translate the methods appropriately
		bird.makeSound();
	}
}

class main {
	public static void main(String args[]) {

		// Create a Bird using anonymous class
		Bird bird = new Bird() {
			public void fly() {
				System.out.println("Flying");
			}

			public void makeSound() {
				System.out.println("Chirp Chirp");
			}
		};

		// Wrap the Bird in the Adapter → it now behaves like a ToyDuck
		ToyDuck adapter = new BirdAdapter(bird);

		// Client calls squeak() on ToyDuck → internally calls bird.makeSound()
		adapter.squeak();
	}
}
