package nl.les12vinylshopdto.les12vinylshopdto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "stock")
public class StockEntity extends BaseEntity {

    private String condition;
    private BigDecimal price;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "album_id")
    private AlbumEntity album;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }
}
