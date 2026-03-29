package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.entities.BaseEntity;

import java.util.List;

public interface DTOMapper<RESPONSE, REQUEST , ENTITY extends BaseEntity> {
    RESPONSE mapToDto(ENTITY model);

    List<RESPONSE> mapToDto(List<ENTITY> models);

    ENTITY mapToEntity(REQUEST genreModel);
}