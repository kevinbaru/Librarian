package library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalendarTest {
	//initializes the date and a calendar
	int date;
	Calendar newCalendar;
	Calendar newCalendar2;
	
	@Before
	public void setUp() throws Exception {
		//declares a new calendar
		newCalendar = new Calendar();
		newCalendar2 = new Calendar();
	}

	@Test
	public void testCalendar() {
		//makes sure that the constructor has worked
		assertEquals(newCalendar.getDate(), 0);
		assertEquals(newCalendar2.getDate(), 0);
	}

	@Test
	public void testGetDate() {
		//makes sure the getDate() getter works
		assertEquals(newCalendar.getDate(), 0);
		assertEquals(newCalendar2.getDate(), 0);
		//checks false positive
		assertNotEquals(newCalendar.getDate(), 1);
		assertNotEquals(newCalendar2.getDate(), 1);
	}

	@Test
	public void testAdvance() {
		//advances the calendar by one and calendar2 twice
		newCalendar.advance();
		newCalendar2.advance();
		newCalendar2.advance();
		//makes sure the calendar has properly incremented
		assertEquals(newCalendar.getDate(), 1);
		assertEquals(newCalendar2.getDate(), 2);
		//repeat above
		newCalendar.advance();
		newCalendar2.advance();
		assertEquals(newCalendar.getDate(), 2);
		assertEquals(newCalendar2.getDate(), 3);
		//checks to make sure no false positives are returned
		assertNotEquals(newCalendar.getDate(),3);
		assertNotEquals(newCalendar2.getDate(),2);
	}

}
