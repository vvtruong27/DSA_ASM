// Node class to represent each element in the queue
class Node {
    int data;       // Value of the node
    Node next;     // Pointer to the next node in the queue

    // Constructor to initialize the node
    public Node(int data) {
        this.data = data;
        this.next = null; // Initially, the next pointer is null
    }
}

// Queue class using a linked list
class QueueByLinkedList {
    private Node front;  // Pointer to the front of the queue
    private Node rear;   // Pointer to the rear of the queue
    private int size;    // Current size of the queue

    // Constructor to initialize the queue
    public QueueByLinkedList() {
        front = null;    // Initially, front is null
        rear = null;     // Initially, rear is null
        size = 0;        // Initially, size is 0
    }

    // Method to add an element to the queue (enqueue)
    public void enqueue(int value) {
        Node newNode = new Node(value); // Create a new node
        if (isEmpty()) {
            front = newNode; // If queue is empty, front and rear point to new node
            rear = newNode;
        } else {
            rear.next = newNode; // Link the new node to the end of the queue
            rear = newNode;       // Update rear to point to the new node
        }
        size++; // Increase the size
    }

    // Method to remove and return the front element (dequeue)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1; // Queue is empty
        }
        int value = front.data; // Get the front value
        front = front.next;      // Move front to the next node
        size--;                  // Decrease the size
        if (front == null) {
            rear = null; // If the queue becomes empty, update rear to null
        }
        return value; // Return the dequeued value
    }

    // Method to view the front element without removing it (peek)
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot peek.");
            return -1; // Queue is empty
        }
        return front.data; // Return the front value
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0; // Queue is empty if size is 0
    }

    // Method to get the current size of the queue
    public int getSize() {
        return size; // Return the current size
    }
}
