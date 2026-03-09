package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherDTOMapper implements DTOMapper<PublisherResponseDTO, PublisherRequestDTO, PublisherEntity> {

    @Override
    public PublisherResponseDTO mapToDto(PublisherEntity publisher) {
        return new PublisherResponseDTO(
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getContactDetails()
        );
    }

    @Override
    public List<PublisherResponseDTO> mapToDto(List<PublisherEntity> publishers) {
        return publishers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherEntity mapToEntity(PublisherRequestDTO dto) {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setName(dto.name());
        publisher.setAddress(dto.address());
        publisher.setContactDetails(dto.contactDetails());
        return publisher;
    }
}