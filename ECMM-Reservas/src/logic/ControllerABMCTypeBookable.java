package logic;

import java.util.ArrayList;

import data.DataTypeBookable;
import entities.*;
public class ControllerABMCTypeBookable {
	
	private ControllerABMCPerson ctrlPer;
	private Person activePerson=null;
	private DataTypeBookable dataTypeBookable;
	private Application app;
	
	public ControllerABMCTypeBookable(){
		ctrlPer= new ControllerABMCPerson(); //Preguntar al profe si debe ser Singleton//
		app = Application.getInstancia();
		activePerson= app.getActivePerson(); 
		dataTypeBookable= new DataTypeBookable();
	}
	
	
	public void RegisterTypeBookable(TypeBookable b)throws Exception{
//		app.isLoggedIn();
//		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_TYPEBOOKABLE)){
//			//lanzo exepci�n
//		}
		dataTypeBookable.add(b);
	}
	
	public void ModifyTypeBookable(TypeBookable b)throws Exception{
//		app.isLoggedIn();	
//		if(!activePerson.getPrivileges().contains(AccessLevel.MODIFY_TYPEBOOKABLE)){
//			//lanzo exepci�n
//		}
		dataTypeBookable.update(b);
	}
	
	public void DeleteTypeBookable(TypeBookable b)throws Exception{
//		app.isLoggedIn();
//		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_TYPEBOOKABLE)){
//			//lanzo exepci�n
//		}
		dataTypeBookable.delete(b);
	}

	public ArrayList<TypeBookable> getAll()throws Exception{
		return dataTypeBookable.getAll();
		
		
	}

	public TypeBookable getByName(TypeBookable t) throws Exception{
		return dataTypeBookable.getByName(t);
		
	
	}
	
}
