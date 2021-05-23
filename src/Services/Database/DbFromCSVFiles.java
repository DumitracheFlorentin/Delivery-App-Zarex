package Services.Database;

import Order.ListOfProducts;
import Restaurant.*;
import User.*;

import java.io.*;

public class DbFromCSVFiles {
    // VARIABLES
    String line = "";

    public void readClientsFromFile(ListOfClients listOfClients){
        try{
            BufferedReader brClients = new BufferedReader(new FileReader("db_clients.csv"));

            while((line = brClients.readLine()) != null){
                // Variables
                String[] values = line.split(",");

                // Init a new Client
                Client clientX = new Client("id","username", "password", "email", "firstName", "lastName", "address", "phoneNumber", false);

                // Use a function to read from the CSV File
                switchClientControl(listOfClients, clientX, values);
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readProductsFromFile(ListOfProducts listOfProducts){
        try{
            BufferedReader brProducts = new BufferedReader(new FileReader("db_products.csv"));

            while((line = brProducts.readLine()) != null){
                // Variables
                String[] values = line.split(",");

                // Init a new Client
                Product productX = new Product();

                // Use a function to read from the CSV File
                switchProductControl(listOfProducts, productX, values);
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Menus readMenuFromFile(Menus listOfMenus, ListOfProducts listOfProducts){
        Menu menu1 = new Menu();
        menu1.addProduct(listOfProducts.getProductByIndex(0));
        menu1.addProduct(listOfProducts.getProductByIndex(4));
        menu1.addProduct(listOfProducts.getProductByIndex(5));

        Menu menu2 = new Menu();
        menu2.addProduct(listOfProducts.getProductByIndex(0));
        menu2.addProduct(listOfProducts.getProductByIndex(2));
        menu2.addProduct(listOfProducts.getProductByIndex(3));

        Menu menu3 = new Menu();
        menu3.addProduct(listOfProducts.getProductByIndex(1));
        menu3.addProduct(listOfProducts.getProductByIndex(2));
        menu3.addProduct(listOfProducts.getProductByIndex(5));

        listOfMenus.addMenu(menu1);
        listOfMenus.addMenu(menu2);
        listOfMenus.addMenu(menu3);

        return listOfMenus;
    }

    public void readRestaurantsFromFile(ListOfRestaurants listOfRestaurants, Menus listOfMenus){
        try{
            BufferedReader brRestaurants = new BufferedReader(new FileReader("db_restaurants.csv"));

            while((line = brRestaurants.readLine()) != null){
                // Variables
                String[] values = line.split(",");

                // Init a new Client
                Restaurant restaurantX = new Restaurant();

                // Use a function to read from the CSV File
                switchRestaurantControl(listOfRestaurants, listOfMenus, restaurantX, values);
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readCarsFromFile(ListOfCourierCars listOfCourierCars){
        try{
            BufferedReader brCars = new BufferedReader(new FileReader("db_cars.csv"));

            while((line = brCars.readLine()) != null){
                // Variables
                String[] values = line.split(",");

                // Init a new Client
                CourierCar courierCarX = new CourierCar();

                // Use a function to read from the CSV File
                switchCarsControl(listOfCourierCars, courierCarX, values);
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readCouriersFromFile(ListOfCouriers listOfCouriers, ListOfCourierCars listOfCourierCars){
        try{
            BufferedReader brCouriers = new BufferedReader(new FileReader("db_couriers.csv"));

            while((line = brCouriers.readLine()) != null){
                // Variables
                String[] values = line.split(",");

                // Init a new Client
                Courier courierX = new Courier();

                // Use a function to read from the CSV File
                switchCourierControl(listOfCouriers, listOfCourierCars,courierX, values);
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void switchClientControl(ListOfClients listOfClients, Client clientX, String[] values){
        for(int i = 0 ; i < values.length ; i++){
            clientControlSwitch( i, clientX, values[i]);
        }
        listOfClients.addClient(clientX);
    }

    public void switchProductControl(ListOfProducts listOfProducts, Product productX, String[] values){
        for(int i = 0 ; i < values.length ; i++){
            productControlSwitch( i, productX, values[i]);
        }
        listOfProducts.addProduct(productX);
    }

    public void switchRestaurantControl(ListOfRestaurants listOfRestaurants, Menus listOfMenus, Restaurant restaurantX, String[] values){
        for(int i = 0 ; i < values.length ; i++){
            restaurantControlSwitch(listOfMenus, i, restaurantX, values[i]);
        }
        listOfRestaurants.addRestaurant(restaurantX);
    }

    public void switchCarsControl(ListOfCourierCars listOfCourierCars, CourierCar courierCarX, String[] values){
        for(int i = 0 ; i < values.length ; i++){
            carControlSwitch(i, courierCarX, values[i]);
        }
        listOfCourierCars.addCar(courierCarX);
    }

    public void switchCourierControl(ListOfCouriers listOfCouriers, ListOfCourierCars listOfCourierCars, Courier courierX, String[] values){
        for(int i = 0 ; i < values.length ; i++){
            courierControlSwitch(listOfCourierCars, i, courierX, values[i]);
        }
        listOfCouriers.addCourier(courierX);
    }

    public void clientControlSwitch(int index, Client clientX, String value){
        switch(index){
            case 0:
                clientX.setId(value);
                break;
            case 1:
                clientX.setUsername(value);
                break;
            case 2:
                clientX.setPassword(value);
                break;
            case 3:
                clientX.setEmail(value);
                break;
            case 4:
                clientX.setFirstName(value);
                break;
            case 5:
                clientX.setLastName(value);
                break;
            case 6:
                clientX.setAddress(value);
                break;
            case 7:
                clientX.setPhoneNumber(value);
                break;
            case 8:
                clientX.setIsAdmin(Boolean.parseBoolean(value));
                break;
        }
    }

    public void productControlSwitch( int index, Product productX, String value){
        switch(index){
            case 0:
                productX.setId(value);
                break;
            case 1:
                productX.setName(value);
                break;
            case 2:
                productX.setPrice(Float.parseFloat(value));
                break;
            case 3:
                productX.setRating(Float.parseFloat(value));
                break;
            case 4:
                productX.setDescription(value);
                break;
        }
    }

    public void restaurantControlSwitch(Menus listOfMenus ,int index, Restaurant restaurantX, String value){
        switch(index){
            case 0:
                restaurantX.setId(value);
                break;
            case 1:
                restaurantX.setName(value);
                break;
            case 2:
                restaurantX.setPhoneNumber(value);
                break;
            case 3:
                restaurantX.setAddress(value);
                break;
            case 4:
                restaurantX.setCity(value);
                break;
            case 5:
                restaurantX.setRating(Float.parseFloat(value));
                break;
            case 6:
                Menu menuX = listOfMenus.getSpecificMenu(Integer.parseInt(value) - 1);
                restaurantX.setMenu(menuX);
                break;
        }
    }
    public void carControlSwitch( int index, CourierCar courierCarX, String value){
        switch(index){
            case 0:
                courierCarX.setId(value);
                break;
            case 1:
                courierCarX.setType(value);
                break;
            case 2:
                courierCarX.setModel(value);
                break;
            case 3:
                courierCarX.setNumber(value);
                break;
            case 4:
                courierCarX.setColor(value);
                break;
        }
    }

    public void courierControlSwitch( ListOfCourierCars listOfCourierCars,int index, Courier courierX, String value){
        switch(index){
            case 0:
                courierX.setId(value);
                break;
            case 1:
                courierX.setFirstName(value);
                break;
            case 2:
                courierX.setLastName(value);
                break;
            case 3:
                courierX.setAddress(value);
                break;
            case 4:
                courierX.setPhoneNumber(value);
                break;
            case 5:
                CourierCar carX = listOfCourierCars.getCarByIndex(Integer.parseInt(value) - 1);
                courierX.setCar(carX);
                break;
            case 6:
                if(value.equalsIgnoreCase("BUSY") || value.equalsIgnoreCase("FREE")){
                    courierX.setStatus(value);
                }
        }
    }

}
