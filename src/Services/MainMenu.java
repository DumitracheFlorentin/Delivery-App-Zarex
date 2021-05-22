package Services;

import Restaurant.ListOfRestaurants;
import User.Client;

import java.util.Scanner;

public class MainMenu {

    public void showMenu(Client client){
        System.out.println("Hello " + client.getUsername() + "! Glad to have you back!");
        System.out.println();
        System.out.println("1. See your profile");
        System.out.println("2. Make an order");
        System.out.println("3. See all the restaurants");
        System.out.println("4. Find restaurants by city");
        System.out.println("5. See the history of your orders");
        if(client.getIsAdmin()){
            System.out.println("6. Add a courier");
            System.out.println("7. See all the orders");
            System.out.println("8. See all the couriers");
            System.out.println("9. Delete an user");
        }
        System.out.println("0. Exit");
        System.out.println();

    }

    public void seeProfile(Client client){
        System.out.println("Your info are: ");
        System.out.println("Username: " + client.getUsername());
        System.out.println("Email: " + client.getEmail());

        if(client.getFirstName().equalsIgnoreCase("") && client.getLastName().equalsIgnoreCase("") && client.getAddress().equalsIgnoreCase("") &&
        client.getPhoneNumber().equalsIgnoreCase("")){
            System.out.println("It looks like you just registered your account! Let's setup your private informations!");

            editPersonalInfo(client);

            System.out.println();
            System.out.println("Here we go! Now we will be use this data for your orders!");
            System.out.println();

            getPersonalInfo(client);
        } else {
            getPersonalInfo(client);
        }
    }

    public void seeAllTheRestaurants(ListOfRestaurants listOfRestaurants){
        for(int i = 1 ; i <= listOfRestaurants.sizeOfList() ; i++){
            System.out.println(i + ". " + listOfRestaurants.getRestaurantName(i) );
        }
    }

    public void editPersonalInfo(Client client){
        Scanner firstNameInput = new Scanner(System.in);
        Scanner lastNameInput = new Scanner(System.in);
        Scanner addressInput = new Scanner(System.in);
        Scanner phoneNumberInput = new Scanner(System.in);

        System.out.print("First Name: ");
        String firstNameIn = firstNameInput.nextLine();
        client.setFirstName(firstNameIn);
        System.out.print("Last Name: ");
        String lastNameIn = lastNameInput.nextLine();
        client.setLastName(lastNameIn);
        System.out.print("Phone Number: ");
        String phoneNumberIn = phoneNumberInput.nextLine();
        client.setPhoneNumber(phoneNumberIn);
        System.out.print("Address: ");
        String addressIn = addressInput.nextLine();
        client.setAddress(addressIn);
    }

    public void getPersonalInfo(Client client){
        System.out.println();
        System.out.println("Your personal info: ");
        System.out.println("First name: " + client.getFirstName());
        System.out.println("Last name: " + client.getLastName());
        System.out.println("Address: " + client.getAddress());
        System.out.println("Phone number: " + client.getPhoneNumber());
    }

    public void errorWrongOption(MainMenu mainMenu, Client clientX, String optionIn, Scanner optionInput){
        System.out.println("Wrong option! You will be redirected to the menu!");
        System.out.println();
        mainMenu.showMenu(clientX);
        System.out.println();
        System.out.print("Your option: ");
        optionIn = optionInput.nextLine();
    }

    public void goBackToMenu(MainMenu mainMenu, Client clientX, String optionIn, Scanner optionInput){
        System.out.println();
        System.out.print("Press ENTER to get back to the main menu: ");
        Scanner goToMenuInput = new Scanner(System.in);
        String goToMenuIn = goToMenuInput.nextLine();
        System.out.println();
        mainMenu.showMenu(clientX);
        System.out.println();
        System.out.print("Your option: ");
        optionIn = optionInput.nextLine();
    }

}
