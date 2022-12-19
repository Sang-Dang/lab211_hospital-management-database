package gui.menu;

/**
 *
 * @author User
 */
public class Table extends CommonMethods {
    String table;
    private final int PADDING = OFFSET + 3;

    protected Table() {
        table = "";
    }

    protected String generateTable(String[] options) {
        int width = getLongestOption(options) + PADDING;
        table += getDivider(width);
        for(String i: options) 
            table += "| " + i + generateSpaces(width-3-i.length()) + "|\n";
        table += getDivider(width);
        return table;
    }

    protected String generateTable(String[] options, String title) {
        // if title is longer than the longest option in list, use title length as base width. in either case, add width with PADDING
        int width = (getLongestOption(options) > title.length()) ? getLongestOption(options) + PADDING : title.length() + PADDING;
        // if title length and width are even and odd or vice versa, increment width
        width = (width + title.length()) % 2 != 0 ? width + 1 : width;

        table += new Title().generateTitle(width, title);
        table += getDivider(width);

        for(String i: options) 
            table += "| " + i + generateSpaces(width-3-i.length()) + "|\n";
        table += getDivider(width);
        return table;
    }
}
