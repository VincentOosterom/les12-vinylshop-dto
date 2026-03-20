package nl.les12vinylshopdto.les12vinylshopdto.dto.stock;

import java.math.BigDecimal;

public class StockResponseDTO {
    private Long id;
    private String condition;
    private double price;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
