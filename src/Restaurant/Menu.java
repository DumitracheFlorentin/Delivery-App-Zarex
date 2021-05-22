package Restaurant;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Product> menu = new ArrayList<Product>();

    public ArrayList<Product> getListOfProducts() {
        return menu;
    }

    public void addProduct(Product x){
        menu.add(x);
    }

    public void showMenu(){
        System.out.println(menu);
    }

    @Override
    public String toString() {
        return menu.toString();
    }
}
