package com.github.AlexF1789.BibliOpen;

public class Autore {

    private String cognome, nome, descrizione;
    private int annoNascita, ID;

    public Autore(String cognome, String nome, String descrizione, int annoNascita, int ID) {
        this.cognome = cognome;
        this.nome = nome;
        this.descrizione = descrizione;
        this.annoNascita = annoNascita;
        this.ID = ID;
    }

    // getters e setters
    public String getCognome() {
        return this.cognome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public int getAnnoNascita() {
        return this.annoNascita;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getID() {
        return this.ID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setAnnoNascita(int annoNascita) {
        this.annoNascita = annoNascita;
    }

    public boolean sincronizzaModifiche() {
        try {
            Database db = new Database();
            db.sincronizzaAutore(this);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
