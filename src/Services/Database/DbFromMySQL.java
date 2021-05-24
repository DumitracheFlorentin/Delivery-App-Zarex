package Services.Database;

import Cart.Cart;
import Order.ListOfProducts;
import Order.Order;
import Restaurant.Product;
import Restaurant.Restaurant;
import Restaurant.ListOfMenus;
import Restaurant.BridgeTableMenu;
import Restaurant.ListOfRestaurants;
import Order.ListOfOrders;
import User.*;

import java.sql.*;

public class DbFromMySQL {
    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/zarex", "root", "Ciscoconpa55");
        } catch (SQLException throwables) {
            throw new RuntimeException("No access to db!");
        }
    }

    /*-------------- CLIENT & PRIVATEINFO --------------*/

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
            String query = "INSERT into privateinfo(privateInfoID, firstName, lastName, address, phoneNumber, clientID, courierID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, client.getPrivateInfoId());
            stmnt.setString(2, client.getFirstName());
            stmnt.setString(3, client.getLastName());
            stmnt.setString(4, client.getAddress());
            stmnt.setString(5, client.getPhoneNumber());
            stmnt.setString(6, client.getId());
            stmnt.setString(7, null);
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
                String query = "DELETE from client WHERE id = ?;";
                PreparedStatement stmnt = connection.prepareStatement(query);
                stmnt.setString(1, id);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*-------------- PRODUCT --------------*/

    public void createProduct(Product product){
        try (Connection connection = getConnection()) {
            String query = "INSERT into product(id, name, price, rating, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, product.getId());
            stmnt.setString(2, product.getName());
            stmnt.setFloat(3, product.getPrice());
            stmnt.setFloat(4, product.getRating());
            stmnt.setString(5, product.getDescription());
            stmnt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readPrivateInfoCouriers(ListOfCouriers listOfCouriers){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM privateinfo";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                for(int i = 0 ; i < listOfCouriers.getSizeOfList() ; i++){
                    if(listOfCouriers.getCourierByIndex(i).getId().equalsIgnoreCase(resSet.getString(7))){
                        listOfCouriers.getCourierByIndex(i).setFirstName(resSet.getString(2));
                        listOfCouriers.getCourierByIndex(i).setLastName(resSet.getString(3));
                        listOfCouriers.getCourierByIndex(i).setAddress(resSet.getString(4));
                        listOfCouriers.getCourierByIndex(i).setPhoneNumber(resSet.getString(5));
                    }
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readProducts(ListOfProducts listOfProducts){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM product";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                Product productX = new Product();
                productX.setId(resSet.getString(1));
                productX.setName(resSet.getString(2));
                productX.setPrice(resSet.getFloat(3));
                productX.setRating(resSet.getFloat(4));
                productX.setDescription(resSet.getString(5));
                listOfProducts.addProduct(productX);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product){
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE product SET name = ?, price = ?, rating = ?, description = ? WHERE id = ?;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, product.getName());
            stmnt.setFloat(2, product.getPrice());
            stmnt.setFloat(3, product.getRating());
            stmnt.setString(4, product.getDescription());
            stmnt.setString(5, product.getId());
            stmnt.executeUpdate();

            System.out.println("Success!");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllProducts(){
        try (Connection connection = getConnection()) {
            String query = "DELETE from product;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecificProduct(ListOfProducts listOfProducts, String id){
        try (Connection connection = getConnection()) {
            boolean check = false;

            for(int i = 0 ; i < listOfProducts.sizeOfList() ; i++){
                if(listOfProducts.getProductByIndex(i).getId().equalsIgnoreCase(id)){
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


    /*-------------- COURIER & CARS --------------*/

    public void createCourierCar(CourierCar courierCar, Courier courier){
        try (Connection connection = getConnection()) {
            String query = "INSERT into couriercar(carID, type, model, number, color, courierID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, courierCar.getId());
            stmnt.setString(2, courierCar.getType());
            stmnt.setString(3, courierCar.getModel());
            stmnt.setString(4, courierCar.getNumber());
            stmnt.setString(5, courierCar.getColor());
            stmnt.setString(6, courier.getId());
            stmnt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPrivateInfoCourier(Courier courier){
        try (Connection connection = getConnection()) {
            String query = "INSERT into privateinfo(privateInfoID, firstName, lastName, address, phoneNumber, clientID, courierID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, courier.getPrivateInfoId());
            stmnt.setString(2, courier.getFirstName());
            stmnt.setString(3, courier.getLastName());
            stmnt.setString(4, courier.getAddress());
            stmnt.setString(5, courier.getPhoneNumber());
            stmnt.setString(6, null);
            stmnt.setString(7, courier.getId());
            stmnt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePrivateInfoCourier(Courier courier){
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE privateinfo SET firstName = ?, lastName = ?, address = ?, phoneNumber = ? WHERE courierID = ?;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, courier.getFirstName());
            stmnt.setString(2, courier.getLastName());
            stmnt.setString(3, courier.getAddress());
            stmnt.setString(4, courier.getPhoneNumber());
            stmnt.setString(5, courier.getId());
            stmnt.executeUpdate();

            System.out.println("Success!");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCourierCar(CourierCar courierCar, Courier courier){
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE couriercar SET type = ?, model = ?, number = ?, color = ?, courierID = ? WHERE id = ?;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, courierCar.getType());
            stmnt.setString(2, courierCar.getModel());
            stmnt.setString(3, courierCar.getNumber());
            stmnt.setString(4, courierCar.getColor());
            stmnt.setString(5, courier.getId());
            stmnt.setString(6, courierCar.getId());
            stmnt.executeUpdate();

            System.out.println("Success!");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readCourierCar(ListOfCourierCars listOfCourierCars){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM couriercar";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                CourierCar courierCarX = new CourierCar();
                courierCarX.setId(resSet.getString(1));
                courierCarX.setType(resSet.getString(2));
                courierCarX.setModel(resSet.getString(3));
                courierCarX.setNumber(resSet.getString(4));
                courierCarX.setColor(resSet.getString(5));
                listOfCourierCars.addCar(courierCarX);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecificCourierCar(ListOfCourierCars listOfCourierCars, String id){
        try (Connection connection = getConnection()) {
            boolean check = false;

            for(int i = 0 ; i < listOfCourierCars.getSizeOfList() ; i++){
                if(listOfCourierCars.getCarByIndex(i).getId().equalsIgnoreCase(id)){
                    check = true;
                }
            }

            if(check){
                String query = "DELETE from couriercar WHERE id like ?;";
                PreparedStatement stmnt = connection.prepareStatement(query);
                stmnt.setString(1, id);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCourier(Courier courier){
        try (Connection connection = getConnection()) {
            String query = "INSERT into courier(id, status) VALUES (?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, courier.getId());
            stmnt.setString(2, courier.getStatus());
            stmnt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCourier(Courier courier){
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE courier SET status = ? WHERE id = ?;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, courier.getStatus());
            stmnt.setString(2, courier.getId());
            stmnt.executeUpdate();

            System.out.println("Success!");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readCourier(ListOfCouriers listOfCouriers){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM courier";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                Courier courierX = new Courier();
                courierX.setId(resSet.getString(1));
                courierX.setStatus(resSet.getString(2));
                listOfCouriers.addCourier(courierX);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecificCourier(ListOfCouriers listOfCouriers, String id){
        try (Connection connection = getConnection()) {
            boolean check = false;

            for(int i = 0 ; i < listOfCouriers.getSizeOfList() ; i++){
                if(listOfCouriers.getCourierByIndex(i).getId().equalsIgnoreCase(id)){
                    check = true;
                }
            }

            if(check){
                String query = "DELETE from courier WHERE id like ?;";
                PreparedStatement stmnt = connection.prepareStatement(query);
                stmnt.setString(1, id);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*-------------- RESTAURANT --------------*/

    public void createRestaurant(Restaurant restaurant){
        try (Connection connection = getConnection()) {
            String query = "INSERT into restaurant(restaurantID, name, phoneNumber, address, city, rating) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, restaurant.getId());
            stmnt.setString(2, restaurant.getName());
            stmnt.setString(2, restaurant.getPhoneNumber());
            stmnt.setString(2, restaurant.getAddress());
            stmnt.setString(2, restaurant.getCity());
            stmnt.setFloat(2, restaurant.getRating());
            stmnt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readRestaurant(ListOfRestaurants listOfRestaurants){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM restaurant";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                Restaurant restaurantX = new Restaurant();
                restaurantX.setId(resSet.getString(1));
                restaurantX.setName(resSet.getString(2));
                restaurantX.setPhoneNumber(resSet.getString(3));
                restaurantX.setAddress(resSet.getString(4));
                restaurantX.setCity(resSet.getString(5));
                restaurantX.setRating(resSet.getFloat(6));
                listOfRestaurants.addRestaurant(restaurantX);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRestaurant(Restaurant restaurant){
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE restaurant SET name = ?, phoneNumber = ?, address = ?, city = ?, rating = ? WHERE restaurantID = ?;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, restaurant.getName());
            stmnt.setString(2, restaurant.getPhoneNumber());
            stmnt.setString(3, restaurant.getAddress());
            stmnt.setString(4, restaurant.getCity());
            stmnt.setFloat(5, restaurant.getRating());
            stmnt.executeUpdate();

            System.out.println("Success!");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecificRestaurant(ListOfRestaurants listOfRestaurants, String id){
        try (Connection connection = getConnection()) {
            boolean check = false;

            for(int i = 0 ; i < listOfRestaurants.sizeOfList() ; i++){
                if(listOfRestaurants.getRestaurantByIndex(i).getId().equalsIgnoreCase(id)){
                    check = true;
                }
            }

            if(check){
                String query = "DELETE from restaurant WHERE restaurantID like ?;";
                PreparedStatement stmnt = connection.prepareStatement(query);
                stmnt.setString(1, id);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*-------------- MENU --------------*/

    public void createMenu(BridgeTableMenu bridgeTableMenu, Restaurant restaurant, Product product){
        try (Connection connection = getConnection()) {
            String query = "INSERT into menu(menuID, restaurantID, productID) VALUES (?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, bridgeTableMenu.getMenuID());
            stmnt.setString(2, restaurant.getId());
            stmnt.setString(2, product.getId());
            stmnt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readMenu(ListOfMenus listOfMenus){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM menu";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                BridgeTableMenu bridgeTableMenu = new BridgeTableMenu();
                bridgeTableMenu.setMenuID(resSet.getString(1));
                bridgeTableMenu.setRestaurantID(resSet.getString(2));
                bridgeTableMenu.setProductID(resSet.getString(3));
                listOfMenus.addMenu(bridgeTableMenu);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBridgeMenu(BridgeTableMenu bridgeTableMenu, Restaurant restaurant, Product product){
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE menu SET restaurantID = ?, productID = ? WHERE menuID = ?;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, restaurant.getId());
            stmnt.setString(2, product.getId());
            stmnt.setString(3, bridgeTableMenu.getMenuID());
            stmnt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecificBridgeTabelMenu(ListOfMenus listOfMenus, String id){
        try (Connection connection = getConnection()) {
            boolean check = false;

            for(int i = 0 ; i < listOfMenus.sizeOfList() ; i++){
                if(listOfMenus.getSpecificMenu(i).getMenuID().equalsIgnoreCase(id)){
                    check = true;
                }
            }

            if(check){
                String query = "DELETE from menu WHERE menuID like ?;";
                PreparedStatement stmnt = connection.prepareStatement(query);
                stmnt.setString(1, id);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*-------------- ORDER --------------*/

    public void createOrder(Order order){
        try (Connection connection = getConnection()) {
            String query = "INSERT into orderinfo(orderID, restaurantID, clientUsername, totalPrice) VALUES (?, ?, ?, ?)";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, order.getId());
            stmnt.setString(2, order.getRestaurantID());
            stmnt.setString(3, order.getClientUsername());
            stmnt.setFloat(4, order.getTotalPrice());
            stmnt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readOrder(ListOfOrders listOfOrders){
        try (Connection connection = this.getConnection()) {
            Statement stmnt = connection.createStatement();

            String query = "SELECT * FROM orderinfo";
            ResultSet resSet = stmnt.executeQuery(query);

            while(resSet.next()){
                Order orderX = new Order();
                orderX.setId(resSet.getString(1));
                orderX.setRestaurantID(resSet.getString(2));
                orderX.setClientUsername(resSet.getString(3));
                orderX.setTotalPrice(resSet.getFloat(4));
                listOfOrders.addOrder(orderX);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateOrder(Order orderX){
        try (Connection connection = this.getConnection()) {
            String query = "UPDATE orderinfo SET restaurantID = ?, clientUsername = ?, totalPrice = ? WHERE orderID = ?;";
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, orderX.getRestaurantID());
            stmnt.setString(2, orderX.getClientUsername());
            stmnt.setFloat(4, orderX.getTotalPrice());
            stmnt.setString(5, orderX.getId());

            stmnt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSpecificOrder(ListOfOrders listOfOrders, String id){
        try (Connection connection = getConnection()) {
            boolean check = false;

            for(int i = 0 ; i < listOfOrders.sizeOfList() ; i++){
                if(listOfOrders.getOrderByIndex(i).getId().equalsIgnoreCase(id)){
                    check = true;
                }
            }

            if(check){
                String query = "DELETE from orderinfo WHERE orderID like ?;";
                PreparedStatement stmnt = connection.prepareStatement(query);
                stmnt.setString(1, id);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
