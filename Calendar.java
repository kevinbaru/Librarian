package library;

public class Calendar {
	//creates initial date instance variable
	private int date;
	/**
	 * creates a calendar with the date set as 0
	 */
	public Calendar() {
		this.date=0;
	}
	/**
	 * Returns the current date
	 * @return
	 */
	public int getDate(){
		return this.date;
	}
	/**
	 * advances the date by one
	 */
	public void advance(){
		this.date+=1;
	}

}
