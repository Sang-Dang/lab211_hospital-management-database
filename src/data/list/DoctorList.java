package data.list;

import data.objects.Doctor;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author User
 */
public class DoctorList extends ObjectList<Doctor>{
    
    public DoctorList() {
        super();
        super.filepath = super.fileLocations.getDoctorPath();
    }

    @Override
    public String getFilepath() {
        return super.filepath;
    }

    @Override
    public Doctor getInstance() {
        return new Doctor();
    }
    
    @Override 
    public String getObjectName() {
        return "Doctor";
    }

    @Override
    public List<Doctor> filter(String ID) {
        return stream().filter(doctor -> doctor.getDepartmentID().equals(ID)).collect(Collectors.toList());
    }
}
