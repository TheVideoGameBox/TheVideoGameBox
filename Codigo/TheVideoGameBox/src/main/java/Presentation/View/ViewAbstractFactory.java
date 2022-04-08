package Presentation.View;

public abstract class ViewAbstractFactory {
    private static ViewAbstractFactory instance;
    
    public static ViewAbstractFactory getInstance(){
        if (instance == null)
            instance = new ViewFactory();

        return instance;
    }

    public abstract IView createView(int event);
}
