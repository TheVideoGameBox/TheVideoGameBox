package Presentation.Controller;

public class Context {

    private int event;
    private Object data;

    public Context(int event, Object data) {
        this.event = event;
        this.data = data;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
