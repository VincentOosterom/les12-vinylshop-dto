package nl.les12vinylshopdto.les12vinylshopdto.dto.album;

import java.time.LocalDate;

public class AlbumResponseDto {

    private long id;
    private String title;
    private int releaseYear;

    public AlbumResponseDto(long id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = LocalDate.now().getYear();
    }

    public Long getId() {return id;}

    public String getTitle() {return title;}

    public int getReleaseYear() {return releaseYear;}

}
