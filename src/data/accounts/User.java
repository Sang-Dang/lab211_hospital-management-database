package data.accounts;

import tools.Validate;

/**
 *
 * @author User
 */
public class User {
    private static final int ADMIN_CODE = 2;
    private static final int DOCTOR_CODE = 1;
    private static final int PATIENT_CODE = 0;
    static final String SEP = ",";
    private static final int numAttributes = 3;
    private static final int numRoles = 3;
    
    private String username;
    private String password;
    private int role;
    
    public User() {
        
    }

    public User(String username, String password, int role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public final void setUsername(String username) {
        this.username = Validate.isBlankString(username) ? null : username;
    }

    public final void setPassword(String password) {
        this.password = Validate.isBlankString(password) ? null : password;
    }

    public final void setRole(int role) {
        this.role = Validate.validateInteger(role, numRoles + 1, -1) ? role : -1;
    }

    public String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public static int getADMIN_CODE() {
        return ADMIN_CODE;
    }

    public static int getDOCTOR_CODE() {
        return DOCTOR_CODE;
    }

    public static int getPATIENT_CODE() {
        return PATIENT_CODE;
    }
    
    public boolean checkPassword(String pass) {
        return this == null ? false : pass.equals(password);
    }
    
    public boolean parseString(String s) {
        if(s == null)
            return false;
        String[] parts = s.split(SEP);
        int index = -1;
        if(parts.length == numAttributes) {
            setUsername(parts[++index]);
            setPassword(parts[++index]);
            setRole(Integer.parseInt(parts[++index]));
            return true;
        }
        return false;
    }
}
