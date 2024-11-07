class QueueByArray {
    private int[] queue;      // Array to hold the queue elements
    private int front;        // Index of the front element
    private int rear;         // Index of the rear element
    private int size;         // Current size of the queue
    private int capacity;     // Maximum capacity of the queue

    // Constructor to initialize the queue
    public QueueByArray(int capacity) {
        this.capacity = capacity; // Set maximum capacity
        queue = new int[capacity]; // Create the array
        front = 0;                 // Initialize front index
        rear = -1;                 // Initialize rear index
        size = 0;                  // Initialize size
    }

    // Method to add an element to the queue (enqueue)
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue " + value);
            return; // Queue is full
        }
        rear = (rear + 1) % capacity; // Circular increment of rear
        queue[rear] = value;           // Add value to the rear
        size++;                         // Increase the size
    }

    // Method to remove and return the front element (dequeue)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue");
            return -1; // Queue is empty
        }
        int value = queue[front];     // Get the front value
        front = (front + 1) % capacity; // Circular increment of front
        size--;                        // Decrease the size
        return value;                 // Return the dequeued value
    }

    // Method to view the front element without removing it (peek)
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot peek");
            return -1; // Queue is empty
        }
        return queue[front]; // Return the front value
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0; // Queue is empty if size is 0
    }

    // Method to check if the queue is full
    public boolean isFull() {
        return size == capacity; // Queue is full if size equals capacity
    }

    // Method to get the current size of the queue
    public int getSize() {
        return size; // Return the current size
    }
}
