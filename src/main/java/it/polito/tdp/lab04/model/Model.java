package it.polito.tdp.lab04.model;

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

}
