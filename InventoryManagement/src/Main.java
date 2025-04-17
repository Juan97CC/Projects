import java.util.Scanner;

public class Main {

    /**
     * The main method to start the program.
     * Initializes the player, creates the shop inventory,
     * and enters the main menu loop.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pname;
        System.out.println("Please enter Player name:");
        pname = sc.next();
        Player pl = new Player(pname, 45);
        ArrayManager ht = new ArrayManager(101);
        ht.printTable();
        mainMenu(ht, pl, sc);
        pl.printCharacter();
    }

    /**
     * Prompts the user for a non-negative integer input.
     *
     * @param sc      Scanner object for input
     * @param message Prompt message
     * @return A valid non-negative integer
     */
    public static int getInteger(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
            sc.nextLine();
        }
    }

    /**
     * Prompts the user for a positive double input.
     *
     * @param sc      Scanner object for input
     * @param message Prompt message
     * @return A valid positive double
     */
    public static double getDouble(Scanner sc, String message) {
        double value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a double.");
            }
            sc.nextLine();
        }
    }

    /**
     * Adds new weapons to the shop inventory.
     * Continues until the user types "end".
     *
     * @param h  ArrayManager handling the shop
     * @param sc Scanner object for input
     */
    public static void addWeapons(ArrayManager h, Scanner sc) {
        System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
        String weaponName;
        int weaponRange, weaponDamage;
        double weaponWeight, weaponCost;
        int quantity;

        System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
        weaponName = sc.next();
        while (!weaponName.equals("end")) {
            weaponRange = getInteger(sc, "Please enter the Range of the Weapon (0-10):");
            weaponDamage = getInteger(sc, "Please enter the Damage of the Weapon:");
            weaponWeight = getDouble(sc, "Please enter the Weight of the Weapon (in pounds):");
            weaponCost = getDouble(sc, "Please enter the Cost of the Weapon:");
            Weapon w = new Weapon(weaponName.toLowerCase(), weaponRange, weaponDamage, weaponWeight, weaponCost);
            quantity = getInteger(sc, "Please enter the quantity in stock:");
            h.put(w, quantity);
            System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
            weaponName = sc.next();
        }
    }

    /**
     * Displays the shop inventory and player's balance.
     *
     * @param ht Shop manager
     * @param p  Player object
     */
    public static void showRoomMenu(ArrayManager ht, Player p) {
        System.out.println("WELCOME TO THE SHOWROOM!!!!");
        ht.printTable();
        System.out.println("You have " + p.money + " money.");
        System.out.println("Please select a weapon to buy('end' to quit):");
    }

    /**
     * Allows the player to view and buy items from the shop.
     *
     * @param ht Shop inventory
     * @param p  Player object
     * @param sc Scanner object for input
     */
    public static void showRoom(ArrayManager ht, Player p, Scanner sc) {
        String choice;
        showRoomMenu(ht, p);
        choice = sc.next();
        while (!choice.equals("end") && !p.inventoryFull()) {
            ShopItem si = ht.get(choice.toLowerCase());
            if (si != null) {
                if (p.buy(si.item)) {
                    si.numberInStock--;
                }
            } else {
                System.out.println(" ** " + choice + " not found!! **");
            }
            showRoomMenu(ht, p);
            choice = sc.next();
        }
        System.out.println("");
    }

    /**
     * The main menu for interacting with the shop and backpack.
     *
     * @param ht Shop manager
     * @param p  Player object
     * @param sc Scanner object
     */
    public static void mainMenu(ArrayManager ht, Player p, Scanner sc) {
        int choice = 0;

        while (choice != 7 && !p.inventoryFull()) {
            System.out.println("1) Add Items to the shop");
            System.out.println("2) Delete Items from the shop");
            System.out.println("3) Buy from the shop");
            System.out.println("4) View backpack");
            System.out.println("5) View Player");
            System.out.println("6) Delete Backpack item");
            System.out.println("7) Exit");
            choice = getInteger(sc, "Enter a int: ");

            switch (choice) {
                case 1:
                    addWeapons(ht, sc);
                    break;
                case 2:
                    System.out.println("Delete Items from the shop:");
                    ht.printTable();
                    System.out.println("Please select a weapon to delete");
                    ht.delete(sc.next());
                    break;
                case 3:
                    showRoom(ht, p, sc);
                    break;
                case 4:
                    System.out.println("View in Backpack:");
                    p.printBackpack();
                    System.out.println("Enter any character to continue");
                    sc.next();
                    break;
                case 5:
                    System.out.println("View Character:");
                    p.printCharacter();
                    System.out.println("Enter any character to continue");
                    sc.next();
                    break;
                case 6:
                    String name;
                    int range, damage;

                    System.out.println("What is the name of the weapon");
                    name = sc.next();

                    System.out.println("What is the range of the weapon");
                    range = getInteger(sc, "Enter a Int: ");

                    System.out.println("What is the damage of the weapon");
                    damage = getInteger(sc, "Enter a Int: ");

                    p.removeBackpackItem(name.toLowerCase(), damage, range);
                    break;
            }
        }
    }
}
