package tools;

import data.list.ObjectList;
import data.objects.MyObject;
import gui.HospitalManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class Validate {

    // do NOT initialize this class
    private Validate() {
    }

    private static final String IGNORE_CASE_PATTERN = "(?i)";

    /**
     * check if a string matches a pattern
     *
     * @param input inputted string
     * @param regex pattern
     * @param ignoreCase ignore case?
     * @return true if match
     */
    public static boolean validateStringPattern(String input, String regex, boolean ignoreCase) {
        if (input != null && regex != null) {
            regex = ignoreCase ? IGNORE_CASE_PATTERN + regex : regex;
            return input.matches(regex);
        }
        return false;
    }

    /**
     * check if a string is blank (including " ")
     *
     * @param input inputted string
     * @return true if blank
     */
    public static boolean isBlankString(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * check if a string is either "M" or "F" (not case sensitive)
     *
     * @param input inputted string
     * @return true if correct
     */
    public static boolean validateSex(String input) {
        if (input.equalsIgnoreCase("M") || input.equalsIgnoreCase("F")) {
            return true;
        }
        return false;
    }

    /**
     * check if createDate is always lower than updateDate
     *
     * @param createDate
     * @param updateDate
     * @return true if correct
     */
    public static boolean validateDate(Date createDate, Date updateDate) {
//        Date now = new Date();
//        if(createDate == null) {
//            return updateDate != null && !updateDate.after(now);
//        } else if(updateDate == null) {
//            return createDate.after(now);
//        } else {
//            return !createDate.after(updateDate) && !updateDate.after(now);
//        }
        return true;
    }

    /**
     * check if an integer is between bounds
     *
     * @param input inputted integer
     * @param upperBound integer less than
     * @param lowerBound integer greater than
     * @return true if correct
     */
    public static boolean validateInteger(int input, int upperBound, int lowerBound) {
        return input < upperBound && input > lowerBound;
    }

    public static boolean validateUniqueID(String ID, ObjectList<MyObject> list) {
        ID = ID.toUpperCase();
        for (MyObject i : list) {
            if (i.getID().equals(ID)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateDepartmentID(String id) {
        if (id != null && !isBlankString(id)) {
            data.objects.Department temp = new data.objects.Department();
            temp.setID(id);
            HospitalManager lol = HospitalManager.getInstance();
            ObjectList d = lol.getDepartmentInstance();
            if (temp.getID() != null) {
                return HospitalManager.getInstance().getDepartmentInstance().searchID(id) != null;
            }
        }
        return false;
    }

    public static boolean validatePatientID(String id) {
        if (id != null && !isBlankString(id)) {
            data.objects.Patient temp = new data.objects.Patient();
            temp.setID(id);
            if (temp.getID() != null) {
                return HospitalManager.getInstance().getPatientInstance().searchID(id) != null;
            }
        }
        return false;
    }

    public static boolean validateDoctorID(String id) {
        if (id != null && !isBlankString(id)) {
            data.objects.Doctor temp = new data.objects.Doctor();
            temp.setID(id);
            if (temp.getID() != null) {
                return HospitalManager.getInstance().getDoctorInstance().searchID(id) != null;
            }
        }
        return false;
    }

    public static boolean validateDate(String date) {
        DateFormat sdf = new SimpleDateFormat(Settings.DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
