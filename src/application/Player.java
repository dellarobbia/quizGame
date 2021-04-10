package application;

import java.util.ArrayList;

public class Player 
{
	private String name;
	private int score;
	private ArrayList<Answer> answers;
	
	//New Player
	public Player(String name) 
	{
		setName(name);
		setScore(0);
		setAnswers(new ArrayList<Answer>());
	}
	
	public Player(String name, int score)
	{
		setName(name);
		setScore(score);
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getScore()
	{
		return score;
	}
	public String getScoreString()
	{
		String scoreString = null;
		scoreString = scoreString + score;
		return Integer.toString(score);
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public ArrayList<Answer> getAnswers()
	{
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers)
	{
		this.answers = answers;
	}
	
	public void submitUserAnswer(Question question, String answer, long timestamp)
	{
		answers.add(new Answer(question, answer, timestamp));
	}
	public void submitUserAnswer(Answer answer)
	{
		answers.add(answer);
	}
	
	public void calcCurrentScore(ArrayList<Question> questions)
	{
		score = 0;
		int counter = 0;
		int numAnswers = answers.size() - 1;
		String answer = null;
		String correctAnswer = null;
		
		for(counter = 0; counter <= numAnswers; counter++)
		{
			answer = answers.get(counter).getUserAnswer();
			correctAnswer = questions.get(counter).getCorrectAnswer();
			
			if(answer.equals(correctAnswer))
				score += 1;
		}
	}
	
	public long sumTime()
	{
		long totalTime = 0;
		
		for(Answer answer : answers)
		{
			totalTime += answer.getTimestamp();
		}
		
		return totalTime;
	}
}
