package Services;

import Cart.Cart;
import Order.ListOfOrders;
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
    // INIT LIST OF CLIENTS
    ListOfClients listOfClients = new ListOfClients();

    // INIT LIST OF RESTAURANTS
    ListOfRestaurants listOfRestaurants = new ListOfRestaurants();

    // INIT LIST OF COURIERS
    ListOfCouriers listOfCouriers = new ListOfCouriers();

    // INIT LIST OF MENUS
    Menus listOfMenus = new Menus();

    // INIT LIST OF ORDERS
    ListOfOrders listOfOrders = new ListOfOrders();

    // INIT A CART
    Cart cart = Cart.getInstance();

    // Init DB
    DbFromScript firstDBConnection = new DbFromScript();

    // METHODS
    StartupMenu loginOrRegMenu = new StartupMenu();
    MenuMethods methodsMenu = new MenuMethods();
    CartMethods cartMethods = new CartMethods();
    CheckoutMethods checkoutMethods = new CheckoutMethods();

    // VARIABLES
    boolean loggedUser = false;
    Client clientX = new Client();


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
            methodsMenu.showMenu(clientX);
            System.out.println();
            System.out.print("Your option: ");
            String optionIn = optionInput.nextLine();

            Scanner secOptionInput = new Scanner(System.in);

            while(!optionIn.equalsIgnoreCase("0")){
                if(optionIn.equalsIgnoreCase("1")){

                    methodsMenu.seeProfile(clientX);

                    System.out.println();
                    System.out.println("Options:");
                    System.out.println("1. Edit your data");
                    System.out.println("2. Back to menu");
                    System.out.print("Your option: ");

                    String secOptionIn = secOptionInput.nextLine();

                    if(secOptionIn.equalsIgnoreCase("1")){
                        methodsMenu.editPersonalInfo(clientX);
                    }else if(secOptionIn.equalsIgnoreCase("2")){
                        methodsMenu.showMenu(clientX);
                        System.out.println();
                        System.out.print("Your option: ");
                        optionIn = optionInput.nextLine();
                    }

                } else if (optionIn.equalsIgnoreCase("2")) {
                    Scanner restaurantInput = new Scanner(System.in);

                    if(clientX.getFirstName().equalsIgnoreCase("") && clientX.getLastName().equalsIgnoreCase("") && clientX.getAddress().equalsIgnoreCase("") &&
                            clientX.getPhoneNumber().equalsIgnoreCase("")){
                        System.out.println("It looks like you just registered your account! Let's setup your private informations first!");

                        methodsMenu.editPersonalInfo(clientX);

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
                            cartMethods.initCart(listOfRestaurants.getRestaurantName(restaurantIn), listOfRestaurants, restaurantIn );

                            boolean cartItems = true;

                            // ADD ITEM TO CART
                            cartMethods.addProductsToCart(cartItems, listOfRestaurants.getRestaurantByIndex(restaurantIn - 1).getMenu().sizeOfList(), specRestaurant, cart);

                            // CHECKOUT
                            checkoutMethods.checkout(listOfCouriers, listOfOrders, specRestaurant, clientX, cart);
                        }
                    }
                } else if(optionIn.equalsIgnoreCase("3")){
                    methodsMenu.seeAllTheRestaurants(listOfRestaurants);
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
                        methodsMenu.showMenu(clientX);
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

                        methodsMenu.seeAllTheRestaurants(listOfRestaurants);
                        System.out.println("0. Back to menu");
                        System.out.println();
                        System.out.print("Your option:");
                        int deletedItem = indexOfDeletedItem.nextInt();
                        if(deletedItem > 0 && deletedItem < listOfRestaurants.sizeOfList()){
                            listOfRestaurants.deleteSpecificItem(deletedItem - 1);
                        }else {
                            methodsMenu.errorWrongOption(methodsMenu, clientX, optionIn, optionInput);
                        }

                    } else {
                        methodsMenu.errorWrongOption(methodsMenu, clientX, optionIn, optionInput);
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
                            methodsMenu.goBackToMenu(methodsMenu, clientX, optionIn, optionInput);
                        }
                    }
                    if(t == 0){
                        System.out.println("The city maybe does not exit or we cannot find any restaurant in it!");
                        methodsMenu.goBackToMenu(methodsMenu, clientX, optionIn, optionInput);
                    }
                } else if(optionIn.equalsIgnoreCase("5")){
                    int i = 0;
                    for (i = 0 ; i < listOfOrders.sizeOfList() ; i++){
                        if(listOfOrders.getOrderByIndex(i).getClientUsername().equalsIgnoreCase(clientX.getUsername())){
                            System.out.println(listOfOrders.getOrderByIndex(i));
                        }
                    }
                    if(i > 0){
                        methodsMenu.goBackToMenu(methodsMenu, clientX, optionIn, optionInput);
                    } else {
                        System.out.println("At this time, there are no orders!");
                        methodsMenu.goBackToMenu(methodsMenu, clientX, optionIn, optionInput);
                    }


                } else if(optionIn.equalsIgnoreCase("6") && clientX.getIsAdmin()){
                    System.out.println("Registrate a new courier");
                    System.out.println();

                    Courier courierX = new Courier();

                    methodsMenu.addNewCourier(courierX);
                    listOfCouriers.addCourier(courierX);

                    System.out.println("Courier registered!");

                } else if(optionIn.equalsIgnoreCase("7") && clientX.getIsAdmin()){
                    int i = 0;
                    for (i = 0 ; i < listOfOrders.sizeOfList() ; i++){
                        System.out.println((i+1) + ". " + listOfOrders.getOrderByIndex(i));
                    }

                    if(i > 0){
                        methodsMenu.goBackToMenu(methodsMenu, clientX, optionIn, optionInput);
                    } else {
                        System.out.println("At this time, there are no orders!");
                        methodsMenu.goBackToMenu(methodsMenu, clientX, optionIn, optionInput);
                    }
                } else if(optionIn.equalsIgnoreCase("8") && clientX.getIsAdmin()){
                    for(int i = 0 ; i < listOfCouriers.getSizeOfList() ; i++){
                        System.out.println(listOfCouriers.getCourierByIndex(i));
                    }
                    methodsMenu.goBackToMenu(methodsMenu, clientX, optionIn, optionInput);
                } else if(optionIn.equalsIgnoreCase("9") && clientX.getIsAdmin()){
                    methodsMenu.deleteSpecificCourier(listOfCouriers);
                } else if (optionIn.equalsIgnoreCase("10") && clientX.getIsAdmin()){
                    methodsMenu.deleteSpecificClient(listOfClients);
                } else {
                    System.out.println("Wrong option! You will be redirected to menu!");
                    System.out.println();
                }

                methodsMenu.showMenu(clientX);
                System.out.println();
                System.out.print("Your option: ");
                optionIn = optionInput.nextLine();
            }
        }
    }

}
