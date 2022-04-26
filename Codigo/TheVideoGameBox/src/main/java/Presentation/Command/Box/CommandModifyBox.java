package Presentation.Command.Box;

import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.bson.types.ObjectId;

public class CommandModifyBox implements ICommand {

    @Override
    public Context execute(Object data) {
        SABox saBox = SAAbstractFactory.getInstance().createSABox();
        TBox box = (TBox) data;
        ObjectId result = saBox.modifyBox(box);
        Context con;

        if (result != null) {
            con = new Context(Event.RES_MODIFY_BOX_OK, result);
        } else {
            con = new Context(Event.RES_MODIFY_BOX_KO, null);
        }

        return con;
    }
}
