package data.accounts;

import tools.LoadFilePath;
import tools.Inputter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import tools.Settings;

/**
 * this class provides functionality for log in including the login method
 * @author User
 */
public class LogIn {
     private static final LogIn instance = new LogIn();
     private final String filePath;
     
     private User currentUser;
     
     private LogIn() {
         super();
         filePath = LoadFilePath.getInstance().getAccountPath();
     }
     
     public static LogIn getInstance() {
         return instance;
     }
     
     public String getRole() {
         switch(currentUser.getRole()) {
             case 0:
                 return "Patient";
             case 1:
                 return "Doctor";
             case 2:
                 return "Admin";
         }
         return null;
     }
     
     private User findUser(String username) {
         try {
             BufferedReader input = new BufferedReader(new FileReader(filePath));
             String line = "";
             while((line = input.readLine()) != null) {
                 String[] parts = line.split(User.SEP);
                 if(parts[0].equals(username)) {
                     return new User(parts[0].trim(),parts[1],Integer.parseInt(parts[2].trim()));
                 }
             }
             input.close();
         } catch(IOException e) {
         }
         return null;
     }
     
     public boolean login() {
         boolean cont;
         int tries = Settings.PASSWORD_TRIES;
         System.out.println("FOR DEBUG ADMIN ACCOUNT - Username: potatoboy2000, Password: 12345 ");
         System.out.println("For more accounts, check src\\datafiles\\accounts.dat");
         do {
            cont = false;
            if(tries == 0) {
                System.out.println("You have been locked out. ");
                return false;
            }
            System.out.println("---- Login ----");
            
            String inUsername = Inputter.inputNonBlankString("Username: ");
            String inPassword = Inputter.inputNonBlankString("Password (you have " + tries + (tries == 1 ? " try" : " tries") + " left): ");
            User found = findUser(inUsername);
            if(found == null || !found.checkPassword(inPassword)) {
                cont = true;
                System.out.println("Incorrect username/password. Try again.");
            } else {
                System.out.println("Login successful");
                currentUser = found;
            }
            tries--;
            System.out.println("");
         } while(cont);
         return true;
     }
     
     public boolean checkRole(int role) {
         return currentUser.getRole() >= role;
     }
}
