package it.polito.tdp.lab04.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corsodao; StudenteDAO studentedao;
	
	public Model() {
		this.corsodao = new CorsoDAO();
		this.studentedao = new StudenteDAO();
	}
	
	public CorsoDAO getCorsodao() {
		return corsodao;
	}
	
	public StudenteDAO getStudenteDAO() {
		return studentedao;
	}
	
	public Map<Integer, Studente> getMappaStudenti(){
		return studentedao.getMappaStudenti();
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		return corsodao.getCorsidelloStudente(matricola);
	}
	
	public List<Corso> getTuttiICorsi() {
		return corsodao.getTuttiICorsi();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return corsodao.getStudentiIscrittiAlCorso(corso);
	}

	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsodao.inscriviStudenteACorso(studente, corso);
	}
	
	public Studente getStudenteDataMatricola(Integer matricola) {
		return studentedao.getStudenteDataMatricola(matricola);
	}
}
