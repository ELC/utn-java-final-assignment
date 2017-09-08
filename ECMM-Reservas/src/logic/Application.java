package logic;

import entities.AccessLevel;
import entities.Person;

public class Application {
	private static Application instancia;
	
	private Person activePerson;
	
	public Person getActivePerson() {
		return activePerson;
	}

	public void setActivePerson(Person activePerson) {
		this.activePerson = activePerson;
	}
	
	public void LogOutPerson() throws Exception{
		isLoggedIn();
		activePerson = null;
	}

	public void isLoggedIn() throws Exception{
		if(activePerson==null){
			throw new Exception();
		}
	}
	
	public void hasPermission(AccessLevel permission) throws Exception{
		if(!activePerson.getPrivileges().contains(permission)){
			throw new Exception();
		}
	}
	
	private Application(){}
	
	public static Application getInstancia(){
		if (Application.instancia == null){
			Application.instancia = new Application();
		}
		return Application.instancia;
	}
}
