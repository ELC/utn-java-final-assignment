package logic;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.DataReservation;
import entities.*;

public class ControllerABMCReservation {
	private DataReservation dataRes;
	
	public ControllerABMCReservation(){
		dataRes = new DataReservation();
	}
	
	public void RegisterReservation(Reservation re)throws Exception{
//		app.isLoggedIn();	
//		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_RESERVATION)){
//			//lanzo exepción
//		}
		dataRes.add(re);
	}
	
	public ArrayList<Reservation> getAllReservation()throws Exception{
//		app.isLoggedIn();
//		if(!activePerson.getPrivileges().contains(AccessLevel.ACCESS_RESERVATION)){
//			//lanzo exepción
//		}
		return dataRes.getAll();
	}
	
	public List<Reservation> getAllByUser() throws Exception{
		Application.getInstancia().isLoggedIn();
		ArrayList<Reservation> reservations = (ArrayList<Reservation>) dataRes.getByIdPerson(Application.getInstancia().getActivePerson());
		
//		List<Reservation> filteredReservations = reservations;
//		for(Reservation re : reservations){
//			if (re.getDate().toLocalDate().compareTo(LocalDate.now()) < 0){
//				filteredReservations.remove(re);
//			}
//		}
		Timestamp hoy = new Timestamp((new Date()).getTime());
		
		reservations.removeIf(s -> s.getDate().before(hoy)); // Hace lo mismo que lo de arriba
		return reservations;		
	}

	public void DeleteReservation(Reservation re)throws Exception{
//		app.isLoggedIn();
//		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_RESERVATION)){
//			//lanzo exepción
//		}
		dataRes.delete(re);
	}
}
