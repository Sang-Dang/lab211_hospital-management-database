package tools;

/**
 *
 * @author User
 */
public class Settings {
    public static boolean ALLOW_CLS;
    public static boolean RT_SAVE;
    public static boolean SLOW_MODE;
    
    public static final String[] OPTIONS = {
        "Allow clear screen.",
        "Real-time saving.",
        "Slow-mode - Press enter to continue after every operation.",
    };
    
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String CONFIG_PATH = ".\\src\\datafiles\\config.dat";
    public static final int PASSWORD_TRIES = 5;
}
