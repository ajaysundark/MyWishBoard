package client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import core.ColleagueBuilder;
import core.ColleagueVO;
import service.db.BirthdayFetcher;

/**
 * @author asundark
 *
 */
public class WishClient {

        public static void main(String[] args) {

        		// Sample DB entries
        		 BirthdayFetcher fetcher = new BirthdayFetcher();
        		 
        		 
				try
				{
					
					ColleagueVO velma;
					ColleagueVO fred;
					ColleagueVO daphne;
					ColleagueVO shaggy;
					ColleagueVO scooby;
					ColleagueVO scrappy;
					
					//wrap strings [first_name, last_name, dob, designation, contact_no, email, location] to a colleagueVO object
					velma = wrapToColleague("Velma", "Dinkley", "4/05/1986", "TheGenius", "404317", "velma.dinkley@hannabarbera.org", "Caravan17");
					fred = wrapToColleague("Fred", "Jones", "6/6/1981", "TheOverActor", "404311", "fred.jones@hannabarbera.org", "Caravan11");
					daphne = wrapToColleague("Daphne", "Blake", "09/01/1982", "TheBlonde", "404309", "daphne.blake@hannabarbera.org", "Caravan09");
					shaggy = wrapToColleague("Shaggy", "Roggers", "29/3/1980", "TheBestPal", "404313", "shaggy.roggers@hannabarbera.org", "Caravan13");
					scooby = wrapToColleague("Scooby", "Doo", "07/09/1987", "TheHero", "404314", "scooby.doo@hannabarbera.org", "Caravan14");
					scrappy = wrapToColleague("Scrappy", "Doo", "11/10/1992", "TheBold", "404315", "scrappy.doo@hannabarbera.org", "Caravan15");
					
					fetcher.addColleagueToDB(velma);
					fetcher.addColleagueToDB(fred);
					fetcher.addColleagueToDB(daphne);
					fetcher.addColleagueToDB(shaggy);
					fetcher.addColleagueToDB(scooby);
					fetcher.addColleagueToDB(scrappy);
				} catch (ParseException e)
				{
					System.out.println("Invalid input!");
				}
        		 
        		 
        		 
                /**
                 * Fetch Entries from DB through BirthdayFetcher
                 * Send Mail to respective entries through MailDirector
                 * **/

                List<ColleagueVO> birthdays = fetcher.fetchBirthdayColleagues();
                for (ColleagueVO colleague : birthdays)
				{
					System.out.println("Today is my birthday.");
					System.out.println(colleague);
				}
        }

		private static ColleagueVO wrapToColleague(String...params) throws ParseException
		{
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dob = sdf.parse("params[2]");
			ColleagueVO sample = ColleagueBuilder.aColleague()
					
					.addFirstName(params[0])

					.addLastName(params[1])

					.addDOB(dob)

					.addDesignation(params[3])

					.addContactNo(params[4])

					.addEmailId(params[5])

					.addLocation(params[6])

					.build();
			return sample;
		}

}