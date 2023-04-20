package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button1;

    @FXML
    private Button Button2;

    @FXML
    private Button Button3;

    @FXML
    private Button Button4;

    @FXML
    private Button Pulsante;

    @FXML
    private TextField Cognome;

    @FXML
    private TextField Matricola;

    @FXML
    private TextField Nome;

    @FXML
    private TextArea Risultato;

    @FXML
    private ComboBox<String> SceltaCorsi;
    
    Model model;
    
    @FXML
    void CercaCorsi(ActionEvent event) {
    	String m = this.Matricola.getText();
    	Integer matricola = 0;
    	try {
    		matricola = Integer.parseInt(m);
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		this.Risultato.setText("Matricola inserita nel formato sbagliato");
    	}
    	
    	//controllo esistenza studente
    	if (model.getMappaStudenti().containsKey(matricola)) {
    		
    		LinkedList<Corso> corsi = (LinkedList<Corso>) model.getCorsiStudente(matricola);
		    	
	    	if (SceltaCorsi.getValue() == null) {
	    		
		    	for (Corso c : corsi) {
		    		Risultato.appendText(c.toString() + "\n");
		    	}
		    	if (corsi.isEmpty()) {
		    		Risultato.setText("Nessuno corso frequentato dallo studente " + matricola);
		    	}
	    	}
	    	else {
	    		String corso = SceltaCorsi.getValue();
	    		boolean flag = false;
	    		for (Corso c : corsi) {
	    			if (c.getNome().compareTo(corso)==0) {
	    				flag = true;
	    			}
	    		}
	    		if (flag) {
	    			Risultato.setText("Studente già iscritto al corso");
	    		}else {
	    			Risultato.setText("Studente non iscritto al corso");
	    		}
	    	}
    	}
	    else {
	    	Risultato.setText("Studente non esistente");
	    	}
    }

    @FXML
    void CercaIscrittiCorso(ActionEvent event) {
    	Risultato.clear();
    	String corso = this.SceltaCorsi.getValue();
    	Corso C = null;
    	for (Corso c : model.getTuttiICorsi()) {
    		if (c.getNome().compareTo(corso)==0) {
    			C = c;
    		}
    	}
    	
    	LinkedList<Studente> listaStudenti = (LinkedList<Studente>) model.getStudentiIscrittiAlCorso(C);
    	
    	if (Matricola.getText() == null) {
	    	for (Studente s : listaStudenti) {
	    		Risultato.appendText(s.toString() + "\n");
	    	}
	    	if (listaStudenti.isEmpty()) {
	    		Risultato.setText("Nessuno studente appartenente al corso " + corso);
	    	}
    	}
    	
    	else {
    		String m = this.Matricola.getText();
        	Integer matricola = 0;
        	try {
        		matricola = Integer.parseInt(m);
        	}catch(NumberFormatException e) {
        		e.printStackTrace();
        		this.Risultato.setText("Matricola inserita nel formato sbagliato");
        	}
    		
        	boolean flag = false;
        	for (Studente s : listaStudenti) {
        		if(s.getMatricola()==matricola) {
        			flag = true;
        		}
        	}
        	
        	if (flag) {
    			Risultato.setText("Studente già iscritto al corso");
    		}else {
    			Risultato.setText("Studente non iscritto al corso");
    		}
        	
    	}
    }

    @FXML
    void Iscrivi(ActionEvent event) {
    	String corso = SceltaCorsi.getValue();
    	
    	String m = this.Matricola.getText();
    	Integer matricola = 0;
    	try {
    		matricola = Integer.parseInt(m);
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		this.Risultato.setText("Matricola inserita nel formato sbagliato");
    	}
    	Studente s = model.getMappaStudenti().get(matricola);
    	Corso C = null;
    	for (Corso c : model.getTuttiICorsi()) {
    		if (c.getNome().compareTo(corso)==0) {
    			C = c;
    		}
    	}
    	boolean flag = false;
    	if (C!=null) {
    		flag = model.inscriviStudenteACorso(s, C);
	    	if(flag) {
	    		Risultato.setText("Studente iscritto al corso");
	    	}else {
	    		Risultato.setText("Studente già iscritto al corso");
	    	}
    	}else {
    		Risultato.setText("Corso non trovato");
    	}

    }

    @FXML
    void Reset(ActionEvent event) {
    	Nome.clear();
    	Matricola.clear();
    	Cognome.clear();
    	Risultato.clear();
    }
    
    @FXML
    void TrovaStudente(ActionEvent event) {
    	String m = this.Matricola.getText();
    	Studente s = null;
    	try {
    		Integer matricola = Integer.parseInt(m);
    		s = model.getStudenteDataMatricola(matricola);
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		this.Risultato.setText("Matricola inserita nel formato sbagliato");
    	}
    	if (s!=null) {
    		this.Nome.setText(s.getNome());
    		this.Cognome.setText(s.getCognome());
    	}
    	else {
    		this.Risultato.setText("Studente non trovato! \n Matricola non presente nel database");
    	}
    }

    @FXML
    void initialize() {
        assert Button1 != null : "fx:id=\"Button1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Button2 != null : "fx:id=\"Button2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Button3 != null : "fx:id=\"Button3\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Button4 != null : "fx:id=\"Button4\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Pulsante != null : "fx:id=\"Pulsante\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Cognome != null : "fx:id=\"Cognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Matricola != null : "fx:id=\"Matricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Nome != null : "fx:id=\"Nome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Risultato != null : "fx:id=\"Risultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert SceltaCorsi != null : "fx:id=\"SceltaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	for (Corso c : model.getTuttiICorsi()) {
        	SceltaCorsi.getItems().add(c.getNome());
        }
        SceltaCorsi.getItems().add("");
    }
}
