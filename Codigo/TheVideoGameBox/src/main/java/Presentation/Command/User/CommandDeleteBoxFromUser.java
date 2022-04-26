package Presentation.Command.User;

import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;

public class CommandDeleteBoxFromUser implements ICommand {

    @Override
    public Context execute(Object data) {
        SAUser saUser = SAAbstractFactory.getInstance().createSAUser();
        ;
        Pair<ObjectId, ObjectId> args = (Pair<ObjectId, ObjectId>) data;
        saUser.deleteUserBoxFromDatabase(args.getLeft(), args.getRight());

        return new Context(Event.RES_DELETE_BOX_FROM_USER_OK, null);

    }
}
