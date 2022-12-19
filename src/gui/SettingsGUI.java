package gui;

import gui.menu.Menu;
import java.lang.reflect.Field;
import tools.Inputter;
import tools.Settings;
import tools.SettingsManager;

/**
 *
 * @author User
 */
public class SettingsGUI {
    
    private SettingsGUI() {}
    public static final String TITLE = "SETTINGS";

    public static void showGUI() {
        do {
            String[] formattedOptions = parseOptions();
            
            Menu.printNumberedTable(formattedOptions, TITLE);

            int choice = Inputter.inputOption("What setting do you want to change? ", formattedOptions.length + 1) - 1;
            if (choice == formattedOptions.length - 1) {
                if(!SettingsManager.getInstance().save()) {
                    System.out.println("Save unsuccessful.");
                } else {
                    System.out.println("Settings saved successfully.");
                }
                break;
            }
            SettingsManager.getInstance().switchState(choice);
        } while(true);
    }
    
    private static String[] parseOptions() {
        String[] result = new String[Settings.OPTIONS.length + 1];
        int index = 0;
        for(Field i: SettingsManager.getInstance().getFields()) {
            StringBuilder sb = new StringBuilder();
            try {
                if(i.getBoolean(i) == true) {
                    sb.append("[x] ");
                } else {
                    sb.append("[ ] ");
                }
            } catch(IllegalAccessException | IllegalArgumentException e) {
                System.out.println(e);
            }
            sb.append(Settings.OPTIONS[index]);
            result[index] = sb.toString();
            index++;
        }
        result[result.length - 1] = "Save and Return to Main menu...";
        return result;
    }

    public static void main(String[] args) {
        showGUI();
    }
}
