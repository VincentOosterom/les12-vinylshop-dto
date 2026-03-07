package nl.les12vinylshopdto.les12vinylshopdto.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDto;
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
    public List<AlbumResponseDto> findAllAlbums() {
        return albumDTOMapper.mapToDto(albumRepository.findAll());
    }

    // ✅ FIND BY ID

    public AlbumResponseDto findAlbumById(Long id) {
        AlbumEntity album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));

        return albumDTOMapper.mapToDto(album);
    }

    // ✅ CREATE
    public AlbumResponseDto createAlbum(@Valid AlbumRequestDto input) {
        AlbumEntity albumEntity = albumDTOMapper.mapToEntity(input);

        albumEntity = albumRepository.save(albumEntity);

        return albumDTOMapper.mapToDto(albumEntity);
    }

    // ✅ UPDATE

    public AlbumResponseDto updateAlbum(Long id, @Valid AlbumRequestDto input) {
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
