package fr.eni.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Genre;

@Repository
public class GenreDAOImpl implements GenreDAO {

	private static final String FIND_BY_ID = "SELECT id, titre FROM Genre WHERE id = :idGenre";
	private static final String FIND_ALL = "SELECT id, titre FROM Genre";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public GenreDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public Genre read(long id) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("idGenre", id);
		
		return this.jdbcTemplate.queryForObject(FIND_BY_ID, 
				map, 
				new BeanPropertyRowMapper<>(Genre.class));
	}

	@Override
	public List<Genre> findAll() {
		
		return this.jdbcTemplate.query(FIND_ALL, new GenreRowMapper());
	}

}


class GenreRowMapper implements RowMapper<Genre>{

	@Override
	public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
		Genre genre = new Genre(rs.getLong("id"), rs.getString("titre"));

		return genre;
	}
	
	
	
	
}


