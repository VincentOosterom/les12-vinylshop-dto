package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.PublisherRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.PublisherResponseDto;
import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherDTOMapper implements DTOMapper<PublisherResponseDto, PublisherRequestDto, PublisherEntity> {

    @Override
    public PublisherResponseDto mapToDto(PublisherEntity publisher) {
        return new PublisherResponseDto(
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getContactDetails()
        );
    }

    @Override
    public List<PublisherResponseDto> mapToDto(List<PublisherEntity> publishers) {
        return publishers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherEntity mapToEntity(PublisherRequestDto dto) {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setName(dto.name());
        publisher.setAddress(dto.address());
        publisher.setContactDetails(dto.contactDetails());
        return publisher;
    }
}