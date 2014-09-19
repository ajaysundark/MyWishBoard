package main.java.service.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.core.ColleagueBuilder;
import main.java.core.ColleagueVO;
import main.java.ifs.WishLogger;

public class BirthdayFetcher
{
	private ResultSet todaysWishes;
	private List<ColleagueVO> resultList = new ArrayList<ColleagueVO>();
	private WishLogger logger;

	DBConnector dbObject = new DBConnector();
	private static final String mysqlQuery = "SELECT last_name, dob, designation, contact_no, email, cubicle" 
	+ " FROM data_table " + "WHERE ExtractMonth(dob) = ExtractMonth(CURRENT_DATE) "	+ "AND ExtractDay(dob) = ExtractDay(CURRENT_DATE)";

	public List<ColleagueVO> fetchBirthdayColleagues()
	{
		if (dbObject == null) { return null; }

		logger = dbObject.getMyLogger();

		try
		{
			if (dbObject.initConnection())
			{
				Connection myConnection = dbObject.getDBConnection();
				Statement stmt = myConnection.createStatement();

				todaysWishes = stmt.executeQuery(mysqlQuery);
				while (todaysWishes.next())
				{
					ColleagueVO birthdayColleague = ColleagueBuilder.aColleague()

					.addLastName(todaysWishes.getString(1))

					.addDOB(todaysWishes.getDate(2))

					.addDesignation(todaysWishes.getString(3))

					.addContactNo(todaysWishes.getString(4))

					.addEmaidId(todaysWishes.getString(5))

					.addLocation(todaysWishes.getString(6))

					.build();

					resultList.add(birthdayColleague);

				}
			}
		} catch (Exception e)
		{
			logger.logError("DB Access Error at " + getClass(), e);
		} finally {
			dbObject.terminateConnection();
		}
		return resultList;
	}
}