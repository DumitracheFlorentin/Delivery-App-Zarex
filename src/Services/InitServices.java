package Services;

import Order.Cart;
import Order.ListOfOrders;
import Order.Order;
import Restaurant.ListOfRestaurants;
import Restaurant.Menus;
import Restaurant.Menu;
import Restaurant.Restaurant;
import Restaurant.Product;
import Services.Database.DbFromScript;
import User.Client;
import User.Courier;
import User.ListOfClients;
import User.ListOfCouriers;


import java.util.Scanner;

public class InitServices {
    ListOfClients listOfClients = new ListOfClients();
    ListOfRestaurants listOfRestaurants = new ListOfRestaurants();
    ListOfCouriers listOfCouriers = new ListOfCouriers();
    Menus listOfMenus = new Menus();
    StartupMenu loginOrRegMenu = new StartupMenu();
    MainMenu mainMenu = new MainMenu();
    boolean loggedUser = false;
    Client clientX = new Client();
    ListOfOrders listOfOrders = new ListOfOrders();

    // INIT A CART
    Cart cart = Cart.getInstance();

    // Init DB
    DbFromScript firstDBConnection = new DbFromScript();


    public void welcomeApp(){
        // Init DB
        firstDBConnection.getClientsFromSimpleDB(listOfClients);
        firstDBConnection.getRestaurantsFromSimpleDB(listOfRestaurants);
        firstDBConnection.getMenuFromSimpleDB(listOfMenus);
        firstDBConnection.getCouriersFromSimpleDB(listOfCouriers);

        // Init LOG IN & REGISTER SYSTEM
        String option = "";
        loginOrRegMenu.welcomeMessage();
        option = loginOrRegMenu.formMenu();

        if(option.equalsIgnoreCase("1")){
            loggedUser = loginOrRegMenu.loginMenu(listOfClients, clientX);
        } else if(option.equalsIgnoreCase("2")){
            loginOrRegMenu.signupMenu(listOfClients);
            System.out.println("You have successfully created an account!");
            loggedUser = loginOrRegMenu.loginMenu(listOfClients, clientX);
        }
    }

    public void getAllMenu(){
        Scanner optionInput = new Scanner(System.in);

        if(loggedUser){
            System.out.println("Success!");
            mainMenu.showMenu(clientX);
            System.out.println();
            System.out.print("Your option: ");
            String optionIn = optionInput.nextLine();

            Scanner secOptionInput = new Scanner(System.in);

            while(!optionIn.equalsIgnoreCase("0")){
                if(optionIn.equalsIgnoreCase("1")){

                    mainMenu.seeProfile(clientX);
                    System.out.println();
                    System.out.println("Options:");
                    System.out.println("1. Edit your data");
                    System.out.println("2. Back to menu");
                    System.out.print("Your option: ");
                    String secOptionIn = secOptionInput.nextLine();

                    if(secOptionIn.equalsIgnoreCase("1")){
                        mainMenu.editPersonalInfo(clientX);
                    }else if(secOptionIn.equalsIgnoreCase("2")){
                        mainMenu.showMenu(clientX);
                        System.out.println();
                        System.out.print("Your option: ");
                        optionIn = optionInput.nextLine();
                    }

                } else if (optionIn.equalsIgnoreCase("2")) {
                    Scanner restaurantInput = new Scanner(System.in);

                    if(clientX.getFirstName().equalsIgnoreCase("") && clientX.getLastName().equalsIgnoreCase("") && clientX.getAddress().equalsIgnoreCase("") &&
                            clientX.getPhoneNumber().equalsIgnoreCase("")){
                        System.out.println("It looks like you just registered your account! Let's setup your private informations first!");

                        mainMenu.editPersonalInfo(clientX);
                        System.out.println("Now you can come back to make an order!");
                    } else {
                        System.out.println("Choose a restaurant: ");
                        System.out.println();
                        int i = 0;
                        for(i = 0 ; i < listOfRestaurants.sizeOfList() ; i++){
                            System.out.println((i+1) + ". " + listOfRestaurants.getRestaurantByIndex(i));
                        }
                        System.out.println((i+1) + ". Back to menu");
                        System.out.print("Your option: ");
                        int restaurantIn = restaurantInput.nextInt();

                        while(restaurantIn < 0 || restaurantIn > i + 1){
                            System.out.println("Something went wrong! Try another value.");
                            restaurantIn = restaurantInput.nextInt();
                        }

                        if(restaurantIn > 0 && restaurantIn <= i){
                            Restaurant specRestaurant = listOfRestaurants.getSpecificRestaurant(restaurantIn - 1);

                            System.out.println("Welcome to " + listOfRestaurants.getRestaurantName(restaurantIn) + " restaurant!");
                            System.out.println();
                            System.out.println("Our menu: ");
                            for(int j = 0 ; j < listOfRestaurants.getRestaurantByIndex(restaurantIn - 1).getMenu().sizeOfList() ; j++){
                                System.out.println((j+1) + ". " + listOfRestaurants.getRestaurantByIndex(restaurantIn - 1).getMenu().getSpecificProductFromMenu(j));
                            }

                            System.out.println("Cart: ");
                            System.out.println("NOTE: To complete the command enter the index -1 ");

                            boolean cartItems = true;

                            Scanner cartInput = new Scanner(System.in);
                            Scanner qtyInput = new Scanner(System.in);

                            while(cartItems){
                                System.out.print("Add product according to index: ");
                                int cartIn = cartInput.nextInt();
                                if(cartIn == -1){
                                    cartItems = false;
                                } else if(cartIn > 0) {
                                    System.out.print("Add quantity for the selected product: ");
                                    int qtyIn = qtyInput.nextInt();
                                    if(qtyIn <= 0) {
                                        System.out.println("Quantity unavailable!");
                                    } else {
                                        Product prodSelected =  specRestaurant.getMenu().getListOfProducts().get(cartIn - 1);
                                        cart.addFoodToCart(prodSelected, qtyIn);
                                    }
                                } else {
                                    System.out.println("Index unavailable! Try again...");
                                }
                            }
                            System.out.println("Do you want to make the order? (Y/N)");
                            Scanner orderInput = new Scanner(System.in);
                            String orderIn = orderInput.nextLine();
                            boolean checked = false;

                            while(!checked){
                                if(orderIn.equalsIgnoreCase("y") || orderIn.equalsIgnoreCase("Y")){
                                    System.out.println("--------CHECKOUT--------");
                                    System.out.println();
                                    System.out.println("Restaurant Name: " + specRestaurant.getName());
                                    System.out.println("Your Address: " + clientX.getAddress());
                                    System.out.println("Products: ");
                                    cart.showCart();
                                    System.out.print("Total Amount: ");
                                    cart.getTotalPrice();

                                    Order orderX = new Order(specRestaurant.getId(), clientX.getUsername(), cart);

                                    listOfOrders.addOrder(orderX);

                                    checked = true;
                                } else if(orderIn.equalsIgnoreCase("n") || orderIn.equalsIgnoreCase("N")){
                                    System.out.println("You will be redirected to the main menu!");
                                    checked = true;
                                }else{
                                    System.out.println("The value does not exist! Try anything else!");
                                    orderIn = orderInput.nextLine();
                                }
                            }
                        }
                    }
                } else if(optionIn.equalsIgnoreCase("3")){
                    mainMenu.seeAllTheRestaurants(listOfRestaurants);
                    System.out.println();
                    System.out.println("Options:");
                    System.out.println("1. Back to menu");
                    if(clientX.getIsAdmin()){
                        System.out.println("2. Add restaurant");
                        System.out.println("3. Delete restaurant");
                    }
                    System.out.print("Your option: ");
                    String secOptionIn = secOptionInput.nextLine();

                    if(secOptionIn.equalsIgnoreCase("1")){
                        mainMenu.showMenu(clientX);
                        System.out.println();
                        System.out.print("Your option: ");
                        optionIn = optionInput.nextLine();
                    } else if(secOptionIn.equalsIgnoreCase("2") && clientX.getIsAdmin()){
                        Scanner nameInput = new Scanner(System.in);
                        Scanner addressInput = new Scanner(System.in);
                        Scanner phoneNumberInput = new Scanner(System.in);
                        Scanner cityInput = new Scanner(System.in);
                        Scanner ratingInput = new Scanner(System.in);
                        Scanner numberMenuInput = new Scanner(System.in);

                        System.out.println("Firstly, you need to complete some informations about the new restaurant!");
                        System.out.print("Name: ");
                        String nameIn = nameInput.nextLine();
                        System.out.print("Address: ");
                        String addressIn = addressInput.nextLine();
                        System.out.print("Phone number: ");
                        String phoneNumberIn = phoneNumberInput.nextLine();
                        System.out.print("City: ");
                        String cityIn = cityInput.nextLine();
                        System.out.print("Rating: ");
                        Float ratingIn = ratingInput.nextFloat();
                        System.out.println("Create a menu: ");
                        System.out.print("How many products do you want to have in menu?: ");

                        Menu menuX = new Menu();

                        int numberMenuIn = numberMenuInput.nextInt();
                        for(int i = 0 ; i < numberMenuIn ; i++){
                            Scanner nameProductInput = new Scanner(System.in);
                            Scanner priceProductInput = new Scanner(System.in);
                            Scanner ratingProductInput = new Scanner(System.in);
                            Scanner descriptionProductInput = new Scanner(System.in);

                            System.out.println("Product number " + (i+1));
                            System.out.print("Name: " );
                            String nameProductIn = nameProductInput.nextLine();
                            System.out.print("Price: " );
                            Float priceProductIn = priceProductInput.nextFloat();
                            System.out.print("Rating: " );
                            Float ratingProductIn = ratingProductInput.nextFloat();
                            System.out.print("Description: " );
                            String descriptionProductIn = descriptionProductInput.nextLine();
                            System.out.println();

                            Product productX = new Product(nameProductIn, priceProductIn, ratingProductIn, descriptionProductIn);
                            menuX.addProduct(productX);
                        }

                        Restaurant newRestaurant = new Restaurant(nameIn, phoneNumberIn, addressIn, cityIn, ratingIn, menuX);
                        listOfRestaurants.addRestaurant(newRestaurant);

                    } else if(secOptionIn.equalsIgnoreCase("3") && clientX.getIsAdmin()){
                        Scanner indexOfDeletedItem = new Scanner(System.in);

                        mainMenu.seeAllTheRestaurants(listOfRestaurants);
                        System.out.println("0. Back to menu");
                        System.out.println();
                        System.out.print("Your option:");
                        int deletedItem = indexOfDeletedItem.nextInt();
                        if(deletedItem > 0 && deletedItem < listOfRestaurants.sizeOfList()){
                            listOfRestaurants.deleteSpecificItem(deletedItem - 1);
                        }else {
                            mainMenu.errorWrongOption(mainMenu, clientX, optionIn, optionInput);
                        }

                    } else {
                        mainMenu.errorWrongOption(mainMenu, clientX, optionIn, optionInput);
                    }
                } else if(optionIn.equalsIgnoreCase("4")){
                    Scanner cityInput = new Scanner(System.in);
                    System.out.print("Write a city: ");
                    String cityIn = cityInput.nextLine();
                    int t = 0;
                    for(int i = 0 ; i < listOfRestaurants.sizeOfList() ; i++){
                        if(listOfRestaurants.getRestaurantByIndex(i).getCity().equalsIgnoreCase(cityIn)){
                            t++;
                            System.out.println(t + ". " + listOfRestaurants.getRestaurantByIndex(i).getName());
                            mainMenu.goBackToMenu(mainMenu, clientX, optionIn, optionInput);
                        }
                    }
                    if(t == 0){
                        System.out.println("The city maybe does not exit or we cannot find any restaurant in it!");
                        mainMenu.goBackToMenu(mainMenu, clientX, optionIn, optionInput);
                    }
                } else if(optionIn.equalsIgnoreCase("6") && clientX.getIsAdmin()){
                    System.out.println("Registrate a new courier");
                    System.out.println();

                    Courier courierX = new Courier();

                    mainMenu.addNewCourier(courierX);
                    listOfCouriers.addCourier(courierX);

                    System.out.println("Courier registered!");

                } else if(optionIn.equalsIgnoreCase("8") && clientX.getIsAdmin()){
                    for(int i = 0 ; i < listOfCouriers.getSizeOfList() ; i++){
                        System.out.println(listOfCouriers.getCourierByIndex(i));
                    }
                    mainMenu.goBackToMenu(mainMenu, clientX, optionIn, optionInput);
                } else if(optionIn.equalsIgnoreCase("9") && clientX.getIsAdmin()){
                    mainMenu.deleteSpecificCourier(listOfCouriers);
                } else if (optionIn.equalsIgnoreCase("10") && clientX.getIsAdmin()){
                    mainMenu.deleteSpecificClient(listOfClients);
                }

                mainMenu.showMenu(clientX);
                System.out.println();
                System.out.print("Your option: ");
                optionIn = optionInput.nextLine();
            }
        }
    }

}
