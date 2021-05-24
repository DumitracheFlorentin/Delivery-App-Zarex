package Services;

import Order.ListOfProducts;
import Restaurant.*;

public class GetMenus {

    public void getMenus(ListOfRestaurants listOfRestaurants, ListOfMenus listOfBridgeMenus, ListOfProducts listOfProducts){
        for(int i = 0 ; i < listOfRestaurants.sizeOfList(); i++){
            Menu menuX = new Menu();
            for(int j = 0 ; j < listOfBridgeMenus.sizeOfList() ; j++){
                if(listOfBridgeMenus.getSpecificMenu(j).getRestaurantID().equalsIgnoreCase(listOfRestaurants.getRestaurantByIndex(i).getId())){
                    for(int z = 0 ; z < listOfProducts.sizeOfList() ; z++){
                        if(listOfProducts.getProductByIndex(z).getId().equalsIgnoreCase(listOfBridgeMenus.getSpecificMenu(j).getProductID())){
                            menuX.addProduct(listOfProducts.getProductByIndex(z));
                        }
                    }
                }
            }
            listOfRestaurants.getRestaurantByIndex(i).setMenu(menuX);
        }
    }
}
