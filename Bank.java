/**
 * Blueprint class for Bank
 * Mahib Rahman
 * 3/27/2024
 */



 public class Bank implements BankInterface {

    // Private attributes
    private String nameOfBank;
    private Account[] accounts;
    private int numOfAccounts;

    /**
     * Constructor for objects of class Bank
     * @param nameOfBank a String, the name of the bank
     */
    public Bank(String nameOfBank) {
        // initialize attributes
        this.nameOfBank = nameOfBank;
        // allocates storage for the array
        this.accounts = new Account[100];
        this.numOfAccounts = 0;
    }

    /**
     * addAccount(): adds an account into the bank
     * @param account - Account object that will be added into the bank
     * Precondition: account must be of type Account
     * Postcondition: Account is added to the bank
     */
    public void addAccount(Account account) {
        if (this.accounts.length > this.numOfAccounts){
            this.accounts[numOfAccounts] = account;
            numOfAccounts += 1;
        }
    }

    /**
     * search(): Searches through the accounts in the bank by id number
     * @param id - String representation of id number
     * @return - Returns the Account associated with the same id number
     * precondition: id must be a string
     * postcondition: Accounts are sorted and the account is found in bank
     */
    public Account search(String id) {
        /* This is linear search (is commented out for my own purpose)
        for (int i = 0; i < this.numOfAccounts; i++) {
            if (this.accounts[i].getID().equals(id)) {
                return this.accounts[i];
            }
        }
        */
        // Sort the accounts in ascending order so the precondition for binary search could get satisfied. 
        sortAccounts();

        // This will use binary search to search for an account with id
        int index = binarySearch(this.accounts, 0, numOfAccounts - 1, id);

        if (index != -1) {
            return this.accounts[index];
        } 
        
        else {
            // This will be returned instead if the account cannot be found
            // after the loop finishes running
            return null;
        }

    }
    
    /**
     * deposit(): Will deposit money into an account that is associataed with the id number given
     * @param id - String representation of id number
     * @param moneyToDeposit - Money object that determines how much money to deposit
     * precondition: id must be a string, and moneyToDeposit must be a Money object
     * postcondition: Deposit transaction is performed on the account in bank
     */
    public void deposit(String id, Money moneyToDeposit) {
        // Searches for accounts with the same id from the parameter
        Account accountUsed = search(id);

        // The account will deposit only if there is an existing account in the bank
        if (accountUsed != null) {
            accountUsed.deposit(moneyToDeposit);
        }

    }

    /**
     * withdraw(): Will withdraw money from an account that is associated with the id number
     * @param id - String representation of id number
     * @param moneyToWithdraw - Money object that determines how much money to withdraw
     * precondition: id must be a string and moneyToWithdraw must be a Money Object
     * postcondition: withdraw method is run on the account in bank
     */
    public void withdraw(String id, Money moneyToWithdraw) {
        // Searches for accounts with the same id from the parameter
        Account accountUsed = search(id);
        accountUsed.withdraw(moneyToWithdraw);
    }

    /*
     * toString(): String representation of the Bank object
     */
    public String toString() {
        String result = "Name of bank: " + this.nameOfBank + 
                         "\nNumber of accounts: " + this.numOfAccounts +
                         "\nAccounts in bank: ";

        for (int i = 0; i < this.numOfAccounts; i++) {
            result += "\n" + this.accounts[i].toString();
        }

        return result;
    }

    /**
     * getNameOfBank(): Getter method to return the name of bank
     * @return this.nameOfBank of type String
     */
    public String getNameOfBank() {
        return this.nameOfBank;
    }

    /**
     * getNumOfAccounts(): Getter method to return the number of accounts in bank
     * @return this.numOfAccounts of type int
     */
    public int getNumOfAccounts() {
        return this.numOfAccounts;
    }

    /**
     * getAccounts(): Getter method to return the information of each account in bank
     * @return result: Type account[]
     */
    public Account[] getAccounts() {
        return this.accounts;
    }
    
    /**
     * sortAccounts(): This method will sort accounts in ascending order based on 
     * the account's id number. 
     * Precondition: There must be a collection of accounts 
     * Postcondition: accounts is sorted in ascending order based on id
     * @return none
     */
    public void sortAccounts() {
        // Calls the quick sort method in SortsClass.Java which will sort the accounts by ID from smallest ID to Largest.
        SortsClass.quickSort(this.accounts, 0, this.numOfAccounts - 1);
    }


    public static int binarySearch(Account[] accounts, int first, int last, String id) {
        // Searches the array items anArray[first] through
        // anArray[last] for value by using a binary search.
        // Precondition: 0 <= first, last <= SIZE-1, where
        // SIZE is the maximum size of the array, and
        // anArray[first] <= anArray[first+1] <= ... <= anArray[last].
        // Postcondition: If value is in the array, the method
        // returns the index of the array item that equals value;
        // otherwise the method returns -1.
        
          int index;
        
          if (first > last) {
        
            index = -1;      // value not in original array
        
          } 
          else {
        
            // Invariant: If value is in anArray, 
            // anArray[first] <= value <= anArray[last]
        
            int mid = (first + last)/2;
        
            if (id.equals(accounts[mid].getID())) {
        
              index = mid;  // value found at anArray[mid]
        
            } 
            else if (id.compareTo(accounts[mid].getID()) == -1) {
        
              index = binarySearch(accounts, first, mid-1, id);   // point X
        
            } 
            else {
        
              index = binarySearch(accounts, mid+1, last, id);    // point Y
        
            }  // end if
          }  // end if
        
          return index;
        }  // end binarySearch
}
