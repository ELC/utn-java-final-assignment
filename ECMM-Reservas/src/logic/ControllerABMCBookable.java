package logic;

import java.util.ArrayList;
import java.util.Date;

import data.DataBookable;
import entities.*;
public class ControllerABMCBookable {
	
	private ControllerABMCPerson ctrlPer;
	private Person activePerson=null;
	private DataBookable dataBookable;
	private Application app;
	
	public ControllerABMCBookable(){
		ctrlPer= new ControllerABMCPerson(); //Preguntar al profe si debe ser Singleton//
		app = Application.getInstancia();
		activePerson= app.getActivePerson(); 
		dataBookable= new DataBookable();
	}
	
	
	public void RegisterBookable(Bookable b)throws Exception{
//		app.isLoggedIn();
//		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_BOOKABLE)){
//			//lanzo exepción
//		}
		dataBookable.add(b);
	}
	
	public void ModifyBookable(Bookable b)throws Exception{
//		app.isLoggedIn();	
//		if(!activePerson.getPrivileges().contains(AccessLevel.MODIFY_BOOKABLE)){
//			//lanzo exepción
//		}
		dataBookable.update(b);
	}
	
	public void DeleteBookable(Bookable b)throws Exception{
//		app.isLoggedIn();
//		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_BOOKABLE)){
//			//lanzo exepción
//		}
		dataBookable.delete(b);
	}

	
	public Bookable getByName(Bookable b)throws Exception{
		return dataBookable.getByName(b);
		
	}
	
	public Bookable getByName(String name)throws Exception{
		return dataBookable.getByName(name);
		
	}
	
	public ArrayList<Bookable> GetAll()throws Exception{
		return dataBookable.getAll();
	
	}

public ArrayList<Bookable> getAllByType(TypeBookable bookable_type) throws Exception{
		return dataBookable.getAllByType(bookable_type);
	
}


public ArrayList<Bookable> getAllAvailable(TypeBookable bookable_type,Date date) throws Exception{
	return dataBookable.getAvailableBookable(bookable_type, (java.sql.Date) date);

}
}
