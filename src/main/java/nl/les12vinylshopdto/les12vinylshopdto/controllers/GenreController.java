package nl.les12vinylshopdto.les12vinylshopdto.controllers;

import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.GenreRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.GenreResponseDto;
import nl.les12vinylshopdto.les12vinylshopdto.entities.GenreEntity;
import nl.les12vinylshopdto.les12vinylshopdto.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // GET one
    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> getGenreById(@PathVariable Long id) {
        GenreResponseDto genre = genreService.findGenreById(id);
        if (genre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genre);
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAllGenres() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    //  POST
    @PostMapping
    public ResponseEntity<GenreResponseDto> createGenre(
            @Valid @RequestBody GenreRequestDto genre) {

        GenreResponseDto created = genreService.createGenre(genre);

        URI location = URI.create("/genres/" + created.getId());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDto> updateGenre(
            @PathVariable Long id,
            @Valid @RequestBody GenreRequestDto genre) {

        GenreResponseDto updated = genreService.updateGenre(id, genre);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}