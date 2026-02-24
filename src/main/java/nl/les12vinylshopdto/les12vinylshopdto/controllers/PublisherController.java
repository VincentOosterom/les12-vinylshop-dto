package nl.les12vinylshopdto.les12vinylshopdto.controllers;

import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
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


    @GetMapping("/{id}")
    public ResponseEntity<PublisherEntity> getPublisherById(@PathVariable Long id) {
        PublisherEntity publisher = publisherService.findPublisherById(id);

        if (publisher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publisher);
    }

    @GetMapping
    public ResponseEntity<List<PublisherEntity>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.findAllPublishers());
    }

    @PostMapping
    public ResponseEntity<PublisherEntity> createPublisher(@RequestBody PublisherEntity publisher) {
        PublisherEntity newPublisher = publisherService.createPublisher(publisher);

        URI location = URI.create("/publishers/" + newPublisher.getId());

        return ResponseEntity.created(location).body(newPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherEntity> updatePublisher(@PathVariable Long id, @RequestBody PublisherEntity publisher) {
        PublisherEntity updatedPublisher = publisherService.updatePublisher(id, publisher);
        if (updatedPublisher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }

}