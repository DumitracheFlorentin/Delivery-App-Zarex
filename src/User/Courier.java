package User;

public class Courier extends PrivateInfo{
    private CourierCar car;
    private String status;

    public Courier(String firstName, String lastName, String address, String phoneNumber, CourierCar car, String status) {
        super(firstName, lastName, address, phoneNumber);
        this.car = car;
        this.status = status;
    }

    public Courier(String firstName, String lastName, String address, String phoneNumber, CourierCar car) {
        super(firstName, lastName, address, phoneNumber);
        this.car = car;
        this.status = "FREE";
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
}
