package nl.les12vinylshopdto.les12vinylshopdto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenreRequestDto(

        @NotBlank
        @Size(min = 2, max = 100)
        String name,

        @Size(max = 255)
        String description
) {}