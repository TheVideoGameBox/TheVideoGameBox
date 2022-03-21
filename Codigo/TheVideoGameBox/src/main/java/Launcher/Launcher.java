package Launcher;

import javax.swing.SwingUtilities;

import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.ViewFactory;
import Presentation.View.Main.ViewPrincipal;

public class Launcher {

	public static void main(String[] args) {
		try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    ViewFactory.getInstance().createView(new Context(Event.VIEW, null));
                }
            });
        } catch (Exception e) {
        	e.printStackTrace(); 	
        }
	}

}
