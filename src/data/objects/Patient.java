package data.objects;

import data.list.ObjectList;
import gui.HospitalManager;
import tools.Inputter;
import tools.Validate;



/**
 * SAVE FORMAT: ID,NAME,ADDRESS,AGE
 * @author User
 */
public class Patient extends Person {
    
    private static final String ID_PREFIX = "PAT";
    private static final String ID_PATTERN = ID_PREFIX + Person.ID_PATTERN;
    private static final int AGE_UPPER_BOUND = 130;
    private static final int AGE_LOWER_BOUND = 0;
    private static final int NUM_ATTRIBUTES = 4;
    private static final String PRINT_ID = "PATxxxxx";
    
    private int age;
    
    public Patient() {
        super();
    }
    
    public Patient(String ID, String name, String address, int age) {
        super(ID,name,address);
        setAge(age);
    }
    
    public final void setAge(int age) {
        if(Validate.validateInteger(age, AGE_UPPER_BOUND, AGE_LOWER_BOUND))
            this.age = age;
    }
    
    public final void setAge(String ageString) {
        int age = Integer.parseInt(ageString.trim());
        if(Validate.validateInteger(age, AGE_UPPER_BOUND, AGE_LOWER_BOUND))
            this.age = age;
    }
    
    @Override
    public String getID_PATTERN() {
        return Patient.ID_PATTERN;
    }
    
    @Override
    public String getPRINT_ID() {
        return PRINT_ID;
    }
    
    public static ObjectList getListInstance() {
        return HospitalManager.getInstance().getPatientInstance();
    }
    
    @Override 
    public String toString() {
        return super.toString() + SEP + age;
    }
    
    @Override
    public void input() {
        System.out.println("---- Input Patient ----");
        super.input(getListInstance());
        this.age = Inputter.inputBoundedIntegerExclusive("Enter age: ", AGE_LOWER_BOUND, AGE_UPPER_BOUND);
        System.out.println("-----------------------");
    }
    
    @Override
    public boolean setAttributes(String[] attributes) {
        if(attributes == null || attributes.length != NUM_ATTRIBUTES)
            return false;
        int index = -1;
        super.setID(attributes[++index]);
        super.setName(attributes[++index]);
        super.setAddress(attributes[++index]);
        this.setAge(attributes[++index]);
        return true;
    }
}
