package logic;

import data.DataTypeBookable;
import entities.*;
public class ControllerABMCTypeBookable {
	
	private ControllerABMCPerson ctrlPer;
	private Person activePerson=null;
	private DataTypeBookable dataTypeBookable;
	public ControllerABMCTypeBookable(){
		ctrlPer= new ControllerABMCPerson(); //Preguntar al profe si debe ser Singleton//
		activePerson= ctrlPer.getActivePerson(); 
		dataTypeBookable= new DataTypeBookable();
	}
	
	
	public void RegisterTypeBookable(TypeBookable b){
		ctrlPer.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_TYPEBOOKABLE)){
			//lanzo exepción
		}
		dataTypeBookable.add(b);
	}
	
	public void ModifyTypeBookable(TypeBookable b){
		ctrlPer.isLoggedIn();	
		if(!activePerson.getPrivileges().contains(AccessLevel.MODIFY_TYPEBOOKABLE)){
			//lanzo exepción
		}
		dataTypeBookable.update(b);
	}
	
	public void DeleteTypeBookable(TypeBookable b){
		ctrlPer.isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_TYPEBOOKABLE)){
			//lanzo exepción
		}
		dataTypeBookable.delete(b);
	}
}
