package Restaurant;

import Services.GenerateID;

public class Product implements GenerateID {
    private String id;
    private String name;
    private float price;
    private float rating;
    private String description;

    public Product(){}

    public Product(String name, float price, float rating, String description) {
        this.id = GenerateID.genID();
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Name = '" + name + '\'' +
                ", Price = '" + price + '\'' +
                ", Rating = '" + rating + '\'' +
                ", Description = '" + description + '\'' +
                '}';
    }
}
