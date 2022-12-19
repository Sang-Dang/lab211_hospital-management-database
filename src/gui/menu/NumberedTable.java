package gui.menu;

/**
 *
 * @author User
 */
public class NumberedTable extends CommonMethods {
    String table = "";
    private final int PADDING = OFFSET + 5;

    private int getOptionsNumberMaxLength(String[] options) {
        int num = options.length + 1;
        int count = 0;
        while(num != 0) {
            count++;
            num/=10;
        }
        return count;
    }

    protected String generateTable(String[] options) {
        int width = getLongestOption(options) + getOptionsNumberMaxLength(options) + PADDING;

        table += getDivider(width);
        for(int i = 0; i < options.length; i++) 
            table += "| " + (i+1) + ". " + options[i] + generateSpaces(width - 5 - getOptionsNumberMaxLength(options) - options[i].length()) + "|\n";
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
        for(int i = 0; i < options.length; i++) 
            table += "| " + (i+1) + ". " + options[i] + generateSpaces(width - 5 - getOptionsNumberMaxLength(options) - options[i].length()) + "|\n";
        table += getDivider(width);
        return table;
    }
}