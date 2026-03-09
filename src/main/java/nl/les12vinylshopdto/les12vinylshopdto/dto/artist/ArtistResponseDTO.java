package nl.les12vinylshopdto.les12vinylshopdto.dto.artist;

public class ArtistResponseDTO {

    private final Long id;
    private final String name;
    private final String biography;

    public ArtistResponseDTO(long id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }
}
