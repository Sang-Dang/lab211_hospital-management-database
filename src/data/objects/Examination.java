package data.objects;

import data.list.ObjectList;
import gui.HospitalManager;
import java.text.ParseException;
import java.util.Date;
import tools.Inputter;
import tools.Validate;
import tools.Util;

/**
 * SAVE FORMAT: ID,DOCID,PATID,DATE,RESULT
 * @author User
 */
public class Examination extends data.objects.MyObject {
    private static final String ID_PREFIX = "EX";
    private static final String ID_PATTERN = ID_PREFIX + "\\d{5}";
    private static final int NUM_ATTRIBUTES = 5;
    
    private String ID;
    private String docID;
    private String patID;
    private String result;
    private Date date;
    
    public Examination() {}

    public Examination(String examID, String docID, String patID, Date date, String result) {
        this.ID = examID;
        this.docID = docID;
        this.patID = patID;
        this.result = result;
        this.date = date;
    }
    
    /**
     * SETTERS
     */

    public void setID(String examID) {
        if(Validate.validateStringPattern(examID, ID_PATTERN, true))
            this.ID = examID;
    }

    // TODO: VALIDATE DOCTOR ID BASED ON LIST
    public void setDocID(String docID) {
        this.docID = Validate.validateDoctorID(docID) ? docID : null;
    }

    public void setPatID(String patID) {
        this.patID = Validate.validatePatientID(patID) ? patID : null;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * GETTERS
     */

    @Override
    public String getID() {
        return ID;
    }

    public String getDocID() {
        return docID;
    }

    public String getPatID() {
        return patID;
    }

    public String getResult() {
        return result;
    }

    public Date getDate() {    
        return date;
    }
    
    public static ObjectList getListInstance() {
        return HospitalManager.getInstance().getExaminationInstance();
    }
    
    @Override
    public boolean setAttributes(String[] attributes) {
        if(attributes == null || attributes.length != NUM_ATTRIBUTES) 
            return false;
        int index = -1;
        this.setID(attributes[++index]);
        this.setDocID(attributes[++index]);
        this.setPatID(attributes[++index]);
        try {
            this.setDate(Util.toDate(attributes[++index]));
        } catch(ParseException e) {}
        this.setResult(attributes[++index]);
        
        return true;
    }

    @Override
    public void input() {
        System.out.println("---- Record Examination ----");
        this.ID = Inputter.inputUniquePatternString("Enter ID (EXxxxxx): ", ID_PATTERN, getListInstance());
        this.docID = Inputter.inputDoctorID("Enter Doctor ID: ");
        this.patID = Inputter.inputPatientID("Enter Patient ID: ");
        this.date = Inputter.inputDate("Enter date: ");
        this.result = Inputter.inputNonBlankString("Enter result: ");
        System.out.println("----------------------------");
    }

    @Override
    public String getID_PATTERN() {
        return ID_PATTERN;
    }
    
    @Override
    public String toString() {
        return ID + SEP + docID + SEP + patID + SEP + Util.toDate(date) + SEP + result;
    }
}
