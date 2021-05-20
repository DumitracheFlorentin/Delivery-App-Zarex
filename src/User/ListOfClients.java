package User;
import java.util.*;

public class ListOfClients {
    private ArrayList<Client> listOfClients = new ArrayList<Client>();

    public ArrayList<Client> getListOfClients() {
        return listOfClients;
    }

    public void addClient(Client x){
        listOfClients.add(x);
    }

    public void showClients() {
        for(int i = 0 ; i < listOfClients.size(); i++)
        {
            System.out.println(listOfClients.get(i));
        }
    }

    public boolean checkExistingUsername(String name) {
        for(int i = 0 ; i < listOfClients.size(); i++) {
            String nameOfUser = listOfClients.get(i).getUsername();
            if (Objects.equals(nameOfUser, name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String password) {
        for(int i = 0 ; i < listOfClients.size(); i++)
        {
            String passwordOfUser =  listOfClients.get(i).getPassword();
            if(Objects.equals(passwordOfUser, password)){
                return true;
            }
        }
        return false;
    }

    public String getClientPassword(String name){
        for(int i = 0 ; i < listOfClients.size() ; i++) {
            String nameOfUser = listOfClients.get(i).getUsername();
            if (Objects.equals(nameOfUser, name)) {
                return listOfClients.get(i).getPassword();
            }
        }
        return "";
    }

    public Client getSpecificClientByName(String name){
        for(int i = 0 ; i < listOfClients.size() ; i++) {
            String nameOfUser = listOfClients.get(i).getUsername();
            if (Objects.equals(nameOfUser, name)) {
                return listOfClients.get(i);
            }
        }
        Client NotFoundClient = new Client("NotFound", "NotFound", "NotFound");
        return NotFoundClient;
    }

    public boolean existingClient(String username, String password){
        for(int i = 0 ; i < listOfClients.size() ; i++){
            String name =  listOfClients.get(i).getUsername();

            if(Objects.equals(name, username)){
                String pass =  listOfClients.get(i).getPassword();
                if(Objects.equals(pass, password)){
                    return true;
                }
            }
        }
        return false;
    }

    public Client getSpecificClient(int index){
        return listOfClients.get(index);
    }

    public int sizeOfList(){
        return listOfClients.size();
    }




}
