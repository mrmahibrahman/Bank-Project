import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Tests Checking.java

public class CheckingTest {
    private Checking _checking;

    // Default constructor for class CheckingTest
    public CheckingTest() {

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        _checking = new Checking("Mahib", "123", new Money(100,0), "Checking", new Money(50,0));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        _checking = null;
    }

    /**
     * Test methods 
     */

     /**
      * Tests the withdraw method when the withdraw method exceeds the balance by more than the overdraft. 
      */
    // @Test
    // public void testWithdrawExcedingOverdraft() {
    //     Money remainingBalance = _checking.withdraw(new Money(200, 0));
    //     assertTrue("Error in testWithdrawExceedingOverdraft", remainingBalance.equals(new Money(100, 0)));
    // }

    // Tests overWithdrawing by checking if the insufficient funds is thrown correctly.
    @Test
    public void testOverwithdraw() {
        // Create a checking account.
        Checking acc = new Checking("Mahib Rahman", "620", new Money(100), "Checking", new Money(50));

        try {
            // Withdraw an amount that should cause an exception of type InsufficientFundsException...
            acc.withdraw( new Money(200, 0) );

            // If we reach this point in the code, that means the exception was not thrown as expected, so this test case fails.
            fail(); 
        }
        catch (InsufficientFundsException ife) {
            System.out.println("InsufficientFunds Exception on testOverwithdraw");
            ife.printStackTrace();
        }
        
    }


    /**
     * Tests the withdraw method when the withdraw amount does not exceed the balance by more than the overdraft amount + the amount you currently have. 
     */
    @Test
    public void testWithdrawNotExceedingOverdraft() {
        Money remainingBalance = _checking.withdraw(new Money(20, 0));
        assertTrue("Error in testWithdrawNotExceedingOverdraft", remainingBalance.equals(new Money(80, 0)));
    }

    /**
     * Tests the withdraw method when the withdraw amount equals the balance by more than the overdraft amount + how much you currently you have.
     */
    @Test
    public void testWithdrawEqualOverdraft() {
        Money initialBalance = new Money(100, 0);
        Money overdraftMaximum = new Money(50, 0);
        Checking account = new Checking("John Doe", "123", initialBalance, "Checking", overdraftMaximum);
        
        Money remainingBalance = account.withdraw(new Money(150, 0));
        assertTrue(new Money(-50, 0).equals(remainingBalance));
    }


}





