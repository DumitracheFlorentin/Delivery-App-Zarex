package Services;

import User.Client;
import User.ListOfClients;

public class InitServices {
    ListOfClients listOfClients = new ListOfClients();
    StartupMenu loginOrRegMenu = new StartupMenu();


    public void welcomeApp(){
        String option = "";
        boolean loggedUser = false;
        Client clientX = new Client();
        loginOrRegMenu.welcomeMessage();
        option = loginOrRegMenu.formMenu();

        if(option.equalsIgnoreCase("1")){
            loggedUser = loginOrRegMenu.loginMenu(listOfClients, clientX);
        } else if(option.equalsIgnoreCase("2")){
            loginOrRegMenu.signupMenu(listOfClients);
            System.out.println("You have successfully created an account!");
            loggedUser = loginOrRegMenu.loginMenu(listOfClients, clientX);
        }
        if(loggedUser){
            System.out.println("Success!");
        }


    }
}
