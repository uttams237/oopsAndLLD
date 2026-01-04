# OOP Concepts Learning - Java Examples

A structured collection of Java examples to learn Object-Oriented Programming concepts for LLD preparation.

## 📁 Folder Structure

```
oops/
├── README.md           # This file
├── pillars/            # 4 Pillars of OOP
│   ├── EncapsulationDemo.java
│   ├── InheritanceDemo.java
│   ├── PolymorphismDemo.java
│   └── AbstractionDemo.java
└── lld/                # LLD Basics
    ├── InterfaceDemo.java
    └── LldBasicsDemo.java
```

## 🎯 4 Pillars of OOP

| Pillar | File | Key Concept |
|--------|------|-------------|
| **Encapsulation** | `pillars/EncapsulationDemo.java` | Private fields + Public getters/setters |
| **Inheritance** | `pillars/InheritanceDemo.java` | `extends`, `super`, `@Override` |
| **Polymorphism** | `pillars/PolymorphismDemo.java` | Overloading (compile-time) + Overriding (runtime) |
| **Abstraction** | `pillars/AbstractionDemo.java` | `abstract` classes and methods |

## 🔧 LLD Basics

| Topic | File | Key Concept |
|-------|------|-------------|
| **Interfaces** | `lld/InterfaceDemo.java` | `implements`, multiple inheritance, loose coupling |
| **Composition** | `lld/LldBasicsDemo.java` | HAS-A vs IS-A, Dependency Injection |

## 🚀 How to Run

```bash
# Compile all files
cd pillars && javac *.java && cd ../lld && javac *.java && cd ..

# Run individual demos
java -cp pillars EncapsulationDemo
java -cp pillars InheritanceDemo
java -cp pillars PolymorphismDemo
java -cp pillars AbstractionDemo
java -cp lld InterfaceDemo
java -cp lld LldBasicsDemo
```

## 📖 Learning Order

1. **Start with Pillars** (in order): Encapsulation → Inheritance → Polymorphism → Abstraction
2. **Then LLD Basics**: Interfaces → Composition vs Inheritance

Each file contains:
- Detailed header comments explaining the concept
- Inline comments explaining each code section
- **KEY TAKEAWAY** summary at the end of execution
