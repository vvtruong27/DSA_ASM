package StudentManagementSystem.Core;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class StudentManagement {
    private StudentStack studentStack;
    private Scanner scanner;

    public StudentManagement() {
        studentStack = new StudentStack(); // Initialize the student stack
        scanner = new Scanner(System.in); // Create a Scanner for user input
        generateStudentData(studentStack, 1000); // Generate student data
    }

    // Method to generate student data
    public static void generateStudentData(StudentStack studentStack, int numberOfStudents) {
        Random random = new Random();

        for (int i = 1; i <= numberOfStudents; i++) {
            String id = "S" + String.format("%04d", i); // Generate student ID like S0001, S0002, ...
            String name = "Student" + i; // Generate student name like Student1, Student2, ...
            float marks = 1 + random.nextFloat() * 9; // Generate random marks between 1 and 10

            Student student = new Student(id, name, marks); // Create a new student object
            studentStack.push(student); // Push student to the stack
        }
    }

    // Method to enter the number of students and their information
    public void inputStudentData() {
        try {
            System.out.print("Enter the number of students: ");
            int numberOfStudents = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (numberOfStudents <= 0) {
                System.out.println("Number of students must be greater than 0.");
                return;
            }

            for (int i = 0; i < numberOfStudents; i++) {
                System.out.println("Entering data for student " + (i + 1));
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Marks: ");
                float marks = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character

                if (marks < 0 || marks > 10) {
                    System.out.println("Marks must be between 0 and 10.");
                    i--; // Retry the current iteration
                    continue;
                }

                // Create a new Student object and push it onto the stack
                Student student = new Student(id, name, marks);
                studentStack.push(student);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values for marks.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to display menu options
    public void displayMenu() {
        while (true) {
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    // Method to display all student information
    private void displayAllStudents() {
        if (studentStack.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

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
        try {
            System.out.println("\n--- Adding a Student ---");
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Marks: ");
            float marks = scanner.nextFloat();
            scanner.nextLine(); // Consume the newline character

            if (marks < 0 || marks > 10) {
                System.out.println("Marks must be between 0 and 10.");
                return;
            }

            Student student = new Student(id, name, marks);
            studentStack.push(student); // Push the new student onto the stack
            System.out.println("Student added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric value for marks.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to update student information
    private void updateStudent() {
        try {
            System.out.print("Enter the ID of the student to update: ");
            String idToUpdate = scanner.nextLine();
            boolean found = false;
            StudentStack tempStack = new StudentStack(); // Temporary stack to hold students

            while (!studentStack.isEmpty()) {
                Student student = studentStack.pop(); // Remove the top student from the stack
                if (student.getId().equals(idToUpdate)) {
                    System.out.print("New Name: ");
                    student.setName(scanner.nextLine()); // Update student name
                    System.out.print("New Marks: ");
                    student.setMarks(scanner.nextFloat()); // Update student marks
                    scanner.nextLine(); // Consume the newline character

                    if (student.getMarks() < 0 || student.getMarks() > 10) {
                        System.out.println("Marks must be between 0 and 10.");
                        tempStack.push(student);
                        continue;
                    }

                    found = true;
                    System.out.println("Student information updated successfully.");
                }
                tempStack.push(student);
            }

            // Restore the original stack
            while (!tempStack.isEmpty()) {
                studentStack.push(tempStack.pop());
            }

            if (!found) {
                System.out.println("Student with ID " + idToUpdate + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid marks.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to delete a student
    private void deleteStudent() {
        try {
            System.out.print("Enter the ID of the student to delete: ");
            String idToDelete = scanner.nextLine();
            boolean found = false;
            StudentStack tempStack = new StudentStack(); // Temporary stack to hold students

            while (!studentStack.isEmpty()) {
                Student student = studentStack.pop(); // Remove the top student from the stack
                if (student.getId().equals(idToDelete)) {
                    found = true;
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
                System.out.println("Student with ID " + idToDelete + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to search for a student by ID
    private void searchStudentById() {
        try {
            System.out.print("Enter the ID of the student to search: ");
            String idToSearch = scanner.nextLine();
            boolean found = false;
            StudentStack tempStack = new StudentStack(); // Temporary stack to hold students

            while (!studentStack.isEmpty()) {
                Student student = studentStack.pop(); // Remove the top student from the stack
                if (student.getId().equals(idToSearch)) {
                    found = true;
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
                System.out.println("Student with ID " + idToSearch + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to sort students by marks
    private void sortStudentsByMarks() {
        try {
            System.out.println("--- Sorting Students by Marks ---");

            StudentStack bubbleStack = cloneStack(studentStack);

            // Measure Bubble Sort runtime
            long bubbleStart = System.nanoTime();
            bubbleSortStack(bubbleStack);
            long bubbleEnd = System.nanoTime();
            System.out.println("Bubble Sort completed. Time taken: " + (bubbleEnd - bubbleStart) + " ns");

            // Measure Merge Sort runtime
            long mergeStart = System.nanoTime();
            mergeSortStack();
            long mergeEnd = System.nanoTime();
            System.out.println("Merge Sort completed. Time taken: " + (mergeEnd - mergeStart) + " ns");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to clone a stack
    private StudentStack cloneStack(StudentStack original) {
        StudentStack tempStack = new StudentStack();
        StudentStack clonedStack = new StudentStack();

        // Transfer elements from original to tempStack (to reverse the order)
        while (!original.isEmpty()) {
            tempStack.push(original.pop());
        }

        // Transfer elements from tempStack to original and clonedStack (to maintain
        // order)
        while (!tempStack.isEmpty()) {
            Student student = tempStack.pop();
            original.push(student);
            clonedStack.push(student);
        }

        return clonedStack;
    }

    // Bubble Sort for Stack
    private void bubbleSortStack(StudentStack bubbleStack) {
        StudentStack tempStack = new StudentStack();
        int size = bubbleStack.size();

        for (int i = 0; i < size - 1; i++) {
            boolean swapped = false;
            Student previous = bubbleStack.pop();

            for (int j = 0; j < size - 1 - i; j++) {
                Student current = bubbleStack.pop();

                if (previous.getMarks() > current.getMarks()) {
                    tempStack.push(previous);
                    previous = current;
                    swapped = true;
                } else {
                    tempStack.push(current);
                }
            }

            tempStack.push(previous);
            while (!tempStack.isEmpty()) {
                bubbleStack.push(tempStack.pop());
            }

            if (!swapped)
                break; // Optimization if no swaps occur
        }
    }

    // Merge Sort for Stack
    private void mergeSortStack() {
        studentStack = mergeSortHelper(studentStack);
    }

    private StudentStack mergeSortHelper(StudentStack stack) {
        if (stack.size() <= 1)
            return stack;

        // Split stack into two halves
        StudentStack leftStack = new StudentStack();
        StudentStack rightStack = new StudentStack();
        int mid = stack.size() / 2;

        for (int i = 0; i < mid; i++) {
            leftStack.push(stack.pop());
        }
        while (!stack.isEmpty()) {
            rightStack.push(stack.pop());
        }

        // Recursively sort both halves
        leftStack = mergeSortHelper(leftStack);
        rightStack = mergeSortHelper(rightStack);

        // Merge the sorted halves
        return mergeStacks(leftStack, rightStack);
    }

    private StudentStack mergeStacks(StudentStack leftStack, StudentStack rightStack) {
        StudentStack resultStack = new StudentStack();
        StudentStack tempStack = new StudentStack();

        while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
            if (leftStack.peek().getMarks() <= rightStack.peek().getMarks()) {
                tempStack.push(leftStack.pop());
            } else {
                tempStack.push(rightStack.pop());
            }
        }

        while (!leftStack.isEmpty()) {
            tempStack.push(leftStack.pop());
        }
        while (!rightStack.isEmpty()) {
            tempStack.push(rightStack.pop());
        }

        while (!tempStack.isEmpty()) {
            resultStack.push(tempStack.pop());
        }

        return resultStack;
    }

}
