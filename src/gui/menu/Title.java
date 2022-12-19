package gui.menu;

/**
 *
 * @author User
 */
public class Title extends CommonMethods {
    String title;

    Title() {
        title = "";
    }

    protected String generateTitle(int width, String titleName) {
        title += getDivider(width);
        title += "|" + generateSpaces((width - titleName.length() - 2)/2) + titleName + generateSpaces((width - titleName.length() - 2)/2) + "|\n"; 
        return title;
    }
}
