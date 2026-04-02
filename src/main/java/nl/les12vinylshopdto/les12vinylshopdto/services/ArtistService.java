package nl.les12vinylshopdto.les12vinylshopdto.services;

import jakarta.transaction.Transactional;
import nl.les12vinylshopdto.les12vinylshopdto.dto.artist.ArtistRequestDTO;
import nl.les12vinylshopdto.les12vinylshopdto.dto.artist.ArtistResponseDTO;
import nl.les12vinylshopdto.les12vinylshopdto.entities.ArtistEntity;
import nl.les12vinylshopdto.les12vinylshopdto.exceptions.RecordNotFoundException;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.ArtistDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistDTOMapper artistDTOMapper;

    public ArtistService(ArtistRepository artistRepository, ArtistDTOMapper artistDTOMapper) {
        this.artistRepository = artistRepository;
        this.artistDTOMapper = artistDTOMapper;
    }

    @Transactional
    public List<ArtistResponseDTO> findAllArtists() {
        return artistDTOMapper.mapToDto(artistRepository.findAll());
    }

    @Transactional
    public ArtistResponseDTO findArtistById(Long id) {
        ArtistEntity artistEntity = getArtistEntity(id);
        return artistDTOMapper.mapToDto(artistEntity);
    }

    private ArtistEntity getArtistEntity(Long id) {
        ArtistEntity artistEntity = artistRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Artist " + id + " not found"));
        return artistEntity;
    }

    @Transactional
    public ArtistResponseDTO createArtist(ArtistRequestDTO artistModel) {
        ArtistEntity artistEntity = artistDTOMapper.mapToEntity(artistModel);
        artistEntity = artistRepository.save(artistEntity);
        return artistDTOMapper.mapToDto(artistEntity);
    }

    @Transactional
    public ArtistResponseDTO updateArtist(Long id, ArtistRequestDTO artistModel) {
        ArtistEntity existingArtistEntity = getArtistEntity(id);

        existingArtistEntity.setName(artistModel.getName());
        existingArtistEntity.setBiography(artistModel.getBiography());

        existingArtistEntity = artistRepository.save(existingArtistEntity);
        return artistDTOMapper.mapToDto(existingArtistEntity);
    }

    @Transactional
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    public List<ArtistResponseDTO> getArtistsForAlbum(Long albumId) {
        return artistDTOMapper.mapToDto(artistRepository.findArtistsByAlbumsId(albumId));
    }
}
