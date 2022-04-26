package Logic.Game;

import org.bson.types.ObjectId;

import java.util.List;

public class TGame {

    private ObjectId id;
    private String name;
    private String cover;
    private List<String> involved_companies;
    private List<String> genres;
    private List<String> platforms;
    private String summary;
    private int release_dates;
    private String image;

    public TGame(String name, String cover, List<String> involved_companies, List<String> categories, List<String> platforms, String summary, int release_dates, String image) {
        this.name = name;
        this.cover = cover;
        this.involved_companies = involved_companies;
        this.genres = categories;
        this.platforms = platforms;
        this.summary = summary;
        this.release_dates = release_dates;
        this.image = image;
    }

    public TGame(ObjectId id, String name, String cover, List<String> involved_companies, List<String> categories, List<String> platforms, String summary, int release_dates, String image) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.involved_companies = involved_companies;
        this.genres = categories;
        this.platforms = platforms;
        this.summary = summary;
        this.release_dates = release_dates;
        this.image = image;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> getInvolved_companies() {
        return involved_companies;
    }

    public void setInvolved_companies(List<String> involved_companies) {
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

    public int getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(int release_dates) {
        this.release_dates = release_dates;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                ", release_dates='" + release_dates + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
