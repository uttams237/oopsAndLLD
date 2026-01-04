/**
 * =====================================================
 * COMPOSITION OVER INHERITANCE
 * =====================================================
 * 
 * WHY PREFER COMPOSITION?
 * 1. Flexibility: You can change behavior at runtime.
 * 2. Loose Coupling: Classes don't depend on internal implementation of others.
 * 3. Avoids Explosion of Subclasses: Instead of FlyingRobot, SwimmingRobot, 
 *    FlyingSwimmingRobot, etc., you just swap components.
 * 4. "Is-A" vs "Has-A": 
 *    - Inheritance = Is-A (A Dog IS-A Animal) -> Static/Rigid
 *    - Composition = Has-A (A Robot HAS-A Engine) -> Dynamic/Flexible
 * =====================================================
 */

// 1. Define an Interface for Behavior
interface MovementStrategy {
    void move();
}

// 2. Implementation variants (The "Components")
class WalkingStrategy implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Moving by walking on two legs.");
    }
}

class FlyingStrategy implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Moving by flying using propellers.");
    }
}

class SwimmingStrategy implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Moving by swimming using fins.");
    }
}

// 3. The Main Class (Using Composition)
class Robot {
    private String name;
    // COMPOSITION: Robot HAS-A MovementStrategy
    private MovementStrategy movementStrategy;

    public Robot(String name, MovementStrategy strategy) {
        this.name = name;
        this.movementStrategy = strategy;
    }

    // This is where the flexibility comes in!
    // We can change the behavior AT RUNTIME.
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public void performMove() {
        System.out.print(name + ": ");
        movementStrategy.move();
    }
}

public class CompositionDemo {
    public static void main(String[] args) {
        System.out.println("=== Composition over Inheritance Demo ===\n");

        // Create a Robot that walks
        Robot myRobot = new Robot("Robo-1", new WalkingStrategy());
        myRobot.performMove();

        // Change behavior to Flying at runtime - NO NEW CLASSES NEEDED!
        System.out.println("\n--- Upgrading to Flying Mode ---");
        myRobot.setMovementStrategy(new FlyingStrategy());
        myRobot.performMove();

        // Change behavior to Swimming
        System.out.println("\n--- Switching to Underwater Mode ---");
        myRobot.setMovementStrategy(new SwimmingStrategy());
        myRobot.performMove();

        System.out.println("\n=== KEY TAKEAWAY ===");
        System.out.println("Inheritance is rigid (Robot is-a Walker).");
        System.out.println("Composition is flexible (Robot has-a MovementStrategy).");
        System.out.println("You can wrap different behaviors and swap them like pieces of LEGO.");
    }
}
