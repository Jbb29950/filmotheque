package fr.eni.filmotheque.ihm.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bll.FilmService;
import fr.eni.filmotheque.bo.Participant;

@Component
public class StringToParticipant implements Converter<String, Participant> {

	private FilmService filmService;
	
	public StringToParticipant(FilmService filmService) {
		this.filmService = filmService;
	}
	
	@Override
	public Participant convert(String idParticipant) {
		
		return this.filmService.consulterParticipantParId(Long.parseLong(idParticipant));
	}

}
