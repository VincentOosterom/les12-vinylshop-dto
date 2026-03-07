package nl.les12vinylshopdto.les12vinylshopdto.services;

import jakarta.validation.Valid;
import nl.les12vinylshopdto.les12vinylshopdto.dto.GenreRequestDto;
import nl.les12vinylshopdto.les12vinylshopdto.dto.GenreResponseDto;
import nl.les12vinylshopdto.les12vinylshopdto.entities.GenreEntity;
import nl.les12vinylshopdto.les12vinylshopdto.mapper.GenreDTOMapper;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreDTOMapper genreDTOMapper;

    public GenreService(GenreRepository genreRepository,
                        GenreDTOMapper genreDTOMapper) {
        this.genreRepository = genreRepository;
        this.genreDTOMapper = genreDTOMapper;
    }

    // ✅ FIND ALL
    public List<GenreResponseDto> findAllGenres() {
        return genreDTOMapper.mapToDto(genreRepository.findAll());
    }

    // ✅ FIND BY ID
    public GenreResponseDto findGenreById(Long id) {
        GenreEntity genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        return genreDTOMapper.mapToDto(genre);
    }

    // ✅ CREATE
    public GenreResponseDto createGenre(@Valid GenreRequestDto input) {

        GenreEntity genreEntity = genreDTOMapper.mapToEntity(input);

        genreEntity = genreRepository.save(genreEntity);

        return genreDTOMapper.mapToDto(genreEntity);
    }

    // ✅ UPDATE
    public GenreResponseDto updateGenre(Long id, @Valid GenreRequestDto input) {

        GenreEntity existingGenre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        existingGenre.setName(input.name());
        existingGenre.setDescription(input.description());

        GenreEntity saved = genreRepository.save(existingGenre);

        return genreDTOMapper.mapToDto(saved);
    }

    // ✅ DELETE
    public void deleteGenre(Long id) {

        GenreEntity existingGenre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        genreRepository.delete(existingGenre);
    }
}