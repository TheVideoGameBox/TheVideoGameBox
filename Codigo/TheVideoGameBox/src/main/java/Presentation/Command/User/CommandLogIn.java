package Presentation.Command.User;

import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Logic.User.TUser;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.bson.types.ObjectId;

public class CommandLogIn implements ICommand {

    @Override
    public Context execute(Object data) {
        SAUser saUser = SAAbstractFactory.getInstance().createSAUser();
        TUser user = (TUser) data;
        ObjectId result = saUser.logIn(user);
        Context con;

        if (result != null) con = new Context(Event.RES_LOGIN_USER_OK, result);
        else con = new Context(Event.RES_LOGIN_USER_KO, null);

        return con;
    }
}
