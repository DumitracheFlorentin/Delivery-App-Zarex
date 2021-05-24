package Restaurant;

import Services.GenerateID;

public class BridgeTableMenu implements GenerateID {
    private String menuID;
    private String restaurantID;
    private String productID;

    public BridgeTableMenu(){}

    public BridgeTableMenu(String restaurantID, String productID) {
        this.menuID = GenerateID.genID();
        this.restaurantID = restaurantID;
        this.productID = productID;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "BridgeTableMenu{" +
                "menuID='" + menuID + '\'' +
                ", restaurantID='" + restaurantID + '\'' +
                ", productID='" + productID + '\'' +
                '}';
    }
}
