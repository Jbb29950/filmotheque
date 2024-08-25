package fr.eni.filmotheque.ihm;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.filmotheque.bll.FilmService;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.exception.BusinessException;
import jakarta.validation.Valid;

@Controller
@SessionAttributes({"genresSession"})
public class FilmController {
	
	private FilmService filmService;

	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}
	
	
	@GetMapping("/films")
	public String listerFilms(Model model) {
		
		List<Film> listeFilms = this.filmService.getFilms();
		
		model.addAttribute("films", listeFilms);
		return "list-films";
	}
	
	
	//Méthodes mappées
	//Afficher 1 film connaissant son id !!
	@GetMapping("/film")
	public String afficherFilm(@RequestParam("id") Integer id, Model modele) {
		
		System.out.println("id reçu = "+id);
		
		Film film = null;
		try {
			film = filmService.findFilmById(id);
		}catch(NoSuchElementException exc) {
			//TODO : Gerer le cas ou on ne trouve pas le film 		
		}
		
		modele.addAttribute("film", film);
		
		return "detail-film";
	}
	
	
	@GetMapping("/films/creer")
	public String creerFilm(Model model) {
		// création du film utilisé dans le formulaire
		Film film = new Film();
		model.addAttribute("film", film);
		
		
		// passer la liste de participant dans le modele
		List<Participant> listeParticipant = this.filmService.consulterParticipants();
		model.addAttribute("participants", listeParticipant);
		
		
		return "form-film";
	}
	
	@PostMapping("/films/creer")
	public String creerFilm(@Valid @ModelAttribute("film") Film film,
			BindingResult bindingResult, Model model) {
		System.out.println("film = " + film);
		
		if(bindingResult.hasErrors()) {
			return "form-film";
		}else {
			try {
				filmService.add(film);
				return "redirect:/films";
			} catch (BusinessException e) {
				e.getMessages().forEach(m->{
					ObjectError error = new ObjectError("globalError", m);
					bindingResult.addError(error);
				});
				
				List<Participant> listeParticipant = this.filmService.consulterParticipants();
				model.addAttribute("participants", listeParticipant);
				return "form-film";
			}
			
			
		}
		
		
	}
	
	
	@ModelAttribute("genresSession")
	public List<Genre> chargerGenresEnSession(){
		System.out.println("chargerGenresEnSession");
		return filmService.getGenres();
	}
	@GetMapping("/supprimer")
    public String supprimerFilm(@RequestParam("id") Long id){
System.out.println("id du film a supprimé"+id);
            filmService.supprimerFilm(id);
        
        return "redirect:/films";
    }

}
