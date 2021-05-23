package User;

import Services.GenerateID;

public class Courier extends PrivateInfo implements GenerateID {
    private String id;
    private CourierCar car;
    private String status;

    public Courier() {
        this.id = GenerateID.genID();
    }

    public Courier(String firstName, String lastName, String address, String phoneNumber, CourierCar car, String status) {
        super(firstName, lastName, address, phoneNumber);
        this.id = GenerateID.genID();
        this.car = car;
        this.status = status;
    }

    public Courier(String firstName, String lastName, String address, String phoneNumber, CourierCar car) {
        super(firstName, lastName, address, phoneNumber);
        this.id = GenerateID.genID();
        this.car = car;
        this.status = "FREE";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourierCar getCar() {
        return car;
    }

    public void setCar(CourierCar car) {
        this.car = car;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "First Name='" + getFirstName() + '\'' +
                ", Last Name='" + getLastName() + '\'' +
                ", Phone Number='" + getPhoneNumber() + '\'' +
                ", Address='" + getAddress() + '\'' +
                ", Status='" + status + '\'' +
                ", Car='" + car + '\'' +
                '}';
    }
}
