package Logic.Box;

import java.util.List;

import org.bson.types.ObjectId;

public class TBox {
	
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
    
    public TBox(String name, String description, Privacy privacy, List<Category> categories) {
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.categories = categories;
		this.active = true;
	}


	public TBox(ObjectId id, String name, String description, Privacy privacy, List<Category> categories, boolean active) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.categories = categories;
		this.active = active;
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


	public Privacy getPrivacy() {
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
    
    
}

