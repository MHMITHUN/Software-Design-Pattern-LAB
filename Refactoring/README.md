# ♟️ Chess Game Refactoring Lab

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg?style=for-the-badge)](https://github.com/MHMITHUN/Software-Design-Pattern-LAB)
[![Refactoring](https://img.shields.io/badge/Refactoring-Clean%20Code-blue.svg?style=for-the-badge)](https://refactoring.guru/)
[![Design Pattern](https://img.shields.io/badge/Design%20Pattern-SOLID%20%26%20OOP-red.svg?style=for-the-badge)](https://en.wikipedia.org/wiki/SOLID)

Welcome to the **Chess Game Refactoring Lab**! This project is a hands-on exercise designed to practice identifying common **code smells** and refactoring them into clean, maintainable, and object-oriented code. 

Here, you'll find a side-by-side comparison of a poorly written codebase (the `smelly` package) and its clean, refactored counterpart (the `refactored` package).

---

## 📌 Table of Contents
1. [Project Structure](#-project-structure)
2. [Code Smells Identified](#-code-smells-identified)
3. [Refactoring Solutions Applied](#-refactoring-solutions-applied)
4. [How to Run the Project](#-how-to-run-the-project)
5. [Running Tests](#-running-tests)
6. [Detailed Audit Report](#-detailed-audit-report)

---

## 📁 Project Structure

The codebase is organized into two main packages to help you easily compare the "before" and "after" states of the code:

```text
.
├── src/
│   └── com/
│       └── directi/
│           └── training/
│               └── codesmells/
│                   ├── Main.java                # Entry point for the smelly version
│                   ├── Refactor_Main.java       # Entry point for the refactored version
│                   │
│                   ├── smelly/                  # 🔴 Legacy code filled with code smells
│                   │   ├── chess/               # GameEngine, ChessBoard, Player, Cell, Move
│                   │   └── pieces/              # Pawn, King, Queen, Bishop, Knight, Rook (and redundant subclasses)
│                   │
│                   └── refactored/              # 🟢 Clean, refactored code
│                       ├── chess/               # Improved GameEngine, ChessBoard, Player, Cell, MoveUtil
│                       └── pieces/              # Polymorphic Piece class and concrete implementations
├── test/                                        # 🧪 JUnit test cases
│   └── com/
│       └── directi/
│           └── trainining/
│               └── codesmells/
│                   ├── ChessGameTest.java       # Tests for the smelly version
│                   └── Refactor_ChessGameTest.java # Tests for the refactored version
└── Code_Smells_Report.md                        # 📝 Detailed code smell audit report
```

---

## 🔍 Code Smells Identified

We identified and resolved several major code quality issues in the smelly package:

1. **Subclass Explosion (Lazy Classes)**: Separate classes existed for left and right pieces (like `LeftBishop` and `RightBishop`) even though they behaved identically.
2. **Switch Statements / Conditional Complexity**: The `Piece` class used complex `switch` cases to determine movement rules based on character types, violating the **Open-Closed Principle (OCP)**.
3. **Indecent Exposure (Encapsulation Violations)**: Critical variables were declared `public`, allowing external classes to modify them directly.
4. **Feature Envy**: `GameEngine` was handling board initialization and setup, which is a job that belongs to `ChessBoard`. Similarly, `ChessBoard` was directly accessing `Player` data.
5. **Duplicate Code**: Identical movement and setup logic was repeated across methods and classes.
6. **Primitive Obsession**: Raw integers were constantly used for board coordinates instead of leveraging coordinates/positions as proper objects.
7. **Long Methods**: Large, hard-to-read methods like `resetBoard()` in `GameEngine` had too many responsibilities.

---

## 🛠️ Refactoring Solutions Applied

Here is how we addressed those smells to make the code clean and maintainable:

- **Polymorphism over Switch Cases**: Turned the base `Piece` class into an `abstract` class and let each specific piece (`Pawn`, `King`, `Queen`, etc.) implement its own movement rules.
- **Collapsed Class Hierarchy**: Deleted all redundant `Left` and `Right` subclasses, unifying their behavior back into their main piece classes.
- **Proper Encapsulation**: Changed fields from `public` to `private` and introduced safe getter and setter methods.
- **Introducing Parameter Objects**: Passed `Position` objects directly instead of passing long lists of raw coordinate integers (`fromRow`, `fromColumn`, etc.).
- **Responsibility Redistribution**: Moved the board setup logic from `GameEngine` to `ChessBoard` (where it naturally belongs).

---

## 🚀 How to Run the Project

### Option 1: Using an IDE (recommended)
1. Clone this repository:
   ```bash
   git clone https://github.com/MHMITHUN/Software-Design-Pattern-LAB.git
   ```
2. Open the `Refactoring` folder in your IDE (IntelliJ IDEA, Eclipse, or VS Code).
3. Set up the `src` folder as the source directory and `test` folder as the test directory.
4. Run the main files:
   - **Smelly Version**: Run `com.directi.training.codesmells.Main`
   - **Refactored Version**: Run `com.directi.training.codesmells.Refactor_Main`

### Option 2: Using the Command Line
Navigate to this directory (`Refactoring`) in your terminal and run:

**To Compile:**
```bash
javac -d out/production/Refactoring -sourcepath src src/com/directi/training/codesmells/Main.java src/com/directi/training/codesmells/Refactor_Main.java
```

**To Run the Smelly Version:**
```bash
java -cp out/production/Refactoring com.directi.training.codesmells.Main
```

**To Run the Refactored Version:**
```bash
java -cp out/production/Refactoring com.directi.training.codesmells.Refactor_Main
```

---

## 🧪 Running Tests

We wrote JUnit tests to ensure that refactoring didn't break any core gameplay mechanics.

**From your IDE:**
Right-click on the `test` directory or specific test files (`ChessGameTest` or `Refactor_ChessGameTest`) and select **Run 'Tests'**.

**From the Command Line:**
```bash
java -cp "lib/junit.jar;lib/hamcrest-core.jar;out/production/Refactoring" org.junit.runner.JUnitCore com.directi.trainining.codesmells.Refactor_ChessGameTest
```

---

## 👥 Contributors
- **Mahamudul Hasan Mithun** (Project Owner & Lead Developer)
- **Gemini** (AI Assistant by Google DeepMind)


