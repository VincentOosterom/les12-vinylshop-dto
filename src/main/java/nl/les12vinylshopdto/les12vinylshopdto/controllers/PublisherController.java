package nl.les12vinylshopdto.les12vinylshopdto.controllers;

import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.PublisherRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.PublisherResponseDto;
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
    public ResponseEntity<PublisherResponseDto> getPublisherById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.findPublisherById(id));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<PublisherResponseDto>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.findAllPublishers());
    }

    // ✅ POST
    @PostMapping
    public ResponseEntity<PublisherResponseDto> createPublisher(
            @Valid @RequestBody PublisherRequestDto publisher) {

        PublisherResponseDto created = publisherService.createPublisher(publisher);

        URI location = URI.create("/publishers/" + created.getId());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    // ✅ PUT
    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> updatePublisher(
            @PathVariable Long id,
            @Valid @RequestBody PublisherRequestDto publisher) {

        PublisherResponseDto updated =
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