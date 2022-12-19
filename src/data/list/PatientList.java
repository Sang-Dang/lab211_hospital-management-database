package data.list;

import data.objects.Patient;
import java.util.List;

/**
 *
 * @author User
 */
public class PatientList extends ObjectList<Patient> {
    public PatientList() {
        super();
        super.filepath = fileLocations.getPatientPath();
    }
    
    @Override
    public String getFilepath() {
        return super.filepath;
    }
    
    @Override
    public Patient getInstance() {
        return new Patient();
    }
    
    @Override
    public String getObjectName() {
        return "Patient";
    }

    @Override
    public List<Patient> filter(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
