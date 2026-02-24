package nl.les12vinylshopdto.les12vinylshopdto.controllers;

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
    public ResponseEntity<GenreEntity> getGenreById(@PathVariable Long id) {
        GenreEntity genre = genreService.findGenreById(id);
        if (genre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genre);
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<GenreEntity>> getAllGenres() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    // POST
    @PostMapping
    public ResponseEntity<GenreEntity> createGenre(@RequestBody GenreEntity genre) {
        GenreEntity created = genreService.createGenre(genre);

        URI location = URI.create("/genres/" + created.getId());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<GenreEntity> updateGenre(@PathVariable Long id, @RequestBody GenreEntity genre) {
        GenreEntity updated = genreService.updateGenre(id, genre);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}