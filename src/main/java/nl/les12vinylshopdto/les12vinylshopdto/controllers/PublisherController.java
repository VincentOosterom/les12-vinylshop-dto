package nl.les12vinylshopdto.les12vinylshopdto.controllers;

import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.helpers.UrlHelper;
import nl.les12vinylshopdto.les12vinylshopdto.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;
    private final UrlHelper urlHelper;

    public PublisherController(PublisherService publisherService, UrlHelper urlHelper) {
        this.publisherService = publisherService;
        this.urlHelper = urlHelper;

    }
    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublishers() {
        List<PublisherResponseDTO> publishers = publisherService.findAllPublishers();
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> getPublisherById(@PathVariable Long id)  {
        PublisherResponseDTO publisher = publisherService.findPublisherById(id);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(@RequestBody  @Valid PublisherRequestDTO publisherRequestDTO) {
        PublisherResponseDTO newPublisher = publisherService.createPublisher(publisherRequestDTO);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.getId())).body(newPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> updatePublisher(@PathVariable Long id, @RequestBody  @Valid PublisherRequestDTO publisherRequestDTO)  {
        PublisherResponseDTO updatedPublisher = publisherService.updatePublisher(id, publisherRequestDTO);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}