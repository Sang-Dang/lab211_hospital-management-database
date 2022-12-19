package gui.menu;

/**
 *
 * @author User
 */
public class CommonMethods {
    protected static final int OFFSET = 7;

    protected int getLongestOption(String[] options) {
        int max = options[0].length();
        for(String i: options) 
            if(max < i.length())
                max = i.length();
        return max;
    }

    protected String getDivider(int width) {
        String returnValue = "";
        returnValue += "+";
        for(int i = 0; i < width - 2; i++) 
            returnValue += "-";
        returnValue += "+\n";
        return returnValue;
    }

    protected String generateSpaces(int num) {
        String returnValue = "";
        for(int i = 0; i < num; i++) 
            returnValue += " ";
        return returnValue;
    }
}
