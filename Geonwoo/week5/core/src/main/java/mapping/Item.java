package mapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id @GeneratedValue
    @Column(name = "")
    private Long id;

    private String name;

    private int price;

    private int stockQunatity;

    public Item() {
    }

    public Item(String name, int price, int stockQunatity) {
        this.name = name;
        this.price = price;
        this.stockQunatity = stockQunatity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQunatity() {
        return stockQunatity;
    }

    public void setStockQunatity(int stockQunatity) {
        this.stockQunatity = stockQunatity;
    }
}
