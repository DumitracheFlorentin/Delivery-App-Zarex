package Services;

import Restaurant.ListOfRestaurants;
import Restaurant.Menu;
import Services.Database.DbFromScript;
import User.Client;
import User.ListOfClients;

import java.util.Scanner;

public class InitServices {
    ListOfClients listOfClients = new ListOfClients();
    ListOfRestaurants listOfRestaurants = new ListOfRestaurants();
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
        listOfRestaurants.showRestaurants();

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
                    System.out.print("Your option: ");
                    String secOptionIn = secOptionInput.nextLine();

                    if(secOptionIn.equalsIgnoreCase("1")){
                        mainMenu.showMenu(clientX);
                        System.out.println();
                        System.out.print("Your option: ");
                        optionIn = optionInput.nextLine();
                    } else {
                        System.out.println("Wrong option! You will be redirected to the menu!");
                        System.out.println();
                        mainMenu.showMenu(clientX);
                        System.out.println();
                        System.out.print("Your option: ");
                        optionIn = optionInput.nextLine();
                    }
                }
            }
        }
    }

}
