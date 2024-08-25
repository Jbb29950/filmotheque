package fr.eni.filmotheque.dal;

import java.util.List;

import fr.eni.filmotheque.bo.Participant;

public interface ParticipantDAO {
	
	List<Participant> findAll();
	Participant read(long id);

}
