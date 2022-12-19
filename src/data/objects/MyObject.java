package data.objects;

import data.list.ObjectList;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import tools.Validate;

/**
 *
 * @author User
 */
public abstract class MyObject<T> {
    public MyObject() {}
    public static final String SEP = ",";
    
    public abstract String getID_PATTERN();
    public abstract void input();
    public abstract String getID();
    public abstract boolean setAttributes(String[] attributes);
    @Override
    public abstract String toString();
    
    public boolean parseString(String s) {
        if(!Validate.isBlankString(s)) {
            return setAttributes(s.split(SEP));
        }
        return false;
    }
}
