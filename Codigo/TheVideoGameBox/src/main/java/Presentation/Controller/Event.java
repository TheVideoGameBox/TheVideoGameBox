package Presentation.Controller;

public class Event {

    // Codigos de eventos para la UI [0-100)
    public static final int VIEW = 0;
    public static final int BACK = 1;
    public static final int BACK_AUX = 2;


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
    public static final int SEARCH_ALL_BY_PLATFORM = 109;
    public static final int RES_SEARCH_ALL_BY_PLATFORM_OK = 110;
    public static final int RES_SEARCH_ALL_BY_PLATFORM_KO = 111;


    // Codigos de eventos para USER [300-500)
    public static final int CREATE_USER = 300;
    public static final int RES_CREATE_USER_OK = 301;
    public static final int RES_CREATE_USER_KO = 302;
    public static final int VIEW_CREATE_USER = 303;
    public static final int LOGIN_USER = 304;
    public static final int RES_LOGIN_USER_OK = 305;
    public static final int RES_LOGIN_USER_KO = 306;
    public static final int VIEW_LOGIN = 307;
    public static final int ASSOCIATE_BOX = 308;
    public static final int RES_ASSOCIATE_BOX_OK = 309;
    public static final int RES_ASSOCIATE_BOX_KO = 310;
    public static final int USER_BOXES = 311;
    public static final int RES_USER_BOXES_OK = 312;
    public static final int RES_USER_BOXES_KO = 313;
    public static final int UPDATE_USER_BOX_LIST = 314;
    public static final int RES_UPDATE_USER_BOX_LIST_OK = 315;
    public static final int RES_UPDATE_USER_BOX_LIST_KO = 316;
    public static final int DELETE_BOX_FROM_USER = 317;
    public static final int RES_DELETE_BOX_FROM_USER_OK = 318;
    public static final int RES_DELETE_BOX_FROM_USER_KO = 319;


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
    public static final int RES_SEARCH_ALL_BOXES_BY_NAME_OK = 511;
    public static final int RES_SEARCH_ALL_BOXES_BY_NAME_KO = 512;
    public static final int RES_SHOW_BOX_OK = 513;
    public static final int RES_SHOW_BOX_KO = 514;
    public static final int SHOW_BOX = 515;
    public static final int DELETE_BOX = 516;
    public static final int RES_DELETE_BOX_OK = 517;
    public static final int RES_DELETE_BOX_KO = 518;
    public static final int DELETE_GAME_FROM_BOX = 519;
    public static final int RES_DELETE_GAME_FROM_BOX_OK = 520;
    public static final int RES_DELETE_GAME_FROM_BOX_KO = 521;
    public static final int UPDATE_GAME_LIST = 522;
    public static final int RES_UPDATE_GAME_LIST_OK = 523;
    public static final int RES_UPDATE_GAME_LIST_KO = 524;
    public static final int MODIFY_BOX = 525;
    public static final int RES_MODIFY_BOX_OK = 526;
    public static final int RES_MODIFY_BOX_KO = 527;
    public static final int VIEW_MODIFY_BOX = 528;

}
