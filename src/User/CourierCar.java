package User;

import Services.GenerateID;

public class CourierCar implements GenerateID {
    private String id;
    private String type;
    private String model;
    private String number;
    private String color;

    public CourierCar(String type, String model, String number, String color) {
        this.id = GenerateID.genID();
        this.type = type;
        this.model = model;
        this.number = number;
        this.color = color;
    }

    public CourierCar() {
        this.id = GenerateID.genID();
        this.type = "";
        this.model = "";
        this.number = "";
        this.color = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Type='" + type + '\'' +
                ", Model='" + model + '\'' +
                ", Number='" + number + '\'' +
                ", Color='" + color + '\'' +
                '}';
    }
}
