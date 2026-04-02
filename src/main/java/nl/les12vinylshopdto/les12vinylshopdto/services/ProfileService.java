package nl.les12vinylshopdto.les12vinylshopdto.services;

import jakarta.persistence.EntityNotFoundException;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.profile.ProfileResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import nl.les12vinylshopdto.les12vinylshopdto.entities.ProfileEntity;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.AlbumDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.ProfileDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.AlbumRepository;

import nl.les12vinylshopdto.les12vinylshopdto.repositories.ProfileRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileDTOMapper profileMapper;
    private final AlbumRepository albumRepository;
    private final AlbumDTOMapper albumMapper;

    public ProfileService(ProfileRepository profileRepository, ProfileDTOMapper profileMapper, AlbumRepository albumRepository, AlbumDTOMapper albumMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    public List<ProfileResponseDTO> findAllProfiles() {
        List<ProfileEntity> profileEntities = profileRepository.findAll();
        return profileEntities.stream()
                .map(profileMapper::mapToDto)
                .toList();
    }

    public ProfileResponseDTO findOrCreateProfile(Authentication authentication) {
        var kcid = authentication.getName();
        return profileMapper.mapToDto(findOrCreateProfileEntity(kcid));
    }

    private ProfileEntity findOrCreateProfileEntity(String kcid){
        if(profileRepository.existsByKcid(kcid)) {
            return profileRepository.findByKcid(kcid).get();
        } else {
            return createProfileEntity(kcid);
        }
    }

    private ProfileEntity createProfileEntity(String kcid) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setKcid(kcid);
        return (ProfileEntity) profileRepository.save(profileEntity);
    }

    public void linkAlbum(Authentication authentication, Long albumId) {
        var kcid = authentication.getName();
        AlbumEntity album = albumRepository.findById(albumId).orElseThrow(() -> new EntityNotFoundException("Album met ID " + albumId + " not found"));
        ProfileEntity profileEntity = findOrCreateProfileEntity(kcid);

        profileEntity.getAlbums().add(album);
        profileRepository.save(profileEntity);
    }

    public List<AlbumResponseDTO> getAlbumForProfile(String kcid) {
        var profile = findOrCreateProfileEntity(kcid);
        List<AlbumEntity> albums = profile.getAlbums();
        if (albums == null || albums.isEmpty()) {
            return List.of();
        }
        return albumMapper.mapToDto(albums);
    }
}