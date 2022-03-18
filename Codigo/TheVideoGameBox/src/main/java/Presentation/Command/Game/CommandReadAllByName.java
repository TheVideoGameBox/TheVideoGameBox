package Presentation.Command.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Command.Context;
import Presentation.Command.ICommand;
import Presentation.Controller.Event;

import java.util.List;

public class CommandReadAllByName implements ICommand {

    @Override
    public Context execute(Object data) {
        SAGame saGame = SAAbstractFactory.getInstance().createSAGame();
        List<TGame> result = saGame.SearchAllByname((String) data);
        Context con;

        if(!result.isEmpty()) con = new Context(Event.ResSearchAllByNameOk, result);
        else con = new Context(Event.ResSearchAllByNameKo, result);

        return con;
    }
}
