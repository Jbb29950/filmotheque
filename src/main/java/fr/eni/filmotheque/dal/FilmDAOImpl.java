package fr.eni.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;

@Repository
public class FilmDAOImpl implements FilmDAO {

	private static final String FIND_ALL = "SELECT id, titre, annee, duree, synopsis, id_genre, id_realisateur FROM film";
	private static final String FIND_BY_ID = "SELECT id, titre, annee, duree, synopsis, id_genre, id_realisateur FROM film WHERE id = :idFilm";
	private static final String INSERT = "INSERT INTO FILM (titre,annee,duree,synopsis, id_realisateur,id_genre) VALUES (:titre,:annee,:duree,:synopsis,:idRealisateur,:idGenre)";
	private static final String COUNT_FILM_BY_TITRE = "SELECT count(*) nbFilm FROM FILM WHERE titre = :titre";
	private static final String DELETE_FILM="delete from film where id = :id";
	private static final String DELETE_ACTEURS="DELETE FROM ACTEURS WHERE id_film = :id";
	private static final String DELETE_AVIS="DELETE FROM AVIS WHERE id_film = :id";


	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	public FilmDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Film> findAll() {
		return jdbcTemplate.query(FIND_ALL, new FilmRowMapper());
	}

	@Override
	public Film read(long idFilm) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("idFilm", idFilm);
		
		
		return jdbcTemplate.queryForObject(FIND_BY_ID, map, new FilmRowMapper());
	}

	@Override
	public void create(Film film) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("titre", film.getTitre());
		map.addValue("annee", film.getAnnee());
		map.addValue("duree", film.getDuree());
		map.addValue("synopsis", film.getSynopsis());
		map.addValue("idRealisateur", film.getRealisateur().getId());
		map.addValue("idGenre", film.getGenre().getId());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
		jdbcTemplate.update(INSERT, map, keyHolder);
		
		if(keyHolder!= null && keyHolder.getKey() != null) {
			int idFilm = keyHolder.getKey().intValue();
			film.setId(idFilm);
		}
	}

	@Override
	public int findByTitre(String titre) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("titre", titre);
		
		return this.jdbcTemplate.queryForObject(COUNT_FILM_BY_TITRE, map, Integer.class);
	}

	@Override
	public void delete(Film film) {
	    MapSqlParameterSource map = new MapSqlParameterSource();
	    map.addValue("id", film.getId());

	    jdbcTemplate.update(DELETE_AVIS, map);
	    jdbcTemplate.update(DELETE_ACTEURS, map);

	    
	    jdbcTemplate.update(DELETE_FILM, map);
	}


class FilmRowMapper implements RowMapper<Film>{

	@Override
	public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
		Genre genre = new Genre();
		genre.setId(rs.getLong("id_genre"));
		
		Participant realisateur = new Participant();
		realisateur.setId(rs.getLong("id_realisateur"));

		
		Film film = new Film(rs.getInt("id"), 
				rs.getString("titre"), rs.getInt("annee"), rs.getInt("duree"), rs.getString("synopsis"), genre, realisateur);
		
		return film;
	}
	
	
	
	
}}

