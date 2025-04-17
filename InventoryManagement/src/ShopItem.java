public class ShopItem {
    Weapon item;            // The weapon being sold
    int numberInStock;      // Quantity of this weapon available in stock

    // Constructor: initializes a shop item with the given weapon and quantity
    public ShopItem(Weapon w, int nInStock){
        item = w;
        numberInStock = nInStock;
    }
}
