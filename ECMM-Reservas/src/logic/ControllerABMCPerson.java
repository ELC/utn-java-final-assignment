package logic;

import data.DataPerson;
import entities.*;


public class ControllerABMCPerson {
	
	private Person activePerson=null;
	private DataPerson dataPer;
	
	public ControllerABMCPerson(){
		dataPer= new DataPerson();
	}
	
	
	public void RegisterPerson(Person p){
		isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.CREATE_USER)){
			//lanzo exepci�n
		}
		dataPer.add(p);
	}
	
	public void LoginPerson(Person p){
		if (activePerson!=null){
			//Lanzo exepci�n	
		}
		Person per= dataPer.getByUsername(p.getUsername());
		if(per.getPassword()!=p.getPassword()){
			//lanzo exepci�n
		}
		activePerson=per;
	}

	public void LogOutPerson(){
		isLoggedIn();
		activePerson=null;
	}
	
	public void ModifyPerson(Person p){
		isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.MODIFY_USER)){
			//lanzo exepci�n
		}
		dataPer.update(p);
	}
	
	public void DeletePerson(Person p){
		isLoggedIn();
		if(!activePerson.getPrivileges().contains(AccessLevel.DELETE_USER)){
			//lanzo exepci�n
		}
		dataPer.delete(p);
	}
	
	

	
	public void isLoggedIn(){
		if(activePerson==null){
			//lanzo exepci�n
		}
	}
	
	public Person getActivePerson() {
		return activePerson;	
	}

}
