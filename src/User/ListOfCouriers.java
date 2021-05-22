package User;

import java.util.ArrayList;

public class ListOfCouriers {
    private ArrayList<Courier> listOfCouriers = new ArrayList<Courier>();

    public ArrayList<Courier> getListOfCouriers() {
        return listOfCouriers;
    }

    public void addCourier(Courier x){
        listOfCouriers.add(x);
    }

    public void removeCourier(int index){
        listOfCouriers.remove(index);
    }

    public int getSizeOfList(){
        return listOfCouriers.size();
    }

    public Courier getCourierByIndex(int index){
        return listOfCouriers.get(index);
    }

}
