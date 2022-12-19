package gui;

import data.list.ExaminationList;
import tools.Util;

/**
 *
 * @author User
 */
public class RecordGUI {
    static void showGUI() {
        ExaminationList selection = HospitalManager.getInstance().getExaminationInstance();
        System.out.println("Examination [" + selection.addObject() + "] has been recorded!");
        HospitalManager.getInstance().RTSave();
        Util.ETC();
    }
}
