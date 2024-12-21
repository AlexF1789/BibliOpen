package com.github.AlexF1789.BibliOpen;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class Server {
    
    @RequestMapping(value="/getLibri", method=RequestMethod.POST, produces="application/json")
    public String getLibri(@RequestParam(name="data") String data) {
        JSONObject risposta = new JSONObject();
        JSONObject parametri = new JSONObject(data);

        try {
            Database db = new Database();
            risposta.put("data", db.getLibri(parametri.getInt("limit"), parametri.getInt("offset")));
            risposta.put("esito", true);
            db.chiudiConnessione();
        } catch(Exception e) {
            risposta.put("esito", false);
            risposta.put("message", e.getMessage());
        }

        return risposta.toString();
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json")
    public String performLogin(@RequestParam(name="data") String data, HttpServletRequest richiesta) {
    	JSONObject risposta = new JSONObject();
    	JSONObject parametri = new JSONObject(data);
    	HttpSession sessione = richiesta.getSession();
    	Utente utente;
    	
    	try {
    		// preleviamo dal JSON parametri i due parametri mail e password
    		String mail = parametri.getString("mail"), password = parametri.getString("password");
    		
    		// criptiamo la password con l'algoritmo di hash SHA256
			password = DigestUtils.sha256Hex(password);
    		
    		// verifichiamo che l'utente esista
    		Database db = new Database();
    		utente = db.getUtente(mail, password);
    		
    		if(utente != null) {
    			// salviamo in sessione l'istanza della classe utente
    			Sessione.creaSessione(sessione, utente);
    			
    			if(Sessione.isValida(sessione))
    				risposta.put("esito", true);
    			else {
    				risposta.put("esito", false);
    				risposta.put("message", "ERR_SESSIONE");
    			}
    			
    		} else {
    			risposta.put("esito", false);
    			risposta.put("message", "NOTAUTH");
    		}
    		
    	} catch(Exception e) {
    		risposta.put("esito", false);
    		risposta.put("message", e.getMessage());
    	}
    	
    	return risposta.toString();
    }

	@RequestMapping(value="/logout", method=RequestMethod.POST, produces="application/json")
	public String performLogout(HttpServletRequest richiesta) {
		JSONObject risposta = new JSONObject();

		Sessione.terminaSessione(richiesta.getSession());
		risposta.put("esito", Sessione.isValida(richiesta.getSession()));

		return risposta.toString();
	}

	@RequestMapping(value="/getSessione", method=RequestMethod.POST, produces="application/json")
	public String getSessione(HttpServletRequest richiesta) {
		JSONObject risposta = new JSONObject();
		HttpSession sessione = richiesta.getSession();

		if(Sessione.isValida(sessione)) {
			JSONArray dati = new JSONArray();
			Utente utente = (Utente) sessione.getAttribute("utente");

			if(utente != null) {

				risposta.put("esito", true);

				dati.put(new JSONObject().put("ID", utente.getID()));
				dati.put(new JSONObject().put("cognome", utente.getCognome()));
				dati.put(new JSONObject().put("nome", utente.getNome()));
				dati.put(new JSONObject().put("telefono", utente.getTelefono()));

				risposta.put("data", dati);

			} else
				risposta.put("esito", false);

		} else
			risposta.put("esito", false);

		return risposta.toString();
	}

}
