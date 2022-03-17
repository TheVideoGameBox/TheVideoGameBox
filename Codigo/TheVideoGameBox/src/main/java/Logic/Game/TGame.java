package Logic.Game;

import org.bson.types.ObjectId;

import java.util.List;

public class TGame {
    //MongoDB id
    private ObjectId id;
    //Nombre de juego
    private String name;
    //Portada del juego
    private String cover;
    //Lista de desarrolladores del juego
    private List<String> developer;
    //Lista de categorias del juego
    private List<String> categories;
    //Lista de plataformas(consolas/ordenador) del juego
    private List<String> platforms;
    //Descripcion del juego
    private String description;
    //Booleano para gestionar la baja logica
    private boolean isDeleted;

    //Constructors
    public TGame(String name, String cover, List<String> developer, List<String> categories, List<String> platforms, String description) {
        this.name = name;
        this.cover = cover;
        this.developer = developer;
        this.categories = categories;
        this.platforms = platforms;
        this.description = description;
    }

    public TGame(ObjectId id, String name, String cover, List<String> developer, List<String> categories, List<String> platforms, String description, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.developer = developer;
        this.categories = categories;
        this.platforms = platforms;
        this.description = description;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> getDeveloper() {
        return developer;
    }

    public void setDeveloper(List<String> developer) {
        this.developer = developer;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
