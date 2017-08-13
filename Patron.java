package library;

import java.util.*;


public class Patron {
	//The Patron class has the following instance variables
	private String name; //name of the patron
	private Library library; //which library the patron belongs to
	private ArrayList<Book> bookList; //the book list is a list of books that the patron has
	/**
	 * Constructs a Patron object by setting the name, giving an associated library and creating
	 * an ArrayList of books that the belongs to the patron
	 * @param name the name associated with the patron	
	 * @param library the library associated with the patron
	 */
	public Patron(String name, Library library){
		//sets the name for the patron
		this.setName(name);
		//sets the library for the patron
		this.setLibrary(library);
		//creates the arraylist for the patron
		this.bookList = new ArrayList<Book>();


	}
	/**
	 * Sets the library input as the given library for a patron
	 * @param library associated library of the patron
	 */
	public void setLibrary(Library library) {
		//sets the library of the patron
		this.library = library;

	}
	/**
	 * Returns the set library for a patron	
	 * @return
	 */
	public Library getLibrary(){
		//returns the library associatd with the patron
		return this.library;
	}
	/**
	 * Returns the name of the patron
	 * @return
	 */
	public String getName(){
		//returns the name of the patron
		return this.name;
	}
	/**
	 * adds a given book to the ArrayList of books assigned to a patron
	 * @param book a book that exists from the Book class
	 */
	public void take(Book book){
		//adds a book to the patron ArrayList containing the books belonging to the patron
		this.bookList.add(book);
	}
	/**
	 * Removes a book from the ArrayList of books assigned to a patron
	 * @param book a book that exists from the Book class
	 */
	public void giveBack (Book book){
		//removes a book from the arrayList containing the books belonging to the patron
		this.bookList.remove(book);
	}
	/**
	 * Returns the ArrayList of books assigned to the patron
	 * @return
	 */
	public ArrayList<Book> getBooks(){
		//returns the ArrayList holding the books of the patron
		return this.bookList;
	}
	/**
	 * returns the name associated with the patron as the toString() method
	 */
	public String toString(){
		//returns the name of the patron
		return getName();
	}
	/**
	 * uses a given string input to change the name of the patron
	 * @param name
	 */
	public void setName(String name) {
		//sets the name of the patron
		this.name = name;
	}

}
