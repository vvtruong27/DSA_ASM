package EncapsulationExample;

public class ConstructionProject {
    // Private fields (attributes) encapsulated within the class
    private String projectName;
    private double budget;
    private boolean isCompleted;

    // Constructor to initialize a construction project
    public ConstructionProject(String projectName, double budget) {
        this.projectName = projectName;
        this.budget = budget;
        this.isCompleted = false; // By default, project is not completed
    }

    // Public getter methods to access private fields
    public String getProjectName() {
        return projectName;
    }

    public double getBudget() {
        return budget;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    // Public setter methods to modify private fields
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void completeProject() {
        this.isCompleted = true;
    }

    // A method to display project details
    public void displayProjectInfo() {
        System.out.println("Project Name: " + projectName);
        System.out.println("Budget: $" + budget);
        System.out.println("Project Completed: " + (isCompleted ? "Yes" : "No"));
    }

    public static void main(String[] args) {
        // Create a new construction project
        ConstructionProject project = new ConstructionProject("Library", 50000);

        // Display initial project information
        project.displayProjectInfo();

        // Update the budget and mark the project as completed
        project.setBudget(55000);
        project.completeProject();

        // Display updated project information
        project.displayProjectInfo();
    }
}