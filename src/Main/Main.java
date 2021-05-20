package Main;

import Services.InitServices;

public class Main {

    public static void main(String[] args) {
        InitServices allServices = new InitServices();
        allServices.welcomeApp();
        allServices.getAllMenu();
    }
}
