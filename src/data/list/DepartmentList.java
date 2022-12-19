package data.list;

import data.objects.Department;
import java.util.List;

/**
 *
 * @author User
 */
public class DepartmentList extends ObjectList<Department>{
    
    public DepartmentList() {
        super();
        super.filepath = fileLocations.getDepartmentPath();
    }
    
    @Override
    public String getFilepath() {
        return super.filepath;
    }
    
    @Override
    public Department getInstance() {
        return new Department();
    }    
    
    @Override
    public String getObjectName() {
        return "Department";
    }

    @Override
    public List<Department> filter(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
