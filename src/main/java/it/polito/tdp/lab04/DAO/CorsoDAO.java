package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	StudenteDAO s = new StudenteDAO();		//è giusto metterlo qui questo?
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				corsi.add(new Corso(nome, numeroCrediti, codins, periodoDidattico));
				
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String cod) {
		for (Corso c : this.getTuttiICorsi()) {
			if (c.getCodins().compareTo(cod)==0) {
				return c;
			}
		}
		return null;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		String sql = "SELECT matricola" +
				"FROM iscrizione" +
				"WHERE codins= ? ";
 
		List<Studente> studenti = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				studenti.add(s.getStudenteDataMatricola(rs.getInt("matricola")));
			}
			
			rs.close();
			st.close();
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException("Errore Db", e);
			
		}
		
		return studenti;
	
	}

	public List<Corso> getCorsidelloStudente(int matricola) {
			
			String sql = "SELECT codins" +
					"FROM iscrizione" +
					"WHERE matricola= ? ";
	 
			List<Corso> corsi = new LinkedList<>();
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, matricola);
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					corsi.add(this.getCorso(rs.getString("codins")));
				}
				
				rs.close();
				st.close();
				conn.close();
			}catch(SQLException e) {
				throw new RuntimeException("Errore Db", e);
				
			}
		
		return corsi;
	
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		if (this.getStudentiIscrittiAlCorso(corso).contains(studente)) {
			return false;
		}
		else {
			
			String sql = "INSERT INTO iscrizione" +
					"VALUES (?,?)";
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, studente.getMatricola());
				st.setString(2, corso.getCodins());
				int i = st.executeUpdate();
				if (i == 1) {
					return true;
				}
				st.close();
				conn.close();
			}catch(SQLException e) {
				throw new RuntimeException("Errore Db", e);
			}

		}
		return false;
	}

}
