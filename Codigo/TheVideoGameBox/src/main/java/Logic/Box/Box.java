package Logic.Box;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Box {

    //Falta añadir usuario al que pertenece la Box y array de ID's de Games
    private ObjectId id;
    private String name;
    private String description;
    private Privacy privacy;
    private List<Genres> genres;
    private List<ObjectId> gameList;
    private boolean active;

    public Box() {
    }

    public Box(String name, String description, Privacy privacy, List<Genres> genres) {
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.gameList = new ArrayList<>();
    }

    public Box(String name, String description, Privacy privacy, List<Genres> genres, List<ObjectId> gameList) {
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.gameList = gameList;
    }

    public Box(ObjectId id, String name, String description, Privacy privacy, List<Genres> genres, List<ObjectId> gameList, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.genres = genres;
        this.gameList = gameList;
        this.active = active;
    }

    public Box(TBox box) {
        this.id = box.getId();
        this.name = box.getName();
        this.description = box.getDescription();
        this.privacy = box.getPrivacy();
        this.genres = box.getGenres();
        this.active = box.isActive();
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

    public List<ObjectId> getGameList() {
        return gameList;
    }

    public void setGameList(List<ObjectId> gameList) {
        this.gameList = gameList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TBox toTransfer() {
        return new TBox(id, name, description, privacy, genres, gameList, active);
    }
}
