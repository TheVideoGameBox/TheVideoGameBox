package Presentation.View;

import java.util.List;

import Logic.Game.TGame;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.Game.ViewSearchByName;
import Presentation.View.Game.ViewShowOne;
import Presentation.View.Main.ViewPrincipal;
import org.bson.types.ObjectId;

public class ViewFactory extends ViewAbstractFactory{
	
	
    @SuppressWarnings("unchecked")
	@Override
    public IView createView(Context context) {
        switch(context.getEvent()) {
        	case Event.VIEW, Event.RES_SEARCH_ALL_BY_NAME_KO -> currentView = new ViewPrincipal();
        	case Event.RES_SEARCH_ALL_BY_NAME_OK -> currentView = new ViewSearchByName((List<TGame>) context.getData());
			case Event.RES_SEARCH_ONE_OK -> currentView = new ViewShowOne((TGame) context.getData());
        };
        return currentView;
    }

	@Override
	public IView getCurrentIView() {
		return currentView;
	}
}
