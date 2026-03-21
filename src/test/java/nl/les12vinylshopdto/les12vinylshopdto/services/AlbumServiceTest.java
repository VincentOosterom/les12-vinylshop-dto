package nl.les12vinylshopdto.les12vinylshopdto.services;

import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumExtendedResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import nl.les12vinylshopdto.les12vinylshopdto.entities.GenreEntity;
import nl.les12vinylshopdto.les12vinylshopdto.entities.PublisherEntity;
import nl.les12vinylshopdto.les12vinylshopdto.exceptions.RecordNotFoundException;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.AlbumDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.AlbumExtendedDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.AlbumRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.ArtistRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.GenreRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private AlbumDTOMapper albumDTOMapper;

    @Mock
    private AlbumExtendedDTOMapper albumExtendedDTOMapper;

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private AlbumService albumService;

    private AlbumEntity albumEntity;
    private AlbumResponseDTO albumResponseDTO;
    private AlbumRequestDTO albumRequestDTO;

    @BeforeEach
    void setUp() {
        albumEntity = new AlbumEntity();
        albumEntity.setId(1L);
        albumEntity.setTitle("Dark in the Middle");
        albumEntity.setReleaseYear(2020);
        albumEntity.setArtists(new HashSet<>());
        albumEntity.setStockItems(List.of());

        albumResponseDTO = new AlbumResponseDTO();
        albumResponseDTO.setTitle("Dark in the Middle");

        albumRequestDTO = new AlbumRequestDTO();
        albumRequestDTO.setReleaseYear(2020);
        albumRequestDTO.setTitle("Dark in the Middle");
        albumRequestDTO.setGenreId(1L);
        albumRequestDTO.setPublisherId(1L);
    }


    @Test
    void findAllAlbums() {
        List<AlbumEntity> albumEntities = List.of(albumEntity);
        List<AlbumResponseDTO> dtos = List.of(albumResponseDTO);

        when(albumRepository.findAll()).thenReturn(albumEntities);
        when(albumDTOMapper.mapToDto(anyList())).thenReturn(dtos);

        List<AlbumResponseDTO> result = albumService.findAllAlbums();

        assertThat(result.getFirst().getTitle()).isEqualTo("Dark in the Middle");
    }

    @Test
    void findAlbumById() {
        AlbumExtendedResponseDTO extendedDTO = new AlbumExtendedResponseDTO();

        extendedDTO.setId(1L);
        extendedDTO.setTitle("Dark in the Middle");

        when(albumRepository.findById(1L)).thenReturn(Optional.of(albumEntity));
        when(albumExtendedDTOMapper.mapToDto(albumEntity)).thenReturn(extendedDTO);

        AlbumExtendedResponseDTO result = albumService.findAlbumById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void createAlbum() {
        GenreEntity genre = new GenreEntity();
        PublisherEntity publisher = new PublisherEntity();

        when(albumDTOMapper.mapToEntity(albumRequestDTO)).thenReturn(albumEntity);
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(publisherRepository.findById(1L)).thenReturn(Optional.of(publisher));
        when(albumRepository.save(albumEntity)).thenReturn(albumEntity);
        when(albumDTOMapper.mapToDto(albumEntity)).thenReturn(albumResponseDTO);

        AlbumResponseDTO result = albumService.createAlbum(albumRequestDTO);

        assertThat(result.getTitle()).isEqualTo("Dark in the Middle");
        verify(albumRepository).save(albumEntity);
    }

    @Test
    void updateAlbum_ExistingAlbum() {
        GenreEntity genre = new GenreEntity();
        PublisherEntity publisher = new PublisherEntity();
        when(albumRepository.findById(1L)).thenReturn(Optional.of(albumEntity));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(publisherRepository.findById(1L)).thenReturn(Optional.of(publisher));
        when(albumRepository.save(albumEntity)).thenReturn(albumEntity);
        when(albumDTOMapper.mapToDto(any(AlbumEntity.class))).thenReturn(albumResponseDTO);

        AlbumResponseDTO result = albumService.updateAlbum(1L, albumRequestDTO);

        assertThat(albumEntity.getTitle()).isEqualTo("Dark in the Middle");
        verify(albumRepository).save(albumEntity);
    }

    @Test
    void updateAlbum_NotExistingAlbum(){
        when(albumRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> albumService.updateAlbum(99L, albumRequestDTO))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void deleteAlbum() {
    }

    @Test
    void linkArtist() {
    }

    @Test
    void unlinkArtist() {
    }

    @Test
    void getAlbumsWithStock() {
    }
}