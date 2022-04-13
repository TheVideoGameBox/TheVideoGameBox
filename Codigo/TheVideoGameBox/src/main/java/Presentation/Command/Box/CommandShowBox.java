package Presentation.Command.Box;

import org.bson.types.ObjectId;

import Logic.SAAbstractFactory;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

public class CommandShowBox implements ICommand {
    @Override
    public Context execute(Object data) {
        SABox saBox = SAAbstractFactory.getInstance().createSABox();
        TBox result = saBox.showBox((ObjectId) data);
        Context con;

        if(result != null) con = new Context(Event.RES_SHOW_BOX_OK, result);
        else con = new Context(Event.RES_SHOW_BOX_KO, null);

        return con;
    }
}
