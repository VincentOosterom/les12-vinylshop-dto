package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.publisher.PublisherResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherDTOMapper implements DTOMapper<PublisherResponseDTO, PublisherRequestDTO, PublisherEntity>{

    @Override
    public PublisherResponseDTO mapToDto(PublisherEntity publisher) {
        PublisherResponseDTO dto = new PublisherResponseDTO();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        dto.setAddress(publisher.getAddress());
        dto.setContactDetails(publisher.getContactDetails());
        return dto;
    }

    @Override
    public List<PublisherResponseDTO> mapToDto(List<PublisherEntity> publishers) {
        List<PublisherResponseDTO> result = new ArrayList<>();
        for (PublisherEntity publisher : publishers) {
            result.add(mapToDto(publisher));
        }
        return result;
    }

    @Override
    public PublisherEntity mapToEntity(PublisherRequestDTO dto) {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setName(dto.getName());
        publisher.setAddress(dto.getAddress());
        publisher.setContactDetails(dto.getContactDetails());
        return publisher;
    }
}