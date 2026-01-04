/**
 * =====================================================
 * 4. ABSTRACTION (Fourth Pillar of OOP)
 * =====================================================
 * 
 * DEFINITION:
 * Abstraction is the process of hiding implementation details and 
 * showing only essential information (functionality) to the user.
 * It focuses on "WHAT" the object does, not "HOW" it does it.
 * 
 * HOW TO ACHIEVE ABSTRACTION:
 * 1. Abstract Classes (0 to 100% abstraction)
 * 2. Interfaces (100% abstraction) - covered in InterfaceDemo.java
 * 
 * ABSTRACT CLASS RULES:
 * - Declared using 'abstract' keyword.
 * - CANNOT be instantiated directly (new AbstractClass() is NOT allowed).
 * - Can have both abstract methods (no body) and concrete methods (with body).
 * - Abstract methods MUST be implemented by concrete subclasses.
 * - Can have constructors, fields, and static methods.
 * 
 * WHEN TO USE ABSTRACT CLASS vs INTERFACE:
 * - Abstract Class: When classes share common code/fields.
 * - Interface: When you need a contract without implementation.
 * 
 * LLD RELEVANCE:
 * - Template Method Pattern uses abstract classes.
 * - Abstract Factory Pattern defines abstract creation methods.
 * - Helps enforce a contract while allowing code reuse.
 * =====================================================
 */

// =========================================
// ABSTRACT CLASS
// Cannot be instantiated. Acts as a template.
// =========================================
abstract class Vehicle {
    
    // Regular field - inherited by all subclasses
    protected String brand;
    protected int year;

    // =========================================
    // CONSTRUCTOR
    // Abstract classes CAN have constructors!
    // Called by child class using super().
    // =========================================
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // =========================================
    // ABSTRACT METHOD
    // No implementation here (no body, just semicolon).
    // Subclasses MUST provide implementation.
    // =========================================
    public abstract void startEngine();
    
    public abstract void stopEngine();

    // =========================================
    // CONCRETE METHOD
    // Has implementation. Shared by all subclasses.
    // Can be overridden if needed.
    // =========================================
    public void honk() {
        System.out.println(brand + " vehicle goes: Beep! Beep!");
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}

// =========================================
// CONCRETE CLASS 1
// Must implement ALL abstract methods.
// =========================================
class Car extends Vehicle {
    
    private int numDoors;
    
    public Car(String brand, int year, int numDoors) {
        super(brand, year); // Call parent constructor
        this.numDoors = numDoors;
    }

    @Override
    public void startEngine() {
        System.out.println(brand + " Car: Turning key... Vroom! Engine started.");
    }
    
    @Override
    public void stopEngine() {
        System.out.println(brand + " Car: Engine stopped.");
    }
    
    // Car-specific method
    public void openTrunk() {
        System.out.println("Opening trunk of " + numDoors + "-door " + brand + " car.");
    }
}

// =========================================
// CONCRETE CLASS 2
// Different implementation of abstract methods.
// =========================================
class Motorcycle extends Vehicle {
    
    private String bikeType; // Sport, Cruiser, etc.
    
    public Motorcycle(String brand, int year, String bikeType) {
        super(brand, year);
        this.bikeType = bikeType;
    }

    @Override
    public void startEngine() {
        System.out.println(brand + " " + bikeType + " Motorcycle: Pressing button... Revvv! Engine started.");
    }
    
    @Override
    public void stopEngine() {
        System.out.println(brand + " Motorcycle: Engine stopped.");
    }
    
    // Motorcycle-specific method
    public void doWheelie() {
        System.out.println(brand + " " + bikeType + " doing a wheelie!");
    }
}

// =========================================
// CONCRETE CLASS 3
// Shows that each class can have unique implementation.
// =========================================
class ElectricCar extends Vehicle {
    
    private int batteryCapacity; // in kWh
    
    public ElectricCar(String brand, int year, int batteryCapacity) {
        super(brand, year);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void startEngine() {
        System.out.println(brand + " Electric Car: Silently powering up... Ready to drive.");
    }
    
    @Override
    public void stopEngine() {
        System.out.println(brand + " Electric Car: System shutting down.");
    }
    
    public void showBatteryStatus() {
        System.out.println("Battery capacity: " + batteryCapacity + " kWh");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstraction Demo ===\n");

        // ❌ This would cause a compile error:
        // Vehicle v = new Vehicle("Generic", 2023);
        // Error: Vehicle is abstract; cannot be instantiated

        // ✅ Create concrete subclass objects
        Car myCar = new Car("Toyota", 2023, 4);
        myCar.displayInfo();
        myCar.startEngine();
        myCar.honk();
        myCar.openTrunk();
        myCar.stopEngine();

        System.out.println();

        Motorcycle myBike = new Motorcycle("Harley", 2022, "Cruiser");
        myBike.displayInfo();
        myBike.startEngine();
        myBike.honk();
        myBike.doWheelie();
        myBike.stopEngine();

        System.out.println();

        // =========================================
        // ABSTRACTION IN ACTION
        // Using parent type reference (abstraction!).
        // We don't care about the specific implementation.
        // =========================================
        System.out.println("--- Using abstraction (parent reference) ---");
        
        Vehicle[] vehicles = {
            new Car("Honda", 2023, 2),
            new Motorcycle("Yamaha", 2022, "Sport"),
            new ElectricCar("Tesla", 2024, 100)
        };
        
        for (Vehicle v : vehicles) {
            v.displayInfo();
            v.startEngine();     // Each has different implementation
            v.stopEngine();
            System.out.println();
        }

        System.out.println("=== KEY TAKEAWAY ===");
        System.out.println("Abstraction = Hide complexity, show only what's necessary.");
        System.out.println("Abstract class = Template with abstract (no body) + concrete methods.");
        System.out.println("Use 'abstract' keyword. Child MUST implement abstract methods.");
    }
}
