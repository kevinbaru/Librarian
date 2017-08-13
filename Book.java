package library;

import java.util.ArrayList;

public class Book {
	//sets book instance variables
	String title;
	String author;
	int dueDate=-1;
	
	/**
	 * Constructs a book object with a title and author
	 * @param title title of the book
	 * @param author author of the book
	 */
	public Book(String title,String author) {
		//assigns and a String title and author to the book
		this.title=title;
		this.author=author;
		
	}
	/**
	 * Returns the books title
	 * @return
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 * Returns the books author
	 * @return
	 */
	public String getAuthor(){
		return this.author;
	}
	/**
	 * Returns the books due date
	 * @return
	 */
	public int getDueDate(){
		return this.dueDate;
	}
	/**
	 * Sets the books dueDate as negative one
	 */
	public void checkIn(){
		this.dueDate=-1;
	}
	/**
	 * Sets the books due date as the current date
	 * @param date
	 */
	public void checkOut(int date){
		this.dueDate=date;
	}
	/**
	 * Creates a toString that outputs the books title and author
	 */
	public String toString(){
		return this.title+" " +"by"+" "+ this.author;
	}
	/**
	 * creates an boolean method that checks to see if a specific book is in an ArrayList containing book objects
	 * @param booklist
	 * @return
	 */
	public boolean bookInList(ArrayList<Book> booklist){
		//sets exist value to false
		boolean exist=false;
		//loops through ArrayList containing books
		for(int i=0;i<booklist.size();i++){
			//checks to make sure that the titles and authors of each book are not equal
			if(booklist.get(i).getTitle().equals(this.getTitle())&&
					booklist.get(i).getAuthor().equals(this.getAuthor())){
				//changes the value of exist to true and returns it, else returns false
				exist=true;
				return exist;
			}
			exist=false;	
		
		}
		return exist;
	}

}
