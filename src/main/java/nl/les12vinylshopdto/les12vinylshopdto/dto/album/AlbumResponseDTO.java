package nl.les12vinylshopdto.les12vinylshopdto.dto.album;

import java.time.LocalDate;

public class AlbumResponseDTO {

    private final long id;
    private final String title;
    private final int releaseYear;

    public AlbumResponseDTO(long id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = LocalDate.now().getYear();
    }

    public Long getId() {return id;}

    public String getTitle() {return title;}

    public int getReleaseYear() {return releaseYear;}

}
