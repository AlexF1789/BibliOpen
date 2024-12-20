/* Sessione: classe usata per l'area personale */

package com.github.AlexF1789.BibliOpen;

import jakarta.servlet.http.HttpSession;

public class Sessione {
	
	public static boolean creaSessione(HttpSession sessione, Utente utente) {
		sessione.setAttribute("utente", utente);
		
		return Sessione.isValida(sessione);
	}
	
	public static boolean isValida(HttpSession sessione) {
		Utente utente = (Utente) sessione.getAttribute("utente");
		
		return utente != null;
	}
	
	public static Utente getUtente(HttpSession sessione) {
		if(Sessione.isValida(sessione))
			return (Utente) sessione.getAttribute("utente");
		
		return null;
	}
	
	public static boolean terminaSessione(HttpSession sessione) {
		sessione.invalidate();
		
		return !Sessione.isValida(sessione);
	}
	
}
