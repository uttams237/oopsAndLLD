/**
 * =====================================================
 * 3. POLYMORPHISM (Third Pillar of OOP)
 * =====================================================
 * 
 * DEFINITION:
 * Polymorphism means "many forms". It allows us to perform a single 
 * action in different ways based on the object or parameters.
 * 
 * TWO TYPES:
 * 
 * 1. COMPILE-TIME POLYMORPHISM (Static / Early Binding)
 *    - Achieved through METHOD OVERLOADING.
 *    - Same method name, different parameters (number, type, or order).
 *    - Resolved at compile time.
 * 
 * 2. RUNTIME POLYMORPHISM (Dynamic / Late Binding)
 *    - Achieved through METHOD OVERRIDING.
 *    - Child class provides specific implementation of parent's method.
 *    - Resolved at runtime based on actual object type.
 *    - Requires inheritance and a parent reference pointing to child object.
 * 
 * LLD RELEVANCE:
 * - Runtime polymorphism is the foundation of many design patterns.
 * - Strategy Pattern: Different strategies implement same interface.
 * - Factory Pattern: Returns different types based on conditions.
 * - Open/Closed Principle: Open for extension, closed for modification.
 * =====================================================
 */

// =========================================
// COMPILE-TIME POLYMORPHISM: METHOD OVERLOADING
// Same method name, different parameter lists.
// Compiler decides which method to call based on arguments.
// =========================================
class MathOperations {
    
    // Version 1: Two integers
    public int add(int a, int b) {
        System.out.println("Calling add(int, int)");
        return a + b;
    }

    // Version 2: Two doubles (different TYPE)
    public double add(double a, double b) {
        System.out.println("Calling add(double, double)");
        return a + b;
    }

    // Version 3: Three integers (different NUMBER of params)
    public int add(int a, int b, int c) {
        System.out.println("Calling add(int, int, int)");
        return a + b + c;
    }
    
    // Note: Return type alone is NOT enough for overloading!
    // This would cause error: public double add(int a, int b) // ❌
}

// =========================================
// RUNTIME POLYMORPHISM: METHOD OVERRIDING
// Parent reference can hold child objects.
// Method call is resolved at runtime.
// =========================================

// Parent class
class Shape {
    public void draw() {
        System.out.println("Drawing a generic shape");
    }
    
    public double getArea() {
        return 0; // Default implementation
    }
}

// Child class 1
class Circle extends Shape {
    private double radius = 5.0;
    
    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius);
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

// Child class 2
class Square extends Shape {
    private double side = 4.0;
    
    @Override
    public void draw() {
        System.out.println("Drawing a Square with side " + side);
    }
    
    @Override
    public double getArea() {
        return side * side;
    }
}

// Child class 3
class Triangle extends Shape {
    private double base = 6.0;
    private double height = 4.0;
    
    @Override
    public void draw() {
        System.out.println("Drawing a Triangle with base " + base + " and height " + height);
    }
    
    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("=== Polymorphism Demo ===\n");

        // =========================================
        // 1. COMPILE-TIME POLYMORPHISM (Overloading)
        // =========================================
        System.out.println("--- Compile-time Polymorphism (Overloading) ---\n");
        
        MathOperations math = new MathOperations();
        
        // Compiler picks the right method based on arguments
        System.out.println("Result: " + math.add(5, 10));       // Calls int version
        System.out.println("Result: " + math.add(5.5, 10.5));   // Calls double version
        System.out.println("Result: " + math.add(1, 2, 3));     // Calls 3-param version

        System.out.println("\n--- Runtime Polymorphism (Overriding) ---\n");

        // =========================================
        // 2. RUNTIME POLYMORPHISM (Overriding)
        // Parent reference variable, child object.
        // =========================================
        
        Shape shape; // Parent type reference
        
        // shape points to Circle object
        shape = new Circle();
        shape.draw(); // Calls Circle's draw() at RUNTIME
        System.out.println("Area: " + shape.getArea());
        
        System.out.println();
        
        // Same reference now points to Square object
        shape = new Square();
        shape.draw(); // Calls Square's draw() at RUNTIME
        System.out.println("Area: " + shape.getArea());
        
        System.out.println();
        
        // Demonstrating with an array of shapes (very common pattern)
        System.out.println("--- Processing array of shapes ---");
        Shape[] shapes = { new Circle(), new Square(), new Triangle() };
        
        for (Shape s : shapes) {
            s.draw();                          // Different behavior for each!
            System.out.println("Area: " + s.getArea());
            System.out.println();
        }
        
        System.out.println("=== KEY TAKEAWAY ===");
        System.out.println("Overloading = Same name, different params (compile-time).");
        System.out.println("Overriding = Child redefines parent method (runtime).");
        System.out.println("Parent reference + Child object = Runtime polymorphism.");
    }
}
