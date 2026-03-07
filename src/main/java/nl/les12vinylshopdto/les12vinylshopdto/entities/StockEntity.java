package nl.les12vinylshopdto.les12vinylshopdto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class StockEntity extends BaseEntity {
    private String condition;
    private double price;

    public StockEntity() {

    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
