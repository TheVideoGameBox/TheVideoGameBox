package Presentation.Command.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.bson.types.ObjectId;

public class CommandSearchOne implements ICommand {
    @Override
    public Context execute(Object data) {
        SAGame saGame = SAAbstractFactory.getInstance().createSAGame();
        TGame result = saGame.searchOne((ObjectId) data);
        Context con;

        if (result != null) con = new Context(Event.RES_SEARCH_ONE_OK, result);
        else con = new Context(Event.RES_SEARCH_ONE_KO, null);

        return con;
    }
}
