public class LinkedList {

    private Node start;  // Head of the linked list

    // Constructor: initializes an empty list
    public LinkedList(){
        start = null;
    }

    // Displays all weapons in the list
    public void displayList() {
        Node node;
        if (start == null) {
            System.out.println("_____");
            return;
        }

        node = start;
        while (node != null) {
            System.out.println(node.info + " ");  // info holds a Weapon object
            node = node.link;
        }
        System.out.println();
    }

    // Searches for a weapon by name and returns it if found, otherwise null
    public Weapon weaponSearch(String x) {
        Node node = start;
        while (node != null) {
            if (node.info.getWeaponName().compareTo(x) == 0)
                break;
            node = node.link;
        }

        if (node == null) {
            System.out.println(x + "Have not been found");
            return null;
        } else {
            return node.info;
        }
    }

    // Inserts a weapon at the front of the list
    public void insertFront(Weapon data) {
        Node temp = new Node(data);
        temp.link = start;
        start = temp;
    }

    // Deletes the node that matches the given weapon name
    public void deleteNode(String x) {
        if (start == null) {
            System.out.println("Value " + x + " not present \n");
            return;
        }

        // Case 1: First node is the one to delete
        if (start.info.getWeaponName().compareTo(x) == 0) {
            start = start.link;
            return;
        }

        // Case 2: Delete from middle or end
        Node node = start;

        while (node.info != null) {
            if (node.link.info.getWeaponName().compareTo(x) == 0) {
                break;
            }
            node = node.link;
        }

        if (node.link == null)
            System.out.println("Value " + x + " not present \n");
        else
            node.link = node.link.link;
    }
}
