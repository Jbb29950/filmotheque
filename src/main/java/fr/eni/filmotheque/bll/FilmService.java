package fr.eni.filmotheque.bll;

import java.util.List;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.exception.BusinessException;

public interface FilmService {
	Film findFilmById(Integer id);
	
	List<Film> getFilms();
	
	List<Genre> getGenres();
	
	void add(Film film) throws BusinessException;
	
	Genre findGenreById(Integer id);
	
	List<Participant> consulterParticipants();
	
	Participant consulterParticipantParId(long id);

	void supprimerFilm(Long id);
	
	
	
}
