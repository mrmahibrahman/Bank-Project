/**
 * Mahib Rahman
 * 4/8/2024
 * This program is the interface for bank.java and bank2.java
 */

 public interface BankInterface {
   /**
     * addAccount(): adds an account into the bank
     * @param account - Account object that will be added into the bank
     * Precondition: account must be of type Account
     * Postcondition: Account is added to the bank
     */
    void addAccount(Account account);
 
    /**
     * search(): Searches through the accounts in the bank by id number
     * @param id - String representation of id number
     * @return - Returns the Account associated with the same id number
     * precondition: id must be a string
     * postcondition: Accounts are sorted and the account is found in bank
     */
    Account search(String id);

    /**
     * deposit(): Will deposit money into an account that is associataed with the id number given
     * @param id - String representation of id number
     * @param moneyToDeposit - Money object that determines how much money to deposit
     * precondition: id must be a string, and moneyToDeposit must be a Money object
     * postcondition: Deposit transaction is performed on the account in bank
     */
    void deposit(String id, Money moneyToDeposit);

    /**
     * withdraw(): Will withdraw money from an account that is associated with the id number
     * @param id - String representation of id number
     * @param moneyToWithdraw - Money object that determines how much money to withdraw
     * precondition: id must be a string and moneyToWithdraw must be a Money Object
     * postcondition: withdraw method is run on the account in bank
     */
    void withdraw(String id, Money moneyToWithdraw);

    /*
     * toString(): String representation of the Bank object
     */
    String toString();

    /**
     * getNameOfBank(): Getter method to return the name of bank
     * @return this.nameOfBank of type String
     */
    String getNameOfBank();

     /**
     * getNumOfAccounts(): Getter method to return the number of accounts in bank
     * @return this.numOfAccounts of type int
     */
    int getNumOfAccounts();

    /**
     * getAccounts(): Getter method to return the information of each account in bank
     * @return result: Type account[]
     */
    Account[] getAccounts();

    /**
     * sortAccounts(): This method will sort accounts in ascending order based on 
     * the account's id number. 
     * Precondition: There must be a collection of accounts 
     * @return none
     */
    void sortAccounts();

}
