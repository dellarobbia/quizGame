package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreatePlayerController implements Initializable
{
	@FXML private TextField playerNameTextField;
	
	@FXML private Button startQuizButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}
	
	public void startQuizButton_Pressed(ActionEvent event) throws IOException
	{
		//Show scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizGameScene.fxml"));
		Parent quizGameParent = loader.load();
		Scene quizGameScene = new Scene(quizGameParent);
		
		
		//pass data to new scene
		QuizGameSceneController quizGameSceneController = loader.getController();
		quizGameSceneController.setActiveGame(new Player(playerNameTextField.getText()));
		quizGameSceneController.dispCurrentQuestion();
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(quizGameScene);
		window.show();
	}
}
