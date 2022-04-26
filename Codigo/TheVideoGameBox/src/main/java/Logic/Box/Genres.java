package Logic.Box;

public enum Genres {
    SHOOTER("Shooter"),
    STRATEGY("Strategy"),
    INDIE("Indie"),
    RPG("RPG"),
    RACING("Racing"),
    SANDBOX("Sandbox"),
    HORROR("Horror"),
    SPORTS("Sports"),
    SURVIVAL("Survival");

    private String genre;

    Genres(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return genre;
    }
}
