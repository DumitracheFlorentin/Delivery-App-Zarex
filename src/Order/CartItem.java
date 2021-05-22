package Order;

import Restaurant.Product;

public class CartItem {
    private Product food;
    private int HowMany;

    public CartItem(Product food, int howMany) {
        this.food = food;
        this.HowMany = howMany;
    }

    public Product getFood() {
        return food;
    }

    public void setFood(Product food) {
        this.food = food;
    }

    public int getHowMany() {
        return HowMany;
    }

    public void setHowMany(int howMany) {
        HowMany = howMany;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Food ='" + food + '\'' +
                ", Quantity = '" + HowMany + '\'' +
                '}';
    }
}
