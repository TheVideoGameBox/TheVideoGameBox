package Logic.Box;

public enum Privacy {
    PRIVATE("Privado"),
    PUBLIC("Publico");

    private String privacy;

    Privacy(String privacy){
        this.privacy = privacy;
    }

    @Override
    public String toString(){
        return privacy;
    }
}
