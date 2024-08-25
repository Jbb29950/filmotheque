package fr.eni.filmotheque.bo;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Film implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	
	@NotBlank(message = "Le titre est obligatoire")
	@Size(max = 250,  message = "Le titre ne doit pas dépasser les 250 caractères")
	private String titre;
	
	@Min(value = 1900,  message = "L'année doit être postérieure à 1900")
	private int annee;
	@Min(value = 1, message = "La durée doit être supérieure à 0")
	private int duree;
	@Size(min = 20, max = 250, message = "Le synopsis doit contenir en 20 et 250 caractères")
	private String synopsis;
	
	@NotNull(message="Le genre est obligatoire")
	private Genre genre;
	
	
	private Participant realisateur;
	
	//Constructeurs
	public Film() {
		// TODO Auto-generated constructor stub
	}
	
	public Film(String titre) {
		super();
		this.titre = titre;
	}
	
	public Film(int id, String titre) {
		super();
		this.id = id;
		this.titre = titre;
	}
	
	

	public Film(int id, String titre, Genre genre) {
		super();
		this.id = id;
		this.titre = titre;
		this.genre = genre;
	}
	
	
	

	public Film(int id,
			String titre,
			int annee,
			int duree,
			String synopsis,
			Genre genre, Participant realisateur) {
		super();
		this.id = id;
		this.titre = titre;
		this.annee = annee;
		this.duree = duree;
		this.synopsis = synopsis;
		this.genre = genre;
		this.realisateur = realisateur;
	}

	public Film(int id,
			String titre,
			int annee,
			int duree,
			String synopsis,
			Genre genre) {
		super();
		this.id = id;
		this.titre = titre;
		this.annee = annee;
		this.duree = duree;
		this.synopsis = synopsis;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Participant getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(Participant realisateur) {
		this.realisateur = realisateur;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", annee=" + annee + ", duree=" + duree + ", synopsis="
				+ synopsis + ", genre=" + genre + ", realisateur=" + realisateur + "]";
	}

	

	

	
	
	//Getters et setters
	
	
}
