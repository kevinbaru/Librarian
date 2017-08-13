/**
 * 
 */
package library;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class LibraryTest {
	//creates instance variables to run the test
	//Calendar eachCalendar;
	Library newLibrary;
	Patron newPatron,patron2;
	ArrayList<Book> newBookList;
	ArrayList<Book> collection;
	Book bookA, bookB,bookC,bookD,book,bookE;
	OverdueNotice notice;
	Library newLibrary1;
	Library newLibrary2;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//setup creates new books, notice, a collection to test a libray, a booklist and a patron
		book=new Book("The shelter","Paul Bowles");
		bookA= new Book("adidas goals","john bener");
		bookB= new Book("villa  estate","ken mali");
		bookC= new Book("penncity","benard lota");
		bookD= new Book("linpic","jin miller");
		bookE=new Book("Tha shelter","Peel Bowles");
		//libCalendar=new Calendar();
		notice = new OverdueNotice(newPatron, 5);		
		collection=new ArrayList<Book>();
		Book[] coll={book,bookA,bookB,bookC,bookE};
		collection.addAll(Arrays.asList(coll));
		newLibrary= new Library(collection);
		newLibrary2= new Library(collection);
		newPatron = new Patron("Alfred P.", newLibrary);
		newBookList=newPatron.getBooks();
		
	}

	/**
	 * Test method for {@link library.Library#Library()}.
	 */
	@Test
	public final void testLibrary() {
		newLibrary1 = new Library();
		//makes sure that the constructor works
		assertTrue(newLibrary1.getOkToPrint());
		assertFalse(newLibrary1.isOpen());
	}
	
	/**
	 * Test method for {@link library.Library#Library(ArrayList<Book> collection)}.
	 */
	@Test
	public void testLibraryArrayListOfBook() {
		//makes sure the constructor works 
		assertEquals(newLibrary.getCollection(),collection);
		assertFalse(newLibrary.getOkToPrint());
		assertEquals(newLibrary2.getCollection(),collection);
		assertFalse(newLibrary2.getOkToPrint());
	}
	
	/**
	 * Test method for {@link library.Library# open(java.util.ArrayList)}.
	 */
	@Test
	public void testOpen() {
		//creates new patrons and creates overdue notices that open() will use
		newPatron=newLibrary.issueCard("John");
		patron2=newLibrary.issueCard("Bob");
		book.checkOut(5);
		bookA.checkOut(0);
		newPatron.take(book);
		newLibrary.createOverdueNotices();
		newPatron.take(bookA);
		newLibrary.getEachCalendar().advance();				
		newLibrary.getEachCalendar().advance();
		//opens newLibrary and checks to make sure it is open
		newLibrary.open();
		assertTrue(newLibrary.isOpen());
		//checks to make sure that newLibrary has indeed created the correct number of notices 
		assertEquals(1, newLibrary.open().size());
		//opens library2 and makes sure it is actually open, also makes sure that no notices were added to this library
		newLibrary2.open();
		assertTrue(newLibrary2.isOpen());
		assertEquals(0, newLibrary2.open().size());
	}
	
	/**
	 * Test method for {@link library.Library#print(java.lang.String)}.
	 */
	@Test
	
	
	public final void testCreateOverdueNotices() {
		//sets values and associates to create overdue notices
		newPatron=newLibrary.issueCard("John");
		patron2=newLibrary.issueCard("Bob");
		book.checkOut(5);
		bookA.checkOut(0);
		newPatron.take(book);
		//creates overdue notices
		newLibrary.createOverdueNotices();
		newPatron.take(bookA);
		newLibrary.getEachCalendar().advance();				
		newLibrary.getEachCalendar().advance();
	     
		//tests create of overdue notices
		assertEquals(newLibrary.createOverdueNotices().get(0).getPatron().getName(),"John");
		assertEquals(newLibrary.createOverdueNotices().get(0).getTodaysDate(), 2);
	}

	/**
	 * Test method for {@link library.Library#issueCard(java.lang.String)}.
	 */
	@Test
	public final void testIssueCard() {
		//checks to make sure that the hashmap size is correct as zero before any patrons have been added
		assertEquals(0,newLibrary.patrons.size());
		//adds two patrons to the hashmap
		newLibrary.issueCard("kevin");
		newLibrary.issueCard("Phil");
		//rechecks the hashmap to make sure that both patrons exist
		assertEquals(2,newLibrary.patrons.size());
		
	}

	/**
	 * Test method for {@link library.Library#serve(java.lang.String)}.
	 */
	@Test
	public final void testServe() {
		//issues a card to a new patron
		newLibrary.issueCard("kevin");
		newLibrary.issueCard("phil");
		//serves the patron
		Patron k=newLibrary.serve("kevin");
		//makes sure that the patron is being served
		assertEquals("kevin",k.getName());
		//makes sure that the patron is being served
		Patron p=newLibrary.serve("phil");
		assertEquals("phil",p.getName());
	}

	/**
	 * Test method for {@link library.Library#checkIn(java.util.ArrayList)}.
	 */
	@Test
	public final void testCheckIn() {
		//creates new arraylist
		ArrayList<Integer> cOut = new ArrayList<Integer>();
		//adds integers to arraylist
		cOut.add(1);
		cOut.add(2);
		//issues card and serves a patron
		newLibrary.issueCard("kevin");
		newLibrary.serve("kevin");
		//performs a search
		newLibrary.search("elter");
		//adds to the checkout out books for the patron
	    newLibrary.checkOut(cOut);
	    //checks in books for the patron
		ArrayList<Book> chkdIn= newLibrary.checkIn(cOut);
		//checks to maek sure books are confirmed for what has been checkedin
		assertEquals(5,newLibrary.getCollection().size());
		assertTrue(book.bookInList(chkdIn));
		assertTrue(bookE.bookInList(chkdIn));
		assertFalse(bookB.bookInList(chkdIn));
		//repeat above for another patron
		ArrayList<Integer> cOut2 = new ArrayList<Integer>();
		cOut2.add(1);
		newLibrary.issueCard("phil");
		newLibrary.serve("phil");
		//performs a search
		newLibrary.search("goal");
		//adds to the checkout out books for the patron
	    newLibrary.checkOut(cOut2);
	    //checks in books for the patron
		ArrayList<Book> chkdIn2= newLibrary.checkIn(cOut2);
		//checks to maek sure books are confirmed for what has been checkedin
		assertEquals(5,newLibrary.getCollection().size());
		assertTrue(bookA.bookInList(chkdIn2));
		assertFalse(bookE.bookInList(chkdIn2));
		assertFalse(bookB.bookInList(chkdIn2));
		
		
	}

	/**
	 * Test method for {@link library.Library#search(java.lang.String)}.
	 */
	@Test
	public final void testSearch() {
		//performs search with specified search term
		ArrayList<Book> searchlist=newLibrary.search("elter");
		//makes sure that books that should be returned are returned
		assertTrue(book.bookInList(searchlist));
		assertTrue(bookE.bookInList(searchlist));
		//checks false case/ false-positive case
		assertFalse(bookB.bookInList(searchlist));
		//repeats above for another case
		ArrayList<Book> searchlist2=newLibrary.search("goal");
		assertFalse(book.bookInList(searchlist2));
		assertFalse(bookE.bookInList(searchlist2));
		assertTrue(bookA.bookInList(searchlist2));
	}

	/**
	 * Test method for {@link library.Library#checkOut(java.util.ArrayList)}.
	 */
	@Test
	public final void testCheckOut() {
		//creates new arraylist and serves a patron
		ArrayList<Integer> cOut = new ArrayList<Integer>();
		newLibrary.issueCard("kevin");
		newLibrary.serve("kevin");
		//searches and adds search results to arraylist
		newLibrary.search("elter");
		cOut.add(1);
		cOut.add(2);
		//checksout the books to the patron
		ArrayList<Book> chkdOut= newLibrary.checkOut(cOut);
		//makes sure books ahve been checked out
		assertTrue(book.bookInList(chkdOut));
		assertTrue(bookE.bookInList(chkdOut));
		assertFalse(bookB.bookInList(chkdOut));
		//makes sure the books have been removes from the library
		assertEquals(3,newLibrary.getCollection().size());
		//repeats for another patron
		newLibrary.issueCard("phil");
		newLibrary.serve("phil");
		//searches and adds search results to arraylist
		ArrayList<Integer> cOut2 = new ArrayList<Integer>();
		newLibrary.search("goal");
		cOut2.add(1);
		//checksout the books to the patron
		ArrayList<Book> chkdOut2= newLibrary.checkOut(cOut2);
		//makes sure books ahve been checked out
		assertTrue(bookA.bookInList(chkdOut2));
		assertFalse(bookE.bookInList(chkdOut2));
		
		//makes sure the books have been removes from the library
		assertEquals(2,newLibrary.getCollection().size());
		
	}
		
	@Test
	public void testClose() {
		//opens and closes library
		newLibrary.open();
		newLibrary.close();
		newLibrary2.open();
		newLibrary2.close();
		//tests if library is really closed
		assertFalse(newLibrary.isOpen());
		assertFalse(newLibrary2.isOpen());
	}
	


}
