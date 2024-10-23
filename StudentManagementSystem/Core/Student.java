package StudentManagementSystem.Core;

public class Student {
    // Attributes
    private String id;
    private String name;
    private float marks;

    // Constructors
    public Student(String id, String name, float marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getMarks() {
        return marks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    // Display student information
    public void display() {
        String studentRank = checkRank();
        System.out.println("ID: " + id +
                " - Name: " + name +
                " - Marks: " + marks +
                " - Rank: " + studentRank);
    }

    // Check student rank
    private String checkRank() {
        if (marks <= 5.0) {
            return "Fail";
        } else if (marks <= 6.5) {
            return "Medium";
        } else if (marks <= 7.5) {
            return "Good";
        } else if (marks <= 9) {
            return "Very Good";
        } else if (marks <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid";
        }
    }
}