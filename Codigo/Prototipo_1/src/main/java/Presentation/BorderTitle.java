package Presentation;

import javax.swing.border.TitledBorder;
import java.awt.Color;

public class BorderTitle extends TitledBorder{

	public BorderTitle(String title) {
		super(title);
	}
	public BorderTitle(String title, Color c) {
		super(title);
		setTitleColor(c);
	}

}
