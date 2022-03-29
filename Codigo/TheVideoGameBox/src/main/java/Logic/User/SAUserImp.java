package Logic.User;

import Data.DAOAbstractFactory;
import Data.User.DAOUser;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.bson.types.ObjectId;

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
