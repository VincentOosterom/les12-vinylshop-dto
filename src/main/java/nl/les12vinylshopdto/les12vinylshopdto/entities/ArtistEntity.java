package nl.les12vinylshopdto.les12vinylshopdto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {
    private String name;
    private String biography;

    public ArtistEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
