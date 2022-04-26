package Logic.User;

import Data.Box.DAOBox;
import Data.DAOAbstractFactory;
import Data.User.DAOUser;
import Logic.Box.TBox;
import org.bson.types.ObjectId;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

public class SAUserImp implements SAUser {

    private DAOAbstractFactory daoFactory;

    public SAUserImp() {
        this.daoFactory = DAOAbstractFactory.getInstance();
    }

    public SAUserImp(DAOAbstractFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ObjectId createUser(TUser user) {
        if (!correctEmail(user.getEmail()))
            return null;
        if (!correctUsername(user.getUsername()))
            return null;
        if (!correctPassword(user.getPassword()))
            return null;

        TUser encryptedUser = new TUser(user.getEmail(), user.getUsername(), BCryptUtility.hash(user.getPassword()));

        return daoFactory.createDAOUser().create(encryptedUser);
    }

    @Override
    public ObjectId logIn(TUser user) {
        if (!correctEmail(user.getEmail()))
            return null;
        if (!correctPassword(user.getPassword()))
            return null;

        DAOUser daoUser = daoFactory.createDAOUser();
        TUser aux = daoUser.logIn(user.getEmail());

        if (aux != null && BCryptUtility.verifyHash(user.getPassword(), aux.getPassword()))
            return aux.getId();

        return null;
    }

    @Override
    public ObjectId addBox(ObjectId idUser, ObjectId idBox) {
        DAOUser daoUser = daoFactory.createDAOUser();
        return daoUser.addBox(idUser, idBox);
    }

    @Override
    public List<TBox> userBoxes(ObjectId id) {
        List<TBox> boxes = new ArrayList<>();
        DAOUser daoUser = daoFactory.createDAOUser();
        List<ObjectId> boxesIDs = daoUser.userBoxes(id);

        if (boxesIDs == null) return boxes;

        DAOBox daoBox = daoFactory.createDAOBox();
        for (ObjectId boxID : boxesIDs) {
            TBox tBox = daoBox.showBox(boxID);
            if (tBox == null) return boxes;
            boxes.add(tBox);
        }
        return boxes;
    }

    @Override
    public void deleteUserBoxFromDatabase(ObjectId idUser, ObjectId idBox) {
        daoFactory.createDAOUser().deleteUserBoxFromDatabase(idUser, idBox);
    }

    @Override
    public void deleteFromDatabase(ObjectId id) {
        daoFactory.createDAOUser().deleteFromDatabase(id);
    }

    private boolean correctPassword(String password) {
        return password != null;
    }

    private boolean correctUsername(String username) {
        return username != null && (username.length() > 0 && username.length() <= 50);
    }

    private boolean correctEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}
