package developer.alexangan.ru.bidsandpersonal.Models;

import android.content.SharedPreferences;

public class GlobalConstants
{
    public static SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";

    public static String tokenStr;
    public static int user_id;
    public static boolean logoutInProgress = false;

    public static String API_HOST_URL = "http://api/index.php/";
    public static String API_LOGIN_URL = API_HOST_URL + "api/login";
    public static String USER_AVATAR_URL = "http://api/upload/agent/";


    public static String API_GET_CLIENTS_URL = API_HOST_URL + "api/getClients";

}


