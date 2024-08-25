package fr.eni.filmotheque.dal;

import java.util.List;

import fr.eni.filmotheque.bo.Genre;

public interface GenreDAO {
	
	Genre read(long id);
	
	List<Genre> findAll();

}
