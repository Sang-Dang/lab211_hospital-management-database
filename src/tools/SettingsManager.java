package tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * THIS FILE PROVIDES FUNCTIONALITY FOR THE SETTINGS INTERFACE. 
 * THIS IS A SINGLETON CLASS, ALL ACCESS IS MADE VIA THE STATIC GETINSTANCE METHOD
 * DO NOT CHANGE ANYTHING IN THIS FILE. TO ADD MORE SETTINGS, GO TO SETTINGS.JAVA.
 * @author User
 */
public class SettingsManager extends Settings {
    private static final String SEP = "=";
    private static final SettingsManager instance = new SettingsManager();
    private static ArrayList<Field> settingFields;
    private static Field[] allFields;
    
    /**
     * static initialiser 
     */
    private SettingsManager() {
        allFields = Settings.class.getDeclaredFields();
        settingFields = new ArrayList<>();
        loadSettings();
    }
    
    /**
     * load all NON-FINAL fields into the Field array "settingFields"
     */
    private void loadSettings() {
        for(Field i: allFields) 
            if((i.getModifiers() & Modifier.FINAL) != Modifier.FINAL) {
                i.setAccessible(true);
                settingFields.add(i);
            }
    }
    
    /**
     * load data from external file "settings.dat" into the settings.java
     */
    public static void load() {
        List<String> lines = Util.readLinesFromFile(LoadFilePath.getInstance().getSettingsPath());
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(SEP);
            try {
                settingFields.get(i).setBoolean(null, Boolean.parseBoolean(parts[1].trim()));
            } catch(IllegalAccessException | IllegalArgumentException e) {
                System.out.println(e);
            }
        }
    }
    
    /**
     * switch the state of a setting from true to false and vice versa
     * @param index index of the setting to be changed in the "settingFields" array
     */
    public void switchState(int index) {
        Field here = settingFields.get(index);
        try {
            boolean temp = here.getBoolean(null);
            here.setBoolean(null, !temp);
        } catch(IllegalAccessException | IllegalArgumentException e) {
            System.out.println(e);
        }
    }
    
    /**
     * get the instance of SettingsManager
     * @return 
     */
    public static final SettingsManager getInstance() {
        return instance;
    }
    
    /**
     * get all fields with declared NON-FINAL
     * @return 
     */
    public final ArrayList<Field> getFields() {
        return settingFields;
    }
    
    /**
     * save all settings to the file
     * @return true if all settings saved successfully 
     */
    public boolean save() {
        String save[] = new String[settingFields.size()];
        for(int i = 0; i < save.length; i++) {
            try {
                save[i] = settingFields.get(i).getName() + " = " + settingFields.get(i).getBoolean(null);
            } catch(IllegalAccessException | IllegalArgumentException e) {
                System.out.println(e);
                return false;
            }
        }
        Util.saveLinesToFile(LoadFilePath.getInstance().getSettingsPath(), save);
        return true;
    }
}
