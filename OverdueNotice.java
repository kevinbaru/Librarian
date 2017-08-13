package library;

import java.util.ArrayList;

public class OverdueNotice {
	private Patron patron;
	private int todaysDate;
	
	/**
	 * Constructs an over due notice with a Patron and a todays date
	 * @param patron
	 * @param todaysDate
	 */
	public OverdueNotice(Patron patron, int todaysDate){
		//sets the patron and todays date for the notices
		this.setPatron(patron);
		this.setTodaysDate(todaysDate);
	}
	/**
	 * creates a toString() that prints out the list of books that a patron has as well as which books are late
	 */
	public String toString(){
		//creates an arraylist of books that a patron has
		ArrayList<Book> patronBooks  = getPatron().getBooks();
		//creats two strings that will hold late books and all of the books that a patron has
		String lateBooks = "";
		String booksAndDate = "";
		//loops through the ArrayList containing the books belonging to the patron
		for(int i=0; i < patronBooks.size(); i++ ){
			//checks to see if a books due date is the day before the current date
			if(patronBooks.get(i).getDueDate() < this.getTodaysDate()){
				//adds books that are latebooks to the latebooks string
				lateBooks = lateBooks+ (patronBooks.get(i).getTitle())+", ";
			}
			//creates a list of the books that are in the patron's possesion
			booksAndDate = booksAndDate + patronBooks.get(i).getTitle() + "-" +patronBooks.get(i).getDueDate() + ", " ;
		}
		//assigns a string that will give the patron's name along with all of their books as well as the books which are late
		String stringOut = getPatron().getName()+": "+"You have the following books in your possesion: "+ booksAndDate + "The following books are late: " + lateBooks;
		//returns the above
		return stringOut;
	}
	/**
	 * Returns a patron
	 * @return
	 */
	public Patron getPatron() {
		return patron;
	}
	/**
	 * sets a patron
	 * @param patron
	 */
	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	/**
	 * Returns todays date
	 * @return
	 */
	public int getTodaysDate() {
		return todaysDate;
	}
	/**
	 * sets todays date
	 * @param todaysDate
	 */
	public void setTodaysDate(int todaysDate) {
		this.todaysDate = todaysDate;
	}
}
