package entities;

import java.util.ArrayList;
import java.util.List;

public enum AccessLevel{
	READ	(1 << 0),
	WRITE	(1 << 1),
	MODIFY	(1 << 2),
	DELETE	(1 << 3);
	
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
