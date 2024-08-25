package fr.eni.filmotheque.dal;

import java.util.List;import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Participant;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO {
	
	private static final String FIND_ALL = "select id, nom, prenom from participant";
	private static final String FIND_BY_ID = "select id, nom, prenom from participant where id = :idParticipant";
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public ParticipantDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Participant> findAll() {
		return this.jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Participant.class));
	}

	@Override
	public Participant read(long id) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("idParticipant", id);
		return this.jdbcTemplate.queryForObject(FIND_BY_ID, map, new BeanPropertyRowMapper<>(Participant.class));
	}

}
