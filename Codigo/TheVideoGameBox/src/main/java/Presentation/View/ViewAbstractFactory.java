package Presentation.View;

import Presentation.Controller.Context;

public abstract class ViewAbstractFactory {
    private static ViewAbstractFactory instance;
    protected IView currentView;
    
    public static ViewAbstractFactory getInstance(){
        if (instance == null)
            instance = new ViewFactory();

        return instance;
    }

    public abstract IView createView(Context context);
    public abstract IView getCurrentIView();
}
