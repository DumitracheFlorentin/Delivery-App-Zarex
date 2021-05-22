package Main;

import Services.Database.DbFromScript;
import Services.InitServices;

public class Main {

    public static void main(String[] args) {

        // Init all services
        InitServices allServices = new InitServices();
        allServices.welcomeApp();
        allServices.getAllMenu();
    }
}
