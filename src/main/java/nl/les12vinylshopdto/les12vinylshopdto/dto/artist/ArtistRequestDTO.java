package nl.les12vinylshopdto.les12vinylshopdto.dto.artist;


import jakarta.validation.constraints.NotBlank;

public record ArtistRequestDTO(

        @NotBlank
        String name,
        String biography

) {}

