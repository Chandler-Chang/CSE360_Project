/*Quick explanation on what all the different files mean
//Main.java just initializes the first stage/scene and pops up the login page
The fxml files are all the visuals/gui. Basically the gpu part of the code
The java files are the controllers of each page individually, the cpu part of the code */

package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	//Stage means window, scene is the content inside the window
	private Stage primaryStage;
	
	//makes the window be the loginwindow
    @Override
    public void start(Stage primaryStage){
    	this.primaryStage = primaryStage;
    	LoginWindow();
    }

    public void LoginWindow(){
    	try {
    		//loads the content of the loginpage.fxml file into a pane
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
    		AnchorPane pane = loader.load();
    		
    		//Calls the loginpage controller and sets it as main controller (I think, not really sure)
    		LoginController loginController = loader.getController();
    		loginController.setMain(this);
    		
    		//puts everything inside the pane as a scene and shows it
    		Scene scene = new Scene(pane);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Office Aumation System (OAS)");
    		primaryStage.show();
    		
    		loginController.setData();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}