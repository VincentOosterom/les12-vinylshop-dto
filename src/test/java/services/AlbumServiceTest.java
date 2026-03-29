package services;

import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumExtendedResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.album.AlbumResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.*;
import nl.les12vinylshopdto.les12vinylshopdto.exceptions.RecordNotFoundException;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.AlbumDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.AlbumExtendedDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.AlbumRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.ArtistRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.GenreRepository;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.PublisherRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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

    private AlbumService albumService;

    private AlbumEntity albumEntity;
    private AlbumResponseDTO albumResponseDTO;
    private AlbumRequestDTO albumRequestDTO;

    @BeforeEach
    void setUp() {
        albumService = new AlbumService(albumRepository,
                artistRepository,
                albumDTOMapper,
                albumExtendedDTOMapper,
                publisherRepository,
                genreRepository
        );

        albumEntity = new AlbumEntity();
        albumEntity.setId(1L);
        albumEntity.setTitle("Dark in the Middle");
        albumEntity.setReleaseYear(2020);
        albumEntity.setArtists(new HashSet<>());
        albumEntity.setStockItems(new ArrayList<>());

        albumResponseDTO = new AlbumResponseDTO();
        albumResponseDTO.setTitle("Dark in the Middle");
        albumResponseDTO.setId(1L);

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
        when(albumDTOMapper.mapToDto(any(List.class))).thenReturn(dtos);

        List<AlbumResponseDTO> result = albumService.findAllAlbums();

        assertThat(result).hasSize(1);
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

        when(albumDTOMapper.mapToEntity(any())).thenReturn(albumEntity);
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(publisherRepository.findById(1L)).thenReturn(Optional.of(publisher));
        when(albumRepository.save(any())).thenReturn(albumEntity);
        when(albumDTOMapper.mapToDto(any(AlbumEntity.class))).thenReturn(albumResponseDTO);

        AlbumResponseDTO result = albumService.createAlbum(albumRequestDTO);

        assertThat(result.getTitle()).isEqualTo("Dark in the Middle");
        verify(albumRepository).save(any());
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
    void updateAlbum_NotExistingAlbum() {
        when(albumRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> albumService.updateAlbum(99L, albumRequestDTO))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void deleteAlbum_geenVoorraad_verwijdertAlbum() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(albumEntity));

        albumService.deleteAlbum(1L);

        verify(albumRepository).deleteById(1L);
    }

    @Test
    void deleteAlbum_metVoorraad_verwijdertNiet() {
        StockEntity stockItem = new StockEntity();
        albumEntity.setStockItems(List.of(stockItem));

        when(albumRepository.findById(1L)).thenReturn(Optional.of(albumEntity));

        albumService.deleteAlbum(1L);

        verify(albumRepository, never()).deleteById(1L);

    }

    @Test
    void linkArtist() {
        ArtistEntity artist = new ArtistEntity();
        artist.setAlbums(new HashSet<>());

        when(albumRepository.findById(1L)).thenReturn(Optional.of(albumEntity));
        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));

        albumService.linkArtist(1L, 1L);

        assertThat(albumEntity.getArtists()).contains(artist);
        assertThat(artist.getAlbums()).contains(albumEntity);
    }

    @Test
    void unlinkArtist() {
        ArtistEntity artist = new ArtistEntity();
        artist.setAlbums(new HashSet<>());
        artist.getAlbums().add(albumEntity);
        albumEntity.getArtists().add(artist);

        when(albumRepository.findById(1L)).thenReturn(Optional.of(albumEntity));
        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));

        albumService.unlinkArtist(1L, 1L);

        assertThat(albumEntity.getArtists()).doesNotContain(artist);
        assertThat(artist.getAlbums()).doesNotContain(albumEntity);

    }

    @Test
    void getAlbumsWithStock_true() {
        when(albumRepository.findByStockItemsNotEmpty()).thenReturn(List.of(albumEntity));
        when(albumDTOMapper.mapToDto(any(List.class))).thenReturn(List.of(albumResponseDTO));

        List<AlbumResponseDTO> result = albumService.getAlbumsWithStock(true);

        assertThat(result).hasSize(1);
        verify(albumRepository).findByStockItemsNotEmpty();
    }

    @Test
    void getAlbumsWithStock_false() {
        when(albumRepository.findByStockItemsEmpty()).thenReturn(List.of(albumEntity));
        when(albumDTOMapper.mapToDto(any(List.class))).thenReturn(List.of(albumResponseDTO));

        List<AlbumResponseDTO> result = albumService.getAlbumsWithStock(false);

        assertThat(result).hasSize(1);
        verify(albumRepository).findByStockItemsEmpty();
    }
}



