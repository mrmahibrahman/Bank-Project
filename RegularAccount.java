/**
 * Mahib Rahman
 * RegularAccount.java
 * This was added towards the end when we had to differentiate between a checking account and a regular account in input.txt
 * RegularAccount is a subclass of the Account class
 */

public class RegularAccount extends Account {
    // Calling the super constructor calls the Account constructor, and RegularAccount will have all attributes 
    // and methods of superclass Account.java.
    public RegularAccount(String yourName, String yourId, Money yourBalance, String accountType) {
        super(yourName, yourId, yourBalance, accountType);
    }
}
