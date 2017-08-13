package library;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PatronTest {
	//sets the instance variables for the patron class
	Library newLibrary;
	Patron newPatron;
	Patron newPatron1;
	ArrayList<Book> newBookList;
	ArrayList<Book> collection;
	Book newBook;
	Book newBook1;

	@Before
	public void setUp() throws Exception {
		//creates new patrons and books to test
		newPatron = new Patron("Alfred P.", newLibrary);
		newPatron1 = new Patron("Bob", newLibrary);
		newBook = new Book("Hello", "Adam A");
		newBook1 = new Book("Bye", "Adam A");
	}

	@Test
	public void testPatron() {
		//tests to make sure that the Patron's were appropriatly created with names and library assignments
		assertEquals(newPatron.getName(), "Alfred P.");
		assertEquals(newPatron.getLibrary(), newLibrary);
		assertEquals(newPatron1.getName(), "Bob");
		assertEquals(newPatron1.getLibrary(), newLibrary);
	}

	@Test
	public void testGetName() {
		//tests that the correct name is returned
		assertEquals(newPatron.getName(), "Alfred P.");
		assertEquals(newPatron1.getName(), "Bob");
	}

	@Test
	public void testTake() {
		//tests that the books were assigned to each patron
		newPatron.take(newBook);
		assertTrue(newPatron.getBooks().contains(newBook));
		newPatron1.take(newBook1);
		assertTrue(newPatron1.getBooks().contains(newBook1));
	}

	@Test
	public void testGetLibrary() {
		//tests that the correct library is returned
		assertEquals(newPatron.getLibrary(), newLibrary);
		assertEquals(newPatron1.getLibrary(), newLibrary);
	}

	@Test
	public void testSetLibrary() {
		Library newLibrary2 = new Library();
		newPatron.setLibrary(newLibrary2);
		assertEquals(newPatron.getLibrary(), newLibrary2);
		newPatron1.setLibrary(newLibrary2);
		assertEquals(newPatron1.getLibrary(), newLibrary2);
	}

	@Test
	public void testGiveBack() {
		//tests that each patron has the book removed
		newPatron.take(newBook);
		newPatron.giveBack(newBook);
		assertFalse(newPatron.getBooks().contains(newBook));
		newPatron1.take(newBook1);
		newPatron1.giveBack(newBook1);
		assertFalse(newPatron1.getBooks().contains(newBook1));
	}

	@Test
	public void testGetBooks() {
		//tests that the returned arrays are correct for each patron
		assertTrue(newPatron.getBooks().isEmpty());
		assertTrue(newPatron1.getBooks().isEmpty());

	}

	@Test
	public void testToString() {
		//tests the output of the toString method is correct
		assertEquals(newPatron.toString(), "Alfred P.");
		assertEquals(newPatron1.toString(), "Bob");
	}

	@Test
	public void testSetName() {
		//tests that the names are correctly assigned using the set method
		newPatron.setName("Apple");
		assertEquals(newPatron.getName(), "Apple");
		newPatron1.setName("Banana");
		assertEquals(newPatron1.getName(), "Banana");
	}
}
