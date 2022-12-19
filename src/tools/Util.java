package tools;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import static tools.Settings.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author User
 */
public class Util {
    
    // do NOT initialize this class
    private Util() {}
    
    /**
     * read and output lines from a file into an ArrayList
     * @param path path of file
     * @return result array list
     */
    public static List<String> readLinesFromFile(String path) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(path));
            String line = "";
            while((line = input.readLine()) != null) {
                result.add(line);
            }
            input.close();
            return result;
        } catch(IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static boolean saveLinesToFile(String path, Object[] lines) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(path, false));
            for(Object i: lines) {
                output.write(i.toString() + "\n");
            } 
            output.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static Date toDate(String date) throws ParseException {
        if(date == null) 
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(Settings.DATE_FORMAT);
        sdf.setLenient(false);
        return sdf.parse(date);
    }
    
    public static String toDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Settings.DATE_FORMAT);
        return sdf.format(date);
    }
    
    public static void clear() {
        if(Settings.ALLOW_CLS)
            try {
                Robot rob = new Robot();
                rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
                rob.keyPress(KeyEvent.VK_L); // press "L"
                rob.keyRelease(KeyEvent.VK_L); // unpress "L"
                rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
            } catch(AWTException e) {}
    }
    
    public static void ETC() {
        if(Settings.SLOW_MODE) {
            System.out.print("Press enter to continue...");
            new Scanner(System.in).nextLine();
        }
    }
}
