package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;

public class vitalsController {
	private Main main;
	private Patient patient;
	
	@FXML 
	private TextArea vitalsArea;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void setVitals() {
		vitalsArea.setText(patient.getVitals());
	}
}

