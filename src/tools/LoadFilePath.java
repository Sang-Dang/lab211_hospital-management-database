package tools;

import static gui.HospitalManager.instance;
import java.io.File;
import static tools.Settings.*;

import java.util.List;



/**
 *
 * @author User
 */
public class LoadFilePath {
    
    private static LoadFilePath instance = new LoadFilePath();
    
    private String globalAbsolutePath;
    private String doctorPath;
    private String patientPath;
    private String examPath;
    private String departmentPath;
    private String accountPath;
    private String settingsPath;
    
    private boolean finished = false;
    
    private LoadFilePath() {
        loadPaths();
    }
    
    public static LoadFilePath getInstance() {
        return instance;
    }
    
    private final String[] splitString(String s, Character delimit) {
        StringBuilder sb = new StringBuilder();
        String[] temp = new String[2];
        int index = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == delimit) {
                temp[index] = sb.toString();
                index++;
                sb = new StringBuilder();
                continue;
            }
            sb.append(s.charAt(i));
        }
        temp[index] = sb.toString();

        return temp;
    }
    
    public final boolean loadPaths() {
        List<String> pathsFile = Util.readLinesFromFile(CONFIG_PATH);
        if(pathsFile == null) 
            return false;
        for(int i = 0 ; i < pathsFile.size(); i++) {
            String[] parts = pathsFile.get(i).split("=");
            File temp = new File(parts[1].trim());
            String path = temp.getAbsolutePath();

            if(parts[0].contains("DOCTOR"))
                doctorPath = path;
            else if(parts[0].contains("PATIENT"))
                patientPath = path;
            else if(parts[0].contains("EXAMINATION"))
                examPath = path;
            else if(parts[0].contains("DEPARTMENT"))
                departmentPath = path;
            else if(parts[0].contains("ACCOUNT")) 
                accountPath = path;
            else if(parts[0].contains("SETTINGS")) 
                settingsPath = path;
        }
        finished = true;
        return true;
    }

    public String getDoctorPath() {
        return finished ? doctorPath.toLowerCase() : null;
    }

    public String getPatientPath() {
        return finished ? patientPath.toLowerCase() : null;
    }

    public String getExamPath() {
        return finished ? examPath.toLowerCase() : null;
    }

    public String getDepartmentPath() {
        return finished ? departmentPath.toLowerCase() : null;
    }
    
    public String getAccountPath() {
        return finished ? accountPath.toLowerCase() : null;
    }
    
    public String getSettingsPath() {
        return finished ? settingsPath.toLowerCase() : null;
    }
    
    public static void main(String[] args) {
        System.out.println(LoadFilePath.getInstance().getAccountPath());
        System.out.println(LoadFilePath.getInstance().getSettingsPath());
    }
}
