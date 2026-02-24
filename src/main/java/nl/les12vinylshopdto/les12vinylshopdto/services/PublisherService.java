package nl.les12vinylshopdto.les12vinylshopdto.services;

import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherEntity> findAllPublishers() {
        return publisherRepository.findAll();
    }

    public PublisherEntity findPublisherById(Long id) {
        return getPublisherById(id);
    }

    public PublisherEntity createPublisher(PublisherEntity input) {
        return publisherRepository.save(input);
    }

    public PublisherEntity updatePublisher(Long id, PublisherEntity input) {
        PublisherEntity existingPublisher = getPublisherById(id);

        if (existingPublisher == null) {
            return null;
        }

        existingPublisher.setName(input.getName());
        existingPublisher.setDescription(input.getDescription());

        return publisherRepository.save(existingPublisher);
    }

    public void deletePublisher(Long id) {
        PublisherEntity existingPublisher = getPublisherById(id);

        if (existingPublisher != null) {
            publisherRepository.delete(existingPublisher);
        }
    }

    private PublisherEntity getPublisherById(Long id) {
        Optional<PublisherEntity> optionalPublisher = publisherRepository.findById(id);
        return optionalPublisher.orElse(null);
    }
}
