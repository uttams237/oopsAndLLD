package lld;

/**
 * =====================================================
 * 6. LLD BASICS: COMPOSITION VS INHERITANCE
 * =====================================================
 * 
 * COMPOSITION OVER INHERITANCE
 * This is one of the most important principles in LLD (Low-Level Design).
 * 
 * TWO TYPES OF RELATIONSHIPS:
 * 
 * 1. IS-A RELATIONSHIP (Inheritance)
 *    - "Dog IS-A Animal"
 *    - Implemented using 'extends'.
 *    - Rigid, compile-time binding.
 *    - Changes in parent affect all children.
 * 
 * 2. HAS-A RELATIONSHIP (Composition)
 *    - "Car HAS-A Engine"
 *    - Implemented by having object references as fields.
 *    - Flexible, runtime binding.
 *    - Components can be changed independently.
 * 
 * WHY PREFER COMPOSITION?
 * - Flexibility: Components can be swapped at runtime.
 * - Encapsulation: Internal details are hidden.
 * - Testability: Easy to mock dependencies.
 * - Loose Coupling: Classes are independent.
 * 
 * LLD DESIGN PATTERNS USING COMPOSITION:
 * - Strategy Pattern: Behaviors as objects.
 * - Decorator Pattern: Wrapping objects.
 * - Dependency Injection: Pass dependencies via constructor.
 * 
 * SOLID PRINCIPLES CONNECTION:
 * - Single Responsibility: Each class does one thing.
 * - Open/Closed: Add new features without modifying existing code.
 * - Dependency Inversion: Depend on abstractions.
 * =====================================================
 */

// =========================================
// COMPONENT CLASS 1: Engine
// Can be used by any vehicle (reusable).
// =========================================
class Engine {
    private String type;
    private int horsepower;

    public Engine(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }

    public void start() {
        System.out.println(type + " Engine (" + horsepower + " HP) started.");
    }
    
    public void stop() {
        System.out.println(type + " Engine stopped.");
    }
    
    public String getType() {
        return type;
    }
}

// =========================================
// COMPONENT CLASS 2: Wheels
// Another reusable component.
// =========================================
class Wheels {
    private int size;
    private String material;

    public Wheels(int size, String material) {
        this.size = size;
        this.material = material;
    }

    public void rotate() {
        System.out.println(size + " inch " + material + " wheels rotating.");
    }
    
    public void brake() {
        System.out.println("Brakes applied on " + material + " wheels.");
    }
}

// =========================================
// COMPONENT CLASS 3: GPS
// Optional component - not all cars have it.
// =========================================
class GPS {
    private String model;
    
    public GPS(String model) {
        this.model = model;
    }
    
    public void navigate(String destination) {
        System.out.println(model + " GPS: Navigating to " + destination);
    }
}

// =========================================
// COMPOSED CLASS: ModernCar
// Uses Composition - HAS-A Engine, HAS-A Wheels, HAS-A GPS.
// Much more flexible than inheritance!
// =========================================
class ModernCar {
    // Composition: Car HAS-A these objects
    private Engine engine;
    private Wheels wheels;
    private GPS gps; // Optional component
    private String model;

    // =========================================
    // DEPENDENCY INJECTION via Constructor
    // Components are passed in, not created inside.
    // This makes the class flexible and testable.
    // =========================================
    public ModernCar(String model, Engine engine, Wheels wheels) {
        this.model = model;
        this.engine = engine;
        this.wheels = wheels;
    }

    public void drive() {
        System.out.println("\n--- " + model + " is driving ---");
        engine.start();
        wheels.rotate();
        System.out.println(model + " is moving smoothly...");
    }
    
    public void stop() {
        wheels.brake();
        engine.stop();
    }
    
    // =========================================
    // RUNTIME FLEXIBILITY
    // Can swap engine at runtime! Impossible with inheritance.
    // =========================================
    public void setEngine(Engine newEngine) {
        System.out.println("Swapping engine from " + engine.getType() + " to " + newEngine.getType());
        this.engine = newEngine;
    }
    
    // Add optional component
    public void installGPS(GPS gps) {
        this.gps = gps;
        System.out.println("GPS installed in " + model);
    }
    
    public void navigateTo(String destination) {
        if (gps != null) {
            gps.navigate(destination);
        } else {
            System.out.println("No GPS installed. Please install GPS first.");
        }
    }
}

// =========================================
// BAD EXAMPLE: Inheritance Approach (commented out)
// This shows what NOT to do.
// =========================================
/*
// ❌ BAD: Car extends Engine? A Car IS-A Engine? NO!
class BadCar extends Engine {
    // Problems:
    // 1. Semantically wrong relationship.
    // 2. Cannot change engine at runtime.
    // 3. Cannot extend another class (Java single inheritance).
    // 4. Tight coupling.
}
*/

public class LldBasicsDemo {
    public static void main(String[] args) {
        System.out.println("=== Composition vs Inheritance (LLD Basics) ===");

        // =========================================
        // CREATE COMPONENTS SEPARATELY
        // Each component is independent and reusable.
        // =========================================
        Engine petrolEngine = new Engine("Petrol V6", 300);
        Engine electricEngine = new Engine("Electric", 400);
        Engine hybridEngine = new Engine("Hybrid", 350);
        
        Wheels alloyWheels = new Wheels(18, "Alloy");
        Wheels carbonWheels = new Wheels(20, "Carbon Fiber");
        
        GPS garminGps = new GPS("Garmin DriveAssist 51");

        // =========================================
        // CREATE CARS WITH DIFFERENT COMPONENTS
        // Same car class, different configurations!
        // =========================================
        ModernCar sportsCar = new ModernCar("Ferrari 488", petrolEngine, carbonWheels);
        sportsCar.drive();
        sportsCar.stop();
        
        ModernCar familyCar = new ModernCar("Toyota Camry", petrolEngine, alloyWheels);
        familyCar.installGPS(garminGps);
        familyCar.drive();
        familyCar.navigateTo("Downtown");
        familyCar.stop();

        // =========================================
        // RUNTIME FLEXIBILITY DEMO
        // Change engine without creating new car!
        // =========================================
        System.out.println("\n=== Runtime Flexibility Demo ===");
        
        ModernCar flexCar = new ModernCar("Honda Accord", petrolEngine, alloyWheels);
        flexCar.drive();
        
        // Upgrade to electric engine at runtime!
        flexCar.setEngine(electricEngine);
        flexCar.drive();
        
        // Upgrade again to hybrid!
        flexCar.setEngine(hybridEngine);
        flexCar.drive();
        
        flexCar.navigateTo("Airport"); // No GPS installed

        System.out.println("\n=== KEY TAKEAWAYS ===");
        System.out.println("1. Prefer 'HAS-A' (Composition) over 'IS-A' (Inheritance).");
        System.out.println("2. Use Dependency Injection - pass components via constructor.");
        System.out.println("3. Composition allows runtime flexibility and loose coupling.");
        System.out.println("4. Components are reusable across different classes.");
        System.out.println("5. This is fundamental for LLD and Design Patterns!");
    }
}
