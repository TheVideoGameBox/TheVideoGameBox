package Logic.Box;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Box {

	//Falta añadir usuario al que pertenece la Box y array de ID's de Games
	
	
	//MongoDB id
    private ObjectId id;
    //Nombre de la box
    private String name;
    //Descripcion de la box
    private String description;
    //Privacidad de la box
    private boolean privacy;
    //Categoría de la box
    private String category;
    //Booleano para gestionar la baja logica
    private boolean active;
    
    //Constructors
    
    public Box() {}
    
	public Box(String name, String description, boolean privacy, String category) {
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.category = category;
	}
	
	public Box(ObjectId id, String name, String description, boolean privacy, String category, boolean active) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.category = category;
		this.active = active;
	}
	
	public Box(TBox box) {
		this.id=box.getId();
		this.name=box.getName();
		this.description=box.getDescription();
		this.privacy=box.getPrivacy();
		this.category=box.getCategory();
		this.active=box.isActive();
	}

    //Getters and setters

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPrivacy() {
		return privacy;
	}
	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
    
	
	public TBox toTransfer() {
		return new TBox(id, category, category, active, category, active);
	}
	
	
}
