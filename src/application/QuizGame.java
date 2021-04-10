package application;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QuizGame 
{
	Player player;
	ArrayList<Question> questions;
	File quizFile;
	
	//Constructor if all values are provided
	public QuizGame(Player player, ArrayList<Question> questions) 
	{
		setPlayer(player);
		setQuestions(questions);
		setQuizFile(new File("input.txt"));
	}
	//Constructor if only player is provided; uses pre-built question/answer set from text file
	public QuizGame(Player player)
	{
		setQuizFile(new File("input.txt"));
		setPlayer(player);
		try 
		{
			setQuestions(buildQuestions());
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Player getPlayer()
	{
		return player;
	}
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public ArrayList<Question> getQuestions()
	{
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions)
	{
		this.questions = questions;
	}
	
	public File getQuizFile()
	{
		return quizFile;
	}
	public void setQuizFile(File quizFile)
	{
		this.quizFile = quizFile;
	}
	
	private ArrayList<Question> buildQuestions() throws FileNotFoundException
	{
		ArrayList<Question> questions = new ArrayList<>();
		FileInputStream fileInput = new FileInputStream(quizFile);
		int fileLines = 0;
		
		//Get number of lines
		byte[] byteArray = new byte[(int)quizFile.length()];
		try 
		{
			fileInput.read(byteArray);
			String data = new String(byteArray);
			String[] stringArray = data.split("\r\n");
			fileLines = stringArray.length;
			fileInput.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//Loop through each line to construct the question array
		//Each Question uses 3 lines of input:
		//  1:questionValue1 questionValue2
		//  2:possible1 possible2 possible3 possible 4
		//  3:correctAnswer
		int lineCounter = 1;
		String questionValue1 = null;
		String questionValue2 = null;
		ArrayList<String> possibleAnswers = new ArrayList<>();
		String correctAnswer = null;
		FileReader fileReader = new FileReader(quizFile);
		Scanner fileScanner = new Scanner(fileReader);
		
		while(lineCounter <= fileLines)
		{
			questionValue1 = (fileScanner.next());
			questionValue2 = (fileScanner.next());
			lineCounter++;
			possibleAnswers.add(fileScanner.next());
			possibleAnswers.add(fileScanner.next());
			possibleAnswers.add(fileScanner.next());
			possibleAnswers.add(fileScanner.next());
			lineCounter++;
			correctAnswer = fileScanner.next();
			lineCounter++;
			
			questions.add(new Question(questionValue1, questionValue2, new ArrayList<String>(possibleAnswers), correctAnswer));
			possibleAnswers.clear();
		}
		
		fileScanner.close();
		
		return questions;
	}
}
