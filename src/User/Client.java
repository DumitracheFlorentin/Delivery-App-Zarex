package User;
import Services.generateID;


public class Client extends PrivateInfo implements generateID {
    private String id;
    private String username;
    private String password;
    private String email;
    private boolean isAdmin;

    public Client() {
    }

    public Client(String username, String password, String email) {
        this.id = generateID.genID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = false;
    }

    public Client(String username, String password, String email, boolean isAdmin) {
        this.id = generateID.genID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
