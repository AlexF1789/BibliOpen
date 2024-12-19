package com.github.AlexF1789.BibliOpen;

public class Utente {

    private String cognome, nome;
    private int ID;

    public Utente(String cognome, String nome, int ID) {
        this.ID = ID;
        this.cognome = cognome;
        this.nome = nome;
    }

    // getters e setters
    public String getCognome() {
        return cognome;
    }
    
    public String getNome() {
        return nome;
    }

    public int getID() {
        return ID;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean sincronizzaModifiche() {
        try {
            Database db = new Database();
            db.sincronizzaUtente(this);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
