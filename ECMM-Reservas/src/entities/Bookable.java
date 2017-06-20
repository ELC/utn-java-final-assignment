package entities;

public class Bookable {
	
	private int id;
	private String name;
	private TypeBookable type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public TypeBookable getType() {
		return type;
	}
	public void setType(TypeBookable type) {
		this.type = type;
	}
	

}
