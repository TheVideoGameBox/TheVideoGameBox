package Presentation.Command.User;

import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.bson.types.ObjectId;

import java.util.List;

public class CommandUpdateUserBoxList implements ICommand {

    @Override
    public Context execute(Object data) {
        SAUser saUser = SAAbstractFactory.getInstance().createSAUser();
        List<TBox> result = saUser.userBoxes((ObjectId) data);
        Context con;

        if (result != null) con = new Context(Event.RES_UPDATE_USER_BOX_LIST_OK, result);
        else con = new Context(Event.RES_UPDATE_USER_BOX_LIST_KO, null);

        return con;
    }
}
