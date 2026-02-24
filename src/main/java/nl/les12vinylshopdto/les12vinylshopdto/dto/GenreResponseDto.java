package nl.les12vinylshopdto.les12vinylshopdto.dto;

import jakarta.validation.constraints.*;

public class GenreResponseDto {

    private long id;
    private String name;
    private String description;

    public GenreResponseDto(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}