package nl.les12vinylshopdto.les12vinylshopdto.services;

import nl.les12vinylshopdto.les12vinylshopdto.entities.GenreEntity;
import nl.les12vinylshopdto.les12vinylshopdto.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    //   FIND ALL
    public List<GenreEntity> findAllGenres() {
        return genreRepository.findAll();
    }

    //   FIND BY ID
    public GenreEntity findGenreById(Long id) {
        return getGenreById(id);
    }

    //   CREATE
    public GenreEntity createGenre(GenreEntity input) {
        return genreRepository.save(input);
    }

    public GenreEntity updateGenre(Long id, GenreEntity input) {
        GenreEntity existingGenre = getGenreById(id);

        if (existingGenre == null) {
            return null;
        }

        existingGenre.setName(input.getName());
        existingGenre.setDescription(input.getDescription());

        return genreRepository.save(existingGenre);
    }

    public void deleteGenre(Long id) {
        GenreEntity existingGenre = getGenreById(id);

        if (existingGenre != null) {
            genreRepository.delete(existingGenre);
        }
    }

    private GenreEntity getGenreById(Long id) {
        Optional<GenreEntity> optionalGenre = genreRepository.findById(id);
        return optionalGenre.orElse(null);
    }



}