package entities;

import java.sql.Date;

public class Reservation {

	private Person person;
	private Bookable bookable;
	private Date date;

	public void setDate(Date date) {
		this.date = date;
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

	public Date getDate() {
		return date;
	}
}
