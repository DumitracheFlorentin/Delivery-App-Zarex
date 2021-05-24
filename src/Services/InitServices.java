package Services;

import Cart.Cart;
import Order.ListOfOrders;
import Order.ListOfProducts;
import Restaurant.ListOfRestaurants;
import Restaurant.Menus;
import Restaurant.Restaurant;
import Restaurant.ListOfMenus;
import Services.Database.DbFromCSVFiles;
import Services.Database.DbFromMySQL;
import User.*;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InitServices {
    // INIT LIST OF CLIENTS
    ListOfClients listOfClients = new ListOfClients();

    // INIT LIST OF PRODUCTS
    ListOfProducts listOfProducts = new ListOfProducts();

    // INIT LIST OF RESTAURANTS
    ListOfRestaurants listOfRestaurants = new ListOfRestaurants();

    // INIT LIST OF COURIERS
    ListOfCouriers listOfCouriers = new ListOfCouriers();

    // INIT LIST OF MENUS
    Menus listOfMenus = new Menus();

    // INIT LIST OF BRIDGETABLEMENU
    ListOfMenus listOfBridgeMenus = new ListOfMenus();

    // INIT LIST OF ORDERS
    ListOfOrders listOfOrders = new ListOfOrders();

    // INIT LIST OF COURIER CARS
    ListOfCourierCars listOfCourierCars = new ListOfCourierCars();

    // INIT A CART
    Cart cart = Cart.getInstance();

    // Init DB
    // DbFromScript firstDBConnection = new DbFromScript();
    //DbFromCSVFiles secondDBConnection = new DbFromCSVFiles();
     DbFromMySQL mySqlMethods = new DbFromMySQL();

    // METHODS
    StartupMenu loginOrRegMenu = new StartupMenu();
    MenuMethods methodsMenu = new MenuMethods();
    CartMethods cartMethods = new CartMethods();
    CheckoutMethods checkoutMethods = new CheckoutMethods();
    GetMenus getMenus = new GetMenus();


    // VARIABLES
    boolean loggedUser = false;
    Client clientX = new Client();

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());


    public void welcomeApp(){
        // Init DB - Etapa 1
        // firstDBConnection.getClientsFromSimpleDB(listOfClients);
        // firstDBConnection.getRestaurantsFromSimpleDB(listOfRestaurants);
        // firstDBConnection.getMenuFromSimpleDB(listOfMenus);
        // firstDBConnection.getCouriersFromSimpleDB(listOfCouriers);

        // Init DB - Etapa 2
        //secondDBConnection.readClientsFromFile(listOfClients);
        //secondDBConnection.readProductsFromFile(listOfProducts);
        //secondDBConnection.readMenuFromFile(listOfMenus, listOfProducts);
        //secondDBConnection.readRestaurantsFromFile(listOfRestaurants, listOfMenus);
        //secondDBConnection.readCarsFromFile(listOfCourierCars);
        //secondDBConnection.readCouriersFromFile(listOfCouriers, listOfCourierCars);

        // Init DB - Etapa 3
        mySqlMethods.readClients(listOfClients);
        mySqlMethods.readPrivateInfo(listOfClients);
        mySqlMethods.readProducts(listOfProducts);
        mySqlMethods.readCourier(listOfCouriers);
        mySqlMethods.readPrivateInfoCouriers(listOfCouriers);
        mySqlMethods.readCourierCar(listOfCourierCars);
        mySqlMethods.readMenu(listOfBridgeMenus);
        mySqlMethods.readRestaurant(listOfRestaurants);
        getMenus.getMenus(listOfRestaurants, listOfBridgeMenus, listOfProducts);
        mySqlMethods.readOrder(listOfOrders);

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
                    for(int i = 0 ; i < listOfClients.sizeOfList(); i++){
                        if(clientX.getUsername().equalsIgnoreCase(listOfClients.getSpecificClient(i).getUsername())){
                            methodsMenu.seeProfile(listOfClients.getSpecificClient(i), listOfClients);

                            System.out.println();
                            System.out.println("Options:");
                            System.out.println("1. Edit your data");
                            System.out.println("2. Back to menu");
                            System.out.print("Your option: ");

                            String secOptionIn = secOptionInput.nextLine();

                            if(secOptionIn.equalsIgnoreCase("1")){
                                methodsMenu.editPersonalInfo(listOfClients.getSpecificClient(i));
                                mySqlMethods.updatePrivateInfo(listOfClients.getSpecificClient(i));

                                try{
                                    File file = new File("db_clients.csv");
                                    FileWriter fr = new FileWriter(file);
                                    BufferedWriter buffWriter = new BufferedWriter(fr);
                                    buffWriter.close();
                                    fr.close();

                                } catch(FileNotFoundException e){
                                    System.out.println("File Not Found!");
                                } catch(IOException e){
                                    e.printStackTrace();
                                }


//                                try{
//                                    File file = new File("db_clients.csv");
//                                    FileWriter fr = new FileWriter(file, true);
//                                    BufferedWriter buffWriter = new BufferedWriter(fr);
//
//                                    for(int j = 0 ; j < listOfClients.sizeOfList() ; j++){
//                                        Client clientX = listOfClients.getSpecificClient(i);
//
//
//                                        if(clientX.getUsername().equalsIgnoreCase(listOfClients.getSpecificClient(i).getUsername())){
//                                            buffWriter.write(listOfClients.getSpecificClient(j).getPrivateInfoId() + "," + listOfClients.getSpecificClient(j).getUsername() + "," + listOfClients.getSpecificClient(j).getPassword() + "," + listOfClients.getSpecificClient(j).getEmail() + "," + listOfClients.getSpecificClient(j).getFirstName() + "," + listOfClients.getSpecificClient(j).getLastName() + "," + listOfClients.getSpecificClient(j).getAddress() + "," + listOfClients.getSpecificClient(j).getPhoneNumber() + "," + listOfClients.getSpecificClient(j).getIsAdmin());
//                                        } else {
//                                            buffWriter.write(clientX.getPrivateInfoId() + "," + clientX.getUsername() + "," + clientX.getPassword() + "," + clientX.getEmail() + "," + clientX.getFirstName() + "," + clientX.getLastName() + "," + clientX.getAddress() + "," + clientX.getPhoneNumber() + "," + clientX.getIsAdmin());
//                                        }
//
//                                        buffWriter.newLine();
//                                    }
//                                    buffWriter.close();
//                                    fr.close();
//
//                                } catch(FileNotFoundException e){
//                                    System.out.println("File Not Found!");
//                                } catch(IOException e){
//                                    e.printStackTrace();
//                                }

                                try{
                                    File file = new File("log.csv");
                                    FileWriter fr = new FileWriter(file, true);
                                    BufferedWriter logWriter = new BufferedWriter(fr);
                                    logWriter.write("An user with username " + listOfClients.getSpecificClient(i).getUsername() + " edited his personal informations! " + formatter.format(date));
                                    logWriter.newLine();
                                    logWriter.close();
                                    fr.close();
                                } catch(IOException e) {
                                    e.printStackTrace();
                                }

                            }else if(secOptionIn.equalsIgnoreCase("2")){
                                methodsMenu.showMenu(listOfClients.getSpecificClient(i));
                                System.out.println();
                                System.out.print("Your option: ");
                                optionIn = optionInput.nextLine();
                            }
                        }
                    }
                } else if (optionIn.equalsIgnoreCase("2")) {
                    Scanner restaurantInput = new Scanner(System.in);

                    for(int i = 0 ; i < listOfClients.sizeOfList() ; i++){
                        if(clientX.getUsername().equalsIgnoreCase(listOfClients.getSpecificClient(i).getUsername())){
                            if(listOfClients.getSpecificClient(i).getFirstName().equalsIgnoreCase("") && listOfClients.getSpecificClient(i).getLastName().equalsIgnoreCase("") && listOfClients.getSpecificClient(i).getAddress().equalsIgnoreCase("") &&
                                    listOfClients.getSpecificClient(i).getPhoneNumber().equalsIgnoreCase("")){
                                System.out.println("It looks like you just registered your account! Let's setup your private informations first!");

                                methodsMenu.editPersonalInfo(listOfClients.getSpecificClient(i));
                                mySqlMethods.createPrivateInfo(listOfClients.getSpecificClient(i));

//                                try{
//                                    File file = new File("db_clients.csv");
//                                    FileWriter fr = new FileWriter(file);
//                                    BufferedWriter buffWriter = new BufferedWriter(fr);
//                                    buffWriter.close();
//                                    fr.close();
//
//                                } catch(FileNotFoundException e){
//                                    System.out.println("File Not Found!");
//                                } catch(IOException e){
//                                    e.printStackTrace();
//                                }


//                                try{
//                                    File file = new File("db_clients.csv");
//                                    FileWriter fr = new FileWriter(file, true);
//                                    BufferedWriter buffWriter = new BufferedWriter(fr);
//
//                                    for(int j = 0 ; j < listOfClients.sizeOfList() ; j++){
//                                        Client clientX = listOfClients.getSpecificClient(i);
//
//
//                                        if(clientX.getUsername().equalsIgnoreCase(listOfClients.getSpecificClient(i).getUsername())){
//                                            buffWriter.write(listOfClients.getSpecificClient(j).getPrivateInfoId() + "," + listOfClients.getSpecificClient(j).getUsername() + "," + listOfClients.getSpecificClient(j).getPassword() + "," + listOfClients.getSpecificClient(j).getEmail() + "," + listOfClients.getSpecificClient(j).getFirstName() + "," + listOfClients.getSpecificClient(j).getLastName() + "," + listOfClients.getSpecificClient(j).getAddress() + "," + listOfClients.getSpecificClient(j).getPhoneNumber() + "," + listOfClients.getSpecificClient(j).getIsAdmin());
//                                        } else {
//                                            buffWriter.write(clientX.getPrivateInfoId() + "," + clientX.getUsername() + "," + clientX.getPassword() + "," + clientX.getEmail() + "," + clientX.getFirstName() + "," + clientX.getLastName() + "," + clientX.getAddress() + "," + clientX.getPhoneNumber() + "," + clientX.getIsAdmin());
//                                        }
//
//
//                                        buffWriter.newLine();
//                                    }
//                                    buffWriter.close();
//                                    fr.close();
//
//                                } catch(FileNotFoundException e){
//                                    System.out.println("File Not Found!");
//                                } catch(IOException e){
//                                    e.printStackTrace();
//                                }

                                try{
                                    File file = new File("log.csv");
                                    FileWriter fr = new FileWriter(file, true);
                                    BufferedWriter logWriter = new BufferedWriter(fr);
                                    logWriter.write("An user with username " + listOfClients.getSpecificClient(i).getUsername() + " edited his personal informations! " + formatter.format(date));
                                    logWriter.newLine();
                                    logWriter.close();
                                    fr.close();
                                } catch(IOException e) {
                                    e.printStackTrace();
                                }

                                System.out.println("Now you can come back to make an order!");
                            } else {
                                System.out.println("Choose a restaurant: ");
                                System.out.println();

                                int j = 0;
                                for(j = 0 ; j < listOfRestaurants.sizeOfList() ; j++){
                                    System.out.println((j+1) + ". " + listOfRestaurants.getRestaurantByIndex(j));
                                }

                                System.out.println((j+1) + ". Back to menu");
                                System.out.print("Your option: ");
                                int restaurantIn = restaurantInput.nextInt();

                                while(restaurantIn < 0 || restaurantIn > j + 1){
                                    System.out.println("Something went wrong! Try another value.");
                                    restaurantIn = restaurantInput.nextInt();
                                }

                                if(restaurantIn > 0 && restaurantIn <= j){
                                    Restaurant specRestaurant = listOfRestaurants.getSpecificRestaurant(restaurantIn - 1);
                                    cartMethods.initCart(listOfRestaurants.getRestaurantName(restaurantIn), listOfRestaurants, restaurantIn);

                                    boolean cartItems = true;

                                    // ADD ITEM TO CART
                                    cartMethods.addProductsToCart(cartItems, listOfRestaurants.getRestaurantByIndex(restaurantIn - 1).getMenu().sizeOfList(), specRestaurant, cart);

                                    // CHECKOUT
                                    checkoutMethods.checkout(listOfCouriers, listOfOrders, specRestaurant, listOfClients.getSpecificClient(i), cart);
                                }
                            }
                        }
                    }


                } else if(optionIn.equalsIgnoreCase("3")){
                    methodsMenu.seeAllTheRestaurants(listOfRestaurants);
                    System.out.println();
                    System.out.println("Options:");
                    System.out.println("1. Back to menu");
                    System.out.print("Your option: ");
                    String secOptionIn = secOptionInput.nextLine();

                    if(secOptionIn.equalsIgnoreCase("1")) {
                        methodsMenu.showMenu(clientX);
                        System.out.println();
                        System.out.print("Your option: ");
                        optionIn = optionInput.nextLine();
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

                        }
                    }


                    if(t == 0){
                        System.out.println("The city maybe does not exit or we cannot find any restaurant in it!");
                        methodsMenu.goBackToMenu(methodsMenu, clientX, optionIn, optionInput);
                    }else{
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

                    methodsMenu.addNewCourier(courierX, listOfCourierCars);
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

//                    try{
//                        File file = new File("db_couriers.csv");
//                        FileWriter fr = new FileWriter(file);
//                        BufferedWriter buffWriter = new BufferedWriter(fr);
//                        buffWriter.close();
//                        fr.close();
//
//                    } catch(FileNotFoundException e){
//                        System.out.println("File Not Found!");
//                    } catch(IOException e){
//                        e.printStackTrace();
//                    }

//                    try{
//                        File file = new File("db_couriers.csv");
//                        FileWriter fr = new FileWriter(file,true);
//                        BufferedWriter buffWriter = new BufferedWriter(fr);
//
//                        for(int i = 0 ; i < listOfCouriers.getSizeOfList() ; i++){
//                            Courier courierX = listOfCouriers.getCourierByIndex(i);
//
//                            buffWriter.write(courierX.getPrivateInfoId() + "," + courierX.getFirstName() + "," + courierX.getLastName() + "," + courierX.getAddress() + "," + courierX.getPhoneNumber() + "," + (i+1) + "," + courierX.getStatus());
//                            buffWriter.newLine();
//                        }
//
//                        buffWriter.close();
//                        fr.close();
//
//                    } catch(FileNotFoundException e){
//                        System.out.println("File Not Found!");
//                    } catch(IOException e){
//                        e.printStackTrace();
//                    }

                } else if (optionIn.equalsIgnoreCase("10") && clientX.getIsAdmin()){
                    methodsMenu.deleteSpecificClient(listOfClients);

//                    try{
//                        File file = new File("db_clients.csv");
//                        FileWriter fr = new FileWriter(file);
//                        BufferedWriter buffWriter = new BufferedWriter(fr);
//                        buffWriter.close();
//                        fr.close();
//
//                    } catch(FileNotFoundException e){
//                        System.out.println("File Not Found!");
//                    } catch(IOException e){
//                        e.printStackTrace();
//                    }

//                    try{
//                        File file = new File("db_clients.csv");
//                        FileWriter fr = new FileWriter(file,true);
//                        BufferedWriter buffWriter = new BufferedWriter(fr);
//
//                        for(int i = 0 ; i < listOfClients.sizeOfList() ; i++){
//                            Client clientX = listOfClients.getSpecificClient(i);
//
//                            buffWriter.write(clientX.getPrivateInfoId() + "," + clientX.getUsername() + "," + clientX.getPassword() + "," + clientX.getEmail() + "," + clientX.getFirstName() + "," + clientX.getLastName() + "," + clientX.getAddress() + "," + clientX.getPhoneNumber() + "," + clientX.getIsAdmin());
//                            buffWriter.newLine();
//                        }
//
//                        buffWriter.close();
//                        fr.close();
//
//                    } catch(FileNotFoundException e){
//                        System.out.println("File Not Found!");
//                    } catch(IOException e){
//                        e.printStackTrace();
//                    }
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
