package Logic.Box;

public enum Category {
    SHOOTER("Shooter"),
    STRATEGY("Estrategia"),
    INDIE("Indie"),
    RPG("RPG"),
    RACING("Carreras"),
    SANDBOX("Sandbox"),
    HORROR("Terror"),
    SPORTS("Deporte"),
    SURVIVAL("Supervivencia");

    private String category;

    Category(String category){
        this.category = category;
    }

    @Override
    public String toString(){
        return category;
    }
}
