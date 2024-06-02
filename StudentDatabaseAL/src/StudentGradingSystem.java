import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

// Define a Student class to hold student information
class Student {
    String id;
    String name;
    int age;
    double assignment1;
    double assignment2;
    double finalScore;

    // Constructor to create a new Student object
    public Student(String id, String name, int age, double assignment1, double assignment2) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.finalScore = calculateFinalScore(assignment1, assignment2);
    }

    // Method to calculate the final score based on assignment scores and percentages
    private double calculateFinalScore(double assignment1, double assignment2) {
        double percentage1 = 0.35;
        double percentage2 = 0.65;
        return (assignment1 * percentage1) + (assignment2 * percentage2);
    }

    // Override the toString method to provide a string representation of the Student object
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Assignment 1: " + assignment1 + ", Assignment 2: " + assignment2 + ", Final Score: " + finalScore;
    }
}

public class StudentGradingSystem {
    private ArrayList<Student> students; // List to store all students
    private Scanner scanner; // Scanner object to read user input

    // Constructor to initialize the StudentGradingSystem
    public StudentGradingSystem() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Main method to run the student grading system
    public void run() {
        while (true) {
            displayMenu(); // Display the menu options to the user
            int option = getOption(); // Get the user's chosen option
            processOption(option); // Process the user's chosen option
        }
    }

    // Method to display the menu options to the user
    private void displayMenu() {
        System.out.println("1. Add student");
        System.out.println("2. Remove student");
        System.out.println("3. Search student");
        System.out.println("4. View all students");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to get the user's chosen option
    private int getOption() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid option. Please choose again.");
            scanner.next(); // Consume invalid input
            return getOption();
        }
    }

    // Method to process the user's chosen option
    private void processOption(int option) {
        switch (option) {
            case 1:
                addStudent(); // Add a new student
                break;
            case 2:
                removeStudent(); // Remove a student
                break;
            case 3:
                searchStudent(); // Search for a student
                break;
            case 4:
                viewAllStudents(); // View all students
                break;
            case 5:
                System.exit(0); // Exit the program
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
        }
    }

    // Method to add a new student
    private void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.next().toUpperCase();
        System.out.print("Enter student name: ");
        String name = scanner.next().toUpperCase();

        if (studentExists(id, name)) {
            System.out.println("Student ID or name already exists. Please try again.");
            return;
        }

        int age = getValidAge(); // Get a valid age from the user
        double assignment1 = getValidAssignmentScore("Enter score for assignment 1: "); // Get a valid score for assignment 1
        double assignment2 = getValidAssignmentScore("Enter score for assignment 2: "); // Get a valid score for assignment 2

        students.add(new Student(id, name, age, assignment1, assignment2)); // Add the new student to the list
        System.out.println("Student added successfully.");
    }

    // Method to check if a student with the given ID or name already exists
    private boolean studentExists(String id, String name) {
        for (Student student : students) {
            if (student.id.equals(id) || student.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Method to get a valid age from the user
    private int getValidAge() {
        while (true) {
            try {
                System.out.print("Enter student age: ");
                int age = scanner.nextInt();
                if (age < 1 || age > 100) {
                    System.out.println("Age must be between 1 and 100.");
                } else {
                    return age;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid age. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }
    }

    // Method to get a valid score for an assignment from the user
    private double getValidAssignmentScore(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double score = scanner.nextDouble();
                if (score < 0 || score > 100) {
                    System.out.println("Score must be between 0 and 100.");
                } else {
                    return score;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid score. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }
    }

    // Method to remove a student
    private void removeStudent() {
        System.out.print("Enter student ID or name to remove: ");
        String query = scanner.next().toUpperCase();

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.id.equals(query) || student.name.equals(query)) {
                iterator.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Method to search for a student
    private void searchStudent() {
        System.out.print("Enter student ID or name to search: ");
        String query = scanner.next().toUpperCase();

        for (Student student : students) {
            if (student.id.equals(query) || student.name.equals(query)) {
                System.out.println(student);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Method to view all students
    private void viewAllStudents() {
        System.out.println("List of all students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        StudentGradingSystem system = new StudentGradingSystem();
        system.run();
    }
}
