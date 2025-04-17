public class Player {
    public String name;            // Player's name
    public Backpack backpack;      // Player's backpack containing weapons
    public int numItems;           // Current number of items in backpack
    public double money;           // Player's available money
    public double currWeight;      // Current total weight of items in backpack
    public double maxWeight;       // Maximum weight capacity of the backpack
    public int maxItems;           // Maximum number of items allowed

    // Constructor: initializes a new player with a name and starting money
    public Player(String n, double m) {
        this.name = n;
        this.money = m;
        this.numItems = 0;
        this.backpack = new Backpack();  // New, empty backpack
        this.currWeight = 0.0D;
        this.maxWeight = 90.0D;
        this.maxItems = 10;
    }

    // Attempts to buy a weapon: checks weight, cost, and item limits
    public boolean buy(Weapon w) {
        if (w.weight + this.currWeight <= this.maxWeight &&
                this.money >= w.cost &&
                this.numItems < this.maxItems) {

            Backpack.addWeapon(w);     // Add weapon to backpack
            this.withdraw(w.cost);     // Deduct money
            System.out.println(w.weaponName + " bought...");
            ++this.numItems;
            this.currWeight += w.weight;

            System.out.println(this.numItems);
            System.out.println(this.currWeight);
            return true;
        } else {
            System.out.println("Sorry :( transaction could not be completed \n");
            return false;
        }
    }

    // Withdraws money from the player if they meet item and weight constraints
    public void withdraw(double amt) {
        if (this.currWeight <= this.maxWeight &&
                this.money >= amt &&
                this.numItems < this.maxItems) {
            this.money -= amt;
        }
    }

    // Checks if the backpack is full (max items reached)
    public boolean inventoryFull() {
        return this.numItems == 10;
    }

    // Prints player info including name, money, weight, and item count
    public void printCharacter() {
        System.out.println("Name: " + this.name +
                "\nMoney: " + this.money +
                "\nBack pack weight: " + this.currWeight +
                "\nCurrent Items(In Backpack): " + this.numItems);
    }

    // Displays the player's backpack inventory
    public void printBackpack() {
        System.out.println(this.name + ", you own " + this.numItems +
                " of Weapons, total weight is " + this.currWeight);
        this.backpack.displayTable();  // Call Backpack's display method
    }

    // Removes a specific item from the backpack based on its properties
    public void removeBackpackItem(String weapon, int damage, int range) {
        Weapon v = backpack.search(weapon, damage, range);
        if (v != null) {
            backpack.delete(v);      // Remove weapon from backpack
            currWeight -= v.weight;  // Adjust weight
            numItems--;              // Decrease item count
            System.out.println("Item has been deleted");
        } else {
            System.out.println("Sorry :( item doesn't exist\n\n");
        }
    }
}
