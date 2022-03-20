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
    //Lista de compañias involucradas en el desarrollo del juego
    private List<String> involved_companies;
    //Lista de categorias del juego
    private List<String> genres;
    //Lista de plataformas(consolas/ordenador) del juego
    private List<String> platforms;
    //Descripcion del juego
    private String summary;

    //Constructors

    public TGame(String name, String cover, List<String> involved_companies, List<String> categories, List<String> platforms, String description) {
        this.name = name;
        this.cover = cover;
        this.involved_companies = involved_companies;
        this.genres = categories;
        this.platforms = platforms;
        this.summary = description;
    }

    public TGame(ObjectId id, String name, String cover, List<String> involved_companies, List<String> categories, List<String> platforms, String description) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.involved_companies = involved_companies;
        this.genres = categories;
        this.platforms = platforms;
        this.summary = description;
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

    public List<String> getInvolvedCompanies() {
        return involved_companies;
    }

    public void setInvolvedCompanies(List<String> involved_companies) {
        this.involved_companies = involved_companies;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", involved_companies=" + involved_companies +
                ", genres=" + genres +
                ", platforms=" + platforms +
                ", summary='" + summary + '\'' +
                '}';
    }
}
