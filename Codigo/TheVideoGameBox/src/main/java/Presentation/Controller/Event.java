package Presentation.Controller;

public class Event {

    // Codigos de eventos para la UI [0-100)
    public static final int VIEW = 0;



    //  Codigos de eventos para GAME [100-300)
    public static final int SEARCH_ALL_BY_NAME = 100;
    public static final int RES_SEARCH_ALL_BY_NAME_OK = 101;
    public static final int RES_SEARCH_ALL_BY_NAME_KO = 102;



    // Codigos de eventos para USER [300-500)





    // Codigos de eventos para BOX [500-700)
    public static final int CREATE_BOX = 500;
    public static final int RES_CREATE_BOX_OK = 502;
    public static final int RES_CREATE_BOX_KO = 503;
    public static final int ADD_GAME_TO_BOX = 504;
    public static final int RES_ADD_GAME_TO_BOX_OK = 505;
    public static final int RES_ADD_GAME_TO_BOX_KO = 506;

}
