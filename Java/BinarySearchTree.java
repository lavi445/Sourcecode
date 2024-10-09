// Class representing a node in the BST
class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

// Class representing the BST
class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    // Method to insert a new key in the BST
    void insert(int key) {
        root = insertRec(root, key);
    }

    // Recursive method to insert a new key in the BST
    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        return root;
    }

    // Method to delete a key from the BST
    void delete(int key) {
        root = deleteRec(root, key);
    }

    // Recursive method to delete a key from the BST
    Node deleteRec(Node root, int key) {
        if (root == null) return root;
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    // Method to find the minimum value node in the BST
    int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    // Method to search for a key in the BST
    boolean search(int key) {
        return searchRec(root, key);
    }

    // Recursive method to search for a key in the BST
    boolean searchRec(Node root, int key) {
        if (root == null) return false;
        if (root.key == key) return true;
        if (root.key < key)
            return searchRec(root.right, key);
        return searchRec(root.left, key);
    }

    // Method for inorder traversal of the BST
    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    // Recursive method for inorder traversal of the BST
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Method for preorder traversal of the BST
    void preorder() {
        preorderRec(root);
        System.out.println();
    }

    // Recursive method for preorder traversal of the BST
    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Method for postorder traversal of the BST
    void postorder() {
        postorderRec(root);
        System.out.println();
    }

    // Recursive method for postorder traversal of the BST
    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }
}

// Driver class to test the BST implementation
public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Inserting elements
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Displaying the tree
        System.out.println("Inorder traversal:");
        tree.inorder();

        // Deleting elements
        tree.delete(20);
        tree.delete(30);
        System.out.println("Inorder traversal after deletion:");
        tree.inorder();

        // Searching for an element
        int searchKey = 70;
        System.out.println("Is " + searchKey + " present in the tree? " + tree.search(searchKey));

        // Traversals
        System.out.println("Preorder traversal:");
        tree.preorder();
        System.out.println("Postorder traversal:");
        tree.postorder();
    }
}
