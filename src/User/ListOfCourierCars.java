package User;

import java.util.ArrayList;

public class ListOfCourierCars {
    private ArrayList<CourierCar> listOfCourierCars = new ArrayList<CourierCar>();

    public ArrayList<CourierCar> getListOfCourierCars() {
        return listOfCourierCars;
    }

    public void addCar(CourierCar x){
        listOfCourierCars.add(x);
    }

    public void removeCar(int index){
        listOfCourierCars.remove(index);
    }

    public int getSizeOfList(){
        return listOfCourierCars.size();
    }

    public CourierCar getCarByIndex(int index){
        return listOfCourierCars.get(index);
    }
}
