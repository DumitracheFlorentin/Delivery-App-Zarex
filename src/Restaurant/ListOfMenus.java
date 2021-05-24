package Restaurant;

import java.util.ArrayList;

public class ListOfMenus {
    private ArrayList<BridgeTableMenu> listOfMenus = new ArrayList<BridgeTableMenu>();

    public ArrayList<BridgeTableMenu> getListOfMenus() {
        return listOfMenus;
    }

    public void addMenu(BridgeTableMenu x){
        listOfMenus.add(x);
    }

    BridgeTableMenu getMenu(int index) {
        return listOfMenus.get(index - 1);
    }

    public int sizeOfList(){
        return listOfMenus.size();
    }

    public BridgeTableMenu getSpecificMenu(int index){
        return listOfMenus.get(index);
    }
}
