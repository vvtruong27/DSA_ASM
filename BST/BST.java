package BST;

public class BST {
    // Inner class representing a node in the BST
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root node of the BST
    Node root;

    // Constructor for an empty tree
    public BST() {
        root = null;
    }

    // Method to call for inserting a new key
    void insert(int key) {
        root = insertRec(root, key);
    }

    // Recursive method to insert a new key into the BST
    Node insertRec(Node root, int key) {
        // If the tree is empty, create a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Traverse the tree to find the correct place to insert
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    // Method for in-order traversal (left, node, right)
    void inOrder() {
        inOrderRec(root);
    }

    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.key + " ");
            inOrderRec(root.right);
        }
    }

    // Method for pre-order traversal (node, left, right)
    void preOrder() {
        preOrderRec(root);
    }

    void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Method for post-order traversal (left, right, node)
    void postOrder() {
        postOrderRec(root);
    }

    void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    // Method to search for a key in the BST
    boolean search(int key) {
        return searchRec(root, key) != null;
    }

    Node searchRec(Node root, int key) {
        // If the tree is empty or the key is found at the root
        if (root == null || root.key == key) {
            return root;
        }

        // If the key is smaller than the root's key, search in the left subtree
        if (key < root.key) {
            return searchRec(root.left, key);
        }

        // If the key is larger, search in the right subtree
        return searchRec(root.right, key);
    }

    // Main method to test the BST
    public static void main(String[] args) {
        BST tree = new BST();

        // Insert elements into the BST
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Print traversal results
        System.out.println("In-order traversal:");
        tree.inOrder();

        System.out.println("\nPre-order traversal:");
        tree.preOrder();

        System.out.println("\nPost-order traversal:");
        tree.postOrder();

        // Search for elements
        System.out.println("\nSearch for 40 in the tree: " + (tree.search(40) ? "Found" : "Not Found"));
        System.out.println("Search for 90 in the tree: " + (tree.search(90) ? "Found" : "Not Found"));
    }
}
