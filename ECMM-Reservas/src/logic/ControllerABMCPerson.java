package logic;

import java.util.Objects;
import data.DataPerson;
import entities.*;
import util.AppDataException;


public class ControllerABMCPerson {
	private DataPerson dataPer = new DataPerson();
	private Application app = Application.getInstancia();
	
	public void LoginPerson(Person p) throws Exception{
		if (Application.getInstancia().getActivePerson()!=null){
			throw new AppDataException(null,"Persona ya logueada");
		}
		Person per= dataPer.getByUsername(p);
		if(!(Objects.equals(per.getPassword(), p.getPassword()))){
			throw new AppDataException(null, "Contraseña Incorrecta");
		}
		Application.getInstancia().setActivePerson(per);
	}
	
	public void LogOutPerson() throws Exception{
		Application.getInstancia().LogOutPerson();
	}
	
	public void RegisterPerson(Person p)throws Exception{
		app.isLoggedIn();
//		app.hasPermission(AccessLevel.CREATE_USER);
		dataPer.add(p);
	}
	
	public void ModifyPerson(Person p)throws Exception{
		app.isLoggedIn();
//		app.hasPermission(AccessLevel.MODIFY_USER);
		dataPer.update(p);
	}
	
	public void DeletePerson(Person p)throws Exception{
//		app.isLoggedIn();
//		app.hasPermission(AccessLevel.DELETE_USER);
		dataPer.delete(p);
	}

	public Person getByDni(Person p) throws Exception{
		return dataPer.getByDni(p);
	}
	
	public Person getByDni(String dni) throws Exception{
		return dataPer.getByDni(dni);
	}
}
