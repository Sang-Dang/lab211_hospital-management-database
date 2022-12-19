package tools;

import data.list.ObjectList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static tools.Settings.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Scanner;


/**
 * THIS CLASS IS USED FOR ALL INPUTTING FUNCTIONS. USES VALIDATE CLASS. 
 * @author User
 */
public class Inputter {
    
    // do NOT initialize this class
    private Inputter() {}
    
    private static final Scanner IN = new Scanner(System.in);
    
    /**
     * general input format used for inputting all values in this file
     * @param <T> return type of input
     * @param msg message to be displayed before input
     * @param condition conditions that the input must adhere to (use Lambda expressions)
     * @param scanner Lambda expression for input and processing of data (typically using Scanner)
     * @param errormsg message to be displayed when errors occur
     * @return inputted value
     */
    private static <T> T input(String msg, Predicate<T> condition, Function<T,T> scanner, String errormsg) {
        boolean cont;
        T returnValue = null;
        do {
            cont = true;
            try {
                System.out.print(msg);
                returnValue = scanner.apply(returnValue);
                if(!condition.test(returnValue))
                    throw new Exception(errormsg);
            } catch(Exception e) {
                System.out.println(e);
                cont = false;
            }
        } while(!cont);
        
        return returnValue;
    }
    
    /**
     * get a string that is not blank (and contains at least one character)
     * @param msg message to be displayed before input
     * @return inputted value
     */
    public static String inputNonBlankString(String msg) {
        return input(
                msg,
                p -> !Validate.isBlankString(p),
                f -> IN.nextLine().trim(),
                "Input blank."
        );
    }
    
    public static int inputBoundedIntegerExclusive(String msg, int min, int max) {
        return input(
                msg,
                p -> Validate.validateInteger(p, max, min),
                f -> Integer.parseInt(IN.nextLine().trim()),
                "Input out of bounds."
        );
    }
    
    public static int inputBoundedIntegerInclusive(String msg, int min, int max) {
        return input(
                msg,
                p -> p <= max && p >= min,
                f -> Integer.parseInt(IN.nextLine().trim()),
                "Input out of bounds."
        );
        
    }
    
    public static String inputSex(String msg) {
        return input(
                msg,
                p -> Validate.validateSex(p),
                f -> IN.nextLine().trim(),
                "Input must be M or F."
        );
    }
    
    public static String inputPatternString(String msg, String regex) {
        return input(
                msg,
                p -> Validate.validateStringPattern(p, regex, true),
                p -> IN.nextLine().trim().toUpperCase(),
                "Input does not match pattern."
        );
    }
    
    public static String inputUniquePatternString(String msg, String regex, ObjectList list) {
        return input(
                msg,
                p -> Validate.validateStringPattern(p, regex, true) && Validate.validateUniqueID(p, list),
                f -> IN.nextLine().trim(),
                "Input does not match pattern or Input already exists"
        );
    }
    
    public static String inputDepartmentID(String msg) {
        return input(
                msg,
                p -> Validate.validateDepartmentID(p),
                f -> IN.nextLine().trim().toUpperCase(),
                "Input does not match pattern or Input does not exist."
        );
    }
    
    public static String inputPatientID(String msg) {
        return input(
                msg,
                p -> Validate.validatePatientID(p),
                f -> IN.nextLine().trim().toUpperCase(),
                "Input does not match pattern or Input does not exist."
        );
    }
    
    public static String inputDoctorID(String msg) {
        return input(
                msg,
                p -> Validate.validateDoctorID(p),
                f -> IN.nextLine().trim().toUpperCase(),
                "Input does not match pattern or Input does not exist."
        );
    }
    
    public static Date inputDate(String msg) {
        boolean cont;
        Date returnValue = null;
        do {
            cont = false;
            System.out.print(msg + "(" + DATE_FORMAT + ") ");
            String date = IN.nextLine().trim();
            try {
                returnValue = Util.toDate(date);
            } catch(Exception e) {
                System.out.println(e);
                cont = true;
            }
        } while(cont);
        return returnValue;
    }
    
    public static int inputOption(String msg, int options) {
        return inputBoundedIntegerExclusive(msg,0,options);
    }
    
    public static int inputNumber(String msg) {
        return input(
                msg,
                p -> true,
                f -> Integer.parseInt(IN.nextLine().trim()),
                "Input error."
        );
    }
    
    public static boolean inputYesNo(String msg) {        
        boolean cont;
        String temp;
        do {
            cont = true;
            System.out.print(msg);
            temp = IN.nextLine().trim();
            if((!temp.equalsIgnoreCase("Y"))&&(!temp.equalsIgnoreCase("N"))) {
                System.out.println("Input must be Y or N.");
                cont = false;
            }
        } while(!cont);
        return temp.equalsIgnoreCase("Y");
    }
    
    public static void main(String[] args) {
        System.out.println(inputYesNo("Input: "));
    }
}
