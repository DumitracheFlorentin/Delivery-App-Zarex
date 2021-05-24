package Services;

import Restaurant.ListOfRestaurants;
import Services.Database.DbFromMySQL;
import User.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuMethods {
    DbFromMySQL mySqlMethods = new DbFromMySQL();
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());

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
            System.out.println("9. Delete courier");
            System.out.println("10. Delete user");
        }
        System.out.println("0. Exit");
        System.out.println();

    }

    public void seeProfile(Client client, ListOfClients listOfClients){
        System.out.println("Your info are: ");
        System.out.println("Username: " + client.getUsername());
        System.out.println("Email: " + client.getEmail());

        if(client.getFirstName().equalsIgnoreCase("") && client.getLastName().equalsIgnoreCase("") && client.getAddress().equalsIgnoreCase("") &&
        client.getPhoneNumber().equalsIgnoreCase("")){
            System.out.println("It looks like you just registered your account! Let's setup your private informations!");

            editPersonalInfo(client);

            mySqlMethods.createPrivateInfo(client);

            System.out.println();
            System.out.println("Here we go! Now we will be use this data for your orders!");
            System.out.println();

//            try{
//                File file = new File("db_clients.csv");
//                FileWriter fr = new FileWriter(file);
//                BufferedWriter buffWriter = new BufferedWriter(fr);
//                buffWriter.close();
//                fr.close();
//
//            } catch(FileNotFoundException e){
//                System.out.println("File Not Found!");
//            } catch(IOException e){
//                e.printStackTrace();
//            }


//            try{
//                File file = new File("db_clients.csv");
//                FileWriter fr = new FileWriter(file, true);
//                BufferedWriter buffWriter = new BufferedWriter(fr);
//
//                for(int j = 0 ; j < listOfClients.sizeOfList() ; j++){
//                    Client clientX = client;
//
//
//                    if(clientX.getUsername().equalsIgnoreCase(client.getUsername())){
//                        buffWriter.write(listOfClients.getSpecificClient(j).getPrivateInfoId() + "," + listOfClients.getSpecificClient(j).getUsername() + "," + listOfClients.getSpecificClient(j).getPassword() + "," + listOfClients.getSpecificClient(j).getEmail() + "," + listOfClients.getSpecificClient(j).getFirstName() + "," + listOfClients.getSpecificClient(j).getLastName() + "," + listOfClients.getSpecificClient(j).getAddress() + "," + listOfClients.getSpecificClient(j).getPhoneNumber() + "," + listOfClients.getSpecificClient(j).getIsAdmin());
//                    } else {
//                        buffWriter.write(clientX.getPrivateInfoId() + "," + clientX.getUsername() + "," + clientX.getPassword() + "," + clientX.getEmail() + "," + clientX.getFirstName() + "," + clientX.getLastName() + "," + clientX.getAddress() + "," + clientX.getPhoneNumber() + "," + clientX.getIsAdmin());
//                    }
//
//
//                    buffWriter.newLine();
//                }
//                buffWriter.close();
//                fr.close();
//
//            } catch(FileNotFoundException e){
//                System.out.println("File Not Found!");
//            } catch(IOException e){
//                e.printStackTrace();
//            }

            try{
                File file = new File("log.csv");
                FileWriter fr = new FileWriter(file, true);
                BufferedWriter logWriter = new BufferedWriter(fr);
                logWriter.write("An user with username " + client.getUsername() + " edited his personal informations! " + formatter.format(date));
                logWriter.newLine();
                logWriter.close();
                fr.close();
            } catch(IOException e) {
                e.printStackTrace();
            }

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

    public void addNewCourier(Courier courier, ListOfCourierCars listOfCourierCars){
        Scanner firstNameInput = new Scanner(System.in);
        Scanner lastNameInput = new Scanner(System.in);
        Scanner addressInput = new Scanner(System.in);
        Scanner phoneNumberInput = new Scanner(System.in);
        Scanner carTypeInput = new Scanner(System.in);
        Scanner carModelInput = new Scanner(System.in);
        Scanner carNumberInput = new Scanner(System.in);
        Scanner carColorInput = new Scanner(System.in);

        System.out.print("First Name: ");
        String firstNameIn = firstNameInput.nextLine();
        courier.setFirstName(firstNameIn);
        System.out.print("Last Name: ");
        String lastNameIn = lastNameInput.nextLine();
        courier.setLastName(lastNameIn);
        System.out.print("Phone Number: ");
        String phoneNumberIn = phoneNumberInput.nextLine();
        courier.setPhoneNumber(phoneNumberIn);
        System.out.print("Address: ");
        String addressIn = addressInput.nextLine();
        courier.setAddress(addressIn);
        System.out.print("Car Type: ");
        String carTypeIn = carTypeInput.nextLine();
        System.out.print("Car Model: ");
        String carModelIn = carModelInput.nextLine();
        System.out.print("Car Number: ");
        String carNumberIn = carNumberInput.nextLine();
        System.out.print("Car Color: ");
        String carColorIn = carColorInput.nextLine();
        courier.setStatus("FREE");

        mySqlMethods.createCourier(courier);
        mySqlMethods.createPrivateInfoCourier(courier);

        CourierCar carX = new CourierCar(carTypeIn, carModelIn, carNumberIn,carColorIn);
        listOfCourierCars.addCar(carX);

        mySqlMethods.createCourierCar(carX, courier);
        courier.setCar(carX);




        try{
            File file = new File("log.csv");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter logWriter = new BufferedWriter(fr);
            logWriter.write("A new courier named " + courier.getFirstName() + " " + courier.getLastName() + " was created! " + formatter.format(date));
            logWriter.newLine();
            logWriter.close();
            fr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
//
//        try{
//            File file = new File("db_cars.csv");
//            FileWriter fr = new FileWriter(file, true);
//            BufferedWriter buffWriter = new BufferedWriter(fr);
//            buffWriter.write(carX.getId() + "," + carX.getType() + "," + carX.getModel() + "," + carX.getNumber() + "," + carX.getColor());
//            buffWriter.newLine();
//            buffWriter.close();
//            fr.close();
//        } catch(FileNotFoundException e){
//            System.out.println("File Not Found!");
//        } catch(IOException e){
//            e.printStackTrace();
//        }
//
//        try{
//            File file = new File("db_couriers.csv");
//            FileWriter fr = new FileWriter(file, true);
//            BufferedWriter buffWriter = new BufferedWriter(fr);
//            buffWriter.write(courier.getPrivateInfoId() + "," + courier.getFirstName() + "," + courier.getLastName() + "," + courier.getAddress() + "," + courier.getPhoneNumber() + "," + listOfCourierCars.getSizeOfList() + "," + "FREE");
//            buffWriter.newLine();
//            buffWriter.close();
//            fr.close();
//
//        } catch(FileNotFoundException e){
//            System.out.println("File Not Found!");
//        } catch(IOException e){
//            e.printStackTrace();
//        }

    }

    public void getPersonalInfo(Client client){
        System.out.println();
        System.out.println("Your personal info: ");
        System.out.println("First name: " + client.getFirstName());
        System.out.println("Last name: " + client.getLastName());
        System.out.println("Address: " + client.getAddress());
        System.out.println("Phone number: " + client.getPhoneNumber());
    }

    public void errorWrongOption(MenuMethods mainMenu, Client clientX, String optionIn, Scanner optionInput){
        System.out.println("Wrong option! You will be redirected to the menu!");
        System.out.println();
        mainMenu.showMenu(clientX);
        System.out.println();
        System.out.print("Your option: ");
        optionIn = optionInput.nextLine();
    }

    public void redirectedToMenu(MenuMethods mainMenu, Client clientX, String optionIn, Scanner optionInput){
        System.out.println("You will be redirected to the menu!");
        System.out.println();
        mainMenu.showMenu(clientX);
        System.out.println();
        System.out.print("Your option: ");
        optionIn = optionInput.nextLine();
    }

    public void goBackToMenu(MenuMethods mainMenu, Client clientX, String optionIn, Scanner optionInput){
        System.out.println();
        System.out.print("Press ENTER to get back to the main menu: ");
        Scanner goToMenuInput = new Scanner(System.in);
        String goToMenuIn = goToMenuInput.nextLine();
        System.out.println();
    }

    public void deleteSpecificClient(ListOfClients listOfClients){
        int i = 0;
        for(i = 0 ; i < listOfClients.sizeOfList() ; i++){
            System.out.println((i+1) + ". " + listOfClients.getSpecificClient(i));
        }

        if(listOfClients.sizeOfList() > 0){
            System.out.println((i+1) + ". Back to menu");
            System.out.println();
            System.out.print("Your option: ");
            Scanner deleteClientInput = new Scanner(System.in);
            int deleteClientIn = deleteClientInput.nextInt();

            while(deleteClientIn < 0 || deleteClientIn > i + 1){
                System.out.println("Something went wrong! Try another value.");
                deleteClientIn = deleteClientInput.nextInt();
            }

            if(deleteClientIn > 0 && deleteClientIn <= i){
                if(listOfClients.getSpecificClient(deleteClientIn - 1).getIsAdmin()){
                    System.out.println("Error! You cannot delete this account!");
                }else{
                    try{
                        File file = new File("log.csv");
                        FileWriter fr = new FileWriter(file, true);
                        BufferedWriter logWriter = new BufferedWriter(fr);
                        logWriter.write("A client with username " + listOfClients.getSpecificClient(deleteClientIn - 1).getUsername() + " was deleted! " + formatter.format(date));
                        logWriter.newLine();
                        logWriter.close();
                        fr.close();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }

                    mySqlMethods.deleteSpecificUser(listOfClients, listOfClients.getSpecificClient(deleteClientIn - 1).getId());
                    listOfClients.removeClient(deleteClientIn - 1);
                }

            }
        } else {
            System.out.println("There is no courier registered to the DB!");
            System.out.println();
            System.out.print("Press ENTER to get back to the main menu: ");
            Scanner goToMenuInput = new Scanner(System.in);
            String goToMenuIn = goToMenuInput.nextLine();
            System.out.println();
        }
    }

    public void deleteSpecificCourier(ListOfCouriers listOfCouriers){
        int i = 0;
        for(i = 0 ; i < listOfCouriers.getSizeOfList() ; i++){
            System.out.println((i+1) + ". " + listOfCouriers.getCourierByIndex(i).getFirstName() + " " + listOfCouriers.getCourierByIndex(i).getLastName());
        }

        if(listOfCouriers.getSizeOfList() > 0){
            System.out.println((i+1) + ". Back to menu");
            System.out.println();
            System.out.print("Your option: ");
            Scanner deleteCourierInput = new Scanner(System.in);
            int deleteCourierIn = deleteCourierInput.nextInt();

            while(deleteCourierIn < 0 || deleteCourierIn > i + 1){
                System.out.println("Something went wrong! Try another value.");
                deleteCourierIn = deleteCourierInput.nextInt();
            }

            if(deleteCourierIn > 0 && deleteCourierIn <= i){
                try{
                    File file = new File("log.csv");
                    FileWriter fr = new FileWriter(file, true);
                    BufferedWriter logWriter = new BufferedWriter(fr);
                    logWriter.write("A courier named " + listOfCouriers.getCourierByIndex(deleteCourierIn - 1).getFirstName() + " "  + listOfCouriers.getCourierByIndex(deleteCourierIn - 1).getLastName() + " was deleted! " + formatter.format(date));
                    logWriter.newLine();
                    logWriter.close();
                    fr.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }

                mySqlMethods.deleteSpecificCourier(listOfCouriers, listOfCouriers.getCourierByIndex(deleteCourierIn - 1).getId());
                listOfCouriers.removeCourier(deleteCourierIn - 1);
            }
        } else {
            System.out.println("There is no courier registered to the DB!");
            System.out.println();
            System.out.print("Press ENTER to get back to the main menu: ");
            Scanner goToMenuInput = new Scanner(System.in);
            String goToMenuIn = goToMenuInput.nextLine();
            System.out.println();
        }
    }

}
