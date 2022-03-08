package Logic.Person;

public class TPerson {
    private int id;
    private String nif;
    private String nombre;
    private String apellidos;
    private boolean activo;

    public TPerson(String nif, String nombre, String apellidos) {
        this.id = -1;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.activo = true;
    }

    public TPerson(int id, String nif, String nombre, String apellidos, boolean activo) {
        this.id = id;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
