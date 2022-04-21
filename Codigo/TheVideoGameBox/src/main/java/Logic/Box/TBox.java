package Logic.Box;

import org.bson.types.ObjectId;

import java.util.ArrayList;
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
    //Array de TGames
    private List<ObjectId> gameList;
    //Booleano para gestionar la baja logica
    private boolean active;

    //Constructors


    public TBox(String name, String description, Privacy privacy, List<Genres> genres) {
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.gameList = new ArrayList<>();
    }

    public TBox(String name, String description, Privacy privacy, List<Genres> genres, List<ObjectId> gameList) {
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.gameList = gameList;
    }

    public TBox(ObjectId id, String name, String description, Privacy privacy, List<Genres> genres, List<ObjectId> gameList, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.gameList = gameList;
        this.active = active;
    }

    public TBox(ObjectId _id, String name, String description, Privacy privacy, List<Genres> genres) {
    	 this.id = _id;
         this.name = name;
         this.description = description;
         this.privacy = privacy;
         this.genres = genres;
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

