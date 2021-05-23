package Services.Database;

import User.Client;
import User.ListOfClients;
import java.sql.*;

public class DbFromMySQL {
    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/zarex", "root", "Ciscoconpa55");
        } catch (SQLException throwables) {
            throw new RuntimeException("No access to db!");
        }
    }

    public void createUser(Client client){
        try (Connection connection = getConnection()) {
            String query = "INSERT into client(id, username, password, email, isAdmin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, client.getId());
            stmnt.setString(2, client.getUsername());
            stmnt.setString(3, client.getPassword());
            stmnt.setString(4, client.getEmail());
            stmnt.setBoolean(5, client.getIsAdmin());
            stmnt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPrivateInfo(Client client){
        try (Connection connection = getConnection()) {
            String query = "INSERT into privateinfo(privateInfoID, firstName, lastName, address, phoneNumber, clientID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, client.getPrivateInfoId());
            stmnt.setString(2, client.getFirstName());
            stmnt.setString(3, client.getLastName());
            stmnt.setString(4, client.getAddress());
            stmnt.setString(5, client.getPhoneNumber());
            stmnt.setString(6, client.getId());
            stmnt.executeUpdate();

        }catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public void updatePrivateInfo(Client client){
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE privateinfo SET firstName = ?, lastName = ?, address = ?, phoneNumber = ? WHERE clientID = ?;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, client.getFirstName());
            stmnt.setString(2, client.getLastName());
            stmnt.setString(3, client.getAddress());
            stmnt.setString(4, client.getPhoneNumber());
            stmnt.setString(5, client.getId());
            stmnt.executeUpdate();

            System.out.println("Success!");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readClients(ListOfClients listOfClients){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM client";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                Client clientX = new Client();
                clientX.setId(resSet.getString(1));
                clientX.setUsername(resSet.getString(2));
                clientX.setPassword(resSet.getString(3));
                clientX.setEmail(resSet.getString(4));
                clientX.setIsAdmin(resSet.getBoolean(5));
                listOfClients.addClient(clientX);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readPrivateInfo(ListOfClients listOfClients){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM privateinfo";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                for(int i = 0 ; i < listOfClients.sizeOfList() ; i++){
                    if(listOfClients.getSpecificClient(i).getId().equalsIgnoreCase(resSet.getString(6))){
                        listOfClients.getSpecificClient(i).setFirstName(resSet.getString(2));
                        listOfClients.getSpecificClient(i).setLastName(resSet.getString(3));
                        listOfClients.getSpecificClient(i).setAddress(resSet.getString(4));
                        listOfClients.getSpecificClient(i).setPhoneNumber(resSet.getString(5));
                    }
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUsers(){
        try (Connection connection = getConnection()) {
            String query = "DELETE from client;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecificUser(ListOfClients listOfClients, String id){
        try (Connection connection = getConnection()) {
            boolean check = false;

            for(int i = 0 ; i < listOfClients.sizeOfList() ; i++){
                if(listOfClients.getSpecificClient(i).getId().equalsIgnoreCase(id)){
                    check = true;
                }
            }

            if(check){
                String query = "DELETE from client WHERE id like ?;";
                PreparedStatement stmnt = connection.prepareStatement(query);
                stmnt.setString(1, id);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
