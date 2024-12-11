package StudentManagementSystem.Core;

import java.util.EmptyStackException;

public class StudentStack {
    private static final int DEFAULT_CAPACITY = 1000;
    private Student[] stack;
    private int top;

    public StudentStack() {
        stack = new Student[DEFAULT_CAPACITY]; // Create a stack with default capacity
        top = -1; // Initialize top to -1 to track the top element
    }

    // Push a student onto the stack
    public void push(Student student) {
        if (isFull()) { // Check if the stack is full then resize
            resize();
        }
        stack[++top] = student; // Increment top then push student
    }

    // Pop a student from the stack
    public Student pop() {
        if (isEmpty()) { // Check if the stack is empty
            throw new EmptyStackException();
        }
        Student student = stack[top]; // Get the top student
        stack[top] = null; // Remove the top student
        top--; // Decrement top
        return student; // Pop the top student then decrement top
    }

    // Peek at the top student without removing
    public Student peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[top]; // Return the top student
    }

    // Get the size of the stack
    public int size() {
        return top + 1;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1; // If top is -1, the stack is empty
    }

    // Check if the stack is full
    private boolean isFull() {
        return top == stack.length - 1; // If top is equal to the stack length - 1, the stack is full
    }

    // Resize the stack if it's full
    private void resize() {
        Student[] newStack = new Student[stack.length * 2]; // Create a new stack with double the size
        System.arraycopy(stack, 0, newStack, 0, stack.length); // Copy the elements from the old stack to the new stack
        stack = newStack; // Assign the new stack to the old stack
    }
}
