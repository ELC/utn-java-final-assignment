package logic;

import data.DataPerson;
import entities.*;


public class ControllerABMCPerson {
	
	private DataPerson dataPer;
	private Application app;
	
	public ControllerABMCPerson(){
		dataPer= new DataPerson();
		app = Application.getInstancia();
	}
	
	
	public void RegisterPerson(Person p)throws Exception{
//		app.isLoggedIn();
//		if(!Application.getInstancia().getActivePerson().getPrivileges().contains(AccessLevel.CREATE_USER)){
//			//lanzo exepción
//		}
		dataPer.add(p);
	}
	
	public void LoginPerson(Person p) throws Exception{
		if (Application.getInstancia().getActivePerson()!=null){
			//Lanzo exepción	
		}
		Person per= dataPer.getByUsername(p.getUsername());
		if(per.getPassword()!=p.getPassword()){
			//lanzo exepción
		}
		Application.getInstancia().setActivePerson(per);
	}
	
	public void ModifyPerson(Person p)throws Exception{
//		app.isLoggedIn();
//		if(!Application.getInstancia().getActivePerson().getPrivileges().contains(AccessLevel.MODIFY_USER)){
//			//lanzo exepción
//		}
		dataPer.update(p);
	}
	
	public void DeletePerson(Person p)throws Exception{
//		app.isLoggedIn();
//		if(!Application.getInstancia().getActivePerson().getPrivileges().contains(AccessLevel.DELETE_USER)){
//			//lanzo exepción
//		}
		dataPer.delete(p);
	}

	public Person getByDni(Person p) throws Exception{
		return dataPer.getByDni(p);
		
	}
	
}
