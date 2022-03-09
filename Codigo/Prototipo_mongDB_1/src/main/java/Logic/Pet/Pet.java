package Logic.Pet;

import org.bson.types.ObjectId;

public class Pet {

    private ObjectId id;
    private String nif;
    private String nombre;
    private String tipo;
    private String infoExtra;
    private boolean activo;

    public Pet(ObjectId id, String nif, String nombre, String tipo, String infoExtra, Boolean activo) {
        this.id = id;
        this.nif = nif;
        this.nombre = nombre;
        this.tipo = tipo;
        this.infoExtra = infoExtra;
        this.activo = activo;
    }

    public Pet(TPet tPet) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInfoExtra() {
        return infoExtra;
    }

    public void setInfoExtra(String infoExtra) {
        this.infoExtra = infoExtra;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TPet toTransfer() {
        return new TPet(id, nif, nombre, tipo, infoExtra, activo);
    }

    @Override
    public String toString() {
        return "Person[" +
                "id=" + id +
                ", nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", infoExtra='" + infoExtra + '\'' +
                ", activo=" + activo +
                ']';
    }
}
