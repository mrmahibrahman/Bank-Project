
/**
 * Manage input to be read from either keyboard or file.
 * 
 * @author Mahib Rahman 
 * @version 5/12/2024
 */

 import java.util.Scanner;

 public class InputManager
 {
     // Method: readOneAccountFrom
     // Precondition: inputSource is a Scanner object, already set up
     // to read from a text file or standard input source (keyboard).
     // Postcondition: returns an Account with the data read for its attributes.
     // Assumption: Account data will be in the format of: name,id,balance


     public static Account readOneAccountFrom (Scanner inputSource)
     {
         // Read one line of account data into oneLine
         System.out.println ("Reading: name,id,dollars,cents,accountType,overdraftMaximumDollars,overDraftMaximumCents");
         String oneLine = inputSource.nextLine();
         
         // Parse line of account data, separated by commas.
         Scanner lineTokenizer = new Scanner (oneLine);
         lineTokenizer.useDelimiter (",");
         
         // Get account data (i.e. name, id, dollars, and cents, and account type) from oneLine
         String name = lineTokenizer.next ();
         String id = lineTokenizer.next();
         int dollars = lineTokenizer.nextInt();
         int cents = lineTokenizer.nextInt ();
         String accountType = lineTokenizer.next();


         // Create money object representing our balance
         Money balance = new Money(dollars, cents);


         Account oneAccount;
         // Checks if the account type is either a checking or a regular account
         if (accountType.equals("c")) {
            // reads from file the dollars and cents for our overdraft maximum value
            int dollars2 = lineTokenizer.nextInt();
            int cents2 = lineTokenizer.nextInt();
            Money overDraftMaximum = new Money(dollars2, cents2);

            // Creates checking object
            oneAccount = new Checking (name, id, balance, "Checking", overDraftMaximum);
            System.out.println ("Account read: " + oneAccount);
         }

        else {
            // Create and return an Account object with the data read for one  account.
            oneAccount = new RegularAccount (name, id, balance, "Regular");
            System.out.println ("Account read: " + oneAccount);
         }
         
         return oneAccount;
     } // end readOneAccountFrom

     
 } // end InputManager
 