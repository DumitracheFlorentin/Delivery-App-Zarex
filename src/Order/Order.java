package Order;

import Cart.Cart;
import Services.GenerateID;

import java.util.Date;

public class Order implements GenerateID {
    private String id;
    private String restaurantID;
    private String clientUsername;
    private Float totalPrice;

    public Order(String restaurantID, String clientUsername, Float totalPrice) {
        this.id = GenerateID.genID();
        this.restaurantID = restaurantID;
        this.clientUsername = clientUsername;
        this.totalPrice = totalPrice;
    }

    public Order(){
        this.id = GenerateID.genID();
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", restaurantID='" + restaurantID + '\'' +
                ", clientUsername='" + clientUsername + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
