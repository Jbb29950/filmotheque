package fr.eni.filmotheque.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;

public interface FilmRepository {

	public Optional<Film> findFilmById(Integer idFilm);
	public List<Film> findAll();
	
	public List<Genre> findAllGenre();
	
	public void create(Film film);
	
	public Optional<Genre> findGenreById(Integer idGenre);
	
}
