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
	private Person activePerson=null;
	public ControllerABMCReservation(){
		ctrlPer= new ControllerABMCPerson();
		ctrlBookable= new ControllerABMCBookable();
		ctrlTypeBookable= new ControllerABMCTypeBookable();
		dataRes= new DataReservation();
		activePerson=ctrlPer.getActivePerson();
	}
	
	public void RegisterReservation() {
		ctrlPer.isLoggedIn();	
		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_RESERVATION)){
			//lanzo exepción
		}
		
		TypeBookable type=ctrlTypeBookable.selectType();
		Date date=selectDateTime();
		List<Bookable> books= ctrlBookable.getAllAvailableByDateAndType(date,type);
		Bookable book= selectItem();
		Reservation re= new Reservation();
		re.setPerson(ctrlPer.getActivePerson());
		re.setBookable(book);
		re.setDate(date);
		dataRes.add(re);
	}


	public void ModifyReservation(Reservation re){
		ctrlPer.isLoggedIn();	
		if(!activePerson.getPrivileges().contains(AccessLevel.MODIFY_RESERVATION)){
			//lanzo exepción
		}
		dataRes.update(re);
	}
	
	public void DeleteReservation(Reservation re){
		ctrlPer.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_RESERVATION)){
			//lanzo exepción
		}
		dataRes.delete(re);
	}
		


}
