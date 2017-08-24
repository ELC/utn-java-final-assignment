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
//			//lanzo exepci�n
//		}
		dataPer.add(p);
	}
	
	public void LoginPerson(Person p) throws Exception{
		if (Application.getInstancia().getActivePerson()!=null){
			//Lanzo exepci�n	
		}
		Person per= dataPer.getByUsername(p.getUsername());
		if(per.getPassword()!=p.getPassword()){
			//lanzo exepci�n
		}
		Application.getInstancia().setActivePerson(per);
	}
	
	public void ModifyPerson(Person p)throws Exception{
//		app.isLoggedIn();
//		if(!Application.getInstancia().getActivePerson().getPrivileges().contains(AccessLevel.MODIFY_USER)){
//			//lanzo exepci�n
//		}
		dataPer.update(p);
	}
	
	public void DeletePerson(Person p)throws Exception{
//		app.isLoggedIn();
//		if(!Application.getInstancia().getActivePerson().getPrivileges().contains(AccessLevel.DELETE_USER)){
//			//lanzo exepci�n
//		}
		dataPer.delete(p);
	}

	public Person getByDni(Person p) throws Exception{
		return dataPer.getByDni(p);
		
	}
	
}
