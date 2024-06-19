import java.util.ArrayList;
import java.util.Collections;

/**
 * Blueprint class for Bank2
 * This version of bank uses an arrayList to store accounts rather than just an array
 * Mahib Rahman
 * 4/27/2024
 */

 public class Bank2 implements BankInterface {

    // Private attributes
    private String nameOfBank;
    private ArrayList<Account> accounts;

    /**
     * Constructor for objects of class Bank2
     * @param nameOfBank a String, the name of the bank
     */
    public Bank2(String nameOfBank) {
        // initialize attributes
        this.nameOfBank = nameOfBank;
    
        // Creates an array list to store the accounts
        this.accounts = new ArrayList<Account>();
    }

    /**
     * addAccount(): adds an account into the bank
     * @param account - Account object that will be added into the bank
     * Precondition: account must be of type Account
     * Postcondition: Account is added to the bank array list
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * search(): Searches through the accounts in the bank by id number
     * @param id - String representation of id number
     * @return - Returns the Account associated with the same id number
     * precondition: id must be a string
     * postcondition: Accounts are sorted and the account is found in bank
     */
    public Account search(String id) {
        int size = this.accounts.size();
        for (int i = 0; i < size; i++) {
            if (this.accounts.get(i).getID().equals(id)) {
                return this.accounts.get(i);
            }
        }

        // This will be returned instead if the account cannot be found
        // after the loop finishes running
        return null;
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
                         "\nNumber of accounts: " + this.accounts.size() +
                         "\nAccounts in bank: ";

        for (Account account : this.accounts) {
            result += "\n" + account.toString();
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
     * @return this.accounts.size() of type int
     */
    public int getNumOfAccounts() {
        return this.accounts.size();
    }

    /**
     * getAccounts(): Getter method to return the information of each account in bank
     * @return result: Type account[]
     */
    public Account[] getAccounts() {
        return this.accounts.toArray(new Account[0]);
    }
    
    /**
     * sortAccounts(): This method will sort accounts in ascending order based on 
     * the account's id number. 
     * Precondition: There must be a collection of accounts 
     * @return none
     */
    public void sortAccounts() {
        Collections.sort(this.accounts);
    }
}
