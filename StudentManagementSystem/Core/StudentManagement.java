package StudentManagementSystem.Core;

import java.util.Scanner;

public class StudentManagement {
    private StudentStack studentStack;
    private Scanner scanner;

    public StudentManagement() {
        studentStack = new StudentStack(); // Initialize the student stack
        scanner = new Scanner(System.in); // Create a Scanner for user input
    }

    // Method to enter the number of students and their information
    public void inputStudentData() {
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Entering data for student " + (i + 1));
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Marks: ");
            float marks = scanner.nextFloat();
            scanner.nextLine(); // Consume the newline character

            // Create a new Student object and push it onto the stack
            Student student = new Student(id, name, marks);
            studentStack.push(student);
        }
    }

    // Method to display menu options
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Display all student information");
            System.out.println("2. Add a student");
            System.out.println("3. Update student information");
            System.out.println("4. Delete a student");
            System.out.println("5. Search for a student by ID");
            System.out.println("6. Sort students by marks");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayAllStudents(); // Display all students
                    break;
                case 2:
                    addStudent(); // Add a new student
                    break;
                case 3:
                    updateStudent(); // Update existing student information
                    break;
                case 4:
                    deleteStudent(); // Delete a student
                    break;
                case 5:
                    searchStudentById(); // Search for a student by ID
                    break;
                case 6:
                    sortStudentsByMarks(); // Sort students by their marks
                    break;
                case 7:
                    System.out.println("Exiting the program."); // Exit the program
                    return;
                default:
                    System.out.println("Invalid choice. Please try again."); // Invalid choice handling
            }
        }
    }

    // Method to display all student information
    private void displayAllStudents() {
        System.out.println("\n--- All Students ---");
        StudentStack tempStack = new StudentStack(); // Temporary stack to hold students

        // Display all students
        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop(); // Remove the top student from the stack
            student.display(); // Display student information
            tempStack.push(student); // Push to temporary stack
        }

        // Restore the original stack
        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }
    }

    // Method to add a student
    private void addStudent() {
        System.out.println("\n--- Adding a Student ---");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Marks: ");
        float marks = scanner.nextFloat();
        scanner.nextLine(); // Consume the newline character

        Student student = new Student(id, name, marks);
        studentStack.push(student); // Push the new student onto the stack
        System.out.println("Student added successfully.");
    }

    // Method to update student information
    private void updateStudent() {
        System.out.print("Enter the ID of the student to update: ");
        String idToUpdate = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < studentStack.size(); i++) {
            Student student = studentStack.peek(); // Peek at the top student
            if (student.getId().equals(idToUpdate)) {
                System.out.print("New Name: ");
                student.setName(scanner.nextLine()); // Update student name
                System.out.print("New Marks: ");
                student.setMarks(scanner.nextFloat()); // Update student marks
                scanner.nextLine(); // Consume the newline character
                found = true;
                System.out.println("Student information updated successfully.");
                break;
            }
            studentStack.pop(); // Remove the top student from the stack
        }

        if (!found) {
            System.out.println("Student with ID " + idToUpdate + " not found."); // Student not found
        }
    }

    // Method to delete a student
    private void deleteStudent() {
        System.out.print("Enter the ID of the student to delete: ");
        String idToDelete = scanner.nextLine();
        boolean found = false;
        StudentStack tempStack = new StudentStack(); // Temporary stack to hold students

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop(); // Remove the top student from the stack
            if (student.getId().equals(idToDelete)) {
                found = true; // Student found
                System.out.println("Deleted student: ");
                student.display(); // Display the deleted student information
            } else {
                tempStack.push(student); // Push to temporary stack
            }
        }

        // Restore the original stack
        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student with ID " + idToDelete + " not found."); // Student not found
        }
    }

    // Method to search for a student by ID
    private void searchStudentById() {
        System.out.print("Enter the ID of the student to search: ");
        String idToSearch = scanner.nextLine();
        boolean found = false;
        StudentStack tempStack = new StudentStack(); // Temporary stack to hold students

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop(); // Remove the top student from the stack
            if (student.getId().equals(idToSearch)) {
                found = true; // Student found
                System.out.println("Student found: ");
                student.display(); // Display student information
            }
            tempStack.push(student); // Push to temporary stack
        }

        // Restore the original stack
        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student with ID " + idToSearch + " not found."); // Student not found
        }
    }

    // Method to sort students by marks
    private void sortStudentsByMarks() {
        System.out.println("--- Sorting Students by Marks ---");
        StudentStack sortedStack = new StudentStack(); // Temporary stack to hold sorted students

        // Sort students by marks
        while (!studentStack.isEmpty()) {
            Student currentStudent = studentStack.pop(); // Remove the top student from the stack

            // Insert the current student into the sorted stack
            while (!sortedStack.isEmpty() && sortedStack.peek().getMarks() > currentStudent.getMarks()) {
                studentStack.push(sortedStack.pop()); // Move students back to the original stack
            }
            sortedStack.push(currentStudent); // Push the current student onto the sorted stack
        }

        // Restore the original stack with sorted students
        while (!sortedStack.isEmpty()) {
            studentStack.push(sortedStack.pop());
        }

        // Display sorted students
        System.out.println("--- Students Sorted by Marks ---");
        displayAllStudents(); // Display all students
    }
}
