package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDto;
import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumDTOMapper implements DTOMapper<
        AlbumResponseDto,
        AlbumRequestDto,
        AlbumEntity> {


    @Override
    public AlbumResponseDto mapToDto(AlbumEntity entity) {
        return new AlbumResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getReleaseYear()
        );
    }

    @Override
    public List<AlbumResponseDto> mapToDto(List<AlbumEntity> entities) {
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AlbumEntity mapToEntity(AlbumRequestDto dto) {
        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity.setTitle(dto.title());
        albumEntity.setReleaseYear(dto.releaseYear());
        return albumEntity;
    }
}

