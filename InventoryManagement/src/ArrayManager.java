public class ArrayManager {
    int maxItems;          // Maximum size of the hash table
    int numItems;          // Current number of items in the table
    ShopItem[] table;      // The hash table itself (array of ShopItems)
    int LoadFactor;        // Threshold percentage to limit table occupancy

    // Constructor: initializes the hash table with a given size and default placeholder items
    public ArrayManager(int size) {
        maxItems = size;           // Set the max size
        numItems = 0;              // Initially the table is empty
        LoadFactor = 80;           // Set load factor threshold (80%)
        table = new ShopItem[maxItems];  // Initialize the table

        // Create a placeholder ShopItem to fill the table initially
        Weapon w = new Weapon(null, 0, 0, 0, 0);
        ShopItem temp = new ShopItem(w, 0);
        for (int x = 0; x < maxItems; x++) {
            table[x] = temp;
        }
    }

    // Hash function: computes a hash index from a string key
    public int HashFunction(String key) {
        int value = 0;
        // Calculates a hash value using the first character of the key repeatedly (likely a bug)
        for (int i = 0; i < key.length(); i++) {
            value += key.charAt(0);  // Should likely be key.charAt(i)
        }
        return value % maxItems;  // Modulo to ensure index is within bounds
    }

    // Inserts a new weapon with a given quantity into the hash table using quadratic probing
    public boolean put(Weapon w, int quantity) {
        // Check constraints: not over capacity, positive quantity, under load factor
        if (numItems < maxItems && quantity > 0 && numItems < LoadFactor) {
            int loc = HashFunction(w.weaponName); // Get initial index
            int startLoc = loc;
            int count = 1;

            // Use quadratic probing to find an empty or deleted slot
            while (table[loc].item.weaponName != null &&
                    table[loc].item.weaponName.compareTo("DELETED") != 0) {
                loc = (startLoc + (count * count)) % maxItems;
                count++;
            }

            // Insert the new item
            ShopItem temp = new ShopItem(w, quantity);
            table[loc] = temp;
            numItems++;
            return true;
        }

        return false;  // Insertion failed
    }

    // Retrieves a ShopItem from the table using its key (weapon name)
    public ShopItem get(String key) {
        int loc = HashFunction(key);   // Get starting index
        int startLoc = loc;
        int count = 1;

        // Search for the item using quadratic probing
        while (table[loc].item.weaponName.compareTo(key) != 0 &&
                table[loc].item.weaponName != null) {
            loc = (startLoc + (count * count)) % maxItems;
            count++;
        }

        // If slot is null, item was not found
        if (table[loc] == null) {
            return null;
        }

        return table[loc];  // Return the found item
    }

    // Deletes an item from the table by marking its fields as "DELETED"
    public void delete(String key) {
        int loc = HashFunction(key);   // Get starting index
        int startLoc = loc;
        int count = 1;

        // Search for the item using quadratic probing
        while (table[loc].item.weaponName.compareTo(key) != 0 &&
                table[loc].item.weaponName != null) {
            loc = (startLoc + (count * count)) % maxItems;
            count++;
        }

        // If not found, print error
        if (table[loc] == null) {
            System.out.println("Not found");
        } else {
            // Mark the item as deleted by overwriting its data
            table[loc].item.weaponName = "DELETED";
            table[loc].item.damage = -1;
            table[loc].item.range = -1;
            table[loc].item.weight = -1;
            table[loc].item.cost = -1;
            table[loc].numberInStock = 0;
            numItems--;
        }
    }

    // Prints all items in the hash table, including empty and deleted ones
    public void printTable() {
        // Loop through the table and print each item's details
        for (int x = 0; x < maxItems; x++) {
            System.out.println("Name: " + table[x].item.weaponName +
                    "   Damage:" + table[x].item.damage +
                    "  Range:" + table[x].item.range +
                    "  Cost:" + table[x].item.cost +
                    "     Quantity in stock:" + table[x].numberInStock);
        }
    }
}
