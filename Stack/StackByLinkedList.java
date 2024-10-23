package Stack;

import java.util.LinkedList;

public class StackByLinkedList {
    private LinkedList<Integer> stackList; // LinkedList to store stack elements

    // Constructor to initialize the stack
    public StackByLinkedList() {
        stackList = new LinkedList<>(); // Create an empty LinkedList
    }

    // Method to push an element onto the stack
    public void push(int value) {
        stackList.addFirst(value); // Adds element at the start of the list
        System.out.println("Pushed " + value + " onto the stack.");
    }

    // Method to pop an element from the stack and remove it
    public int pop() {
        if (stackList.isEmpty()) {
            System.out.println("Stack is empty, cannot pop.");
            return -1; // Indicate error or empty stack
        } else {
            int value = stackList.removeFirst(); // Removes and returns the first element
            System.out.println("Popped " + value + " from the stack.");
            return value;
        }
    }

    // Method to peek at the top element of the stack
    public int peek() {
        if (stackList.isEmpty()) {
            System.out.println("Stack is empty, no element to peek.");
            return -1; // Indicate error or empty stack
        } else {
            int value = stackList.getFirst(); // Retrieves but doesn't remove the first element
            System.out.println("Peeked at the top element: " + value);
            return value;
        }
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return stackList.isEmpty(); // Returns true if the list is empty
    }
}
