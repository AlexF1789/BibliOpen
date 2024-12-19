package com.github.AlexF1789.BibliOpen;

public class Libro {
    
    private String titolo;
    private int ID, annoPubblicazione;
    private Autore autore;

    public Libro(String titolo, int ID, int annoPubblicazione, Autore autore) {
        this.titolo = titolo;
        this.ID = ID;
        this.annoPubblicazione = annoPubblicazione;
        this.autore = autore;
    }

    // getters e setters
    public String getTitolo() {
        return this.titolo;
    }

    public int getID() {
        return this.ID;
    }
    
    public int getAnnoPubblicazione() {
        return this.annoPubblicazione;
    }

    public Autore getAutore() {
        return this.autore;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }

}
