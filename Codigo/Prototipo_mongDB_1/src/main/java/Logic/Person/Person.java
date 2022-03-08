package Logic.Person;

import org.bson.types.ObjectId;

public class Person {
    private ObjectId id;
    private String nif;
    private String nombre;
    private String apellidos;
    private Boolean activo;

    public Person() {
    }

    public Person(String nif, String nombre, String apellidos) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.activo = true;
    }

    public Person(String nif, String nombre, String apellidos, Boolean activo) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.activo = activo;
    }

    public Person(TPerson tPerson) {
        this.nif = tPerson.getNif();
        this.nombre = tPerson.getNombre();
        this.apellidos = tPerson.getApellidos();
        this.activo = tPerson.isActivo();
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public TPerson toTransfer() {
        return new TPerson(nif, nombre, apellidos, activo);
    }

    @Override
    public String toString() {
        return "Person[" +
                "id=" + id +
                ", nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", activo=" + activo +
                ']';
    }
}
