package Logic.Box;

import org.bson.types.ObjectId;

import java.util.List;

public class Box {

	//Falta añadir usuario al que pertenece la Box y array de ID's de Games
    private ObjectId id;
    private String name;
    private String description;
    private Privacy privacy;
    private List<Category> categories;
    private boolean active;
	private List<ObjectId> gameList;
	private ObjectId gameId;
    

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

	public Box(ObjectId id, String name, String description, Privacy privacy, List<Category> category, boolean active, List<ObjectId> gameList) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.categories = category;
		this.active = active;
		this.gameList = gameList;
	}

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

	public List<ObjectId> getGameList() {
		return gameList;
	}

	public void setGameList(List<ObjectId> gameList) {
		this.gameList = gameList;
	}
	
	public TBox toTransfer() {
		return new TBox(id, name, description, privacy, categories, active);
	}
	
	
}
