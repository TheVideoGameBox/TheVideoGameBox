package Logic.Person;

public class TPerson {
    private String nif;
    private String nombre;
    private String apellidos;
    private boolean activo;

    public TPerson(String nif, String nombre, String apellidos) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.activo = true;
    }

    public TPerson(String nif, String nombre, String apellidos, boolean activo) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.activo = activo;
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
