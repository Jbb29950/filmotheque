package fr.eni.filmotheque.bo;

import java.io.Serializable;

public class Genre implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String titre;
	
	public Genre() {
		
	}
	
	public Genre(long id, String titre) {
		super();
		this.id = id;
		this.titre = titre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", titre=" + titre + "]";
	}
	
	

}
