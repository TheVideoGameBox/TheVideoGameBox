package Presentation.View.Box;

import javax.swing.JFrame;

import Logic.Box.TBox;
import Presentation.Controller.Context;
import Presentation.View.IView;

public class ViewModifyBox extends JFrame implements IView {
	
	private TBox box;
	
	@Override
	public void update(Context context) {
		// TODO Auto-generated method stub
		
	}
	
	public ViewModifyBox(TBox box) {
		super();
		this.box = box;
		initGUI();
	}
	
	private void initGUI() {
		
	}
	
}
