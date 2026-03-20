package nl.les12vinylshopdto.les12vinylshopdto.controllers;


import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.artist.ArtistRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.artist.ArtistResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.helpers.UrlHelper;
import nl.les12vinylshopdto.les12vinylshopdto.services.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final UrlHelper urlHelper;

    public ArtistController(ArtistService artistService, UrlHelper urlHelper ) {
        this.artistService = artistService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAllArtists() {
        List<ArtistResponseDTO> artistDTOs = artistService.findAllArtists();
        return new ResponseEntity<>(artistDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable Long id) {
        ArtistResponseDTO artistDTO = artistService.findArtistById(id);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> createArtist(@RequestBody @Valid ArtistRequestDTO artistRequestDTO) {
        ArtistResponseDTO artistDTO = artistService.createArtist(artistRequestDTO);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(artistDTO.getId())).body(artistDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> updateArtist(@PathVariable Long id, @RequestBody @Valid ArtistRequestDTO artistRequestDTO) {
        ArtistResponseDTO artistDto = artistService.updateArtist(id, artistRequestDTO);
        return new ResponseEntity<>(artistDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}