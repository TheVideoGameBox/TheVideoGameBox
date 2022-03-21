package Logic.Box;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Box {

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
    
	public Box(String name, String description, boolean privacy, String category) {
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.category = category;
	}
	
	public Box(ObjectId id, String name, String description, boolean privacy, String category, boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.category = category;
		this.isDeleted = isDeleted;
	}
	
	public Box(TBox box) {
		this.id=box.getId();
		this.name=box.getName();
		this.description=box.getDescription();
		this.privacy=box.getPrivacy();
		this.category=box.getCategory();
		this.isDeleted=box.isDeleted();
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
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
	
	public TBox toTransfer() {
		return new TBox(id, category, category, isDeleted, category, isDeleted);
	}
	
	
}
