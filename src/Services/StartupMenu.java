package Services;

import User.Client;
import User.ListOfClients;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class StartupMenu {
    private boolean check = false;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());



    public void welcomeMessage(){
        System.out.println("Designed by Dumitrache Florentin-Cristian");
        System.out.println();
        System.out.println("Welcome to Zarex!!!");
        System.out.println("If you want to have access to our menu you need to log in. Don't have an account yet? SIGN UP");
    }

    public String formMenu(){
        Scanner optionInput = new Scanner(System.in);
        String option = "";
        System.out.println("1. Log in");
        System.out.println("2. Sign up");

        while(!check){
            System.out.print("Your option: ");
            String optionIn = optionInput.nextLine();
            if(Objects.equals(optionIn, "1") || Objects.equals(optionIn, "2")){
                check = true;
                option = optionIn;
            } else {
                System.out.println("ERROR! The option does not exist!");
            }
        }
        return option;
    }

    public boolean loginMenu(ListOfClients listOfClients, Client clientX){

        Scanner usernameInput = new Scanner(System.in);
        Scanner passwordInput = new Scanner(System.in);
        boolean loggedUser = false;

        System.out.print("Username: ");
        String usernameIn = usernameInput.nextLine();
        System.out.print("Password: ");
        String passwordIn = passwordInput.nextLine();

        while(!loggedUser){
            if(listOfClients.checkExistingUsername(usernameIn)){
                    if(listOfClients.getClientPassword(usernameIn).equalsIgnoreCase(passwordIn)){
                        clientX.setId(listOfClients.getSpecificClientByName(usernameIn).getId());
                        clientX.setUsername(listOfClients.getSpecificClientByName(usernameIn).getUsername());
                        clientX.setPassword(listOfClients.getSpecificClientByName(usernameIn).getPassword());
                        clientX.setEmail(listOfClients.getSpecificClientByName(usernameIn).getEmail());
                        clientX.setIsAdmin(listOfClients.getSpecificClientByName(usernameIn).getIsAdmin());

                        try{
                            File file = new File("log.csv");
                            FileWriter fr = new FileWriter(file, true);
                            BufferedWriter logWriter = new BufferedWriter(fr);
                            logWriter.write("A client named " + clientX.getUsername() + " just logged in! " + formatter.format(date));
                            logWriter.newLine();
                            logWriter.close();
                            fr.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }

                        return true;
                    } else {
                        System.out.println("Username or password incorrect! Try Again!");
                        System.out.print("Username: ");
                        usernameIn = usernameInput.nextLine();
                        System.out.print("Password: ");
                        passwordIn = passwordInput.nextLine();
                    }
            }else{
                System.out.println("Username or password incorrect! Try Again!");
                System.out.print("Username: ");
                usernameIn = usernameInput.nextLine();
                System.out.print("Password: ");
                passwordIn = passwordInput.nextLine();
            }
        }
        return false;
    }

    public void signupMenu(ListOfClients listOfClients){
        Scanner usernameInput = new Scanner(System.in);
        Scanner passwordInput = new Scanner(System.in);
        Scanner emailInput = new Scanner(System.in);

        System.out.print("Username: ");
        String usernameIn = usernameInput.nextLine();
        System.out.print("Password: ");
        String passwordIn = passwordInput.nextLine();
        System.out.print("Email: ");
        String emailIn = emailInput.nextLine();

        while(listOfClients.checkExistingUsername(usernameIn)){
            System.out.println("OPS! The username is already taken! Choose another one");
            System.out.print("Username: ");
            usernameIn = usernameInput.nextLine();
        }

        Client client = new Client(usernameIn, passwordIn, emailIn);
        listOfClients.addClient(client);

        try{
            File file = new File("log.csv");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter logWriter = new BufferedWriter(fr);
            logWriter.write("A new client named " + client.getUsername() + " was created! " + formatter.format(date));
            logWriter.newLine();
            logWriter.close();
            fr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        try{
            File file = new File("db_clients.csv");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter buffWriter = new BufferedWriter(fr);
            buffWriter.write(client.getId() + "," + client.getUsername() + "," + client.getPassword() + "," + client.getEmail() + "," + client.getFirstName() + "," + client.getLastName() + "," + client.getAddress() + "," + client.getPhoneNumber() + "," + client.getIsAdmin());
            buffWriter.newLine();
            buffWriter.close();
            fr.close();

        } catch(FileNotFoundException e){
            System.out.println("File Not Found!");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
