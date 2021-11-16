package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class addRemoveController {
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
	public void setData() {
		addRemoveBox.getItems().clear();
		
		addRemoveBox.getItems().addAll(
				"Add",
				"Remove"
				);
	}
	@FXML
	private DatePicker birthdateField;
	@FXML
	private Button backButton, submitButton;
	@FXML
	private ComboBox<String> addRemoveBox;
	@FXML
	private TextField firstNameField, lastNameField, doctorField;
	@FXML
	public void handleBack(ActionEvent event) {
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	@FXML
	public void handleSubmit(ActionEvent event) {
		
	}
}
