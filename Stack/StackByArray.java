package Stack;

public class StackByArray {
    private int maxSize; // Maximum size of the stack
    private int[] stackArray; // Array to store stack elements
    private int top; // Tracks the top element in the stack

    // Constructor to initialize the stack
    public StackByArray(int size) {
        this.maxSize = size;
        this.stackArray = new int[maxSize];
        this.top = -1; // Top is -1 when the stack is empty
    }

    // Method to push an element onto the stack
    public void push(int value) {
        if (top == maxSize - 1) {
            System.out.println("Stack is full, cannot push " + value);
        } else {
            stackArray[++top] = value; // Increment top and insert value
            System.out.println("Pushed " + value + " onto the stack.");
        }
    }

    // Method to pop an element from the stack and remove it
    public int pop() {
        if (top == -1) {
            System.out.println("Stack is empty, cannot pop.");
            return -1; // Indicate error or empty stack
        } else {
            int value = stackArray[top]; // Store the top element
            stackArray[top] = 0; // Clear the element (0 can be replaced by default value if necessary)
            top--; // Decrement the top
            System.out.println("Popped " + value + " from the stack.");
            return value;
        }
    }

    // Method to peek at the top element of the stack
    public int peek() {
        if (top == -1) {
            System.out.println("Stack is empty, no element to peek.");
            return -1; // Indicate error or empty stack
        } else {
            System.out.println("Peeked at the top element: " + stackArray[top]);
            return stackArray[top];
        }
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1; // Returns true if the stack is empty
    }
}
