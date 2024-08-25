package fr.eni.filmotheque.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;

@Repository
public class FilmRepositoryBouchon implements FilmRepository{

	private List<Film> films;
	private List<Genre> genres;
	private static int indexIdFilm;
	
	public FilmRepositoryBouchon() {
		genres = new ArrayList<Genre>();
		genres.add(new Genre(1, "S-F"));
		genres.add(new Genre(2, "Comdedie"));
		genres.add(new Genre(3, "Drame"));
		
		
		indexIdFilm=1;
		films = new ArrayList<>();
		Film film = new Film(indexIdFilm++, "The Fly", genres.get(0));
		films.add(film);
		film = new Film(indexIdFilm++, "ExistenZ");
		film.setGenre(genres.get(0));
		films.add(film);
		film = new Film(indexIdFilm++, "Le grand bleu");
		film.setGenre(genres.get(2));
		films.add(film);
	}
	
	@Override
	public Optional<Film> findFilmById(Integer idFilm) {

		return films.stream().filter(film->film.getId()==idFilm).findFirst();
	}

	@Override
	public List<Film> findAll() {
		return films;
	}

	@Override
	public List<Genre> findAllGenre() {
		return genres;
	}

	@Override
	public void create(Film film) {
		film.setId(indexIdFilm++);
		films.add(film);
	}

	@Override
	public Optional<Genre> findGenreById(Integer idGenre) {
		return genres.stream().filter(g->g.getId() == idGenre).findAny();
	}

}



