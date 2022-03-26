package Logic.Box;

import org.bson.types.ObjectId;

import java.util.List;

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
    private List<Genres> genres;
    //Booleano para gestionar la baja logica
    private boolean active;
	//Array de TGames
	private List<ObjectId> gameList;
	private ObjectId gameId;

    //Constructors
    
    public TBox(String name, String description, Privacy privacy, List<Genres> genres) {
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.genres = genres;
		this.active = true;
	}

	public TBox(ObjectId id, String name, String description, Privacy privacy, List<Genres> genres, boolean active) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.genres = genres;
		this.active = active;
	}

	public TBox(ObjectId id, String name, String description, Privacy privacy, List<Genres> genres, boolean active, List<ObjectId> gameList) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privacy = privacy;
		this.genres = genres;
		this.active = active;
		this.gameList = gameList;
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


	public List<Genres> getGenres() {
		return genres;
	}


	public void setGenres(List<Genres> genres) {
		this.genres = genres;
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
}

