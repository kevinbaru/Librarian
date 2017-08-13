package library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OverdueNoticeTest {
	//creates instance variables to be tested
	OverdueNotice notice;
	OverdueNotice notice2;
	Patron patron;
	Library newLibrary;
	Patron patron2;
	Book newBook1;
	Book newBook2;
	Calendar newCalendar;
	
	@Before
	public void setUp() throws Exception {
		//sets up the following to run tests
		//creates a calendar and advances it 2 days
		newCalendar = new Calendar();
		newCalendar.advance();
		newCalendar.advance();
		//cretes a new patron and an overdue notice for the patron
		patron = new Patron("John", newLibrary);
		notice = new OverdueNotice(patron, 5);
		//second patron
		patron2 = new Patron("Bob", newLibrary);
		notice2 = new OverdueNotice(patron2, 5);
		newBook1 = new Book("Hello", "George Sarge");
		newBook2 = new Book("Goodbye", "Fun Times");
		newBook1.checkOut(5);
		newBook2.checkOut(0);
		patron.take(newBook1);
		patron.take(newBook2);
		patron2.take(newBook1);
		patron2.take(newBook2);
	}

	@Test
	public void testOverdueNotice() {
		//tests to make sure that the notices were created for the correct patron
		assertEquals(notice.getPatron(), patron);
		assertEquals(notice2.getPatron(), patron2);
		//tests that the notice dates are correct
		assertEquals(notice.getTodaysDate(), 5);
		assertEquals(notice2.getTodaysDate(), 5);
	}

	@Test
	public void testToString() {
		//tests to make sure that the late notices are correct)
		assertEquals("John: You have the following books in your possesion: Hello-5, Goodbye-0, The following books are late: Goodbye, ", notice.toString());
		assertEquals("Bob: You have the following books in your possesion: Hello-5, Goodbye-0, The following books are late: Goodbye, ", notice2.toString());
	}

	@Test
	public void testGetPatron() {
		//tests to make sure tha the correct patrons are returned
		assertEquals(notice.getPatron(), patron);
		assertEquals(notice2.getPatron(), patron2);
	}

	@Test
	public void testSetPatron() {
		//tests to make sure that a patron can be changed
		notice.setPatron(patron2);
		assertEquals(notice.getPatron(), patron2);
		notice2.setPatron(patron);
		assertEquals(notice2.getPatron(), patron);
		
	}

	@Test
	public void testGetTodaysDate() {
		//tests to make sure that the date is returned
		assertEquals(notice.getTodaysDate(), 5);
		assertEquals(notice2.getTodaysDate(), 5);
	}

	@Test
	public void testSetTodaysDate() {
		//checks to make sure that the date can be set
		notice.setTodaysDate(4);
		assertEquals(notice.getTodaysDate(), 4);
		notice.setTodaysDate(3);
		assertEquals(notice.getTodaysDate(), 3);
	}

}
