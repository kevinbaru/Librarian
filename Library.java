package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {
	//creates instance variables for the library
	private boolean okToPrint;
	private boolean isOpen;
	private Calendar eachCalendar;
	public  HashMap<String, Patron>patrons;
	private ArrayList<OverdueNotice>dN ;
	public static Patron patronBeingServed;
	private ArrayList<Book> bookSearchOrIn;
	private ArrayList<Book> collection;

	/**
	 * Constructor without any inputs that sets the okToPrint variable to true and the isOpen to false,
	 * creates a new hashmap containing strings and patrons and creates a new calendar
	 */
	public Library() {
		okToPrint = true;
		isOpen = false;
		this.collection = readBookCollection();
		patrons = new HashMap<String, Patron>();
		eachCalendar = new Calendar();

	}
	/**
	 * Construct that takes in a collection of books, creates a new calendar, sets okToPrint to false and creates a new hashmap containing strings and patrons
	 * @param collection
	 */
	public Library(ArrayList<Book> collection){
		this.collection=collection;
		patrons = new HashMap<String, Patron>();
		eachCalendar = new Calendar();
		this.okToPrint = false;
	}

	/**
	 * takes in a .txt file that is a collection of books that will belong to the library and returns that collection
	 * @return
	 */
	private ArrayList<Book> readBookCollection() {
		//creates new file from the books.txt
		File file = new File("books.txt");
		//creates new array to hold a collection of Book objects
		ArrayList<Book> collection = new ArrayList<Book>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				line = line.trim();
				if (line.equals("")) continue; // ignore possible blank lines
				String[] bookInfo = line.split(" :: ");
				collection.add(new Book(bookInfo[0], bookInfo[1]));


			}
			reader.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return collection;
	}

	/**
	 * This method *runs* the library, it has several commands that a librarian can do, it will use all the other methods to complete the 
	 * librarians commands
	 */
	public void start(){
		
		//the following uses the println method to give the initial instructions to the librarian when the main method initializes the start() method
		println("Please select one of the following options by Entering the preceding Numbers");
		println("If the library was closed first press 1 to open it then perform the other options");
		println("1: Open the Library");
		println("2: Issue a card to a Patron");
		println("3: Serve a Patron-Contains the Search,check in and check out options");
		println("7: Close the Library");
		println("8: Quit\n");
		println("NOTE~ In order to check in or check out books you must first serve the patron (must have a library card):");
		println("NOTE 2~ Please serve a patron before searching for a book, when you search for a book capitalization does not matter.");
		//creates a scanner that will read the keyboard inputs
		Scanner sc = new Scanner(System.in);
		//creates an initial i integer that will read the keyboard inputs
		int i = 0;
		//creates a while loop that will run as long as the system is not exited
		while(i != 8){
			//reads the initial value entered by the librarian
			i = Integer.valueOf(sc.nextLine());
			//creates the first if statement that reads the input, in this case whether or not to open the library
			if(i==1){
				//checks to see if the library is already open, this way the librarian cannot open the library without closing it
				if (isOpen()){
					println("The library is already open.");
					println("\n");
					//re-starts the library sequence
					this.start();
				}
				//opens the library
				open();
				//prints out statements of what the librarian can do
				println("The library is now open, the current date is: " + eachCalendar.getDate());
				println(" You can now perfom any of the functions shown shown below:");
				println("Enter the Digit preceding the desired option");
				println("2: To issue a card");
				println("3: Serve a Patron");
				println("4: Check in the Patron's books");
				println("5: Search the library");
				println("6: Check out books to the Patron");
				println("7: To close the library");
				println("8: To quit\n");
				//prints out instruction for the librarian about requirements to check out books, et cetera
				println("NOTE~ In order to check in or check out books you must first serve the patron (must have a library card):");
			}

			if(i==2){
				//checks if the library is open, if the value is false asks to open the library
				if (!isOpen()){
					println("The library is currently closed. Press 1 to first open the library");
					println("\n");
					//re-starts the library sequence
					this.start();
				}
				//asks the librarian to enter a name to issue the card to
				println("Please enter the name of the person that you would like to issue the card to.");
				//sets the reader to wait for a string input
				String st = sc.nextLine();
				if(st != null){
					//calls the issueCard method with the String that the user has input
					issueCard(st);
					//prints out the user that has received/been issues a library card
					println(st + " has now been issued a library card.");
				}

			}
			//option that selects to close the library
			if(i==7){
				//checks to see if the library is open, if it is closed outputs a message stating this
				//prevents the librarian from closing a library which is already closed
				if (!isOpen()){
					println("The library is already closed. Press 1 to first open the library");
					println("\n");
					this.start();
				}
				//intiates the close() method
				this.close();
				//tells the librarian that the library 8is closed
				println("The library is now closed.");
				println("\n");
				//initiates the start() method providing the original options
				this.start();
				//allows for searching the library
				if(i==5){
					//asks for and waits for reader input to search the library, dictating a 4 character minimum
					println("Please enter the string that you would like to search, it should be four or more characters.");
					String sk = sc.nextLine();
					//does not search the library unless the input is 4 characters long
					if(sk.length() <  4){
						println("You have entered a string that is too short");
					}
					else{
						//creates a new Book ArrayList
						ArrayList<Book> bookList = new ArrayList<Book>();
						//calls the search function on the entered String
						bookList = search(sk);
						//if there are no books returned, lets the librarian know that there are no books like the in the library, provides options
						if(bookList.size()==0){
							println(" No book was found");
							println(" Press 5 to make another search");
							println(" press 4 to check in another book");
							println(" press 6 to check out books");
							println(" Press 3 to serve another patron");
							println(" Press 7 to close the library");
							println(" Press 8 to quit");
							println(" press 2 to issue a card");
						}
						//prints the matching books in the library and provides further options
						else{
							printBookList(bookList);
							println("\n");
							println(" Press 4 Check in  books");
							println(" Press 5 to conduct another search");
							println(" press 6 to check out a book");			
							println(" Press 3 to serve another patron");
							println(" Press 7 to close the library");
							println(" Press 8 to quit");
							println(" press 2 to issue a card");
						}

					}

				}
			}
			//if option 3, is selected, the option to  serve a patron
			while(i==3){
				//checks to see if the library is open
				if (!isOpen()){
					println("The library is currently closed. Press 1 to first open the library");
					println("\n");
					this.start();
				}
				//if the library is open asks for input as to which patron to search
				println("Please enter the name of a Patron that you would like serve.");
				String st = sc.nextLine();
				//makes sure that a patron exists that can be served, and if this is false asks for input to create a library card
				while(!this.patrons.containsKey(st)){
					println("You must first issue the person with a card");
					println("Please enter the name of the person that you would like to issue the card to.");
					String sp = sc.nextLine();
					//samne as above for issueing a card
					if(sp != null){
						issueCard(sp);
						println(sp + " has now been issued a library card.");
						st=sp;
					}
				}
				//calls the serve method on the patron
				serve(st);
				println(st + " is now being served.");
				//prints out which books the patron has
				println(patronBeingServed.getName()+" has "+patronBeingServed.getBooks().size()+" books");
				printBookList(patronBeingServed.getBooks());
				println("\n");
				//provides options for the librarian
				println(" You can now perfom any of the functions shown shown below:");
				println("Enter the Digit preceding the desired option");
				println("2: To issue a card");
				println("3: Serve another Patron");
				println("4: Check in the Patron's books");
				println("5: Search the library");
				println("6: Check out books to the Patron");
				println("7: To close the library");
				//println("NOTE~ In order to checkout books you must first search the library:");
				loop2:
					while(true){						
						i = Integer.valueOf(sc.nextLine());
						//repeats the check if library is closed
						if(i==7){

							println("The library is now closed.");
							this.close();

							println("\n");
							this.start();

						}
						//repeats option to quit/exit library
						if(i==8){
							quit();
						}
						if(i==3){							
							break loop2;
						}
						//repeats the second option, card issue
						if(i==2){
							if (!isOpen()){
								println("The library is now closed. Press 1 to first open the library");
								println("\n");
								this.start();
							}
							println("Please enter the name of the person that you would like to issue the card to.");
							String si = sc.nextLine();
							if(si != null){
								issueCard(si);
								println(si + " has now been issued a library card.");
							}

						}
						//if the librarian wants to check in books from the patron
						if(i==4){
							//prints out the patron and the patrons books
							println(patronBeingServed.getName()+" has "+patronBeingServed.getBooks().size()+" books");
							printBookList(patronBeingServed.getBooks());
							//asks the librarian which books should be checked in
							println("Please enter the number(s) corresponding to the books that you wish to check in.");
							//instructs the way the librarian should enter books
							println("If more than one book, separate the numbers with spaces");
							//creates a new list arraylist to accept the user input and asks the reader to wait for theinput
							ArrayList<Integer> bookNumbers = new ArrayList<Integer>();
							String sk = sc.nextLine();
							//splits according to white space
							String[] ans = sk.split(" ");
							//replaces with no space
							String sn=sk.replaceAll("\\s","");
							//assigns an integer k to hold the length of sn
							int k=sn.length();
							//loops through
							for(int y=0; y<k; y++){
								//parses each value and stores it as an integer a
								int a = Integer.parseInt(ans[y]);
								//adds each integer to the arraylist
								bookNumbers.add(a);
							}
							//calls the checkIn method on the arraylist containing the input integers
							checkIn(bookNumbers);
							//prints out commands tha tthe librarian can perform
							println(" press 2 to issue a card");
							println(" press 4 to check in another book");
							println(" press 6 to check out books");
							println(" Press 5 to conduct a search");
							println(" Press 3 to serve another patron");
							println(" Press 7 to close the library");
							println(" Press 8 to quit");
							println("\n");

						}
						//repeats allowing the librarian to search the library
						if(i==5){
							println("Please enter the string that you would like to search, it should be four or more characters.");
							String sk = sc.nextLine();
							if(sk.length() <  4){
								println("You have entered a string that is too short");
							}
							else{
								ArrayList<Book> bookList = new ArrayList<Book>();
								bookList = search(sk);
								if(bookList.size()==0){
									println(" No book was found");
									println(" Press 5 to make another search");
									println(" press 4 to check in another book");
									println(" press 6 to check out books");
									println(" Press 3 to serve another patron");
									println(" Press 7 to close the library");
									println(" Press 8 to quit");
									println(" press 2 to issue a card");
								}
								else{
									printBookList(bookList);
									println("\n");
									println(" Press 4: Check in  books");
									println(" Press 5 to conduct another search");
									println(" press 6 to check out a book");			
									println(" Press 3 to serve another patron");
									println(" Press 7 to close the library");
									println(" Press 8 to quit");
									println(" press 2 to issue a card");
								}

							}

						}
						//if statement for allowing the librarian to check out books to the patron
						if(i==6){
							//checks to make sure that the patron has not checked out the maximum number of books
							if (patronBeingServed.getBooks().size()>=3){
								println(patronBeingServed.getName() + " has the maximum number of books, 3, that are allowed to be out at any one time, please serve another patron");
								println("\n");
								this.start();
							}
							//first asks librarian to search books
							println("Please enter the string that you would like to search, it should be four or more characters.");
							String sy = sc.nextLine();
							if(sy.length() <  4){
								println("You have entered a string that is too short");
							}
							//repeats search function for books
							else{
								ArrayList<Book> bookList = new ArrayList<Book>();
								bookList = search(sy);
								if(bookList.size()==0){
									println("No book was found");
									println(" press 6 to check out another book");
									println("\n");
									println(" press 2 to issue a card");
									println(" Press 4 to Check in books");
									println(" Press 5 to conduct a search");
									println(" Press 3 to serve another patron");
									println(" Press 7 to close the library");
									println(" Press 8 to quit");
									println("\n");
								}
								else{
									printBookList(bookList);
									println("\n");
									//asks the librarian to select which books to check out, similar to above with seperation of white space et  ceterata
									println("Please enter the number(s) corresponding to the book(s) that you wish to check out., maximum of 3");
									println("If more than one book, separate the numbers with spaces");
									ArrayList<Integer> bookNumbers = new ArrayList<Integer>();
									String sk = sc.nextLine();
									String[] ans = sk.split(" ");

									String sn=sk.replaceAll("\\s","");

									int k=sn.length();
									for(int y=0; y<k; y++){

										int a = Integer.parseInt(ans[y]);
										if (bookNumbers.size() <3){
											bookNumbers.add(a);
										}
									}
									
									//calls the check out method on the ArrayList of integers
									checkOut(bookNumbers);
									println("\n");
									println(" press 2 to issue a card");
									println(" Press 4 to Check in books");
									println(" press 6 to check out another book");
									println(" Press 5 to conduct a search");
									println(" Press 3 to serve another patron");
									println(" Press 7 to close the library");
									println(" Press 8 to quit");
									println("\n");
								}

							}


						}


					}

			}
		}
		//calls the quit method
		this.quit();
		//closes the reader
		sc.close();

	}

	/**
	 * @param bookList
	 * prints a numbered list of a booklist containing book objects
	 */
	public void printBookList(ArrayList<Book> bookList){
		//loops through the book list
		for(int i=0; i<bookList.size(); i++){
			//sets int b to be one more than int i, this sets the number as 1 instead of 0
			int b = i+1;
			//prints b associated with the book i
			println(b +": " + bookList.get(i));
		}
	}
	/**
	 * @param bookList
	 * prints a numbered list of the booklist containing integers
	 */
	public void printIntList(ArrayList<Integer> bookList){
		//completes same as above but for integer inputs
		for(int i=0; i<bookList.size(); i++){
			int b = i+1;
			println(b +": " + bookList.get(i));
		}
	}
	/**
	 * creates a print function that uses System.out.print to print a message
	 * @param message message to be printed
	 */
	public void print (String message){
		//checks if okToPrint is true and then prints the message, else doesn't return anything
		if(this.okToPrint == true){
			System.out.print(message);
		}
		else{
			return;
		}
	}
	/**
	 * creates a print function that uses System.out.println to print a message
	 * @param message message to be printed
	 */
	public void println (String message){
		//repeats above but with println
		if(this.okToPrint == true){
			System.out.println(message);
		}
		else{
			return;
		}
	}
	/**
	 * Opens the library by advancing the calendar, and sending out overdue notices, also calls the overdue notice method by returning it
	 * @return
	 */
	public ArrayList<OverdueNotice> open(){

		//advances the day by one
		eachCalendar.advance();
		//changes isOpen to true to indicate that the library is open
		this.isOpen = true;
		//creates an ArrayList by calling createOverdueNotices
		dN=createOverdueNotices();
		//for however many overdue notices there are prints them out
		for(int i = 0; i< dN.size(); i++){
			println( dN.get(i).toString());
		}
		//returns the created notices
		return createOverdueNotices();


	}
	/**
	 * creates over due notices by checking if patron's have books which are overdue
	 * returns an ArrayList of overdue notices
	 * @return
	 */
	public ArrayList<OverdueNotice> createOverdueNotices(){
		int i = 0;
		boolean check = false;
		ArrayList<OverdueNotice> notices = new ArrayList<OverdueNotice>();
		//while i is less than the total number of keys/values in the hashmap
		while (i < patrons.size()){
			//creates an arraylist called notices
			//loops through the hashmap, extracting the Patron from each key (string) from the hashmap
			//and uses their booklist size set the loop parameter
			for(Map.Entry<String, Patron> entry : patrons.entrySet()){
				for(int j = 0; j< entry.getValue().getBooks().size();j++){
					//loops through each Patron, and each book in their booklist and checks if the due date was yesterday
					if(entry.getValue().getBooks().get(j).getDueDate() <= (eachCalendar.getDate()-1)){
						//sets the boolean value to true if they have an overdue book
						check=true;
					}
					//if the value of check is true, adds a notice to the notices arraylist
					if (check == true){
						OverdueNotice dueNotice= new OverdueNotice(entry.getValue(),eachCalendar.getDate());
						notices.add(dueNotice);
					}
				}
				i++;
			}
		}

		return notices;



	}
	/**
	 * Creates a hashmap key-value pair that records a patron (by name) and returns a new patron, effectively this records that a patron has a library card
	 * @param nameOfPatron
	 * @return
	 */
	public Patron issueCard(String nameOfPatron){
		//creates a new patron
		Patron newPatron=new Patron(nameOfPatron,this);
		//stores the patron in the hashmap
		this.patrons.put(nameOfPatron, newPatron);
		return newPatron;
	}
	/**
	 * Serves a patron (name), first checks to make sure that the patron has a library card/is in the hashmap and then reutrns the patron
	 * @param nameOfPatron
	 * @return
	 */
	public Patron serve(String nameOfPatron){
		//checks to make sure the patron exists in the hash map and if so, returns the patron
		if( patrons.containsKey(nameOfPatron)){
			patronBeingServed=patrons.get(nameOfPatron);

		}
		else{
		}
		return patronBeingServed;
	}
	
	/**
	 * This method uses an integer array list as an input and then outputes a book arraylist that uses the patron being currently served to check in books from,
	 * it adds these books to the library's collection and removes them from the patron's list of books
	 * returns the list of books that have been checked in
	 * @param bookNumbers the input integer arraylist
	 * @return
	 */
	public ArrayList<Book> checkIn(ArrayList<Integer>bookNumbers){
		ArrayList<Book>booksIn=new ArrayList<Book>();
		//loops through the arraylist of selected integer numbers
		for(int i=0;i<bookNumbers.size();i++){
			int book_index = bookNumbers.get(i)-1;
			Book bIn=patronBeingServed.getBooks().get(book_index);
			//printBookList(patronBeingServed.getBooks());
			//resets selected books due dates to -1
			bIn.checkIn();
			//adds the book to the collection
			this.collection.add(bIn);

			//adds the book to the output array list of books checked in
			booksIn.add(bIn);
		}
		for(Book bk:booksIn){
			//removes the book from the patron
			patronBeingServed.getBooks().remove(bk);
		}
		//prints the books that the patron has checked in
		println(patronBeingServed +" has checked IN the book(s) below:");
		printBookList(booksIn);
		return booksIn;
	}
	
	/**
	 * The search method checks if a book is in the collection and then returns an arraylist of Books which were found,
	 * it uses lowercase to match anypart of the title or author
	 * @param part
	 * @return
	 */
	public ArrayList<Book> search(String part){
		//creates new ArrayList of Books to hold the findings of the search
		ArrayList<Book>bookSearch=new ArrayList<Book>();
		//creates an instance variable that will store all the found books
		bookSearchOrIn = new ArrayList<Book>();
		//searches for a book in the collection of the library
		for(Book book :this.collection){
			//checks to see if the books author or title matches the input
			if(book.title.toLowerCase().contains(part.toLowerCase())||book.author.toLowerCase().contains(part.toLowerCase())){
				if(!book.bookInList(bookSearch)){
					bookSearch.add(book);				
				}
			}
		}
		//equivaletes the found books array to the instance variable array
		this.bookSearchOrIn = bookSearch;
		return bookSearch;
	}
	
	/**
	 * Takes an arraylist of input integers along with the current patron being served.  Adds books from the collection (removes them)
	 * to the patron and sets a new due date for each checked out book.
	 * @param bookNumbers
	 * @return
	 */
	public ArrayList<Book> checkOut(ArrayList<Integer> bookNumbers){
		//bookNumbers are integer numbers relating to books returned from either
		//creates an arrayList of books
		ArrayList<Book> booksOut = new ArrayList<Book>();
		//loops through from index 0 to the max number that is entered
		for(int i=0; i< bookNumbers.size(); i++){
			//returns the book from bookNumbers that was specified
			int date = this.eachCalendar.getDate();
			//creates an integer book_index that subtracts one from teh book
			int book_index = bookNumbers.get(i)-1;
			//uses the instance variable book or in created from the search list
			Book bOut=getBookSearchOrIn().get(book_index);
			//runs the checkOut method on the book to set the due date from teh current date
			bOut.checkOut(date+7);
			//removes the book from the collection
			this.collection.remove(bOut);
			//adds the book to the patron's list of books
			patronBeingServed.take(bOut);
			booksOut.add(bOut);

		}
		//prints the patron's books
		println(patronBeingServed +" has checked OUT the book(s) below:");	
		
		printBookList(booksOut);
		return booksOut;
	}
	/**
	 * sets the library isOpen to false, thereby not allowing certain commands while the library is *closed*
	 */
	public void close(){
		setOpen(false);
	}

	/**
	 * exits the program using system exit
	 */
	public void quit(){
		println("You have permanently closed the library.");
		System.exit(0);

	}
	/**
	 * this method creates a library and initializes the start method which runs the library
	 * @param args
	 */
	public static void main(String[] args){
		//creates a new library
		Library library = new Library();
		//stars the library
		library.start();
	}

	/*
	 * the following are all getters and setters for the methods above
	 */
	public boolean getOkToPrint() {
		return okToPrint;
	}
	public void setOkToPrint(boolean okToPrint) {
		this.okToPrint = okToPrint;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public Calendar getEachCalendar() {
		return eachCalendar;
	}
	public void setEachCalendar(Calendar eachCalendar) {
		this.eachCalendar = eachCalendar;
	}
	public HashMap<String, Patron> getPatrons() {
		return patrons;
	}
	public void setPatrons(HashMap<String, Patron> patrons) {
		this.patrons = patrons;
	}
	public ArrayList<OverdueNotice> getdN() {
		return dN;
	}
	public void setdN(ArrayList<OverdueNotice> dN) {
		this.dN = dN;
	}
	public static Patron getPatronBeingServed() {
		return patronBeingServed;
	}
	public static void setPatronBeingServed(Patron patronBeingServed) {
		Library.patronBeingServed = patronBeingServed;
	}
	public ArrayList<Book> getBookSearchOrIn() {
		return bookSearchOrIn;
	}
	public void setBookSearchOrIn(ArrayList<Book> bookSearchOrIn) {
		this.bookSearchOrIn = bookSearchOrIn;
	}
	public ArrayList<Book> getCollection() {
		return collection;
	}
	public void setCollection(ArrayList<Book> collection) {
		this.collection = collection;
	}



}