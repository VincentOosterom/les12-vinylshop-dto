package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.artist.ArtistRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.artist.ArtistResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.ArtistEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistDTOMapper implements DTOMapper <ArtistResponseDTO, ArtistRequestDTO, ArtistEntity>  {

    @Override
    public ArtistResponseDTO mapToDto(ArtistEntity entity) {
        return new ArtistResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getBiography()
        );
    }

    @Override
    public List<ArtistResponseDTO> mapToDto(List<ArtistEntity> models) {
        return models.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ArtistEntity mapToEntity(ArtistRequestDTO dto) {
        ArtistEntity artist = new ArtistEntity();
        artist.setName(dto.name());
        artist.setBiography(dto.biography());
        return artist;
    }



}
