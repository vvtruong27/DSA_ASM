package StudentManagementSystem.App;

import StudentManagementSystem.Core.StudentManagement;

public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement(); // Create an instance of StudentManagement
        management.inputStudentData(); // Input student data
        management.displayMenu(); // Display menu for further operations
    }
}
