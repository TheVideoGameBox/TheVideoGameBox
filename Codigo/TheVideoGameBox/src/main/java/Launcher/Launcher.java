package Launcher;

import javax.swing.SwingUtilities;

import Presentation.View.Main.ViewPrincipal;

public class Launcher {

	public static void main(String[] args) {
		try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    new ViewPrincipal();
                }
            });
        } catch (Exception e) {
        	e.printStackTrace(); 	
        }
	}

}
