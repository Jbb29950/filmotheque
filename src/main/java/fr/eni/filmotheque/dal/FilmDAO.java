package fr.eni.filmotheque.dal;

import java.util.List;

import fr.eni.filmotheque.bo.Film;

public interface FilmDAO {
	
	List<Film> findAll();
	Film read(long idFilm);
	void create(Film film);
	int findByTitre(String titre);
	void delete(Film film);

}
