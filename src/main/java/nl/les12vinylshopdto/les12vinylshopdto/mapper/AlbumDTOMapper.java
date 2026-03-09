package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumDTOMapper implements DTOMapper<
        AlbumResponseDTO,
        AlbumRequestDTO,
        AlbumEntity> {


    @Override
    public AlbumResponseDTO mapToDto(AlbumEntity entity) {
        return new AlbumResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getReleaseYear()
        );
    }

    @Override
    public List<AlbumResponseDTO> mapToDto(List<AlbumEntity> entities) {
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AlbumEntity mapToEntity(AlbumRequestDTO dto) {
        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity.setTitle(dto.title());
        albumEntity.setReleaseYear(dto.releaseYear());
        return albumEntity;
    }
}

