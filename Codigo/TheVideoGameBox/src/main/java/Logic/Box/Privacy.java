package Logic.Box;

public enum Privacy {
    PRIVATE("Private"),
    PUBLIC("Public");

    private String privacy;

    Privacy(String privacy) {
        this.privacy = privacy;
    }

    @Override
    public String toString() {
        return privacy;
    }
}
