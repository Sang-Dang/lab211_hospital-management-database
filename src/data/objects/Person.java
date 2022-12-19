package data.objects;

import data.list.ObjectList;
import tools.Validate;
import tools.Inputter;


/**
 *
 * @author User
 */
public abstract class Person extends data.objects.MyObject {
    
    protected static final String ID_PATTERN = "\\d{5}";
    protected static final String SEP = ","; // seperator
    
    private String ID;
    private String name;
    private String address;
    
    protected Person() {}
    
    protected Person(String ID, String name, String address) {
        setID(ID);
        setName(name);
        setAddress(address);
    }
    
    /**
     * used to validate ID pattern from person class and input ID
     * @return ID pattern of child class
     */
    public abstract String getID_PATTERN();
    public abstract String getPRINT_ID();
    
    /**
     * SETTERS
     */
    
    public final void setID(String ID) {
        if(Validate.validateStringPattern(ID, getID_PATTERN(), true)) 
            this.ID = ID.toUpperCase();
    }
    
    public final void setName(String name) {
        if(!Validate.isBlankString(name)) 
            this.name = name;
    }
    
    public final void setAddress(String address) {
        if(!Validate.isBlankString(address)) 
            this.address = address;
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

    public String getAddress() {
        return address;
    }
    
    @Override
    public String toString() {
        return ID + SEP + name + SEP + address;
    }

    public void input(ObjectList instance) {
        this.ID = Inputter.inputUniquePatternString("Enter ID (" + getPRINT_ID() + "): ", getID_PATTERN(), instance);
        this.name = Inputter.inputNonBlankString("Enter name: ");
        this.address = Inputter.inputNonBlankString("Enter address: ");
    }
}
