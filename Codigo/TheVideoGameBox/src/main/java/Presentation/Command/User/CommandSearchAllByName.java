package Presentation.Command.User;

import java.util.List;

import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Logic.User.TUser;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

public class CommandSearchAllByName implements ICommand{

	 @Override
	    public Context execute(Object data) {
	        SAUser saUser = SAAbstractFactory.getInstance().createSAUser();
	        List<TUser> result = saUser.searchAllByName((String) data);
	        Context con;

	        if(result != null && !result.isEmpty()) con = new Context(Event.RES_SEARCH_BY_NAME_OK, result);
	        else con = new Context(Event.RES_SEARCH_BY_NAME_KO, result);

	        return con;
	    }
}
