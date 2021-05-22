package Order;

import Services.generateID;

public class Order implements generateID {
    private String id;
    private String restaurantID;
    private String clientUsername;
    private String totalPrice;
    private ListOfProducts listOfProducts;

    public Order(){}

    public Order(String restaurantID, String clientUsername, String totalPrice, ListOfProducts listOfProducts) {
        this.id = generateID.genID();
        this.restaurantID = restaurantID;
        this.clientUsername = clientUsername;
        this.totalPrice = totalPrice;
        this.listOfProducts = listOfProducts;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ListOfProducts getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(ListOfProducts listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}
