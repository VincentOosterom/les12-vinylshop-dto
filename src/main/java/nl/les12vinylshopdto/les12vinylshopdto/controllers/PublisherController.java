package nl.les12vinylshopdto.les12vinylshopdto.controllers;

import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.services.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    // ✅ GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> getPublisherById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.findPublisherById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.findAllPublishers());
    }

    // ✅ POST
    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(
            @Valid @RequestBody PublisherRequestDTO publisher) {

        PublisherResponseDTO created = publisherService.createPublisher(publisher);

        URI location = URI.create("/publishers/" + created.getId());
        return ResponseEntity
                .created(location)
                .body(created);
    }

    // ✅ PUT
    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> updatePublisher(
            @PathVariable Long id,
            @Valid @RequestBody PublisherRequestDTO publisher) {

        PublisherResponseDTO updated =
                publisherService.updatePublisher(id, publisher);

        return ResponseEntity.ok(updated);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}