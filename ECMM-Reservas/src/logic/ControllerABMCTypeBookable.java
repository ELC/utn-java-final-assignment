package logic;

import java.util.ArrayList;
import data.DataTypeBookable;
import entities.*;

public class ControllerABMCTypeBookable {
	private DataTypeBookable dataTypeBookable = new DataTypeBookable();
	private Application app = Application.getInstancia();

	public void RegisterTypeBookable(TypeBookable b)throws Exception{
		app.isLoggedIn();
//		app.hasPermission(AccessLevel.CREATE_TYPEBOOKABLE);
		dataTypeBookable.add(b);
	}
	
	public void ModifyTypeBookable(TypeBookable b)throws Exception{
		app.isLoggedIn();	
//		app.hasPermission(AccessLevel.MODIFY_TYPEBOOKABLE);
		dataTypeBookable.update(b);
	}
	
	public void DeleteTypeBookable(TypeBookable b)throws Exception{
		app.isLoggedIn();
//		app.hasPermission(AccessLevel.DELETE_TYPEBOOKABLE);
		dataTypeBookable.delete(b);
	}

	public ArrayList<TypeBookable> getAll()throws Exception{
		return dataTypeBookable.getAll();
	}

	public TypeBookable getByName(TypeBookable t) throws Exception{
		return dataTypeBookable.getByName(t);	
	}	
}
