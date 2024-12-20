package com.github.AlexF1789.BibliOpen;

public class Utente {

    private String cognome, nome, telefono;
    private int ID;

    public Utente(String cognome, String nome, String telefono, int ID) {
        this.ID = ID;
        this.cognome = cognome;
        this.nome = nome;
        this.telefono = telefono;
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
    
    public String getTelefono() {
    	return this.telefono;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setTelefono(String telefono) {
    	this.telefono = telefono;
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
