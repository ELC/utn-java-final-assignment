package entities;

import java.util.List;

import entities.AccessLevel;

public class Person {
	
	private int id;
	private String dni;
	private String name;
	private String lastName;
	private String username;
	private String password;
	private boolean enabled;
	private List<AccessLevel> privileges;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getPrivileges() {
		return AccessLevel.combinePermissions(this.privileges);	
	}
	
	public void setPrivileges(int privileges) {
		this.privileges = AccessLevel.parsePermissions(privileges);
	}
}
