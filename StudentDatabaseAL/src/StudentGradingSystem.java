import java.util.ArrayList;
import java.util.Scanner;

// Define a Student class to hold student information
class Student {
    String id;
    String name;
    int age;
    double grade;

    // Constructor to create a new Student object
    public Student(String id, String name, int age, double grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Override the toString method to provide a string representation of the Student object
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
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
            // Display menu options to the user
            System.out.println("1. Add student");
            System.out.println("2. Remove student");
            System.out.println("3. Search student");
            System.out.println("4. View all students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            // Process the user's chosen option
            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    // Method to add a new student to the system
    private void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().toUpperCase(); // Get student ID and convert to uppercase
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().toUpperCase(); // Get student name and convert to uppercase

        // Check if the student ID or name already exists
        for (Student student : students) {
            if (student.id.equals(id) || student.name.equals(name)) {
                System.out.println("Student ID or name already exists. Please try again.");
                return;
            }
        }

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        students.add(new Student(id, name, age, grade)); // Add the new student to the list
        System.out.println("Student added successfully.");
    }

    // Method to remove a student from the system
    private void removeStudent() {
        System.out.print("Enter student ID or name to remove: ");
        String query = scanner.nextLine().toUpperCase(); // Get the student ID or name to remove and convert to uppercase

        // Find and remove the student from the list
        for (Student student : students) {
            if (student.id.equals(query) || student.name.equals(query)) {
                students.remove(student);
                System.out.println("Student removed successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Method to search for a student in the system
    private void searchStudent() {
        System.out.print("Enter student ID or name to search: ");
        String query = scanner.nextLine().toUpperCase(); // Get the student ID or name to search and convert to uppercase

        // Find and display the student
        for (Student student : students) {
            if (student.id.equals(query) || student.name.equals(query)) {
                System.out.println(student);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Method to view all students in the system
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