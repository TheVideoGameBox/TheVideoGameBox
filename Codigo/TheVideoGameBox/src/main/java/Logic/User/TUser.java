package Logic.User;

import org.bson.types.ObjectId;

import java.util.List;

public class TUser {

    private ObjectId id;
    private String email;
    private String username;
    private String password;
    private Boolean active;
    private List<ObjectId> boxList;


    public TUser(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public TUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public TUser(ObjectId id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public TUser(ObjectId id, String email, String username, String password, List<ObjectId> boxList) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.boxList = boxList;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<ObjectId> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<ObjectId> boxList) {
        this.boxList = boxList;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", boxList= " + boxList +
                '}';
    }
}


