package gui;

import data.accounts.LogIn;
import data.list.DepartmentList;
import data.list.DoctorList;
import data.list.ExaminationList;
import data.list.PatientList;
import gui.menu.*;
import tools.Inputter;
import tools.Settings;
import tools.SettingsManager;
import tools.Util;

/**
 *
 * @author User
 */
public class HospitalManager {

    /**
     * SHOW -> Choose Doctor, Patient, Examination, Department, All (allow
     * multi-select), exit -> Select filter -> Print all elements in list to
     * screen. + implement multi-select code in show class (exit to HM if null)
     * + filter using Reflection Predicates, apply filters in each list + print
     * lists in data.list.*
     *
     * SELECT -> Choose Doctor, Patient, Examination, Department (single-select
     * only), exit -> Choose option add, remove, update, exit-> perform
     * operation. + implement single-select in select class (exit to HM if null)
     * + implement select options in select class + run operation in
     * corresponding list RECORD MEDICAL EXAMINATION -> Input variables ->
     * EASIEST SHIT EVER. + just fucking run record in examination list SAVE ->
     * Toggle real-time saving. + boolean value. toggle on/off real-time updates
     * to all lists (boolean stored in settings) SELF-DESTRUCT -> Check
     * credentials (3 times) -> Clear list -> Update lists + check credentials
     * in accounts package + run credentials to clear method in MyList class +
     * delete all lists
     */
    // All references to this class are made via instance. 
    public static final HospitalManager instance = new HospitalManager();

    private static final String[] MENU_ITEMS = {"Show", "Add/Remove/Update", "Record Medical Examination", "Settings", "Save", "Exit"};
    private static final String TITLE = "HOSPITAL MANAGER";
    
    private static boolean changed;

    private DepartmentList departments;
    private DoctorList doctors;
    private ExaminationList examinations;
    private PatientList patients;

    public static HospitalManager getInstance() {
        return instance;
    }

    public DepartmentList getDepartmentInstance() {
        return this.departments;
    }

    public PatientList getPatientInstance() {
        return patients;
    }

    public DoctorList getDoctorInstance() {
        return doctors;
    }

    public ExaminationList getExaminationInstance() {
        return examinations;
    }
    
    public static void setChanged() {
        changed = true;
    }

    private HospitalManager() {
        departments = new DepartmentList();
        doctors = new DoctorList();
        examinations = new ExaminationList();
        patients = new PatientList();
        changed = false;
    }

    private void showGUI() {
        boolean cont = true;
        do {
            Menu.printNumberedTable(MENU_ITEMS, TITLE);
            System.out.println("Current role: " + LogIn.getInstance().getRole());
            int option = Inputter.inputBoundedIntegerInclusive("Enter option: ", 1, MENU_ITEMS.length);
            switch (option) {
                case 1: {
                    ShowGUI.showGUI();
                    break;
                }
                case 2: {
                    OperateGUI.chooseOption();
                    break;
                }
                case 3: {
                    RecordGUI.showGUI();
                    break;
                }
                case 4: {
                    SettingsGUI.showGUI();
                    break;
                }
                case 5: {
                    if (this.save()) {
                        System.out.println("Save successful!");
                    } else {
                        System.out.println("Save failed!");
                    }
                    changed = false;
                    Util.ETC();
                    break;
                }
                default: {
                    if(!Settings.RT_SAVE && changed) {
                        boolean saveChanges = Inputter.inputYesNo("Do you want to save changes (Y/N): ");
                        if(saveChanges) {
                            if(save())
                                System.out.println("Save successful.");
                            else 
                                System.out.println("Save unsuccessful.");
                        }
                    }
                    System.out.println("Exiting system...");
                    cont = false;
                    break;
                }
            }
        } while (cont);
    }

    private boolean initLists() {
        if (!departments.loadFiles()) {
            return false;
        }
        if (!doctors.loadFiles()) {
            return false;
        }
        if (!patients.loadFiles()) {
            return false;
        }
        if (!examinations.loadFiles()) {
            return false;
        }
        return true;
    }

    public boolean save() {
        if (!departments.saveFiles()) {
            return false;
        }
        if (!doctors.saveFiles()) {
            return false;
        }
        if (!patients.saveFiles()) {
            return false;
        }
        if (!examinations.saveFiles()) {
            return false;
        }
        return true;
    }
    
    public void RTSave() {
        if(Settings.RT_SAVE) {
            if(save()) {
                System.out.println("Auto-save successful!");
            } else {
                System.out.println("Auto-save failed.");
            }
        }
    }

    public static void run() {
        HospitalManager m = HospitalManager.getInstance();
        if (!m.initLists()) {
            System.out.println("List init failed. ");
        }
        SettingsManager.load();
        if(LogIn.getInstance().login())
            m.showGUI();
    }
}
