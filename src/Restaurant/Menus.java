package Restaurant;

import java.util.ArrayList;

public class Menus {
    private ArrayList<Menu> listOfMenus = new ArrayList<Menu>();

    public ArrayList<Menu> getListOfMenus() {
        return listOfMenus;
    }

    public void addMenu(Menu x){
        listOfMenus.add(x);
    }

    Menu getMenu(int index) {
        return listOfMenus.get(index - 1);
    }

    public int sizeOfList(){
        return listOfMenus.size();
    }

    public Menu getSpecificMenu(int index){
        return listOfMenus.get(index);
    }
}
