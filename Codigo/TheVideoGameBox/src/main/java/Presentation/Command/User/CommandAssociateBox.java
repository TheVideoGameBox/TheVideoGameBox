package Presentation.Command.User;

import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;

public class CommandAssociateBox implements ICommand {


    @Override
    public Context execute(Object data) {
        SAUser saUser = SAAbstractFactory.getInstance().createSAUser();
        Pair<ObjectId, ObjectId> aux = (Pair<ObjectId, ObjectId>) data;
        ObjectId result = saUser.addBox(aux.getLeft(), aux.getRight());
        Context con;

        if (result != null) con = new Context(Event.RES_ASSOCIATE_BOX_OK, result);
        else con = new Context(Event.RES_ASSOCIATE_BOX_KO, null);

        return con;
    }
}
