package com.github.AlexF1789.BibliOpen;

import org.mariadb.jdbc.Driver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private Connection db;

    // costruttore e "distruttore"
    public Database() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        this.db = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/bibliopen",
            "java", "java"
        );
    }

    public void chiudiConnessione() throws SQLException {
        this.db.close();
    }

    // metodi di accesso ai dati
    public JSONArray getLibri(int limit, int offset) {
        JSONArray risposta = new JSONArray();
        JSONObject record;
        String[] campi = {};

        // restituiamo un vettore vuoto se il numero di dati che ci interessa è <= 0
        if(limit <= 0)
            return risposta;

        // tariamo un limite massimo a 50 libri per transazione
        if(limit >= 50)
            limit = 50;

        PreparedStatement query = this.db.prepareStatement("");
        query.setInt(1,limit);
        query.setInt(2,offset);

        ResultSet risultatoQuery = query.executeQuery();

        while(risultatoQuery.next()) {
            record = new JSONObject();

            for(String campo : campi)
                record.put(campo, risultatoQuery.getString(campo));

            risposta.put(record);
        }

        return risposta;
    }

    // metodi per ottenere oggetti delle classi (utili per modificare i dati)
    public Autore getAutore(int ID) throws SQLException {
        PreparedStatement query = this.db.prepareStatement("SELECT ID, cognome, nome, annoNascita, descrizione FROM autori WHERE ID=? LIMIT 1");
        query.setInt(1,ID);

        ResultSet risultatoQuery = query.executeQuery();

        // portiamo la query al primo risultato e restituiamo null se questo non dovesse esistere
        if(!risultatoQuery.next())
            return null;

        return new Autore(
            risultatoQuery.getString("cognome"),
            risultatoQuery.getString("nome"),
            risultatoQuery.getString("descrizione"),
            risultatoQuery.getInt("annoNascita"),
            risultatoQuery.getInt("ID")
        );
    }

    // metodi di sincronizzazione
    public boolean sincronizzaAutore(Autore autore) throws SQLException {
        PreparedStatement query = this.db.prepareStatement("UPDATE autori SET cognome=?, nome=?, descrizione=?, annoNascita=? WHERE ID=?");
        query.setString(1,autore.getCognome());
        query.setString(1,autore.getNome());
        query.setString(1,autore.getDescrizione());
        query.setInt(1,autore.getAnnoNascita());
        query.setInt(1,autore.getID());

        int righeModificate = query.executeUpdate();

        return (righeModificate > 0);
    }

    public boolean sincronizzaUtente(Utente utente) throws SQLException {
        PreparedStatement query = this.db.prepareStatement("UPDATE utenti SET cognome=?, nome=? WHERE ID=?");
        query.setString(1,utente.getCognome());
        query.setString(2,utente.getNome());
        query.setInt(3,utente.getID());

        int righeModificate = query.executeUpdate();

        return (righeModificate > 0);
    }
}
