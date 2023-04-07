package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	final String sql = "SELECT nome,cognome,matricola,CDS" +
			"FROM studente";
	
	public TreeMap<Integer, Studente> getMappaStudenti(){
		
		TreeMap<Integer, Studente> studenti = new TreeMap<Integer, Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");
				int matricola = rs.getInt("matricola");

				System.out.println(nome + " " + cognome + " " + matricola + " " + cds);
			
				studenti.put(matricola, new Studente(nome, cognome, matricola, cds));
				
			}

			conn.close();
			
			return studenti;
		
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	
	public List<Studente> getListaStudenti(){
		return new LinkedList<Studente>(getMappaStudenti().values());
	}

	
	public Studente getStudenteDataMatricola(Integer matricola) {
		Studente s = getMappaStudenti().get(matricola);				
		return s;
	}

}
