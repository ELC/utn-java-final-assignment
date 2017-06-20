package entities;

import java.sql.Timestamp;

public class Reservation {

	private Person person;
	private Bookable bookable;
	private Timestamp date;

	public Reservation(){
		this.date = new Timestamp(System.currentTimeMillis());
	}

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public Bookable getBookable() {
		return bookable;
	}
	public void setBookable(Bookable bookable) {
		this.bookable = bookable;
	}

	public Timestamp getDate() {
		return date;
	}
}
