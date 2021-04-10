package application;

import java.util.ArrayList;

public class Question
{
	private String questionPrompt;
	private ArrayList<String> possibleAnswers;
	private String correctAnswer;
	
	public Question(String value1, String value2, ArrayList<String> possibleAnswers, String correctAnswer) 
	{
		setQuestionPrompt(value1, value2);
		setPossibleAnswers(possibleAnswers);
		setCorrectAnswer(correctAnswer);
	}
	
	public String getQuestionPrompt()
	{
		return questionPrompt;
	}
	public void setQuestionPrompt(String value1, String value2)
	{
		questionPrompt = value1 + " + " + value2;
	}
	
	public ArrayList<String> getPossibleAnswers()
	{
		return possibleAnswers;
	}
	public void setPossibleAnswers(ArrayList<String> possibleAnswers)
	{
		this.possibleAnswers = possibleAnswers;
	}
	
	public String getCorrectAnswer()
	{
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}
}
