package entities;

import java.util.ArrayList;
import java.util.List;

public enum AccessLevel{
	DELETE_USER	(1 << 0),
	CREATE_USER	(1 << 1),
	MODIFY_USER	(1 << 2),
	CREATE_BOOKABLE	(1 << 3),
	MODIFY_BOOKABLE	(1 << 4),
	DELETE_BOOKABLE	(1 << 5),
	CREATE_TYPEBOOKABLE	(1 << 6),
	MODIFY_TYPEBOOKABLE	(1 << 7),
	DELETE_TYPEBOOKABLE	(1 << 8),
	CREATE_RESERVATION (1 << 9),
	MODIFY_RESERVATION (1 << 10),
	DELETE_RESERVATION (1 << 11);
	
	
	private final int _value;
	AccessLevel(int value){
		_value = value;
	}
	
	public static List<AccessLevel> parsePermissions(int val){
	    List<AccessLevel> apList = new ArrayList<AccessLevel>();
	    for (AccessLevel ap : values()){
	    	if ((val & ap._value) != 0){
	    		apList.add(ap);
	    	}
	    }
	    return apList;
	}

	public static int combinePermissions(List<AccessLevel> privileges){
		int summaryPermission = 0;
		for(AccessLevel permission:privileges){
			summaryPermission |=  permission._value;
		}
		return summaryPermission;
	}
}
