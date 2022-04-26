package Launcher;

import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(() -> ApplicationController.getInstance().action(new Context(Event.VIEW, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
