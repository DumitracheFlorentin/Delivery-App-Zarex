package Order;

import Restaurant.Product;

import java.util.ArrayList;

public class ListOfOrders {
    private ArrayList<Order> listOfOrders = new ArrayList<Order>();

    public ArrayList<Order> getListOfProducts() {
        return listOfOrders;
    }

    public int sizeOfList(){
        return listOfOrders.size();
    }

    public void addOrder(Order x){
        listOfOrders.add(x);
    }

    public Order getOrderByIndex(int index) {
        return listOfOrders.get(index);
    }
}
