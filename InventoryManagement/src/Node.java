public class Node {

    public Weapon info; // Holds the Weapon object
    public Node link;   // Points to the next node in the linked list

    // Constructor: initializes the node with a Weapon object
    public Node(Weapon rec) {
        info = rec;
        link = null;
    }
}

