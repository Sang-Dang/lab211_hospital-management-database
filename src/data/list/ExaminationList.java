package data.list;

import data.objects.Examination;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author User
 */
public class ExaminationList extends ObjectList<Examination>{
    
    public ExaminationList() {
        super();
        super.filepath = fileLocations.getExamPath();
    }
    
    @Override
    public String getFilepath() {
        return super.filepath;
    }
    
    @Override
    public Examination getInstance() {
        return new Examination();
    }
    
    @Override
    public String getObjectName() {
        return "Examination";
    }

    @Override
    public List<Examination> filter(String id) {
        return stream().filter(exam -> exam.getDocID().equals(id) || exam.getPatID().equals(id)).collect(Collectors.toList());
    }
}
