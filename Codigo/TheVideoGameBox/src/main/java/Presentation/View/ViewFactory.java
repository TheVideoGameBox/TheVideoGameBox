package Presentation.View;

import Logic.Box.TBox;
import Logic.Game.TGame;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.Box.*;
import Presentation.View.Game.ViewSearchGamesByName;
import Presentation.View.Game.ViewShowOne;
import Presentation.View.Main.ViewMain;
import Presentation.View.User.ViewLogIn;
import Presentation.View.User.ViewRegister;
import Presentation.View.User.ViewUserBoxes;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ViewFactory extends ViewAbstractFactory {

    @Override
    public IView createView(Context context) {
        IView view = null;

        switch (context.getEvent()) {
            case Event.VIEW:
                view = new ViewMain();
                break;
            case Event.RES_SEARCH_ALL_BY_NAME_OK:
                view = new ViewSearchGamesByName();
                break;
            case Event.RES_SEARCH_ONE_OK:
                view = new ViewShowOne();
                break;
            case Event.VIEW_CREATE_BOX:
                view = new ViewCreateBox();
                break;
            case Event.VIEW_CREATE_USER:
                view = new ViewRegister();
                break;
            case Event.RES_SEARCH_ALL_BOXES_BY_NAME_OK:
                view = new ViewSearchBoxesByName();
                break;
            case Event.RES_LIST_GAMES_OF_BOX_OK:
                view = new ViewListGamesBox((Pair<TBox, List<TGame>>) context.getData());
                break;
            case Event.ADD_GAME_TO_BOX:
                view = new ViewAddGameToBox((Pair<List<TGame>, TBox>) context.getData());
            case Event.LIST_GAMES_OF_BOX:
                view = new ViewListGamesBox((Pair<TBox, List<TGame>>) context.getData());
                break;
            case Event.RES_SHOW_BOX_OK:
                view = new ViewShowBox();
                break;
            case Event.VIEW_LOGIN:
                view = new ViewLogIn();
                break;
            case Event.RES_SEARCH_ALL_BY_PLATFORM_OK:
                view = new ViewSearchGamesByName();
                break;
            case Event.RES_USER_BOXES_OK:
                view = new ViewUserBoxes();
                break;
            case Event.VIEW_MODIFY_BOX:
                view = new ViewModifyBox((TBox) context.getData());
                break;
            default:
                break;
        }

        return view;
    }
}
