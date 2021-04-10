package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reporter 
{
	private String dbFile;
	private QuizGame quizGameResults;
	
	public Reporter(String dbFile, QuizGame quizGameResults) 
	{
		setDBFile(dbFile);
		setQuizGameResults(quizGameResults);
	}
	
	public void setDBFile(String dbFile)
	{
		this.dbFile = dbFile;
	}
	public void setQuizGameResults(QuizGame quizGameResults)
	{
		this.quizGameResults = quizGameResults;
	}
	
	
	
	
	public void addRecord()
	{
		Connection gameDB = openConnection();
		
		String addRecordSQL = 
				"INSERT INTO Game (Player, Score, TotalTime) " +
				"VALUES (?, ?, ?);";
		
		
		try 
		{
			PreparedStatement addRecordStatement = gameDB.prepareStatement(addRecordSQL);
			addRecordStatement.setString(1, quizGameResults.player.getName());
			addRecordStatement.setInt(2, quizGameResults.player.getScore());
			addRecordStatement.setLong(3,  quizGameResults.player.sumTime());
			addRecordStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		closeConnection(gameDB);
	}
	
	private Connection openConnection()
	{
		Connection newConnection = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			newConnection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
		}
		
		return newConnection;
	}
	private void closeConnection (Connection openConnection)
	{
		try 
		{
			openConnection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
