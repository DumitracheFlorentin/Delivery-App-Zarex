package Restaurant;

import Services.GenerateID;

public class Restaurant implements GenerateID {
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private String city;
    private Float rating;
    private Menu menu;

    public Restaurant(){

    }

    public Restaurant(String name, String address, String phoneNumber, String city, Float rating, Menu menu) {
        this.id = GenerateID.genID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.rating = rating;
        this.menu = menu;
    }

    public Float getRating() {
        return rating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "Name = '" + name + '\'' +
                ", Address = '" + address + '\'' +
                ", Phone = '" + phoneNumber + '\'' +
                ", Rating = '" + rating + '\'' +
                ", Menu = '" + menu.getListOfProducts() + '\'' +
                '}';
    }
}
