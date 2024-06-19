import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * Mahib Rahman
 * 3/25/2024
 * Tests the Account class
 */

public class AccountTest {
    private Account _accountOne;

    // Default constructor for test class AccountTest
    public AccountTest(){

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        Money myBalance = new Money(5000, 0);
        _accountOne = new Checking("Mahib", "620220263", myBalance, "Checking", new Money(50,0));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        _accountOne = null;
    }

    /**
     * Test methods 
     */

    // Tests if accounts deposit money correctly
    @Test
    public void testDeposit() {
        Money moneyToDeposit = new Money(250,10);
        Money accountOneDeposit = _accountOne.deposit(moneyToDeposit); 

        Money expectedBalance = new Money(5250, 10);
        assertTrue("Error in testDeposit", expectedBalance.equals(accountOneDeposit));
    }   

    // Tests if the account withdraws money correctly
    @Test
    public void testWithdraw() {
        Money moneyToWithdraw = new Money(250,10);
        Money accountOneWithdraw = _accountOne.withdraw(moneyToWithdraw);

        Money expectedBalance = new Money(4749, 90);
        assertTrue("Error in testWithdraw", expectedBalance.equals(accountOneWithdraw));
    }

    // Tests if money is transfered between accounts correctly
    @Test
    public void testTransfer() {
        Money anotherMoney = new Money(2500,0);
        Account anotherAccount = new RegularAccount("Robert Lewandoski", "32023233", anotherMoney, "Regular");

        _accountOne.transfer(anotherAccount, anotherMoney);

        // Money should be withdrawn from account One
        Money expectedBalance_AccountOne = new Money(2500,0);
        // Money should be added (deposited) to anotherAccount
        Money expectedBalance_AnotherAccount = new Money(5000,0);

        // Checks the balances of both _accountOne and anotherAccount
        assertTrue("Error in testTransfer", expectedBalance_AccountOne.equals(_accountOne.getBalance()));
        assertTrue("Error in testTransfer", expectedBalance_AnotherAccount.equals(anotherAccount.getBalance()));
    }

    // Test conversion of Account object to string
    @Test
    public void testToString() {
        String actual = _accountOne.toString();
        String expected = "Name: Mahib, ID: 620220263, Your Balance: $5000.00";

        assertEquals("Erorr in testToString", expected, actual);
    }

    // Tests if the equals method works if both objects have same attributes
    @Test
    public void testEqualsEquality() {
        Money moneyAmount = new Money(100,0);
        Account accountOne = new Checking("Mahib", "620220263", moneyAmount, "Checking", new Money(50,0));
        Account accountTwo = new Checking("Mahib", "620220263", moneyAmount, "Checking", new Money(50,0));
        
        assertTrue("Error in testEqualsEquality", accountOne.equals(accountTwo));
    }

    // Tests if the equals method works if both objects have different attributes
    @Test
    public void testEqualsInequality() {
        Money moneyOne = new Money(100, 0);
        Account accountOne = new Checking("Mahab", "12451245", moneyOne, "Checking", new Money(50,0));
        Account accountTwo = new Checking("Rahman", "1234515", moneyOne, "Checking", new Money(50,0));

        assertFalse("Error in testEqualsInequality", accountOne.equals(accountTwo));
    }



}

