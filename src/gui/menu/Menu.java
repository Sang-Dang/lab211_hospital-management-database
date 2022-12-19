package gui.menu;

/**
 *
 * @author User
 */
public class Menu {
    public static void printTable(String[] options) {
        System.out.println("");
        System.out.println(new Table().generateTable(options));
    } 
    
    public static void printTable(String[] options, String title) {
        System.out.println("");
        System.out.println(new Table().generateTable(options,title));
    }
    
    public static void printNumberedTable(String[] options) {
        System.out.println("");
        System.out.println(new NumberedTable().generateTable(options));
    }
    
    public static void printNumberedTable(String[] options, String title) {
        System.out.println("");
        System.out.println(new NumberedTable().generateTable(options,title));
    }
    
    public static void main(String[] args) {
        String[] options = {"abc","adsdfds","                                                                                                       ","fdsffdsaffffffffffffffffffffffffffffffffffffffffffffffdsfsd","fdsfdsfdsfdsffdsfdsafdsfdsfdsafadsfdsafsdf","fdsfjdsakfjkdsfj;dsa"};
        printNumberedTable(options,"TITLE");
    }
}
