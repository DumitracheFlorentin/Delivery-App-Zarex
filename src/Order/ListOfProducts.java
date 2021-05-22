package Order;

import Restaurant.Product;

import java.util.ArrayList;
import java.util.Objects;

public class ListOfProducts {
    private ArrayList<Product> listOfProducts = new ArrayList<Product>();

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    public int sizeOfList(){
        return listOfProducts.size();
    }

    public void addProduct(Product x){
        listOfProducts.add(x);
    }

    public boolean checkProductName(String name) {
        for(int i = 0; i < listOfProducts.size(); i++)
        {
            String nameOfProduct =  listOfProducts.get(i).getName();
            if(Objects.equals(nameOfProduct, name)){
                return true;
            }
        }
        return false;
    }

    public Product getProductByIndex(int index) {
        return listOfProducts.get(index);
    }
}
