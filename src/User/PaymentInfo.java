package User;

public class PaymentInfo {
    private String id;
    private String ownerName;
    private String number;
    private String expirationDate;
    private String CVC;

    public PaymentInfo() {
        this.ownerName = "";
        this.number = "";
        this.expirationDate = "";
        this.CVC = "";
    }

    public PaymentInfo(String ownerName, String number, String expirationDate, String CVC) {
        this.ownerName = ownerName;
        this.number = number;
        this.expirationDate = expirationDate;
        this.CVC = CVC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }
}
