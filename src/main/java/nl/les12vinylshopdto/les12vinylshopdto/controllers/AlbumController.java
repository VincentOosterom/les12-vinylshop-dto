package nl.les12vinylshopdto.les12vinylshopdto.controllers;

import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDto;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.AlbumRepository;
import nl.les12vinylshopdto.les12vinylshopdto.services.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    //
    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDto> getAlbumById(@PathVariable Long id) {
        AlbumResponseDto album = albumService.findAlbumById(id);
        if (album == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(album);
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDto>> getAllAlbums() {
        return ResponseEntity.ok(albumService.findAllAlbums());
    }

    // POST
    @PostMapping
    public ResponseEntity<AlbumResponseDto> createAlbum(@RequestBody @Valid AlbumRequestDto album) {

        AlbumResponseDto created = albumService.createAlbum(album);

        URI location = URI.create("/albums/" + created.getId());

        return ResponseEntity.created(location).body(created);
    }

//    PUT

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDto> updateAlbum(@PathVariable Long id, @RequestBody @Valid AlbumRequestDto album) {

        AlbumResponseDto updated =
                albumService.updateAlbum(id, album);

        return ResponseEntity.ok(updated);
    }

//    DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
