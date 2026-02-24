package nl.les12vinylshopdto.les12vinylshopdto.services;

import nl.les12vinylshopdto.les12vinylshopdto.dto.PublisherRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.PublisherResponseDto;
import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
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
    public List<PublisherResponseDto> findAllPublishers() {
        return publisherDTOMapper.mapToDto(publisherRepository.findAll());
    }

    // ✅ FIND BY ID
    public PublisherResponseDto findPublisherById(Long id) {
        PublisherEntity publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        return publisherDTOMapper.mapToDto(publisher);
    }

    // ✅ CREATE
    public PublisherResponseDto createPublisher(PublisherRequestDto input) {

        PublisherEntity publisher = publisherDTOMapper.mapToEntity(input);

        publisher = publisherRepository.save(publisher);

        return publisherDTOMapper.mapToDto(publisher);
    }

    // ✅ UPDATE
    public PublisherResponseDto updatePublisher(Long id, PublisherRequestDto input) {

        PublisherEntity existingPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        existingPublisher.setName(input.name());
        existingPublisher.setAddress(input.address());
        existingPublisher.setContactDetails(input.contactDetails());

        PublisherEntity saved = publisherRepository.save(existingPublisher);

        return publisherDTOMapper.mapToDto(saved);
    }

    // ✅ DELETE
    public void deletePublisher(Long id) {

        PublisherEntity existingPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        publisherRepository.delete(existingPublisher);
    }
}