package nl.les12vinylshopdto.les12vinylshopdto.services;

import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.AlbumDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumDTOMapper albumDTOMapper;

    public AlbumService(AlbumRepository albumRepository, AlbumDTOMapper albumDTOMapper) {
        this.albumRepository = albumRepository;
        this.albumDTOMapper = albumDTOMapper;
    }


    // ✅ Find All
    public List<AlbumResponseDTO> findAllAlbums() {
        return albumDTOMapper.mapToDto(albumRepository.findAll());
    }

    // ✅ FIND BY ID

    public AlbumResponseDTO findAlbumById(Long id) {
        AlbumEntity album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));

        return albumDTOMapper.mapToDto(album);
    }

    // ✅ CREATE
    public AlbumResponseDTO createAlbum(@Valid AlbumRequestDTO input) {
        AlbumEntity albumEntity = albumDTOMapper.mapToEntity(input);

        albumEntity = albumRepository.save(albumEntity);

        return albumDTOMapper.mapToDto(albumEntity);
    }

    // ✅ UPDATE

    public AlbumResponseDTO updateAlbum(Long id, @Valid AlbumRequestDTO input) {
        AlbumEntity existingAlbum = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));

        existingAlbum.setTitle(input.title());
        existingAlbum.setReleaseYear(input.releaseYear());

        AlbumEntity saved = albumRepository.save(existingAlbum);
        return albumDTOMapper.mapToDto(saved);
    }

    public void deleteAlbum(Long id) {

        AlbumEntity existedAlbum = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));

        albumRepository.delete(existedAlbum);
    }


}
