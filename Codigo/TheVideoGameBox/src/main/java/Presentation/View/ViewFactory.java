package Presentation.View;

import Presentation.Controller.Event;
import Presentation.View.Main.ViewPrincipal;

public class ViewFactory extends ViewAbstractFactory{
    @Override
    public IView createView(int event) {
        return switch(event) {
        	case Event.VIEW -> new ViewPrincipal();
        	default -> null;  
        };
    }
}
