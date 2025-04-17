public class Weapon {
    public String weaponName;    // Name of the weapon
    public int range;            // Attack range of the weapon
    public int damage;           // Damage value of the weapon
    public double weight;        // Weight of the weapon
    public double cost;          // Cost of the weapon

    // Constructor: initializes a weapon with all attributes
    public Weapon(String n, int rang, int dam, double w, double c) {
        this.weaponName = n;
        this.damage = dam;
        this.range = rang;
        this.weight = w;
        this.cost = c;
    }

    // Setters: allow updating individual weapon attributes
    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // Getters: retrieve values of individual attributes
    public String getWeaponName() {
        return this.weaponName;
    }

    public int getRange() {
        return this.range;
    }

    public int getDamage() {
        return this.damage;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getCost() {
        return this.cost;
    }

    // toString: returns a simple string representation of the weapon
    public String toString() {
        return this.weaponName + " " + this.weight + "lbs ";
    }
}
