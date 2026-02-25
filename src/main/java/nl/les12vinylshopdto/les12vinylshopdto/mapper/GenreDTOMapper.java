package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.GenreRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.GenreResponseDto;
import nl.les12vinylshopdto.les12vinylshopdto.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreDTOMapper implements DTOMapper<
        GenreResponseDto,
        GenreRequestDto,
        GenreEntity> {

    @Override
    public GenreResponseDto mapToDto(GenreEntity entity) {
        return new GenreResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    @Override
    public List<GenreResponseDto> mapToDto(List<GenreEntity> entities) {
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreEntity mapToEntity(GenreRequestDto dto) {
        GenreEntity genre = new GenreEntity();
        genre.setName(dto.name());
        genre.setDescription(dto.description());
        return genre;
    }
}