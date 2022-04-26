package Presentation.Command.Box;

import Logic.Box.SABox;
import Logic.SAAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;

public class CommandAddGame implements ICommand {

    @Override
    public Context execute(Object data) {
        SABox saBox = SAAbstractFactory.getInstance().createSABox();
        Pair<ObjectId, ObjectId> aux = (Pair<ObjectId, ObjectId>) data;
        ObjectId result = saBox.addGame(aux.getLeft(), aux.getRight());
        Context con;

        if (result != null) con = new Context(Event.RES_ADD_GAME_TO_BOX_OK, result);
        else con = new Context(Event.RES_ADD_GAME_TO_BOX_KO, null);

        return con;
    }
}
