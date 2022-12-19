package gui;

import gui.menu.Menu;
import tools.Inputter;
import data.accounts.LogIn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tools.Util;

/**
 *
 * @author User
 */
public class ShowGUI {

    private static final String[] OPTIONS = {"Doctors", "Doctors by DEP_ID", "Department", "Patient", "Examinations", "Examinations by DOC_ID", "Examinations by PAT_ID", "Finish"};
    private static final String[] PRINTOPTIONS = {"All Doctors", "    + Doctor by Department ID", "Department", "Patient", "All Examinations", "    + Exam by Doctor ID", "    + Exam by Patient ID", "Finish"};
    private static final String TITLE = "DISPLAY MENU";

    static void showGUI() {
        boolean cont = true;
        List<String> chosen = new ArrayList<>();
        do {
            Menu.printNumberedTable(PRINTOPTIONS, TITLE);
            System.out.print("You have chosen " + chosen.size() + " object(s): ");
            for (String i : chosen) 
                System.out.print(i + ", ");
            System.out.println("");
            
            int option = Inputter.inputNumber("What do you want to show?  ");
            switch (option) {
                // ALL DOCTOR
                case 1: {
                    if (LogIn.getInstance().checkRole(0)) {
                        option--;
                        if (chosen.contains(OPTIONS[option])) {
                            chosen.remove(OPTIONS[option]);
                        } else {
                            chosen.add(OPTIONS[option]);
                        }
                    } else {
                        System.out.println("Insufficient Permissions.");
                    }
                    break;
                }
                // DOCTOR BY DEP ID
                case 2: {
                    if (LogIn.getInstance().checkRole(0)) {
                        option--;
                        if (chosen.contains(OPTIONS[option])) {
                            chosen.remove(OPTIONS[option]);
                        } else {
                            chosen.add(OPTIONS[option]);
                        }
                    } else {
                        System.out.println("Insufficient Permissions.");
                    }
                    break;
                }
                // DEPARTMENT
                case 3: {
                    if (LogIn.getInstance().checkRole(0)) {
                        option--;
                        if (chosen.contains(OPTIONS[option])) {
                            chosen.remove(OPTIONS[option]);
                        } else {
                            chosen.add(OPTIONS[option]);
                        }
                    } else {
                        System.out.println("Insufficient Permissions.");
                    }
                    break;
                }
                // PATIENT
                case 4: {
                    if (LogIn.getInstance().checkRole(1)) {
                        option--;
                        if (chosen.contains(OPTIONS[option])) {
                            chosen.remove(OPTIONS[option]);
                        } else {
                            chosen.add(OPTIONS[option]);
                        }
                    } else {
                        System.out.println("Insufficient Permissions.");
                    }
                    break;
                }
                // ALL EXAM
                case 5: {
                    if (LogIn.getInstance().checkRole(1)) {
                        option--;
                        if (chosen.contains(OPTIONS[option])) {
                            chosen.remove(OPTIONS[option]);
                        } else {
                            chosen.add(OPTIONS[option]);
                        }
                    } else {
                        System.out.println("Insufficient Permissions.");
                    }
                    break;
                }
                // EXAMINATION by DOC ID
                case 6: {
                    if (LogIn.getInstance().checkRole(1)) {
                        option--;
                        if (chosen.contains(OPTIONS[option])) {
                            chosen.remove(OPTIONS[option]);
                        } else {
                            chosen.add(OPTIONS[option]);
                        }
                    } else {
                        System.out.println("Insufficient Permissions.");
                    }
                    break;
                }
                // EXAM BY PAT ID
                case 7: {
                    if (LogIn.getInstance().checkRole(1)) {
                        option--;
                        if (chosen.contains(OPTIONS[option])) {
                            chosen.remove(OPTIONS[option]);
                        } else {
                            chosen.add(OPTIONS[option]);
                        }
                    } else {
                        System.out.println("Insufficient Permissions.");
                    }
                    break;
                }
                // FINISH
                case 8: {
                    if (!chosen.isEmpty()) {
                        System.out.println("");
                        printLists(chosen);
                    }
                    cont = false;
                    break;
                }

                default: {
                    cont = false;
                }
            }
        } while (cont);
    }

    private static void printLists(List<String> options) {
        HospitalManager instance = HospitalManager.getInstance();
        for (String i : options) {
            switch (i) {
                case "Doctors": {
                    System.out.println("---- DOCTORS ----");
                    instance.getDoctorInstance().printAllElements();
                    break;
                }
                case "Patient": {
                    System.out.println("---- PATIENTS ----");
                    instance.getPatientInstance().printAllElements();
                    break;
                }
                case "Department": {
                    System.out.println("---- DEPARTMENTS ----");
                    instance.getDepartmentInstance().printAllElements();
                    break;
                }
                case "Examinations": {
                    System.out.println("---- EXAMINATIONS -----");
                    instance.getExaminationInstance().printAllElements();
                    break;
                }
                case "Doctors by DEP_ID": {
                    System.out.println("---- DOCTORS BY DEP_ID ----");
                    String searchID = Inputter.inputNonBlankString("Enter DepartmentID to filter: ");
                    List result = instance.getDoctorInstance().filter(searchID);
                    for(Object x: result) 
                        System.out.println("+ " + x);
                    System.out.println("There are " + result.size() + " Doctors with DEP_ID: " + searchID);
                    break;
                }
                
                case "Examinations by DOC_ID": {
                    System.out.println("---- EXAMINATIONS BY DOC_ID ----");
                    String searchID = Inputter.inputNonBlankString("Enter DoctorID to filter: ");
                    List result = instance.getExaminationInstance().filter(searchID);
                    for(Object x: result) 
                        System.out.println("+ " + x);
                    System.out.println("There are " + result.size() + " Examinations with DOC_ID: " + searchID);
                    break;
                }
                case "Examinations by PAT_ID": {
                    System.out.println("---- EXAMINATIONS BY PAT_ID ----");
                    String searchID = Inputter.inputNonBlankString("Enter PatientID to filter: ");
                    List result = instance.getExaminationInstance().filter(searchID);
                    for(Object x: result)
                        System.out.println("+ " + x);
                    System.out.println("There are  " + result.size() + " Examinations with PAT_ID: " + searchID);
                    break;
                }
            }
            System.out.println("");
        }
        Util.ETC();
    }

    public static void main(String[] args) {
        LogIn.getInstance().login();
        ShowGUI.showGUI();
    }
}
