package nl.les12vinylshopdto.les12vinylshopdto.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumExtendedResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import nl.les12vinylshopdto.les12vinylshopdto.entities.ArtistEntity;
import nl.les12vinylshopdto.les12vinylshopdto.entities.GenreEntity;
import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
import nl.les12vinylshopdto.les12vinylshopdto.exceptions.RecordNotFoundException;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.AlbumDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.AlbumExtendedDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.AlbumRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.ArtistRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.GenreRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final AlbumDTOMapper albumDTOMapper;
    private final AlbumExtendedDTOMapper albumExtendedDTOMapper;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;

    public AlbumService(AlbumRepository albumRepository,
                        ArtistRepository artistRepository,
                        AlbumDTOMapper albumDTOMapper, AlbumExtendedDTOMapper albumExtendedDTOMapper,
                        PublisherRepository publisherRepository,
                        GenreRepository genreRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.albumDTOMapper = albumDTOMapper;
        this.albumExtendedDTOMapper = albumExtendedDTOMapper;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    public List<AlbumResponseDTO> findAllAlbums() {
        return albumDTOMapper.mapToDto(albumRepository.findAll());
    }

    @Transactional
    public AlbumExtendedResponseDTO findAlbumById(Long id)  {
        AlbumEntity albumEntity = getAlbumEntity(id);
        return albumExtendedDTOMapper.mapToDto(albumEntity);
    }

    @Transactional
    public AlbumResponseDTO createAlbum(AlbumRequestDTO albumModel) {
        AlbumEntity albumEntity = albumDTOMapper.mapToEntity(albumModel);
        if (albumModel.getGenreId() != null) {
            albumEntity.setGenre(getGenreEntity(albumModel.getGenreId()));
        } else {
            albumEntity.setGenre(null); // ✅ geen genre meegegeven = null
        }

        if (albumModel.getPublisherId() != null) {
            albumEntity.setPublisher(getPublisherEntity(albumModel.getPublisherId()));
        } else {
            albumEntity.setPublisher(null); // ✅ geen publisher meegegeven = null
        }

        albumEntity = albumRepository.save(albumEntity);
        return albumDTOMapper.mapToDto(albumEntity);
    }

    @Transactional
    public AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO albumModel)  {
        AlbumEntity existingAlbumEntity = getAlbumEntity(id);

        existingAlbumEntity.setTitle(albumModel.getTitle());
        existingAlbumEntity.setReleaseYear(albumModel.getReleaseYear());
        existingAlbumEntity.setPublisher(getPublisherEntity(albumModel.getPublisherId()));
        existingAlbumEntity.setGenre(getGenreEntity(albumModel.getGenreId()));
        existingAlbumEntity = albumRepository.save(existingAlbumEntity);
        return albumDTOMapper.mapToDto(existingAlbumEntity);
    }

    private PublisherEntity getPublisherEntity(long publisherId) {
        return publisherRepository.findById(publisherId).orElseThrow(() -> new RecordNotFoundException("publisher " + publisherId + " not found"));
    }

    private GenreEntity getGenreEntity(long genreId) {
        return genreRepository.findById(genreId).orElseThrow(() -> new RecordNotFoundException("genre " + genreId + " not found"));
    }

    private AlbumEntity getAlbumEntity(Long id) {
        AlbumEntity existingAlbumEntity = albumRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Album " + id + " not found"));
        return existingAlbumEntity;
    }

    @Transactional
    public void deleteAlbum(Long id) {
        AlbumEntity album = getAlbumEntity(id);
//        Verwijder geen albums als je daar nog voorraad van hebt
        if(album.getStockItems().isEmpty()) {
            albumRepository.deleteById(id);
        }
    }

    @Transactional
    public void linkArtist(Long albumId, Long artistId) {
        AlbumEntity existingAlbumEntity = getAlbumEntity(albumId);
        ArtistEntity existingArtistEntity = artistRepository.findById(artistId)
                .orElseThrow(() -> new RecordNotFoundException("Artist " + artistId + " not found"));
        existingArtistEntity.getAlbums().add(existingAlbumEntity);
        existingAlbumEntity.getArtists().add(existingArtistEntity);
        albumRepository.save(existingAlbumEntity);
    }

    @Transactional
    public void unlinkArtist(Long albumId, Long artistId) {
        AlbumEntity existingAlbumEntity = getAlbumEntity(albumId);
        ArtistEntity existingArtistEntity = artistRepository.findById(artistId)
                .orElseThrow(() -> new RecordNotFoundException("Artist " + artistId + " not found"));
        existingArtistEntity.getAlbums().remove(existingAlbumEntity);
        existingAlbumEntity.getArtists().remove(existingArtistEntity);
        albumRepository.save(existingAlbumEntity);
    }

    @Transactional
    public List<AlbumResponseDTO> getAlbumsWithStock(Boolean stock) {
        if(stock) {
            return albumDTOMapper.mapToDto(albumRepository.findByStockItemsNotEmpty());
        } else {
            return albumDTOMapper.mapToDto(albumRepository.findByStockItemsEmpty());
        }
    }
}
