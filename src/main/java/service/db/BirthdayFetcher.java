package service.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.ColleagueBuilder;
import core.ColleagueVO;
import ifs.WishLogger;

public class BirthdayFetcher
{
	private WishLogger logger;

	private static DBConnector dbObject = new DBConnector();
	
	private static final String mysqlQuery = "SELECT first_name, last_name, dob, designation, contact_no, email, cubicle" 
	+ " FROM " + dbObject.getTableName() + " WHERE ExtractMonth(dob) = ExtractMonth(CURRENT_DATE) "	+ "AND ExtractDay(dob) = ExtractDay(CURRENT_DATE)";

	public List<ColleagueVO> fetchBirthdayColleagues()
	{
		if (dbObject == null) { return null; }

		logger = dbObject.getMyLogger();
		List<ColleagueVO> resultList = new ArrayList<ColleagueVO>();

		try
		{
			Connection myConnection = dbObject.getDBConnection();
			if (myConnection != null)
			{
				Statement stmt = myConnection.createStatement();

				ResultSet todaysWishes = stmt.executeQuery(mysqlQuery);
				while (todaysWishes.next())
				{
					ColleagueVO birthdayColleague = ColleagueBuilder.aColleague()
							
					.addFirstName(todaysWishes.getString(1))

					.addLastName(todaysWishes.getString(2))

					.addDOB(todaysWishes.getDate(3))

					.addDesignation(todaysWishes.getString(4))

					.addContactNo(todaysWishes.getString(5))

					.addEmailId(todaysWishes.getString(6))

					.addLocation(todaysWishes.getString(7))

					.build();

					resultList.add(birthdayColleague);

				}
			}
			else {
				logger.logError("Failed to connect to DB!");
			}
		} catch (Exception e)
		{
			logger.logError("DB Access Error at " + getClass(), e);
		} finally {
			dbObject.terminateConnection();
		}
		return resultList;
	}
	
	public boolean addColleagueToDB(ColleagueVO colleague)
	{
		if (colleague == null)
		if (dbObject == null) { return false; }
		
		try
		{
			Connection myConnection = dbObject.getDBConnection();
			String addStmt = "INSERT INTO " + dbObject.getTableName() +
						" (first_name, last_name, dob, designation, contact_no, email, cubicle) " +
						"VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stmt = myConnection.prepareStatement(addStmt);
			setAppropriateValues (
					stmt,
					colleague.getFirst_name(),
					colleague.getLast_name(),
					new java.sql.Date(colleague.getDob().getTime()),
					colleague.getDesignation(),
					colleague.getContact_no(),
					colleague.getEmail(),
					colleague.getLocation()
				);
			stmt.executeUpdate();
		} catch (Exception e) {
			logger.logError("DB Access Error at " + getClass(), e);
		} finally {
			dbObject.terminateConnection();
		}
		
		return true;
	}

	private void setAppropriateValues(PreparedStatement stmt, Object... columns) throws SQLException
	{
		for (int i = 0; i < columns.length; i++)
		{
			stmt.setObject(i, columns[i]);
		}
	}
}