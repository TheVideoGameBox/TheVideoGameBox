package Launcher;

import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.ViewFactory;

import javax.swing.*;

public class Launcher {

	public static void main(String[] args) {
		try {
            SwingUtilities.invokeAndWait(() -> ViewFactory.getInstance().createView(new Context(Event.VIEW, null)));
        } catch (Exception e) {
        	e.printStackTrace(); 	
        }
	}

}
