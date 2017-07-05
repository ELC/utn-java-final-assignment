package entities;

import java.sql.Date;

public class Reservation {
	private int id;
	private Person person;
	private Bookable bookable;
	private Date date;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
