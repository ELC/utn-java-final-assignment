package logic;

import data.DataBookable;
import entities.*;
public class ControllerABMCBookable {
	
	private ControllerABMCPerson ctrlPer;
	private Person activePerson=null;
	private DataBookable dataBookable;
	public ControllerABMCBookable(){
		ctrlPer= new ControllerABMCPerson(); //Preguntar al profe si debe ser Singleton//
		activePerson= ctrlPer.getActivePerson(); 
		dataBookable= new DataBookable();
	}
	
	
	public void RegisterBookable(Bookable b){
		ctrlPer.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_BOOKABLE)){
			//lanzo exepción
		}
		dataBookable.add(b);
	}
	
	public void ModifyBookable(Bookable b){
		ctrlPer.isLoggedIn();	
		if(!activePerson.getPrivileges().contains(AccessLevel.MODIFY_BOOKABLE)){
			//lanzo exepción
		}
		dataBookable.update(b);
	}
	
	public void DeleteBookable(Bookable b){
		ctrlPer.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_BOOKABLE)){
			//lanzo exepción
		}
		dataBookable.delete(b);
	}
}
