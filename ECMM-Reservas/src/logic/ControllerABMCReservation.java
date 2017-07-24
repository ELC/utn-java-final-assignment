package logic;

import java.sql.Date;
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
	
	public void RegisterReservation(TypeBookable type, Date date, Bookable book) {
		app.isLoggedIn();	
		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_RESERVATION)){
			//lanzo exepción
		}
		Reservation re= new Reservation();
		re.setPerson(app.getActivePerson());
		re.setBookable(book);
		re.setDate(date);
		dataRes.add(re);
//		select *
//		from bookable
//		inner join reservas
//			on reservas.cod_bookable = bookable.cod_bookable
//		where reservas.date <> ? or (reservas.date == ? and (reservas.time + ? < ? or ? + ? < reservas.time))
	}


	public void ModifyReservation(Reservation re){
		app.isLoggedIn();	
		if(!activePerson.getPrivileges().contains(AccessLevel.MODIFY_RESERVATION)){
			//lanzo exepción
		}
		dataRes.update(re);
	}
	
	public void DeleteReservation(Reservation re){
		app.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_RESERVATION)){
			//lanzo exepción
		}
		dataRes.delete(re);
	}
		


}
