package data.objects;

import tools.Validate;
import tools.Util;
import data.objects.Department;
import data.list.DepartmentList;
import data.list.ObjectList;
import gui.HospitalManager;
import java.text.ParseException;
import java.util.Date;
import tools.Inputter;


/**
 * SAVE FORMAT: ID,NAME,ADDRESS,SEX,DEPARTMENTID,CREATEDATE,LASTUPDATEDATE
 * @author User
 */
public class Doctor extends Person {
    
    private static final String ID_PREFIX = "DOC";
    private static final String ID_PATTERN = ID_PREFIX + Person.ID_PATTERN;
    private static final int NUM_ATTRIBUTES = 7;
    private static final String PRINT_ID = "DOCxxxxx";
    
    private String sex;
    private String departmentID;
    private Date createDate;
    private Date lastUpdateDate;
    
        
    public Doctor() {
        super();
    }
    
    public Doctor(String ID, String name, String address, String sex, String depID, Date createDate, Date lastUpdateDate) {
        super(ID,name,address);
        setSex(sex);
        setDepartmentID(depID);
        setCreateDate(createDate);
        setLastUpdateDate(lastUpdateDate);
    }
    
    /**
     * SETTERS
     */
    
    // TODO: validate department ID by checking if department ID exists 
    public final void setDepartmentID(String depID) {
        this.departmentID = Validate.validateDepartmentID(depID) ? depID : null;
    }
    
    public final void setSex(String sex) {
        this.sex = Validate.validateSex(sex) ? sex : null;
    }
    
    public final void setCreateDate(Date date) {
        if(Validate.validateDate(date, this.lastUpdateDate))
            this.createDate = date;
    }
    
    public final boolean setCreateDate(String date) {
        try {
            if(Validate.validateDate(date))
                this.createDate = Util.toDate(date);
            return true;
        } catch(ParseException e) {
            return false;
        }
    }
    
    public final boolean setLastUpdateDate(String date) {
        try {
            if(Validate.validateDate(date))
                this.lastUpdateDate = Util.toDate(date);
            return true;
        } catch(ParseException e) {
            return false;
        }
    }
    
    public final void setLastUpdateDate(Date date) {
        if(Validate.validateDate(this.createDate,date))
            this.lastUpdateDate = date;
    }
    
    /**
     * GETTERS
     */
    
    public String getSex() {
        return sex;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastUpdateDate() {    
        return lastUpdateDate;
    }

    @Override
    public String getID_PATTERN() {
        return Doctor.ID_PATTERN;
    }
    
    public static ObjectList getListInstance() {
        return HospitalManager.getInstance().getDoctorInstance();
    }
    
    @Override
    public String getPRINT_ID() {
        return PRINT_ID;
    }
    /**
     * OTHER
     */
    
    /**
     * print format and file output string
     * @return string
     */
    @Override 
    public String toString() {
        return super.toString() + SEP + sex + SEP + departmentID + SEP + Util.toDate(createDate) + SEP + Util.toDate(lastUpdateDate);
    }
    
    @Override
    public void input() {
        System.out.println("---- Input Doctor ----");
        super.input(getListInstance());
        this.sex = Inputter.inputSex("Enter sex: ");
        this.departmentID = Inputter.inputDepartmentID("Enter Department ID: ");
        this.createDate = Inputter.inputDate("Enter create date: ");
        this.lastUpdateDate = Inputter.inputDate("Enter last update date: ");
        System.out.println("\n----------------------");
    }
    
    @Override
    public boolean setAttributes(String[] attributes) {
        if(attributes == null || attributes.length != NUM_ATTRIBUTES)
            return false;
        int index = -1;
        super.setID(attributes[++index]);
        super.setName(attributes[++index]);
        super.setAddress(attributes[++index]);
        this.setSex(attributes[++index]);
        this.setDepartmentID(attributes[++index]);
        this.setCreateDate(attributes[++index]);
        this.setLastUpdateDate(attributes[++index]);
        return true;
    }
}
