package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class QuizGameSceneController implements Initializable 
{
	//Labels
	@FXML private Label playerNameLabel;
	@FXML private Label playerScoreLabel;
	@FXML private Label timerLabel;
	@FXML private Label questionNumberLabel;
	@FXML private Label questionPromptLabel;
	@FXML private Label choice1Label;
	@FXML private Label choice2Label;
	@FXML private Label choice3Label;
	@FXML private Label choice4Label;
	
	//Buttons
	@FXML private Button choice1Button;
	@FXML private Button choice2Button;
	@FXML private Button choice3Button;
	@FXML private Button choice4Button;
	
	//Game Properties
	private QuizGame activeGame;
	private int currentQuestionIndex = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}
	
	
	//Starts the game
	public void setActiveGame(Player activePlayer)
	{
		activeGame = new QuizGame(activePlayer);
	}
	
	//ActiveGame properties
	public Player getActivePlayer()
	{
		return activeGame.player;
	}
	public Question getCurrentQuestion()
	{
		return activeGame.questions.get(currentQuestionIndex);
	}
	public ArrayList<String> getCurrentChoices()
	{
		return getCurrentQuestion().getPossibleAnswers();
	}
	public long getCurrentTimestamp()
	{
		return 5;
	}
	
	//Methods
	private String getQuestionNumberString()
	{
		int questionNumber = currentQuestionIndex + 1;
		return "Question " + questionNumber + " of " + activeGame.getQuestions().size();
	}
	
	private void itterateQuestionNumber()
	{
		currentQuestionIndex++;
	}
	
	public void dispCurrentQuestion()
	{
		Question currentQuestion = activeGame.getQuestions().get(currentQuestionIndex);
		//Player Header
		playerNameLabel.setText(activeGame.getPlayer().getName());
		//Question Header
		questionNumberLabel.setText(getQuestionNumberString());
		//Question Prompt
		questionPromptLabel.setText(currentQuestion.getQuestionPrompt());
		//Choice Labels
		choice1Label.setText(getCurrentChoices().get(0));
		choice2Label.setText(getCurrentChoices().get(1));
		choice3Label.setText(getCurrentChoices().get(2));
		choice4Label.setText(getCurrentChoices().get(3));
	}
	
	//Actions
	public void choiceButton1_Pressed(ActionEvent event)
	{
		setUserAnswer(getCurrentChoices().get(0), event);
	}
	public void choiceButton2_Pressed(ActionEvent event)
	{
		setUserAnswer(getCurrentChoices().get(1), event);
	}
	public void choiceButton3_Pressed(ActionEvent event)
	{
		setUserAnswer(getCurrentChoices().get(2), event);
	}
	public void choiceButton4_Pressed(ActionEvent event)
	{
		setUserAnswer(getCurrentChoices().get(3), event);
	}
	
	private void setUserAnswer(String selection, ActionEvent event)
	{
		if((currentQuestionIndex + 1) <= activeGame.getQuestions().size())
		{
			activeGame.player.submitUserAnswer(new Answer(getCurrentQuestion(), selection, getCurrentTimestamp()));
			itterateQuestionNumber();
			updateScore();
			if((currentQuestionIndex + 1) <= activeGame.getQuestions().size())
				dispCurrentQuestion();
		}
		if((currentQuestionIndex + 1) > activeGame.getQuestions().size())
		{
			try 
			{
				nextScene(event);
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	private void nextScene(ActionEvent event) throws IOException
	{
		//Show scene
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreen.fxml"));
		Parent endScreenParent = loader.load();
		Scene endScreenScene = new Scene(endScreenParent);
		
		
		//pass data to new scene
		EndScreenController endScreenController = loader.getController();
		endScreenController.setFinishedGame(activeGame);
		endScreenController.dispResults();
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(endScreenScene);
		window.show();
	}
	
	private void updateScore()
	{
		activeGame.player.calcCurrentScore(activeGame.getQuestions());
		playerScoreLabel.setText(activeGame.player.getScoreString());
	}
}
