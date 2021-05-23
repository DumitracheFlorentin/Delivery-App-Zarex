package Services.Database;

import Restaurant.Menu;
import Restaurant.Product;
import Restaurant.Restaurant;
import Restaurant.ListOfRestaurants;
import Restaurant.Menus;
import User.*;

public class DbFromScript {
    // CLIENTS
    Client client1 = new Client("Florentin", "FlorentinPW123", "florentin@ljb.ro", true);
    Client client2 = new Client("Alexandru", "AlexandruPW123", "AlexandruDumitrescu@gmail.com");
    Client client3 = new Client("Mihaela", "MihaelaPW123", "MihaelaCristescu@yahoo.ro");
    Client client4 = new Client("Teodor", "TeodorPW123", "TeodorDunescu@gmail.com");

    // CARS
    CourierCar car1 = new CourierCar("BMW", "Seria3", "B99XMX", "RED");
    CourierCar car2 = new CourierCar("Audi", "A4", "B999COL", "BLUE");

    // COURIERS
    Courier courier1 = new Courier("Mihai", "Batan", "Batailor, Bl. C2, Sc. 2", "0735171671", car1, "BUSY");
    Courier courier2 = new Courier("Denis", "Mutu", "Aviatorilor, nr. 2",  "07151716381",car2);

    public ListOfCouriers getCouriersFromSimpleDB(ListOfCouriers listOfCouriers){
        listOfCouriers.addCourier(courier1);
        listOfCouriers.addCourier(courier2);

        return listOfCouriers;
    }


    public ListOfClients getClientsFromSimpleDB(ListOfClients listOfClients){
        listOfClients.addClient(client1);
        listOfClients.addClient(client2);
        listOfClients.addClient(client3);
        listOfClients.addClient(client4);
        return listOfClients;
    }

    public Menus getMenuFromSimpleDB(Menus listOfMenus){
        // PRODUCTS
        Product product1 = new Product("Coaste de porc", 70F, 5F, "Test");
        Product product2 = new Product("Piept de pui in sos de lamaie", 50F, 4.5F, "Test");
        Product product3 = new Product("Piept de rata in sos de fructe de padure", 60F, 5F, "Test");
        Product product4 = new Product("Snitel de porc", 30F, 3.5F, "Test");
        Product product5 = new Product("Paste carbonara", 40F, 3F, "Test");
        Product product6 = new Product("Ciorba de perisoare", 15F, 4F, "Test");

        Menu menu1 = new Menu();
        menu1.addProduct(product1);
        menu1.addProduct(product5);
        menu1.addProduct(product6);

        Menu menu2 = new Menu();
        menu2.addProduct(product1);
        menu2.addProduct(product3);
        menu2.addProduct(product4);

        Menu menu3 = new Menu();
        menu3.addProduct(product2);
        menu3.addProduct(product3);
        menu3.addProduct(product6);

        listOfMenus.addMenu(menu1);
        listOfMenus.addMenu(menu2);
        listOfMenus.addMenu(menu3);

        return listOfMenus;
    }

    public ListOfRestaurants getRestaurantsFromSimpleDB(ListOfRestaurants listOfRestaurants){
        // PRODUCTS
        Product product1 = new Product("Coaste de porc", 100F, 5F, "Test");
        Product product2 = new Product("Piept de pui in sos de lamaie", 50F, 4.5F, "Test");
        Product product3 = new Product("Piept de rata in sos de fructe de padure", 60F, 5F, "Test");
        Product product4 = new Product("Snitel de porc", 30F, 3.5F, "Test");
        Product product5 = new Product("Paste carbonara", 400F, 3F, "Test");
        Product product6 = new Product("Ciorba de perisoare", 15F, 4F, "Test");

        Menu menu1 = new Menu();
        menu1.addProduct(product1);
        menu1.addProduct(product5);
        menu1.addProduct(product6);

        Menu menu2 = new Menu();
        menu2.addProduct(product1);
        menu2.addProduct(product3);
        menu2.addProduct(product4);

        Menu menu3 = new Menu();
        menu3.addProduct(product2);
        menu3.addProduct(product3);
        menu3.addProduct(product6);

        // RESTAURANTS
        Restaurant restaurant1 = new Restaurant("Tokyo","Bucurestilor, nr.2", "0725181617" , "Targoviste",4.5F ,menu1);
        Restaurant restaurant2 = new Restaurant("Lengo", "Bucurestilor, nr.5", "0766721812", "Bucuresti",5F, menu2);
        Restaurant restaurant3 = new Restaurant("Alexo", "Aviatorilor, nr.12", "0718371971", "Craiova",3.5F, menu3);

        listOfRestaurants.addRestaurant(restaurant1);
        listOfRestaurants.addRestaurant(restaurant2);
        listOfRestaurants.addRestaurant(restaurant3);

        return listOfRestaurants;
    }

}
