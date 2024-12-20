package com.github.AlexF1789.BibliOpen;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
