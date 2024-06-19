import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// Driver class for Bank project

public class ATM
{

public static void main (String[] args)
   {
      String id;
      
      try {
         //IOHandlerInterface ioh = new IOHandlerStandard(); // This will use standard input output from terminal
         IOHandlerInterface ioh = new IOHandlerDialog(); // This will use dialog boxes rather than the terminal

         // Read data from a file into a Bank.
         // Each line of the file has info for one account.  
         BankInterface myBank = readFromFile("input.txt");

         // Print all the data stored in the bank.
         System.out.println (myBank);

         boolean isValid;
         
         // Checks if the id entered is valid and is in the bank. If the id is not found in the bank,
         // it will ask the user to enter the correct id again. 
         do {
            // Gets the id from the user
            id = readUserID(ioh);

            // if the user clicks the cancel button or the x button
            // Program will exit.
            if (id.equals("")) {
               ioh.put("User exited");
               return;
            }
            
            ioh.put("You entered " + id);
            isValid = isValid(myBank,id);
            
            // Checks if the account is in the bank or not. 
            if (isValid == false) {
               ioh.put("Result of the Validation: Account not found in bank, please enter the ID correctly.");
            }
      
            else{
               // If account is found in the bank, it will tell you the account type.
               ioh.put("Result of the Validation: Account found!");
               Account account = myBank.search(id);

               ioh.put("Account type " + account.getAccountType());

            }

         } while(isValid == false);
   
         
         boolean noChoice = false;

         // Ask the user to enter a transaction (check balance/deposit/withdraw)
         // Program will terminate if the user hits exit, "x" button, or cancel
         while (noChoice == false) {
            String[] choices = {"deposit", "withdraw", "balance", "exit"};
            // Get transaction from user
            String transaction = ioh.getFromList("Transactions", "Choose a transaction.", choices);

            System.out.println("Transaction selected: " + transaction);

            // Exits the program if the user hits the cancel or x button
            if (transaction == null) {
               ioh.put("Exiting");
               noChoice = true;
            }
            
            // If the user selects balance, validate and execute the balance function
            else if (transaction.equals(choices[2])) {
               validateAndExecuteCheckBalance(myBank, id, ioh);
            }

            // If the user selects withdraw, validate and perform the withdraw operation. 
            else if (transaction.equals(choices[1])) {
               validateAndExecuteWithdraw(myBank, id, ioh);
            }
            
            // If the user selects deposit, validate and perform the deposit operation
            else if (transaction.equals(choices[0])) {
               validateAndExecuteDeposit(myBank, id, ioh);
            }
            
            // If the user selects exit, stop the program
            else if (transaction.equals(choices[3])) {
               ioh.put("Exiting");
               noChoice = true;
            }
            // Keep the program running if the user enters an invalid transaction, but doesn't want to exit the program. 
            else {
               ioh.put("Please enter a valid transaction");
               noChoice = false;
            }
         }
         
         // When the user is done doing transactions, the updated version of the account is updated in 
         // a new output file. 
         writeUpdatedAccountInformation("out_accounts.txt", myBank);

      } // end try	
      catch (IOException ioe) {
         System.out.println("IOException in main: " + ioe.getMessage()); 
 	 ioe.printStackTrace();
      } // end catch
      catch (Exception e) {
         System.out.println("Exception in main: " + e.getMessage());
	 e.printStackTrace();
      } // end catch

   } // end main

   /**
    * readFromFile: Reads the contents from a file  
    * @param fileName - String representing the file name 
    * precondition: fileName must be already existing
    * @return myBank: An updated version of bank object myBank after we add accounts to myBank after we read from the file input.txt.
    * postcondition: myBank is updated with the contents from the input file
    */
   public static BankInterface readFromFile (String fileName) throws IOException
   {
	   // Creata a bank.
	   BankInterface myBank = new Bank("TD Bank");

     	 // Open a file for reading.
      Scanner inputSource = new Scanner (new File(fileName));
       
      // while there are more tokens to read from the input source...
	   while (inputSource.hasNext()) {

		   // Read one line of input from the file into an Account object
        	Account acct = InputManager.readOneAccountFrom (inputSource);
		
		   // Store the account info in the bank.
            myBank.addAccount(acct);
	 } // end while


         return myBank;

    } // end readFromFile

    /**
     * readUserID: Gets the user id from the user
     * @param IOHandlerInterface ioh- Either IOHandlerDialog or IOHandlerStandard 
     * Precondition: ioh must be either IOHandlerDialog or IOHandlerStandard
     * @return/Postcondition String id is read from user.
     */
    public static String readUserID(IOHandlerInterface ioh) {
      return ioh.get("Please enter your ID:");
    }

    /**
     * isValid: determines if the user's id that is entered matches with one of the ids in the bank
     * @param bank - type BankInterface that represents a bank of accounts 
     * @param id - the id that the user enters
     * precondition: bank must be from type BankInterface and id must be a strng
     * @return/Postcondition True if id entered matches with one of the ids in the bank or false if id does not match with any accounts
     */
    public static boolean isValid(BankInterface bank, String id) {
      for (int i = 0; i < bank.getNumOfAccounts(); i++) {
         if (bank.getAccounts()[i].getID().equals(id)){
            return true;
         }
      }

      return false;
    }



      /**
     * validateAndExecuteDeposit(): Validates and performs the get.balance operation from account class
     * @param bank- BankInterface object 
     * @param id- String representation of id
     * @param ioh- IOHandlerInterface object that tells us what type of input/output we use
     * preconditions: Must be a valid BankInterface object, id must be a string, and ioh must be of IOHandlerInterface
     * Postcondition: THe balance operation is performed on the account 
     */
    public static void validateAndExecuteCheckBalance(BankInterface bank, String id, IOHandlerInterface ioh){
      // This will validate if the bank is found
      if (bank == null) {
         ioh.put("No Bank Found.");
      }

      // This will validate if the account is found inside the bank to check the balance.
      Account account = bank.search(id);
      if (account == null) {
         ioh.put("NO Account is found");
      }

      // This will get the balance and output it to the dialog screen
      ioh.put("Account " + id + " balance: " + account.getBalance());
    }
    
      /**
     * validateAndExecuteDeposit(): Validates and performs the deposit operation from account class
     * @param bank- BankInterface object 
     * @param id- String representation of id
     * @param ioh- IOHandlerInterface object that tells us what type of input/output we use
     * preconditions: Must be a valid BankInterface object, id must be a string, and ioh must be of IOHandlerInterface
     * Postcondition: THe deposit operation is performed on the account 
     */
    public static void validateAndExecuteDeposit(BankInterface bank, String id, IOHandlerInterface ioh) {
            // This will validate if the bank is found
            if (bank == null) {
               ioh.put("No Bank Found.");
            }
      
            // This will validate if the account is found inside the bank to check the balance.
            Account account = bank.search(id);
            if (account == null) {
               ioh.put("NO Account is found");
            }
            
         try {
            ioh.put("Enter amount to deposit: ");
            String dollarsString = ioh.get("Enter dollars: ");
            String centsString = ioh.get("Enter cents: ");

            // Convert the string version of dollars and cents into integer version
            int dollars = Integer.parseInt(dollarsString);
            int cents = Integer.parseInt(centsString);

            // This will deposit and output it to the dialog screen
            ioh.put("Account " + id + " balance: " + account.deposit(new Money(dollars,cents)));
         }

         catch (Exception e) {
            ioh.put("Please enter numbers only.");
         }
    }

    /**
     * validateAndExecuteWithdraw(): Validates and performs the withdraw operation from account class
     * @param bank- BankInterface object 
     * @param id- String representation of id
     * @param ioh- IOHandlerInterface object that tells us what type of input/output we use
     * preconditions: Must be a valid BankInterface object, id must be a string, and ioh must be of IOHandlerInterface
     * Postcondition: THe withdraw operation is performed on the account 
     */
    public static void validateAndExecuteWithdraw(BankInterface bank, String id, IOHandlerInterface ioh) {
      // This will validate if the bank is found
      if (bank == null) {
         ioh.put("No Bank Found.");
      }

      // This will validate if the account is found inside the bank to check the balance.
      Account account = bank.search(id);
      if (account == null) {
         ioh.put("NO Account is found");
      }


      try{
         ioh.put("Enter amount to withdraw: ");
         String dollarsString = ioh.get("Enter dollars: ");
         String centsString = ioh.get("Enter cents: ");

         // Converts the string version of dollars and cents into integer versions
         int dollars = Integer.parseInt(dollarsString);
         int cents = Integer.parseInt(centsString);

         
         // This will execute the withdraw method in accounts
         ioh.put("Account " + id + " balance: " + account.withdraw(new Money(dollars,cents)));
      } 

      catch (InsufficientFundsException e) {
         ioh.put("Withdraw amount exceeds overdraft maximum!");
      }

      catch (Exception e) {
         ioh.put("Please enter numbers only.");
      }

   
    }

    public static void writeUpdatedAccountInformation(String outputFileName, BankInterface bank) {
      // ---------------------------------------------------------
      // Writes the updated account info to the outputfile 
      // Precondition: bank parameter must be a BankInterface object
      // Postcondition: Updated account information is stored in a new file in outputFileName
      // ---------------------------------------------------------
      PrintWriter ofStream = null; // declare an output file stream
      
      try {
         // Initialize the output file stream, based on the name of the output file, stored in copyFileName ofStream = new PrintWriter(new FileWriter(copyFileName));    
         ofStream = new PrintWriter(new FileWriter(outputFileName));
         
         // Creates an ArrayList to store the string contents of the accounts
         ArrayList<String> accountsStringInfo = new ArrayList<String>();
          
         // add all the accounts to the array list
         for (int i = 0; i < bank.getNumOfAccounts(); i++) {
            Account account = bank.getAccounts()[i];

            String accountInfo = account.getName() + ", " + account.getID() + ", " + account.getBalance();

            accountsStringInfo.add(accountInfo);
         }

         // Sort the accounts in bank in ascending order by id
         bank.sortAccounts();

         // Writes the contents of each account to the output file
         for (int i = 0; i < accountsStringInfo.size(); i++) {
            ofStream.println(accountsStringInfo.get(i));
         }

         // Alerts us that the account info was written to the output file
         System.out.println("Account info is written to " + outputFileName);

      }  
      catch (IOException e) {
         System.out.println("Error writing accounts to file");
      }

      finally{
         if (ofStream != null) {
            ofStream.close();
         }
      }
    }


} // end ATM