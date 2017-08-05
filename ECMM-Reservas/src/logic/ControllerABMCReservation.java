package logic;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import data.DataReservation;
import entities.*;

public class ControllerABMCReservation {
	private ControllerABMCPerson ctrlPer;
	private ControllerABMCBookable ctrlBookable;
	private ControllerABMCTypeBookable ctrlTypeBookable;
	private DataReservation dataRes;
	private Person activePerson;
	private Application app;
	
	public ControllerABMCReservation(){
		ctrlPer= new ControllerABMCPerson();
		ctrlBookable= new ControllerABMCBookable();
		ctrlTypeBookable= new ControllerABMCTypeBookable();
		Application app = Application.getInstancia();
		dataRes= new DataReservation();
		activePerson=app.getActivePerson();
		app = Application.getInstancia();
	}
	
	public void RegisterReservation(TypeBookable type, Date date, Bookable book){
		app.isLoggedIn();	
		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_RESERVATION)){
			//lanzo exepción
		}
		Reservation re= new Reservation();
		re.setPerson(app.getActivePerson()); // El admin, puede crear reservas a nombre de otros usuarios
		re.setBookable(book);
		re.setDate(date);
		dataRes.add(re);
	}
	
	public List<Reservation> getAllReservation(){
		app.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.ACCESS_RESERVATION)){
			//lanzo exepción
		}
		return dataRes.getAll();
	}
	
	public List<Reservation> getAllByUser(){
		app.isLoggedIn();
		List<Reservation> reservations = dataRes.getByIdPerson(activePerson);
		
//		List<Reservation> filteredReservations = reservations;
//		for(Reservation re : reservations){
//			if (re.getDate().toLocalDate().compareTo(LocalDate.now()) < 0){
//				filteredReservations.remove(re);
//			}
//		}
		
		reservations.removeIf(s -> s.getDate().toLocalDate().compareTo(LocalDate.now()) < 0); // Hace lo mismo que lo de arriba
		return reservations;		
	}

	public void DeleteReservation(Reservation re){
		app.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_RESERVATION)){
			//lanzo exepción
		}
		dataRes.delete(re);
	}
}
