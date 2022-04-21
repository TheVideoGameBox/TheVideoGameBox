package Logic.User;

import Data.Box.DAOBox;
import Data.DAOAbstractFactory;
import Data.Game.DAOGame;
import Data.User.DAOUser;
import Logic.Box.TBox;
import Logic.Game.TGame;
import org.bson.types.ObjectId;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SAUserImp implements SAUser {

	@Override
	public ObjectId createUser(TUser user) {
		if(!correctEmail(user.getEmail()))
			return null;
		if(!correctUsername(user.getUsername()))
			return null;
		if(!correctPassword(user.getPassword()))
			return null;
			
		DAOUser daoUser = DAOAbstractFactory.getInstance().createDAOUser();
		return daoUser.create(user);
	}

	@Override
	public ObjectId logIn(TUser user) {			//Pendiente de encriptar cuando el sistema de registro lo este
		TUser aux;
		if(!correctEmail(user.getEmail()))
			return null;
		if(!correctPassword(user.getPassword()))
			return null;

		DAOUser daoUser = DAOAbstractFactory.getInstance().createDAOUser();
		aux = daoUser.logIn(user.getEmail());
		if(aux != null) {
			if(Objects.equals(aux.getPassword(), user.getPassword()))
				return aux.getId();
		}
		return null;
	}

	@Override
	public ObjectId addBox(ObjectId idUser, ObjectId idBox) {
		DAOUser daoUser = DAOAbstractFactory.getInstance().createDAOUser();
		return daoUser.addBox(idUser, idBox);
	}

	@Override
	public List<TBox> userBoxes(ObjectId id) {
		List<TBox> boxes = new ArrayList<>();
		DAOUser daoUser = DAOAbstractFactory.getInstance().createDAOUser();
		List<ObjectId> boxesIDs = daoUser.userBoxes(id);

		if(boxesIDs == null) return boxes;

		DAOBox daoBox = DAOAbstractFactory.getInstance().createDAOBox();
		for(ObjectId boxID : boxesIDs){
			TBox tBox = daoBox.showBox(boxID);
			if(tBox == null) return boxes;
			boxes.add(tBox);
		}
		return boxes;
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
		}	catch(AddressException ex) {
			result = false;
		}
		return result;
	}

	@Override
	public void deleteFromDatabase(ObjectId id) {
		DAOAbstractFactory.getInstance().createDAOUser().deleteFromDatabase(id);
	}
}
