package nl.les12vinylshopdto.les12vinylshopdto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {
    private String title;
    private int releaseYear;

    public AlbumEntity() {

    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }
}
