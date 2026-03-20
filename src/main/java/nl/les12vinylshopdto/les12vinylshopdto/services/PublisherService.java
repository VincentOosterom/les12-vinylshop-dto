package nl.les12vinylshopdto.les12vinylshopdto.services;

import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
import nl.les12vinylshopdto.les12vinylshopdto.exceptions.RecordNotFoundException;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.PublisherDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.AlbumRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService{

    private final PublisherRepository publisherRepository;
    private final PublisherDTOMapper publisherDtoMapper;
    private final AlbumRepository albumRepository;

    public PublisherService(PublisherRepository publisherRepository, PublisherDTOMapper publisherDtoMapper, AlbumRepository albumRepository) {
        this.publisherRepository = publisherRepository;
        this.publisherDtoMapper = publisherDtoMapper;
        this.albumRepository = albumRepository;
    }

    //    Deze functie maakt gebruik van de Java Stream API. Dat is een korte manier om een for-loop te schrijven, maar het biedt ook nog andere functionaliteiten. Dit wordt niet in de stof behandeld, maar probeer eens te ontcijferen wat deze functie doet. Je zult zien dat het best intuïtief is.
    public List<PublisherResponseDTO> findAllPublishers() {
        return publisherDtoMapper.mapToDto(publisherRepository.findAll());
    }

    public PublisherResponseDTO findPublisherById(Long id)  {
        PublisherEntity publisherEntity = getPublisherEntity(id);
        return publisherDtoMapper.mapToDto(publisherEntity);
    }


    public PublisherResponseDTO createPublisher(PublisherRequestDTO publisherModel) {
        PublisherEntity publisherEntity = publisherDtoMapper.mapToEntity(publisherModel);
        publisherEntity = publisherRepository.save(publisherEntity);
        return publisherDtoMapper.mapToDto(publisherEntity);
    }

    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO publisherModel)  {
        PublisherEntity existingPublisherEntity = getPublisherEntity(id);

        existingPublisherEntity.setName(publisherModel.getName());
        existingPublisherEntity.setAddress(publisherModel.getAddress());
        existingPublisherEntity.setContactDetails(publisherModel.getContactDetails());

        existingPublisherEntity = publisherRepository.save(existingPublisherEntity);
        return publisherDtoMapper.mapToDto(existingPublisherEntity);
    }

    public void deletePublisher(Long id) {
        PublisherEntity publisher = getPublisherEntity(id);
//        Ontkoppel eerst alle relaties, voordat je de entiteit kunt verwijderen.
        for(AlbumEntity album : publisher.getAlbums()){
            album.setPublisher(null);
            albumRepository.save(album);
        }
        publisherRepository.deleteById(id);
    }

    //    Deze helper methode haalt de Entity uit de Repository en valideert het. Deze actie werd op meerdere plekken gedaan, daarom is er een helper methode voor gemaakt.
    private PublisherEntity getPublisherEntity(Long id) {
        PublisherEntity publisherEntity = publisherRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Publisher " + id +" not found"));
        return publisherEntity;
    }

}