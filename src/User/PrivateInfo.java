package User;

import Services.GenerateID;

public class PrivateInfo implements GenerateID {
    private String privateInfoId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public PrivateInfo() {
        this.privateInfoId = GenerateID.genID();
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.phoneNumber = "";
    }

    public PrivateInfo( String firstName, String lastName, String address, String phoneNumber) {
        this.privateInfoId = GenerateID.genID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getPrivateInfoId() {
        return privateInfoId;
    }

    public void setPrivateInfoId(String privateInfoId) {
        this.privateInfoId = privateInfoId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PrivateInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
