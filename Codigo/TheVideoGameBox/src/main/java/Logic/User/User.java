package Logic.User;

import org.bson.types.ObjectId;

public class User {
    private ObjectId id;
    private String email;
    private String username;
    private String password;

    public User() {

    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(ObjectId id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(TUser tUser) {
        this.id = tUser.getId();
        this.email = tUser.getEmail();
        this.username = tUser.getUsername();
        this.password = tUser.getPassword();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Obtener todos los datos de un transfer
    public void transferToMongoEntity(TUser tUser) {
        this.id = tUser.getId();
        this.email = tUser.getEmail();
        this.username = tUser.getUsername();
        this.password = tUser.getPassword();
    }

    //Transformar una entidad de MongoDB a un transfer
    public TUser toTransfer() {
        return new TUser(id, email, username, password);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }
}
