package data.list;

import data.objects.*;
import tools.LoadFilePath;
import tools.Util;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 *
 * @author User
 * @param <T>
 */
public abstract class ObjectList <T extends data.objects.MyObject> extends ArrayList<T> {
    protected String filepath;
    public LoadFilePath fileLocations;
    
    protected ObjectList() {
        super();
        fileLocations = LoadFilePath.getInstance();
    }
    
    public abstract String getFilepath();
    public abstract T getInstance();
    public abstract String getObjectName();
    public abstract List<T> filter(String id);
    
    /**
     * BACKBONE METHODS
     * no inputting, no formatting
     */
    
    /**
     * take the path, turn to file. take the file, split into lines. take the lines, turn into doctor where good. take the doctors, add to fucking system. 
     * @return true if all this shit works (p.s., it does)
     */
    public boolean loadFiles() {
        String path = getFilepath();
        List<String> lines = Util.readLinesFromFile(path);
        if(lines == null)
            return false;
        for(String i: lines) {
            if(i.isEmpty()) continue;
            T temp = getInstance();
            if(!temp.parseString(i))
                return false;
            this.add(temp);
        }
        return true;
    }
    
    public boolean saveFiles() {
        String path = getFilepath();
        return Util.saveLinesToFile(path, this.toArray());
    }
    
    
    /**
     * get index of an object in list
     * @param o
     * @return 
     */
    protected int getIndexOf(Object o) {
        for(int i = 0; i < this.size(); i++) {
            if(this.get(i).equals(o))
                return i;
        }
        return -1;
    }
    
    public T searchID(String ID) {
        for(T i: this) 
            if(i.getID().equals(ID)) 
                return i;
        return null;
    }
    
    public void addObject(MyObject o) {
        this.add((T)o);
    }
    
    public void removeObject(MyObject o) {
        this.remove((T)o);
    }
    
    public T addObject() {
        MyObject newInstance = getInstance();
        newInstance.input();
        this.add((T)newInstance);
        return (T)newInstance;
    }
    
    @Override 
    public void clear() {
        this.clear();
    }
    
    public void printAllElements() {
        for(Object i: this) {
            System.out.println("+ " + i.toString() + " ");
        }
        if(this.size() == 1) 
            System.out.println("There is 1 " + this.getObjectName() + " ");
        else
            System.out.print("There are " + this.size() + " " + this.getObjectName() + "s ");
        System.out.println("in the " + this.getObjectName() + " List.");
    }
    
    public void printAllElements(Predicate<String> condition) {
        int count = 0;
        for(MyObject i: this) {
            if(condition.test(i.getID())) {
                System.out.println(i.toString() + " ");
                count++;
            }
        }
        if(count == 1) 
            System.out.println("There is 1 " + this.getObjectName() + " that matches the given condition ");
        else
            System.out.print("There are " + this.size() + " " + this.getObjectName() + "s that match the given condition ");
        System.out.println("in the " + this.getObjectName() + " List.");
        
    }
}
