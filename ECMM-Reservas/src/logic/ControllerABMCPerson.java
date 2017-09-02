package logic;

import java.util.Objects;

import javax.swing.JOptionPane;

import data.DataPerson;
import entities.*;
import util.AppDataException;


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
			throw new AppDataException(null,"Persona ya logueada");
		}
		Person per= dataPer.getByUsername(p);
		if(!(Objects.equals(per.getPassword(), p.getPassword()))){
			throw new AppDataException(null, "Contraseña Incorrecta");
		}
		Application.getInstancia().setActivePerson(per);
	}
	
	public void LogOutPerson(){
		Application.getInstancia().LogOutPerson();
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
	
	public Person getByDni(String dni) throws Exception{
		return dataPer.getByDni(dni);
		
	}
	
}
