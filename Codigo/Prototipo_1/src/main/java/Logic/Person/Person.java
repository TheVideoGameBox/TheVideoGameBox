package Logic.Person;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "nif")})
@NamedQueries({
        @NamedQuery(name="Person.findByNIF", query="SELECT P FROM Person P WHERE :nif = P.nif"),
        @NamedQuery(name="Person.findAll", query="SELECT P FROM Person P")
})

public class Person implements Serializable {

	private static final long serialVersionUID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nif;
    private String nombre;
    private String apellidos;
    private boolean activo;

    @Version
    private int version;

    public Person() {
    }

    public Person(String nif, String nombre, String apellidos) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Person(int id, String nif, String nombre, String apellidos, boolean activo) {
        this.id = id;
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.activo = activo;
    }

    public Person(TPerson tPerson){
        this.id = tPerson.getId();
        this.nif = tPerson.getNif();
        this.nombre = tPerson.getNombre();
        this.apellidos = tPerson.getApellidos();
        this.activo = tPerson.isActivo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }
}
