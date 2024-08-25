package fr.eni.filmotheque.ihm.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bll.FilmService;
import fr.eni.filmotheque.bo.Genre;

@Component
public class StringToGenreConverter implements Converter<String, Genre> {

	private FilmService filmService;
	
	public StringToGenreConverter(FilmService filmService) {
		this.filmService = filmService;
	}
	
	@Override
	public Genre convert(String id) {
		System.out.println("idGenre = " + id);
		
		return this.filmService.findGenreById(Integer.parseInt(id));
	}

	
	
	
}
