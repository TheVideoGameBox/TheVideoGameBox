package Logic.Box;

import java.util.List;

import org.bson.types.ObjectId;

public class TBox {
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
    private boolean isDeleted;
	
    //Constructors
    
    public TBox(String name, String description, boolean privacy, String category) {
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.category = category;
	}


	public TBox(ObjectId id, String name, String description, boolean privacy, String category, boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.category = category;
		this.isDeleted = isDeleted;
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


	public boolean getPrivacy() {
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


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
    
}

