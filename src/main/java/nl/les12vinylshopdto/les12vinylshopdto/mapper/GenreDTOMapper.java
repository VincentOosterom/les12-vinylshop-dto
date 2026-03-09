package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.genre.GenreRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.genre.GenreResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreDTOMapper implements DTOMapper<
        GenreResponseDTO,
        GenreRequestDTO,
        GenreEntity> {

    @Override
    public GenreResponseDTO mapToDto(GenreEntity entity) {
        return new GenreResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }

    @Override
    public List<GenreResponseDTO> mapToDto(List<GenreEntity> entities) {
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreEntity mapToEntity(GenreRequestDTO dto) {
        GenreEntity genre = new GenreEntity();
        genre.setName(dto.name());
        genre.setDescription(dto.description());
        return genre;
    }
}