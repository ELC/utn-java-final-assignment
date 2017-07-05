package logic;

import java.sql.Date;
import java.util.List;

import entities.*;

public class ControllerABMCReservation {
	private ControllerABMCPerson ctrlPer;
	private ControllerABMCBookable ctrlBookable;
	private ControllerABMCTypeBookable ctrlTypeBookable;
	public ControllerABMCReservation(){
		ctrlPer= new ControllerABMCPerson();
		ctrlBookable= new ControllerABMCBookable();
		ctrlTypeBookable= new ControllerABMCTypeBookable();
	}
	
	public void createReservation() {
		
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

	public void deleteReservation(){}
		


}
