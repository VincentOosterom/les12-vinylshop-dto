package nl.les12vinylshopdto.les12vinylshopdto.dto.album;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlbumRequestDto(


        @NotNull
        int releaseYear,

        @NotBlank
        @Size(min = 2, max = 50)
        String title
) {}



