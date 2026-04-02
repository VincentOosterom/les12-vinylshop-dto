package nl.les12vinylshopdto.les12vinylshopdto.mapper;

import nl.les12vinylshopdto.les12vinylshopdto.dto.profile.ProfileResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.BaseEntity;
import nl.les12vinylshopdto.les12vinylshopdto.entities.ProfileEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProfileDTOMapper {

    public ProfileResponseDTO mapToDto(ProfileEntity entity){
        ProfileResponseDTO dto = new ProfileResponseDTO();
        if(entity.getAlbums()!=null) {
            dto.setAlbums(entity.getAlbums()
                    .stream()
                    .map(BaseEntity::getId)
                    .toList());
        }
        dto.setId(entity.getId());
        dto.setKcid(entity.getKcid());

        return dto;
    }

    public List<ProfileResponseDTO> toDto(List<ProfileEntity> entity){
        return entity.stream().map(this::mapToDto).toList();
    }
}
