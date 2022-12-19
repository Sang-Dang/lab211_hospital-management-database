package gui;

import gui.menu.Menu;
import data.accounts.LogIn;
import data.objects.MyObject;
import tools.Inputter;
import data.list.*;
import java.util.HashSet;
import tools.Util;

/**
 *
 * @author User
 */
public class OperateGUI {

    private static final String[] options = {"Doctor", "Department", "Patient", "Examination", "Return to main menu..."};
    private static final String[] operations = {"Add", "Remove", "Update", "Choose a different option", "Return to main menu..."};
    private static final String title = "OPERATIONS MENU";

    private static ObjectList selection;
    private static boolean flag = true;

    static void chooseOption() {
        if(!LogIn.getInstance().checkRole(1)) {
            System.out.println("Insufficient permissions");
            return;
        }
        
        flag = true;
        boolean cont = true;
        int choice;
        do {
            Menu.printNumberedTable(options, title);
            choice = Inputter.inputNumber("Enter the object you want to operate on: ");
            switch (choice) {
                case 1: {
                    selection = HospitalManager.getInstance().getDoctorInstance();
                    break;
                }
                case 2: {
                    selection = HospitalManager.getInstance().getDepartmentInstance();
                    break;
                }
                case 3: {
                    selection = HospitalManager.getInstance().getPatientInstance();
                    break;
                }
                case 4: {
                    selection = HospitalManager.getInstance().getExaminationInstance();
                    break;
                }
                case 5:
                {
                    cont = false;
                    flag = false;
                    break;
                }
                default: {
                    cont = false;
                }
            }
            if(choice <= 4)
                chooseOperation();
        } while (cont && flag);

    }

    static void chooseOperation() {
        String operationsTitle = selection.getObjectName();
        boolean cont = true;
        int choice;
        do {
            Menu.printNumberedTable(operations, "OPERATING ON " + operationsTitle);
            choice = Inputter.inputNumber("Enter your operation: ");
            switch (choice) {
                case 1: {
                    System.out.println("[" + selection.addObject() + "] added.");
                    break;
                }
                case 2: {
                    String ID = Inputter.inputPatternString("Enter " + selection.getObjectName() + " ID you want to remove: ", selection.getInstance().getID_PATTERN());
                    MyObject o = selection.searchID(ID);
                    if(o == null) {
                        System.out.println("ID cannot be found.");
                        break;
                    }
                    selection.remove(selection.searchID(ID));
                    System.out.println(o.toString());
                    break;
                }
                case 3: {
                    String ID = Inputter.inputPatternString("Enter " + selection.getObjectName() + " ID you want to update: ", selection.getInstance().getID_PATTERN());
                    MyObject o = selection.searchID(ID);
                    if(o == null) {
                        System.out.println("ID cannot be found.");
                        break;
                    }
                    System.out.println("Current: [" + o.toString() + "]");
                    selection.removeObject(o);
                    MyObject newO = selection.getInstance();
                    newO.input();
                    selection.addObject(newO);
                    System.out.println("Update: [" + newO.toString() + "]");
                    break;
                }
                case 4: {
                    cont = false;
                    break;
                }
                case 5: {
                    flag = false;
                    cont = false;
                    break;
                }
            }
            
            if(choice <= 3) {
                HospitalManager.setChanged();
                HospitalManager.getInstance().RTSave();
                Util.ETC();
            }
        } while (cont);
    }
}
