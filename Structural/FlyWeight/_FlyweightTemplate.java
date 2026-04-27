// FLYWEIGHT PATTERN TEMPLATE
// Use when: You have a huge number of objects that share the same state. Save memory by sharing.

import java.util.HashMap;
import java.util.Map;

// 1. Flyweight Interface
interface Flyweight {
    // extrinsicState is passed in at runtime, it changes per call
    void operation(String extrinsicState);
}

// 2. Concrete Flyweight (Shared Object)
class ConcreteFlyweight implements Flyweight {
    // Intrinsic state is stored inside and never changes
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    public void operation(String extrinsicState) {
        System.out.println("Intrinsic: " + intrinsicState + ", Extrinsic: " + extrinsicState);
    }
}

// 3. Flyweight Factory (Cache)
class FlyweightFactory {
    private static Map<String, Flyweight> cache = new HashMap<>();

    public static Flyweight getFlyweight(String intrinsicState) {
        if (!cache.containsKey(intrinsicState)) {
            cache.put(intrinsicState, new ConcreteFlyweight(intrinsicState)); // Create only if missing
        }
        return cache.get(intrinsicState); // Return shared object
    }
}

// 4. Client
class FlyweightTemplateDemo {
    public static void main(String[] args) {
        Flyweight f1 = FlyweightFactory.getFlyweight("SharedState1");
        f1.operation("Context A");

        Flyweight f2 = FlyweightFactory.getFlyweight("SharedState1"); // Reuses f1!
        f2.operation("Context B");

        System.out.println(f1 == f2); // true
    }
}
