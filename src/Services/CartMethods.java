package Services;

import Cart.Cart;
import Restaurant.ListOfRestaurants;
import Restaurant.Product;
import Restaurant.Restaurant;

import java.util.Scanner;

public class CartMethods {
    public void initCart(String restaurantName, ListOfRestaurants listOfRestaurants, int input){
        System.out.println("Welcome to " + restaurantName + " restaurant!");
        System.out.println();
        System.out.println("Our menu: ");
        for(int j = 0 ; j < listOfRestaurants.getRestaurantByIndex(input - 1).getMenu().sizeOfList() ; j++){
            System.out.println((j+1) + ". " + listOfRestaurants.getRestaurantByIndex(input - 1).getMenu().getSpecificProductFromMenu(j));
        }

        System.out.println("Cart: ");
        System.out.println("NOTE: To complete the command enter the index -1 ");
    }

    public void addProductsToCart(boolean cartItems, int sizeofMenu, Restaurant specRestaurant, Cart cart){
        Scanner cartInput = new Scanner(System.in);
        Scanner qtyInput = new Scanner(System.in);

        while(cartItems){
            System.out.print("Add product according to index: ");
            int cartIn = cartInput.nextInt();
            if(cartIn == -1){
                cartItems = false;
            } else if(cartIn > 0 && cartIn <= sizeofMenu) {
                System.out.print("Add quantity for the selected product: ");
                int qtyIn = qtyInput.nextInt();
                if(qtyIn <= 0) {
                    System.out.println("Quantity unavailable!");
                } else {
                    Product prodSelected =  specRestaurant.getMenu().getListOfProducts().get(cartIn - 1);
                    cart.addFoodToCart(prodSelected, qtyIn);
                }
            } else {
                System.out.println("Index unavailable! Try again...");
            }
        }
    }
}
