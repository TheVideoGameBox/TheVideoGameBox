package Presentation.Controller;

public class Event {

    // Codigos de eventos para la UI [0-100)
    public static final int VIEW = 0;
    public static final int BACK = 1;
    
    
    //  Codigos de eventos para GAME [100-300)
    public static final int SEARCH_ALL_BY_NAME = 100;
    public static final int RES_SEARCH_ALL_BY_NAME_OK = 101;
    public static final int RES_SEARCH_ALL_BY_NAME_KO = 102;
    public static final int SEARCH_ONE = 103;
    public static final int RES_SEARCH_ONE_OK = 104;
    public static final int RES_SEARCH_ONE_KO = 105;
    public static final int RANDOM_GAMES = 106;
    public static final int RES_RANDOM_GAMES_OK = 107;
    public static final int RES_RANDOM_GAMES_KO = 108;



    // Codigos de eventos para USER [300-500)
    public static final int CREATE_USER = 300;
    public static final int RES_CREATE_USER_OK = 301;
    public static final int RES_CREATE_USER_KO = 302;
    public static final int VIEW_CREATE_USER = 303;
    public static final int LOGIN_USER = 304;
    public static final int RES_LOGIN_USER_OK = 305;
    public static final int RES_LOGIN_USER_KO = 306;
    public static final int VIEW_LOGIN = 307;


    // Codigos de eventos para BOX [500-700)
    public static final int CREATE_BOX = 500;
    public static final int VIEW_CREATE_BOX = 501;
    public static final int RES_CREATE_BOX_OK = 502;
    public static final int RES_CREATE_BOX_KO = 503;
    public static final int ADD_GAME_TO_BOX = 504;
    public static final int RES_ADD_GAME_TO_BOX_OK = 505;
    public static final int RES_ADD_GAME_TO_BOX_KO = 506;
    public static final int LIST_GAMES_OF_BOX = 507;
    public static final int RES_LIST_GAMES_OF_BOX_OK = 508;
    public static final int RES_LIST_GAMES_OF_BOX_KO = 509;
    public static final int SEARCH_ALL_BOXES_BY_NAME = 510;
    public static final int RES_SEARCH_ALL_BOXES_BY_NAME_OK= 511;
    public static final int RES_SEARCH_ALL_BOXES_BY_NAME_KO= 512;
    public static final int DELETE_BOX = 513;
    public static final int RES_DELETE_BOX_OK= 514;
    public static final int RES_DELETE_BOX_KO= 515;
}
