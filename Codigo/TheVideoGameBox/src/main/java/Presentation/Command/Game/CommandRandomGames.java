package Presentation.Command.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

import java.util.List;

public class CommandRandomGames implements ICommand {
    @Override
    public Context execute(Object data) {
        SAGame saGame = SAAbstractFactory.getInstance().createSAGame();
        List<TGame> result = saGame.random();
        Context con;

        if (result != null && !result.isEmpty()) con = new Context(Event.RES_RANDOM_GAMES_OK, result);
        else con = new Context(Event.RES_RANDOM_GAMES_KO, null);

        return con;
    }
}
