package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class patientSearchController {
		private Main main;
		
		public void setMain(Main main) {
			this.main = main;
		}
		public void setData() {
			
		}
		@FXML
		private DatePicker birthdateBox;
		@FXML
		private Button searchButton, backButton;
		@FXML
		private TextField firstNameBox, lastNameBox;
		//Closes the window when back button is pressed. (Sometimes it caused an error)
		@FXML
		public void handleBack(ActionEvent event) {
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		}
		@FXML
		public void handleSearch(ActionEvent event) {
			
		}
}
