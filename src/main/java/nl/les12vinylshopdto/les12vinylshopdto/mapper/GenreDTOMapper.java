package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.genre.GenreRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.genre.GenreResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreDTOMapper implements DTOMapper<GenreResponseDTO, GenreRequestDTO, GenreEntity> {

    @Override
    public GenreResponseDTO mapToDto(GenreEntity model) {

        var result = new GenreResponseDTO();
        result.setId(model.getId());
        result.setDescription(model.getDescription());
        result.setName(model.getName());
        return result;
    }


    @Override
    public List<GenreResponseDTO> mapToDto(List<GenreEntity> models) {
        var result = new ArrayList<GenreResponseDTO>();
        for (GenreEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;
    }


    @Override
    public GenreEntity mapToEntity(GenreRequestDTO genreModel) {
        var result = new GenreEntity();
        result.setName(genreModel.getName());
        result.setDescription(genreModel.getDescription());
        return result;
    }
}