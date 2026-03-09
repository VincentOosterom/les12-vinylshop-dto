package nl.les12vinylshopdto.les12vinylshopdto.services;

import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
import nl.les12vinylshopdto.les12vinylshopdto.exceptions.ResourceNotFoundException;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.PublisherDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherDTOMapper publisherDTOMapper;

    public PublisherService(PublisherRepository publisherRepository,
                            PublisherDTOMapper publisherDTOMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherDTOMapper = publisherDTOMapper;
    }

    // ✅ FIND ALL
    public List<PublisherResponseDTO> findAllPublishers() {
        return publisherDTOMapper.mapToDto(publisherRepository.findAll());
    }


    // ✅ FIND BY ID
    public PublisherResponseDTO findPublisherById(Long id) {
        PublisherEntity publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));

        return publisherDTOMapper.mapToDto(publisher);
    }

    // ✅ CREATE
    public PublisherResponseDTO createPublisher(PublisherRequestDTO input) {

        PublisherEntity publisher = publisherDTOMapper.mapToEntity(input);

        publisher = publisherRepository.save(publisher);

        return publisherDTOMapper.mapToDto(publisher);
    }

    // ✅ UPDATE
    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO input) {

        PublisherEntity existingPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));

        existingPublisher.setName(input.name());
        existingPublisher.setAddress(input.address());
        existingPublisher.setContactDetails(input.contactDetails());

        PublisherEntity saved = publisherRepository.save(existingPublisher);

        return publisherDTOMapper.mapToDto(saved);
    }

    // ✅ DELETE
    public void deletePublisher(Long id) {

        PublisherEntity existingPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));

        publisherRepository.delete(existingPublisher);
    }
}