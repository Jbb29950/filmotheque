package fr.eni.filmotheque.bll;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.dal.FilmDAO;
import fr.eni.filmotheque.dal.FilmRepository;
import fr.eni.filmotheque.dal.GenreDAO;
import fr.eni.filmotheque.dal.ParticipantDAO;
import fr.eni.filmotheque.exception.BusinessException;

@Service
public class FilmServiceImpl implements FilmService {
	
	private FilmRepository filmRepository;
	private FilmDAO filmDAO;
	private GenreDAO genreDAO;
	private ParticipantDAO participantDAO;

	public FilmServiceImpl(FilmRepository filmRepositor, 
			GenreDAO genreDAO,
			FilmDAO filmDAO,
			ParticipantDAO participantDAO) {
		this.filmRepository = filmRepositor;
		this.genreDAO = genreDAO;
		this.filmDAO = filmDAO;
		this.participantDAO = participantDAO;
	}

	@Override
	public Film findFilmById(Integer id) {
		Film film = filmDAO.read(id); // Récupère un film avec un genre qui a uniquement un id et un realisateur qui a uniquement un id
		film.setGenre(genreDAO.read(film.getGenre().getId())); // recherche un genre complet (id + titre)
		Participant realisateur = participantDAO.read(film.getRealisateur().getId());
		film.setRealisateur(realisateur);
		
		return film;
	}

	@Override
	public List<Film> getFilms() {
		List<Film> films = this.filmDAO.findAll();
		films.forEach(
				f->f.setGenre(this.genreDAO.read(f.getGenre().getId())));
		return films;
	}

	@Override
	public List<Genre> getGenres() {
		return this.genreDAO.findAll();
	}

	@Override
	@Transactional
	public void add(Film film) throws BusinessException {
		BusinessException e = new BusinessException();
		
		// vérification de l'unicité du titre
		int nbFilm = this.filmDAO.findByTitre(film.getTitre());
		if(nbFilm > 0) {
			e.add("Le titre du film existe déjà. Vous ne pouvez pas créé un film existant.");
		}
		
		// vérification de l'existance du genre
		try {
			Genre genre = this.genreDAO.read(film.getGenre().getId());
			if(genre == null) {
				e.add("Le genre n'existe pas. Vous ne pouvez pas créer le film");
			}
		}catch(EmptyResultDataAccessException ex) {
			e.add("Le genre n'existe pas. Vous ne pouvez pas créer le film");
		}
		
		// TODO : tester le réalisateur
		
		
		// TODO : tester les acteurs
		
		
		if(e.getMessages().isEmpty()) {
			this.filmDAO.create(film);
		}else {
			throw e;
		}
		
		
	}

	@Override
	public Genre findGenreById(Integer id) {
		return this.genreDAO.read(id);
	}

	@Override
	public List<Participant> consulterParticipants() {
		return this.participantDAO.findAll();
	}

	@Override
	public Participant consulterParticipantParId(long id) {
		return this.participantDAO.read(id);
	}

	@Override
	public void supprimerFilm(Long id) {
		Film film = filmDAO.read(id);
		this.filmDAO.delete(film);
		
	}
	
	

}
