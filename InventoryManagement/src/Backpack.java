public class Backpack {
    private static LinkedList[] array;      // Array of linked lists used for chaining in the hash table
    private static int numItems;            // Current number of weapons in the backpack
    private static int maxItems;            // Maximum number of items (size of array)
    private static double currWeight;       // Current total weight of items
    private static double maxWeight;        // Maximum weight capacity of the backpack

    // Constructor: initializes the backpack with default values
    public Backpack () {
        maxItems = 30;
        array = new LinkedList[30];         // Initialize array with null slots
        numItems = 0;
        currWeight = 0;
        maxWeight = 90;
    }

    // Hash function: generates index based on weapon name, damage, and range
    public static int hashFunction(String key, int damage, int range) {
        int value = 0;
        for (int i = 0; i < key.length(); i++) {
            value += key.charAt(i);         // Sum ASCII values of characters
        }
        value = (value * range) + damage;   // Mix in range and damage to distribute keys better
        return value % maxItems;            // Modulo to fit within array bounds
    }

    // Displays the entire hash table (all weapon chains at each index)
    public void displayTable() {
        for (int i = 0; i < maxItems; i++) {
            System.out.println("[" + i + "]  --> ");
            if (array[i] != null)
                array[i].displayList();     // Show contents if a list exists
            else
                System.out.println("----"); // Empty slot
        }
    }

    // Searches for a weapon in the backpack using key attributes
    public Weapon search(String key, int dam, int range) {
        int loc = hashFunction(key.toLowerCase(), dam, range);

        if (array[loc] != null)
            return array[loc].weaponSearch(key);  // Delegate search to the linked list

        return null;
    }

    // Adds a weapon to the backpack if under weight and item limits
    public static void addWeapon(Weapon weapon) {
        if (numItems < maxItems && currWeight <= maxWeight) {
            String key = weapon.getWeaponName().toLowerCase();
            int dam = weapon.getDamage();
            int range = weapon.getRange();
            int loc = hashFunction(key, dam, range);

            if (array[loc] == null)
                array[loc] = new LinkedList();  // Initialize list at this index if needed

            array[loc].insertFront(weapon);     // Insert at front of list
            currWeight += weapon.getWeight();   // Update total weight
            numItems++;                         // Increase item count
        }
    }

    // Deletes a weapon from the backpack using its identifying attributes
    public void delete(Weapon key) {
        String name = key.getWeaponName().toLowerCase();
        int dam = key.getDamage();
        int ran = key.getRange();
        int loc = hashFunction(name, dam, ran);

        if (array[loc] != null) {
            array[loc].deleteNode(key.weaponName);  // Remove from list
            numItems--;
        } else {
            System.out.println("Value " + key + " not present \n");
        }
    }
}
