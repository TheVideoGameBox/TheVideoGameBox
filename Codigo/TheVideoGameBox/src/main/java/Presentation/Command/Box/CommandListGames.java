package Presentation.Command.Box;

import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CommandListGames implements ICommand {
    @Override
    public Context execute(Object data) {
        SABox saBox = SAAbstractFactory.getInstance().createSABox();
        Pair<TBox, List<TGame>> result = new Pair<TBox, List<TGame>>() {
            @Override
            public TBox getLeft() {
                return (TBox) data;
            }

            @Override
            public List<TGame> getRight() {
                return saBox.listGames((TBox) data);
            }

            @Override
            public List<TGame> setValue(List<TGame> value) {
                return null;
            }
        };

        Context con;

        if (result != null) con = new Context(Event.RES_LIST_GAMES_OF_BOX_OK, result);
        else con = new Context(Event.RES_LIST_GAMES_OF_BOX_KO, null);

        return con;
    }
}
