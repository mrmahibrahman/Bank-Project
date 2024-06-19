/*
 * Mahib Rahman
 * 3/25/2024
 * A blueprint for Account objects
 */


 public abstract class Account implements Comparable {
    // Private attributes
    private String name, id;
    private Money balance;
    private String accountType;

    /**
     * Constructor - initializes all attributes
     * @param yourName - String representing your name. 
     * @param yourId - String representing your ID number
     * @param yourBalance - Money object representing your balance (the amount of money you have in the account)
     */
    public Account(String yourName, String yourId, Money yourBalance, String accountType) {
        this.name = yourName;
        this.id = yourId;
        this.balance = yourBalance;
        this.accountType = accountType;
    }

    /**
     * deposit(): Deposits or adds money into an account.
     * @param moneyToDeposit - Money object representing how much money you want to add to your account
     * @return - this.balance (Money object): The updated balance of the account
     * precondition: moneyToDeposit must be a money object
     * postcondition: Account Balance is updated
     */
    public Money deposit(Money moneyToDeposit) {
        this.balance = this.balance.add(moneyToDeposit);
        return this.balance;
    }

    /**
     * withdraw(): Withdraws or removes money from an account
     * @param moneyToWithdraw: Money object representing how much money to remove from the account
     * @return Returns this.balance which is the updated balance of the account. 
     * precondition: moneyToWithdraw must be a money object
     * postcondition: this.balance is updated to reflect the changes
     */
    public Money withdraw(Money moneyToWithdraw) {
        this.balance = this.balance.subtract(moneyToWithdraw);
        return this.balance;
    }

    /**
     * transfer(): Transfers money from one account to another account
     * @param anotherAccount - Account object where the money will be deposited into
     * @param theMoney - Money object representing how much money to transfer
     * preconditions: anotherAccount must be an account object, and theMoney must be of type Money
     * postconditions: transfers a certain amount of money from one account to another account
     */
    public void transfer(Account anotherAccount, Money theMoney) {
        this.withdraw(theMoney);
        anotherAccount.deposit(theMoney);
    }

    /**
     * toString(): returns the string representation of the Account object attributes
     * precondition: none
     * postcondition: string representation of account.java is returned 
     */
    public String toString() {
        String result = "Name: " + this.name + ", ID: " + this.id + ", Your Balance: " + this.balance;
        return result;
    }

    /**
     * equals(): Compare the status of two Account objects
     * @param other - other is an Account type which will be compared with the object calling the equals method. 
     * @return true if all attributes of both objects are the same/False otherwise
     * precondition: other must be an Account type
     * postcondition: A boolean value is returned to see if the attributes of both accounts are the same
     */
    public boolean equals(Account other) {
        return ((this.balance.equals(other.balance)) && (this.name.equals(other.name)) && (this.id.equals(other.id)));
    }

    /**
     * getBalance() - Getter method to get the balance of the account. 
     * @return this.balance - Money object representing the balance of the account
     */
    public Money getBalance() {
        return this.balance;
    }

    /**
     * getID(): Getter method to get the ID of the account 
     * @return: this.id - String representing the id number
     */
    public String getID() {
        return this.id;
    }

    /**
     * getName(): Gets the name of the user
     * @return this.name: String representing the name of person who owns the account
     */
    public String getName() {
        return this.name;
    }

    /**
     * getAccountType(): gets the account type of the account
     * @return: this.accountType: String representing the account type.
     */
    public String getAccountType() {
        return this.accountType;
    }



    // compareTo: compare two Account objects.
    // Precondition: parameter o is an Object (of type Account)
    // Postcondition: return 0 if this.id is the same as o.id, -1 if this.id < o.id, 1 if this.id > o.id.
    @Override 
    public int compareTo(Object o)
    {
        if (o instanceof Account) {
            // This will cast o to an Account object if object o has the same instance as Account
            Account other = (Account) o;

            if (this.id.equals(other.id)) {
                return 0;
            }

            // compareTo is a Java string method
            else if(this.id.compareTo(other.id) < 0) {
                return -1;
            }

            else {
                return 0;
            }

        }

        // o is not an Account ...
        else {
            // this will return some integer value 
            return 100;
        }

    }
}
