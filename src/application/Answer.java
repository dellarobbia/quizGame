package application;

public class Answer
{
	private Question question;
	private String userAnswer;
	private long timestamp;
	
	public Answer(Question question, String userAnswer, long timestamp)
	{
		setQuestion(question);
		setUserAnswer(userAnswer);
		setTimestamp(timestamp);
	}
	
	public Question getQuestion()
	{
		return question;
	}
	public void setQuestion(Question question)
	{
		this.question = question;
	}
	
	public String getUserAnswer()
	{
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer)
	{
		this.userAnswer = userAnswer;
	}
	
	public long getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}
	
	public boolean userCorrect()
	{
		if(userAnswer.equals(question.getCorrectAnswer()))
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		String isCorrect = "Incorrect";
		if(userCorrect())
			isCorrect = "Correct";
		return question.getQuestionPrompt() + ": " + isCorrect + " " + timestamp + " secs";
	}
}
