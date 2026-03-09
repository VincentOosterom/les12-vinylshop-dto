package nl.les12vinylshopdto.les12vinylshopdto.dto.stock;

import java.math.BigDecimal;

public class StockResponseDTO {

    private final Long id;
    private final String condition;
    private final BigDecimal price;

    public StockResponseDTO(Long id, String condition, BigDecimal price)  {
        this.id = id;
        this.condition = condition;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getCondition() {
        return condition;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
