package it.polito.tdp.lab04.model;

public class Studente {
	
	private String nome; private String cognome; private int matricola; private String cds;


	public Studente(String nome, String cognome, int matricola, String cds) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.cds = cds;
	}


	
	public String getNome() {
		return nome;
	}



	public String getCognome() {
		return cognome;
	}



	public int getMatricola() {
		return matricola;
	}



	public String getCds() {
		return cds;
	}



	@Override
	public String toString() {
		return "Studente [nome=" + nome + ", cognome=" + cognome + ", matricola=" + matricola + "]";
	}
	
	
	
	

}
