package Services.Database;

import Restaurant.Menu;
import Restaurant.Product;
import Restaurant.Restaurant;
import Restaurant.ListOfRestaurants;
import User.Client;
import User.ListOfClients;

public class DbFromScript {

    // COURIERS
//    Couriers courier1 = new Couriers("Mihai", "Mihai123", "Mihai@hau.ro",
//            "Batailor, Bl. C2, Sc. 2", "Mihai", "Batan", "0735171671",15, 7, 1998, "Volvo", "B59ADC");
//    Couriers courier2 = new Couriers("Denis", "DenisPW123", "denis@glovo.ro",
//            "Aviatorilor, nr. 2", "Denis", "Mutu", "07151716381",15, 5, 1977, "BMW", "B999COC");


    public ListOfClients getClientsFromSimpleDB(ListOfClients listOfClients){
        // CLIENTS
        Client client1 = new Client("Florentin", "FlorentinPW123", "florentin@ljb.ro");
        Client client2 = new Client("Alexandru", "AlexandruPW123", "AlexandruDumitrescu@gmail.com");
        Client client3 = new Client("Mihaela", "MihaelaPW123", "MihaelaCristescu@yahoo.ro");
        Client client4 = new Client("Teodor", "TeodorPW123", "TeodorDunescu@gmail.com");

        listOfClients.addClient(client1);
        listOfClients.addClient(client2);
        listOfClients.addClient(client3);
        listOfClients.addClient(client4);
        return listOfClients;
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
