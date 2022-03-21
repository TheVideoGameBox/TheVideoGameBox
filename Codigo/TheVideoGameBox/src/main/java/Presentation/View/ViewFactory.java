package Presentation.View;

import java.util.List;

import Logic.Game.TGame;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.Game.ViewSearchByName;
import Presentation.View.Main.ViewPrincipal;

public class ViewFactory extends ViewAbstractFactory{
	
	
    @SuppressWarnings("unchecked")
	@Override
    public IView createView(Context context) {
        switch(context.getEvent()) {
        	case Event.VIEW -> currentView = new ViewPrincipal();
        	case Event.RES_SEARCH_ALL_BY_NAME_OK -> currentView = new ViewSearchByName((List<TGame>) context.getData());
        };
        return currentView;
    }

	@Override
	public IView getCurrentIView() {
		return currentView;
	}
}
