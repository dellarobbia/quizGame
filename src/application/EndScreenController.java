package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EndScreenController implements Initializable
{
	//Labels
	@FXML private Label answerLabel1;
	@FXML private Label answerLabel2;
	@FXML private Label answerLabel3;
	@FXML private Label answerLabel4;
	@FXML private Label answerLabel5;
	@FXML private Label answerLabel6;
	@FXML private Label answerLabel7;
	@FXML private Label answerLabel8;
	@FXML private Label answerLabel9;
	@FXML private Label answerLabel10;
	@FXML private Label answerTotalLabel;
	
	//Buttons
	@FXML private Button finishedButton;
	@FXML private Button reportButton;
	
	private QuizGame finishedGame;
	
	public void setFinishedGame(QuizGame finishedGame)
	{
		this.finishedGame = finishedGame;
	}
	
	public void dispResults()
	{
		//set labels
		answerLabel1.setText(finishedGame.player.getAnswers().get(0).toString());
		answerLabel2.setText(finishedGame.player.getAnswers().get(1).toString());
		answerLabel3.setText(finishedGame.player.getAnswers().get(2).toString());
		answerLabel4.setText(finishedGame.player.getAnswers().get(3).toString());
		answerLabel5.setText(finishedGame.player.getAnswers().get(4).toString());
		answerLabel6.setText(finishedGame.player.getAnswers().get(5).toString());
		answerLabel7.setText(finishedGame.player.getAnswers().get(6).toString());
		answerLabel8.setText(finishedGame.player.getAnswers().get(7).toString());
		answerLabel9.setText(finishedGame.player.getAnswers().get(8).toString());
		answerLabel10.setText(finishedGame.player.getAnswers().get(9).toString());
		answerTotalLabel.setText("Total Score: " + finishedGame.player.getScoreString() + " Total Time: " + sumTime() + " secs");
		//write results to DB
		submitReport();
	}
	
	private long sumTime()
	{
		long totalTime = 0;
		
		for(Answer answer : finishedGame.player.getAnswers())
		{
			totalTime += answer.getTimestamp();
		}
		
		return totalTime;
	}
	
	public void finishedButton_Pressed(ActionEvent event)
	{
		closeProgram(event);
	}
	
	public void reportButton_Pressed(ActionEvent event)
	{
		submitReport();
		closeProgram(event);
	}
	
	private void closeProgram(ActionEvent event)
	{
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	private void submitReport()
	{
		Reporter gameReporter = new Reporter("QuizGameDB.db", finishedGame);
		gameReporter.addRecord();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}
}
