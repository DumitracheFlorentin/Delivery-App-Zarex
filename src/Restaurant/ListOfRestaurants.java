package Restaurant;

import java.util.ArrayList;

public class ListOfRestaurants {
    private ArrayList<Restaurant> listOfRestaurants = new ArrayList<Restaurant>();

    public ArrayList<Restaurant> getListOfRestaurants() {
        return listOfRestaurants;
    }

    public void addRestaurant(Restaurant x){
        listOfRestaurants.add(x);
    }

    public void showRestaurants(){
        for (Restaurant  restaurant: listOfRestaurants) {
            System.out.println(restaurant);
        }
    }

    public Restaurant getSpecificRestaurant(int index){
        return listOfRestaurants.get(index);
    }

    public String getRestaurantName(int index) {
        for(int i = 0; i < listOfRestaurants.size() ; i++)
            if(i == index - 1){
                return listOfRestaurants.get(i).getName();
            }
        return listOfRestaurants.get(0).getName();
    }

    public void showRestaurantsName(){
        for(int i = 0; i < listOfRestaurants.size(); i++)
        {
            System.out.println(i+1 + ". " + listOfRestaurants.get(i).getName());
        }
    }

    public int sizeOfList(){
        return listOfRestaurants.size();
    }

    public void deleteSpecificItem(int index){
        listOfRestaurants.remove(index);
    }

    public Restaurant getRestaurantByIndex(int index){
        return listOfRestaurants.get(index);
    }
}
