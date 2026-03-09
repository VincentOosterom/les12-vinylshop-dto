package nl.les12vinylshopdto.les12vinylshopdto.dto.album;

import jakarta.validation.constraints.*;

public record AlbumRequestDTO(

        @Min(1877)
        @Max(2100)
        int releaseYear,

        @NotBlank
        @Size(min = 3, max = 100)
        String title
) {}



