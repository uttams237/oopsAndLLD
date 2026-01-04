/**
 * =====================================================
 * 2. INHERITANCE (Second Pillar of OOP)
 * =====================================================
 * 
 * DEFINITION:
 * Inheritance is the process where one class (child) acquires the 
 * properties (methods and fields) of another class (parent).
 * 
 * KEY CONCEPTS:
 * - Super Class (Parent): The class whose features are inherited.
 * - Sub Class (Child): The class that inherits from another class.
 * - 'extends' keyword: Used to establish inheritance relationship.
 * - 'super' keyword: Used to refer to parent class members.
 * 
 * TYPES OF INHERITANCE IN JAVA:
 * 1. Single Inheritance: One child, one parent.
 * 2. Multilevel Inheritance: A -> B -> C (chain).
 * 3. Hierarchical Inheritance: One parent, multiple children.
 * Note: Java does NOT support multiple inheritance with classes (use interfaces).
 * 
 * BENEFITS:
 * - Code Reusability: Child class reuses code from parent.
 * - Method Overriding: Child can customize parent behavior.
 * - Polymorphism: Parent reference can hold child objects.
 * 
 * LLD RELEVANCE:
 * - "IS-A" relationship (Dog IS-A Animal).
 * - Be careful: Prefer Composition over Inheritance for flexibility.
 * - Used in Template Method pattern, Strategy pattern base classes.
 * =====================================================
 */

// =========================================
// PARENT CLASS (Super Class / Base Class)
// Contains common properties and behaviors.
// =========================================
class Animal {
    // Field: Can be accessed by child classes
    String name;

    // Method: Common behavior shared by all animals
    public void eat() {
        System.out.println(name + " is eating.");
    }

    // Method: Another common behavior
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

// =========================================
// CHILD CLASS (Sub Class / Derived Class)
// Inherits from Animal using 'extends' keyword.
// Gets all public/protected members of Animal.
// =========================================
class Dog extends Animal {
    
    // Child-specific method: Only Dog has this
    public void bark() {
        System.out.println(name + " is barking.");
    }
    
    // =========================================
    // METHOD OVERRIDING
    // Child provides its own implementation of parent's method.
    // @Override annotation is optional but recommended.
    // =========================================
    @Override
    public void eat() {
        System.out.println(name + " is eating dog food.");
    }
}

// =========================================
// ANOTHER CHILD CLASS
// Demonstrates hierarchical inheritance.
// =========================================
class Cat extends Animal {
    
    public void meow() {
        System.out.println(name + " is meowing.");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating cat food.");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== Inheritance Demo ===\n");

        // =========================================
        // Creating child class objects
        // =========================================
        Dog myDog = new Dog();
        myDog.name = "Buddy"; // Accessing INHERITED field from Animal

        System.out.println("Dog behaviors:");
        myDog.eat();   // Inherited but OVERRIDDEN method
        myDog.sleep(); // Inherited method (unchanged)
        myDog.bark();  // Child-specific method,

        System.out.println();

        Cat myCat = new Cat();
        myCat.name = "Whiskers";

        System.out.println("Cat behaviors:");
        myCat.eat();
        myCat.sleep();
        myCat.meow();
        
        System.out.println("\n=== KEY TAKEAWAY ===");
        System.out.println("Inheritance = 'IS-A' relationship. Child gets all parent features.");
        System.out.println("Use 'extends' to inherit. Use '@Override' to customize behavior.");
    }
}
