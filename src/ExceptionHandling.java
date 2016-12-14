
/* This is an Example program for customized Exceptional handling. 
 * In this program I am evaluating date and gives back 
 * either it is today or tomorrow or upcoming or past. */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/* Customized exception for invalid dates*/

class InvalidDateException extends Exception {
	public InvalidDateException(String s) {
		super(s);
	}
}

class ExceptionHandling {

	/* This is a method that validates Leap year, Non-leap year, date, month */

	private static void validation(int year, int month, int date) throws InvalidDateException {
		boolean yearType = false;
		if ((year % 400 == 0 || ((year % 4 == 0) && (year
				% 100 != 0)))) { /* checking whether it is leap year or not */
			yearType = true;
		}
		if (month >= 13) { // Validating months and throw's exception
			throw new InvalidDateException("Invalid month");
		}
		if (month < 13) {
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if ((date >= 32)) { // Validating dates of months which are
									// having 31 and throw's exception
					throw new InvalidDateException("Invalid date. It consists of 31 days only");
				}
			} else if ((yearType && month == 2) && (date > 29)) { // Validating
																	// feb month
																	// dates
																	// basing on
																	// yearType
				throw new InvalidDateException("Invalid date. It is a leap Year, it consists of 29 days only");
			} else if (month == 2 && date >= 28) {
				throw new InvalidDateException("Invalid date. It is a Non-leap Year, it consists of 28 days only");
			} else if (date >= 31) {
				throw new InvalidDateException("Invalid date. It consists of 30 days only"); // Validating
																								// dates
																								// of
																								// remaining
																								// months

			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		Calendar todaysDate = Calendar
				.getInstance(); /*
								 * Using calendar class for getting Today's date
								 * setting hours,minutes,seconds to zero
								 */
		todaysDate.set(Calendar.HOUR_OF_DAY, 0);
		todaysDate.set(Calendar.MINUTE, 0);
		todaysDate.set(Calendar.SECOND, 0);
		todaysDate.set(Calendar.MILLISECOND, 0);
		Calendar tomorrowDate = Calendar
				.getInstance(); /*
								 * Using calendar class for getting Tomorrow's
								 * date by adding one day and setting
								 * hours,minutes,seconds to zero
								 */
		tomorrowDate.add(Calendar.DATE, 1);
		tomorrowDate.set(Calendar.HOUR_OF_DAY, 0);
		tomorrowDate.set(Calendar.MINUTE, 0);
		tomorrowDate.set(Calendar.SECOND, 0);
		tomorrowDate.set(Calendar.MILLISECOND, 0);
		Calendar yesterdayDate = Calendar
				.getInstance(); /*
								 * Using calendar class for getting Yesterday's
								 * date by adding negative one day and setting
								 * hours,minutes,seconds to zero
								 */
		yesterdayDate.add(Calendar.DATE, -1);
		yesterdayDate.set(Calendar.HOUR_OF_DAY, 0);
		yesterdayDate.set(Calendar.MINUTE, 0);
		yesterdayDate.set(Calendar.SECOND, 0);
		yesterdayDate.set(Calendar.MILLISECOND, 0);

		try {
			System.out.println("Enter date you needed : "); // reading input of
															// date from user
			int date = sc.nextInt();
			System.out.println("Enter month you needed : ");
			int month = sc.nextInt();
			System.out.println("Enter year you needed : ");
			int year = sc.nextInt();
			int monthMinus = month - 1;
			int yearMinus = year - 1900;

			validation(year, month, date); // calling method for validation of
											// year, month, date

			Date userDate = new Date(yearMinus, monthMinus, date); // passing
																	// values
																	// into
																	// object
			Date today = todaysDate.getTime(); // Assigning calendar format to
												// date format
			Date tomorrow = tomorrowDate.getTime();
			Date yesterday = yesterdayDate.getTime();
			SimpleDateFormat displayFormat = new SimpleDateFormat("EEEE dd/MM/yyyy"); // Giving
																						// specific
																						// formatting
																						// to
																						// display
			SimpleDateFormat displayFormatForPastFuture = new SimpleDateFormat("EEEE dd-MMMM 'on' yyyy ");
			System.out.println("Today's date is : " + displayFormat.format(today));
			System.out.println("User entered date is : " + displayFormat.format(userDate));

			if (userDate.equals(today)) { // Checking user given date and
											// today's date
				System.out.println("It's Today only");
			} else if (userDate.after(today)) { // Checking user given date and
												// tomorrow's date or upcoming
												// date
				if (userDate.equals(tomorrow)) {
					System.out.println("The Entered date is Tomorrow");
				} else {
					System.out.println(
							"The Entered date is upcoming on : " + displayFormatForPastFuture.format(userDate));
				}
			} else if (userDate.before(today)) { // Checking user given date and
													// yesterday's date or
													// passed date
				if (userDate.equals(yesterday)) {
					System.out.println("The Entered date was Yesterday");
				} else {
					System.out
							.println("The Entered date was passed on : " + displayFormatForPastFuture.format(userDate));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sc.close(); // closing scanner
		}
	}

}