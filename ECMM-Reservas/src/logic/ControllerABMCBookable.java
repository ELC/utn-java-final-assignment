package logic;

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
	
	
	public void RegisterBookable(Bookable b){
		app.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_BOOKABLE)){
			//lanzo exepci�n
		}
		dataBookable.add(b);
	}
	
	public void ModifyBookable(Bookable b){
		app.isLoggedIn();	
		if(!activePerson.getPrivileges().contains(AccessLevel.MODIFY_BOOKABLE)){
			//lanzo exepci�n
		}
		dataBookable.update(b);
	}
	
	public void DeleteBookable(Bookable b){
		app.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_BOOKABLE)){
			//lanzo exepci�n
		}
		dataBookable.delete(b);
	}
}
