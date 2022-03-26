package Logic.Game;

import org.bson.types.ObjectId;

import java.util.List;

public class Game {

    private ObjectId id;
    private String name;
    private String cover;
    private List<String> involved_companies;
    private List<String> genres;
    private List<String> platforms;
    private String summary;
    private int release_dates;

    public Game() {

    }

    public Game(String name, String cover, List<String> involved_companies, List<String> categories, List<String> platforms, String description, int release_dates) {
        this.name = name;
        this.cover = cover;
        this.involved_companies = involved_companies;
        this.genres = categories;
        this.platforms = platforms;
        this.summary = description;
        this.release_dates = release_dates;
    }

    public Game(ObjectId id, String name, String cover, List<String> involved_companies, List<String> categories, List<String> platforms, String description, int release_dates) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.involved_companies = involved_companies;
        this.genres = categories;
        this.platforms = platforms;
        this.summary = description;
        this.release_dates = release_dates;
    }

    public Game(TGame tGame){
        this.id = tGame.getId();
        this.name = tGame.getName();
        this.cover = tGame.getCover();
        this.involved_companies = tGame.getInvolvedCompanies();
        this.genres = tGame.getGenres();
        this.platforms = tGame.getPlatforms();
        this.summary = tGame.getSummary();
        this.release_dates = tGame.getRelease_dates();
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

    //Obtener todos los datos de un transfer
    public void setData(TGame tGame){
        this.id = tGame.getId();
        this.name = tGame.getName();
        this.cover = tGame.getCover();
        this.involved_companies = tGame.getInvolvedCompanies();
        this.genres = tGame.getGenres();
        this.platforms = tGame.getPlatforms();
        this.summary = tGame.getSummary();
        this.release_dates = tGame.getRelease_dates();
    }

    public int getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(int release_dates) {
        this.release_dates = release_dates;
    }

    //Transformar una entidad de MongoDB a un transfer
    public TGame toTransfer(){
        return new TGame(id, name, cover, involved_companies, genres, platforms, summary, release_dates);
    }

    @Override
    public String toString() {
        return "{ " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", involved_companies=" + involved_companies +
                ", categories=" + genres +
                ", platforms=" + platforms +
                ", summary='" + summary + '\'' +
                ", release_dates='" + release_dates + '\'' +
                " }";
    }
}
