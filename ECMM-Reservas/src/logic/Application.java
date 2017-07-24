package logic;

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
	
	
	public void LogOutPerson(){
		isLoggedIn();
		activePerson =null;
	}

	public void isLoggedIn(){
		if(activePerson==null){
			//lanzo exepción
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
