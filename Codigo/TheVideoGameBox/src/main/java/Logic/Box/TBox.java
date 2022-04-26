package Logic.Box;

import org.bson.types.ObjectId;

import java.util.List;

public class TBox {

    //Falta añadir usuario al que pertenece la Box y array de ID's de Games

    private ObjectId id;
    private String name;
    private String description;
    private Privacy privacy;
    private List<Genres> genres;
    private List<ObjectId> gameList;
    private boolean active;
    private ObjectId owner;

    //Constructors

    public TBox(String name, String description, Privacy privacy, List<Genres> genres) {
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.active = true;
    }

    public TBox(ObjectId id, String name, String description, Privacy privacy, List<Genres> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
    }

    public TBox(String name, String description, Privacy privacy, List<Genres> genres, ObjectId owner) {
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.owner = owner;
        this.active = true;
    }

    public TBox(String name, String description, Privacy privacy, List<Genres> genres, List<ObjectId> gameList, ObjectId owner) {
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.gameList = gameList;
        this.owner = owner;
        this.active = true;
    }

    public TBox(ObjectId id, String name, String description, Privacy privacy, List<Genres> genres, List<ObjectId> gameList, boolean active, ObjectId owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.gameList = gameList;
        this.active = active;
        this.owner = owner;
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

    public ObjectId getOwner() {
        return owner;
    }

    public void setOwner(ObjectId owner) {
        this.owner = owner;
    }
}

