/**
 * Mahib Rahman
 * Checking.java is a subclass of the superclass Account.java
 */

 public class Checking extends Account {
    // Private attributes
    private Money overdraftMaximum; 

    // Initializes attributes with constructor
    /**
     * 
     * @param yourName - String representing the users name
     * @param yourId - String represnting the users id
     * @param yourBalance - Money object representing your current balance
     * @param accountType - String representing your account type
     * @param overdraftMaximum - Money object representing the overdraft maximum
     */
    public Checking(String yourName, String yourId, Money yourBalance, String accountType, Money overdraftMaximum) {
        super(yourName, yourId, yourBalance, accountType);
        this.overdraftMaximum = overdraftMaximum;        
    }
    
    @Override
    /**
     * withdraw(): Overrides the withdraw method of account so that it checks if the 
     * amount to be withdrawn exceeds the balance by the overdraft maximum
     * @param: moneyToWithdraw: Money object representing how much money to withdraw
     * return: the updated money object of balance in account class
     * precondition: moneyToWithdraw must be a money object
     * postcondition: Withdraw method is performed if it meets the requirements regarding overdraftMaximum and withdraw (look at comments down below)
     */
    public Money withdraw(Money moneyToWithdraw) {
        // Adds the current balance of checking account to the overdraft so we can compare
        // this added amount to the money to withdraw
        Money availableBalance = this.getBalance().add(overdraftMaximum);

        // Checks if the amount to withdraw exceeds the balance by the overdraft maximum
        // Will withdraw the money if withdraw amount does not exceed overdraft
        if (moneyToWithdraw.compareTo(availableBalance) < 0) {
            super.withdraw(moneyToWithdraw);
            return this.getBalance();
        }

        // Withdraws money if the amount is equal to the overdraft maximum
        else if (moneyToWithdraw.compareTo(availableBalance) == 0) {
            super.withdraw(moneyToWithdraw);
            return this.getBalance();
        }

        // If withdraw does exceed the balance by more than the overdraft ammount, then you cannot withdraw!
        else {
            throw new InsufficientFundsException("Trying to withdraw too much: " + moneyToWithdraw + " from Account" + getID());
            // return this.getBalance();
        }

    }

}
