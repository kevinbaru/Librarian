package library;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class BookTest {
	//initializes instance variables
	String title;
	String author;
	int dueDate=-1;
	Book newBook,bookA;
	ArrayList<Book> booklist;
	ArrayList<Book> doolist;
	
	@Before
	public void setUp() throws Exception {
		//creates objects to be tested
		newBook = new Book("Hello", "Bob A");
		bookA=new Book("The shelter","Paul Bowles");
		booklist=new ArrayList<Book>();
		Book[] coll={newBook,bookA};
		booklist.addAll(Arrays.asList(coll));
		
	}

	@Test
	public void testBook() {
		//tests constructor to make sure all were assigned
		assertEquals(newBook.getAuthor(), "Bob A");
		assertEquals(newBook.getTitle(), "Hello");
		assertEquals(newBook.getDueDate(), -1);
		//checks false positives
		assertNotEquals(newBook.getDueDate(), 1);
		assertNotEquals(newBook.getAuthor(), "Bob B");
		assertNotEquals(newBook.getTitle(), "Hellow");
	}

	@Test
	public void testGetTitle() {
		//repeated from above to check get function
		assertEquals(newBook.getAuthor(), "Bob A");
		assertNotEquals(newBook.getAuthor(), "Bob B");
	}

	@Test
	public void testGetAuthor() {
		//repeated from above to check get function
		assertEquals(newBook.getAuthor(), "Bob A");
		assertNotEquals(newBook.getAuthor(), "Bob B");
	}

	@Test
	public void testGetDueDate() {
		//repeated from above to check get function
		assertEquals(newBook.getDueDate(), -1);
		assertNotEquals(newBook.getDueDate(), 1);
	}

	@Test
	public void testCheckIn() {
		//runs check in on newBook
		newBook.checkIn();
		//confirms nothing has changed
		assertEquals(newBook.getAuthor(), "Bob A");
		assertEquals(newBook.getTitle(), "Hello");
		assertEquals(newBook.getDueDate(), -1);
	}

	@Test
	public void testCheckOut() {
		//sets due date of the book
		newBook.checkOut(5);
		bookA.checkOut(3);
		//checks due date
		assertEquals(newBook.getDueDate(), 5);
		assertEquals(bookA.getDueDate(), 3);
		//checks false positive
		assertNotEquals(newBook.getDueDate(), 1);
		assertNotEquals(bookA.getDueDate(), 1);
	}

	@Test
	public void testToString() {
		//tests the toString output
		assertEquals(newBook.toString(), "Hello by Bob A");
		assertEquals(bookA.toString(), "The shelter by Paul Bowles");
	}
	@Test
	public void testBookInList(){
		//test the BookInList function
		assertTrue(bookA.bookInList(booklist));
		assertTrue(newBook.bookInList(booklist));
		
	}

}
