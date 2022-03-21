package Logic.Box;

import org.bson.types.ObjectId;

import java.util.List;

public class Box {

	//Falta añadir usuario al que pertenece la Box y array de ID's de Games
	
	
	//MongoDB id
    private ObjectId id;
    //Nombre de la box
    private String name;
    //Descripcion de la box
    private String description;
    //Privacidad de la box
    private Privacy privacy;
    //Categoría de la box
    private List<Category> categories;
    //Booleano para gestionar la baja logica
    private boolean active;
    
    //Constructors
    
    public Box() {}
    
	public Box(String name, String description, Privacy privacy, List<Category> categories) {
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.categories = categories;
	}
	
	public Box(ObjectId id, String name, String description, Privacy privacy, List<Category> categories, boolean active) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.categories = categories;
		this.active = active;
	}
	
	public Box(TBox box) {
		this.id = box.getId();
		this.name = box.getName();
		this.description = box.getDescription();
		this.privacy = box.getPrivacy();
		this.categories = box.getCategories();
		this.active = box.isActive();
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
	public Privacy isPrivacy() {
		return privacy;
	}
	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
    
	
	public TBox toTransfer() {
		return new TBox(id, name, description, privacy, categories, active);
	}
	
	
}
