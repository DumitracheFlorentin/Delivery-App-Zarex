package Services;

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
