package Services;

import Cart.Cart;
import Order.Order;
import Restaurant.Restaurant;
import User.Client;
import User.ListOfCouriers;
import Order.ListOfOrders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CheckoutMethods {
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());

    public void checkout(ListOfCouriers listOfCouriers, ListOfOrders listOfOrders, Restaurant specRestaurant, Client clientX, Cart cart){
        Scanner goToMenuInput = new Scanner(System.in);
        Scanner orderInput = new Scanner(System.in);
        boolean checked = false;

        System.out.print("Do you want to make the order? (Y/N)");
        String orderIn = orderInput.nextLine();

        while(!checked){
            if(orderIn.equalsIgnoreCase("y") || orderIn.equalsIgnoreCase("Y")){
                boolean check = false;

                System.out.println("--------CHECKOUT--------");
                System.out.println();
                for(int j = 0 ; j < listOfCouriers.getSizeOfList() ; j++){
                    if(listOfCouriers.getCourierByIndex(j).getStatus().equalsIgnoreCase("FREE")){
                        System.out.println("Courier: " + listOfCouriers.getCourierByIndex(j).getFirstName() + " " + listOfCouriers.getCourierByIndex(j).getLastName());
                        listOfCouriers.getCourierByIndex(j).setStatus("BUSY");
                        check = true;
                        break;
                    }
                }
                if(!check){
                    System.out.println("At the moment, there is no courier available, try to make the order in 5 minutes!");

                }else{
                    System.out.println("Restaurant Name: " + specRestaurant.getName());
                    System.out.println("Your Address: " + clientX.getAddress());
                    System.out.println("Products: ");
                    cart.showCart();
                    System.out.print("Total Amount: ");
                    cart.getTotalPrice();

                    Order orderX = new Order(specRestaurant.getId(), clientX.getUsername(), cart);

                    listOfOrders.addOrder(orderX);

//                    try{
//                        File file = new File("log.csv");
//                        FileWriter fr = new FileWriter(file, true);
//                        BufferedWriter logWriter = new BufferedWriter(fr);
//                        logWriter.write("A new order placed by " + clientX.getUsername() + " with the total amount of " + cart.getPrice() + " RON was registered! " + formatter.format(date));
//                        logWriter.newLine();
//                        logWriter.close();
//                        fr.close();
//                    } catch(IOException e) {
//                        e.printStackTrace();
//                    }
                }
                checked = true;
                System.out.println();
                System.out.print("Press ENTER to get back to the main menu: ");
                String goToMenuIn = goToMenuInput.nextLine();
                System.out.println();
            } else if(orderIn.equalsIgnoreCase("n") || orderIn.equalsIgnoreCase("N")){
                System.out.println("You will be redirected to the main menu!");
                checked = true;
                System.out.println();
                System.out.print("Press ENTER to get back to the main menu: ");
                String goToMenuIn = goToMenuInput.nextLine();
                System.out.println();
            }else{
                System.out.println("The value does not exist! Try anything else!");
                orderIn = orderInput.nextLine();
            }
        }
    }
}
