package data.objects;

import data.list.ObjectList;
import gui.HospitalManager;
import java.text.ParseException;
import tools.Validate;
import java.util.Date;
import tools.Inputter;
import tools.Util;

/**
 * SAVE FORMAT: ID,NAME,CREATEDATE,LASTUPDATEDATE
 * @author User
 */
public class Department extends data.objects.MyObject {
    private static final String ID_PREFIX = "DEP";
    private static final String ID_PATTERN = ID_PREFIX + "\\d{5}";
    private static final int NUM_ATTRIBUTES = 4;
    
    private String ID;
    private String name;
    private Date createDate;
    private Date lastUpdateDate;
    
    public Department() {}
    
    public Department(String ID, String name, Date createDate, Date lastUpdateDate) {
        setID(ID);
        setName(name);
        setCreateDate(createDate);
        setLastUpdateDate(lastUpdateDate);
    }
    
    /**
     * SETTERS
     */
    
    public final void setID(String ID) {
        if(Validate.validateStringPattern(ID, ID_PATTERN, true)) 
            this.ID = ID.toUpperCase();
    }
    
    public final void setName(String name) {
        if(!Validate.isBlankString(name))
            this.name = name;
    }
    
    public final void setCreateDate(Date createDate) {
        if(Validate.validateDate(createDate, this.lastUpdateDate))
            this.createDate = createDate;
    }
    
    public final void setLastUpdateDate(Date lastUpdateDate) {
        if(Validate.validateDate(this.createDate, lastUpdateDate))
            this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * GETTERS
     */
    
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    @Override
    public String getID_PATTERN() {
        return ID_PATTERN;
    }
    
    public static ObjectList getListInstance() {
        return HospitalManager.getInstance().getDepartmentInstance();
    }
    
    @Override
    public void input() {
        System.out.println("---- Input Department ----");
        this.ID = Inputter.inputUniquePatternString("Enter ID (DEPxxxxx): ", ID_PATTERN, getListInstance());
        this.name = Inputter.inputNonBlankString("Enter name: ");
        this.createDate = Inputter.inputDate("Enter create date: ");
        this.lastUpdateDate = Inputter.inputDate("Enter last update date: ");
        System.out.println("--------------------------");
    }
    
    @Override
    public boolean setAttributes(String[] attributes) {
        if(attributes == null || attributes.length != NUM_ATTRIBUTES) 
            return false;
        int index = -1;
        this.setID(attributes[++index]);
        this.setName(attributes[++index]);
        try {
            this.setCreateDate(Util.toDate(attributes[++index]));
            this.setLastUpdateDate(Util.toDate(attributes[++index]));
        } catch(ParseException e) {
        }
        return true;
    }
    
    @Override
    public String toString() {
        return ID + SEP + name + SEP + Util.toDate(createDate) + SEP + Util.toDate(lastUpdateDate);
    }
}
