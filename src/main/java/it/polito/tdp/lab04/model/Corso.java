package it.polito.tdp.lab04.model;

public class Corso {
	
	String nome; int crediti; String codins; int pd;


	public Corso(String nome, int crediti, String codins, int pd) {
		super();
		this.nome = nome;
		this.crediti = crediti;
		this.codins = codins;
		this.pd = pd;
	}


	public String getNome() {
		return nome;
	}


	public int getCrediti() {
		return crediti;
	}


	public String getCodins() {
		return codins;
	}


	public int getPd() {
		return pd;
	}


	@Override
	public String toString() {
		return "Corso [nome=" + nome + ", crediti=" + crediti + ", codins=" + codins + ", pd=" + pd + "]";
	}
	
	
	
	

}
