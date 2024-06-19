import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Mahib Rahman
 * 3/27/2024
 * Tests the Bank class
 */

public class BankTest2 {
    private Bank2 _bank;

    // Default constructor for class BankTest
    public BankTest2() {

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        _bank = new Bank2("TD Bank");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        _bank = null;
    }

    /**
     * Test methods 
     */

    @Test
    //Tests addAccount method
    public void testAddAccount() {
        // Test 1: Adding one account
        Money money1 = new Money(500,100);
        Account account = new Checking("Erling Haaland", "123", money1, "Checking", new Money(50,0));

        _bank.addAccount(account);

        assertEquals("Error in testAddAccount", 1, _bank.getNumOfAccounts());
        assertEquals("Error in testAddAccount", account, _bank.search("123"));

        // Test 2: Adding two accounts
        Account account2 = new Checking("Mahib", "620", money1, "Checking", new Money(50, 0));
        _bank.addAccount(account2);
        assertEquals("Error in testAddAccount", 2, _bank.getNumOfAccounts());
        assertEquals("Error in testAddAccount", account2, _bank.search("620"));
        
    }

    // Tests the search method 
    @Test
    public void testSearch() {
        Money money = new Money(500,0);
        Account account1 = new Checking("Mahib", "123", money, "Checking", new Money(50, 0));
        _bank.addAccount(account1);

        // Test 1: Search for an account that exists:
        assertEquals("Error in testSearch", account1, _bank.search("123"));

        // Test 2: Search for an account that does not exist
        // I used this for reference: https://junit.org/junit4/javadoc/4.13/org/junit/Assert.html
        assertNull(_bank.search("1241551"));
    }

    // Tests the deposit method 
    @Test
    public void testDeposit() {
        Money moneyToDeposit = new Money(500, 0);
        Money money1 = new Money(300, 0);
        Money money2 = new Money(200,0);

        // Create accounts
        Account account = new Checking("Erling Haaland", "123", money1, "Checking", new Money(50,0));
        Account account2 = new Checking("Mahib", "620", money2, "Checking", new Money(50,0));

        // Add accounts into the bank
        _bank.addAccount(account);
        _bank.addAccount(account2);

        //Test 1: Checking if different accounts get deposited correctly
        _bank.deposit("123", moneyToDeposit);
        _bank.deposit("620", moneyToDeposit);

        // Expected money for accounts one and two
        Money expectedMoneyAccount1 = new Money(800,0);
        Money expectedMoneyAccount2 = new Money(700,0);

        assertTrue("Error in testDeposit", expectedMoneyAccount1.equals(_bank.search("123").getBalance()));
        assertTrue("Error in testDeposit", expectedMoneyAccount2.equals(_bank.search("620").getBalance()));
    }

    // Tests the withdraw method
    @Test
    public void testWithdraw() {
        Money moneyToWithdraw = new Money(100, 0);

        Money money1 = new Money(300, 0);
        Money money2 = new Money(200,0);

        // Create accounts
        Account account = new Checking("Erling Haaland", "123", money1, "Checking", new Money(50,0));
        Account account2 = new Checking("Mahib", "620", money2, "Checking",new Money (50, 0));

        // Add accounts into the bank
        _bank.addAccount(account);
        _bank.addAccount(account2);

        //Test 1: Checking if different accounts get withdrawn correctly
        _bank.withdraw("123", moneyToWithdraw);
        _bank.withdraw("620", moneyToWithdraw);

        // Expected money for accounts one and two
        Money expectedMoneyAccount1 = new Money(200,0);
        Money expectedMoneyAccount2 = new Money(100,0);

        assertTrue("Error in testWithdraw", expectedMoneyAccount1.equals(_bank.search("123").getBalance()));
        assertTrue("Error in testWithdraw", expectedMoneyAccount2.equals(_bank.search("620").getBalance()));
    }

    // Tests if the object's string representation is correct
    @Test
    public void testToString() {
        Money money1 = new Money(300, 0);
        Money money2 = new Money(200,0);

        Account account = new Checking("Erling Haaland", "123", money1, "Checking", new Money(50,0));
        Account account2 = new Checking("Mahib", "620", money2, "Checking", new Money(50,0));

        // Add accounts to _bank
        _bank.addAccount(account);
        _bank.addAccount(account2);

        String expectedResult = "Name of bank: TD Bank\n" +
                                "Number of accounts: 2\n" +
                                "Accounts in bank: \n" +
                                "Name: Erling Haaland, ID: 123, Your Balance: $300.00\n" +
                                "Name: Mahib, ID: 620, Your Balance: $200.00";

        // Actual result                      
        String result = _bank.toString();


        assertTrue("Error in testToString", expectedResult.equals(result));
    }

    @Test
    // Tests the sortAccounts() method to see if the method sorts the accounts in ascending order based on id.
    public void testSortAccounts() {
        Money money1 = new Money(300, 0);
        Money money2 = new Money(200,0);

        Account account = new Checking("Erling Haaland", "123", money1, "Checking", new Money(50,0));
        Account account2 = new Checking("Mahib", "620", money2, "Checking", new Money(50,0));
        Account account3 = new Checking("Darwin Nunez", "555", money2, "Checking", new Money(30,0));

        // Add accounts to _bank
        _bank.addAccount(account);
        _bank.addAccount(account2);
        _bank.addAccount(account3);

        // Sorts accounts into ascending order based on id number                
        _bank.sortAccounts();

        // Gets the accounts array 
        Account[] sortedAccounts = _bank.getAccounts();

        assertEquals("Error in testoStringting", "123", sortedAccounts[0].getID());
        assertEquals("Error in testToString", "555", sortedAccounts[1].getID());
        assertEquals("Error in testToString", "620", sortedAccounts[2].getID());
    }


}
