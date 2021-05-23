package Cart;

import Restaurant.Product;

import java.util.ArrayList;

public class Cart {
    private static Cart cart;
    private float totalPrice;
    private ArrayList<CartItem> products = new ArrayList<CartItem>();

    public ArrayList<CartItem> getProducts() {
        return products;
    }

    private Cart() {
        this.totalPrice = 0;
    }

    public static Cart getInstance(){
        if(cart == null){
            cart = new Cart();
            return cart;
        } else {
            return cart;
        }
    }

    public void addFoodToCart(Product food, int howMany){
        int check = 0;

        for(CartItem product : products){
            if(product != null && product.getFood().getId() == food.getId()){
                product.setHowMany(product.getHowMany() + howMany);
                check = 1;
            }
        }

        if(check == 0){
            CartItem newProduct = new CartItem(food, howMany);
            products.add(newProduct);
        }

        totalPrice = totalPrice + (food.getPrice() * howMany);
    }

    public void showCart(){
        for (CartItem  product : products) {
            System.out.println("Name: " + product.getFood().getName() + " Price: " + product.getFood().getPrice() + " Quantity: " + product.getHowMany());
        }
    }

    public void getTotalPrice(){
        System.out.println(totalPrice + "RON - ONLY CASH");
    }

    public float getPrice(){return totalPrice;}

    public void clearCart(){
        products.clear();
    }
}
