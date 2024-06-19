import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Mahib Rahman
// Test the Money class. 

public class MoneyTest 
{
    private Money _amount;

    /**
     * Default constructor for test class MoneyTest
     */
    public MoneyTest()
    {
        //System.out.println("JUnit Framework calls Constructor of test class before executing test methods");
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        _amount = new Money(0, 0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        _amount = null;
    }

    /**
     * Test methods 
     */
    
    // Test creation of Money objects.
    @Test
    public void testCreate()
    {
         assertEquals("Error in testCreate", 0, _amount.getDollars());
         assertEquals("Error in testCreate", 0, _amount.getCents());
         
         Money amount2 = new Money (4, 50);
         
         assertEquals("Error in testCreate", 4, amount2.getDollars());
         assertEquals("Error in testCreate", 50, amount2.getCents());
         
         Money amount3 = new Money (-4, -50);
         
         assertEquals("Error in testCreate", -4, amount3.getDollars());
         assertEquals("Error in testCreate", -50, amount3.getCents());
         
    }
   
    // Test conversion of Money object to String.
    @Test
    public void testToString()
    {
        // First test: cents is two digits
        Money amount = new Money (7, 55);
        String actual = amount.toString();
        String expected = "$7.55";
        assertTrue("Error in testToString", actual.equals(expected));
        
        // Second test: cents is one digit
        Money amount2 = new Money (7, 5);
        String actual2 = amount2.toString();
        String expected2 = "$7.05";
        assertTrue("Error in testToString", actual2.equals(expected2));
    }
    
    // Test equality of money values that are the same.
    @Test
    public void testEquality()
    {
        Money myCash = new Money (3, 75);
        Money yourCash = new Money (3, 75);
        
        assertTrue ("Error in testEquality", myCash.equals(yourCash));
        
        Money myAmount = new Money (50, 0);
        Money yourAmount = new Money (50, 0);
        
        assertTrue ("Error in testEquality", myAmount.equals(yourAmount));
    }
    
    // Test inequality of money values that are different.
    @Test
    public void testInequality()
    {
        Money myCash = new Money (3, 75);
        Money difftDollarsSameCents = new Money (4, 75);    
        Money difftDollarsDifftCents = new Money (4, 80);   
        Money sameDollarsDifftCents = new Money (3, 80);   
        
        assertFalse ("Error in testInequality", myCash.equals(difftDollarsSameCents));
        assertFalse ("Error in testInequality", myCash.equals(difftDollarsDifftCents));
        assertFalse ("Error in testInequality", myCash.equals(sameDollarsDifftCents));
    }
    
    // Test addition of money values such that the sum of the cents do not exceed 99.
    @Test
    public void testSimpleAdd()
    {
       Money amount2 = new Money (2, 30);
       Money amount3 = new Money (3, 50);
       
       Money actualAmount = amount2.add (amount3);
       // actualAmount now has the sum of amount2 + amount 3
       
       // Expected result is $5.80
       Money expectedAmount = new Money (5, 80);
       
       assertTrue ("Error in testSimpleAdd", actualAmount.equals(expectedAmount));
       //assertEquals("Error in testSimpleAdd", 5, actualAmount.getDollars());
       //assertEquals("Error in testSimpleAdd", 80, actualAmount.getCents());
    }
    
    // Test complex addition  of two money values, i.e. sum of cents is greater than or equal to 100.
    @Test
    public void testComplexAdd()
    {
        // First test: sum of cents is 100.
        
        Money myCash = new Money (3, 50);
        Money yourCash = new Money (4, 50);            
        
        // Expected result is $8.00
        Money expectedAmount = new Money (8, 0);
       
        Money actualAmount = myCash.add(yourCash);
        
        //System.out.println (actualAmount.toString()); // just for tracing purposes
        
        assertTrue ("Error in testComplexAdd", actualAmount.equals(expectedAmount));     
        
        // Second test: sum of cents is greater than 100...
        Money myCashOne = new Money(3, 50);
        Money yourCashOne = new Money(4, 60);

        Money expectedAmountOne = new Money(8, 10);

        Money actualAmountOne = myCashOne.add(yourCashOne);

        assertTrue("Error in testComplexAdd", actualAmountOne.equals(expectedAmountOne));
        
    }

    // Test subtraction method of two money values whos cents difference does not go below .10. 
    @Test
    public void testSimpleSubtraction() {
        Money myCash = new Money(5,30);
        Money yourCash = new Money(1,20);

        // Expected amount: $4.10
        Money expectedAmount = new Money(4,10);
        Money actualAmount = myCash.subtract(yourCash);

        assertTrue("Error in testSimpleSubtraction", expectedAmount.equals(actualAmount));
    }

    // Test substraction method of two money values whos differents of cents goes below 0.10.
    @Test
    public void testComplexSubtraction() {
        Money myCash = new Money(5, 30);
        Money yourCash = new Money(2, 30);

        // Expected amount: $3.00
        Money expectedAmount = new Money(3,0);
        Money actualAmount = myCash.subtract(yourCash);

        assertTrue("Error in testComplexSubtraction", expectedAmount.equals(actualAmount));

        Money myCash1 = new Money(5,30);
        Money yourCash1 = new Money(3,50);

        // Expected amount: $1.80
        Money expectedAmount1 = new Money(1, 80);
        Money actualAmount1 = myCash1.subtract(yourCash1);

        assertTrue("Error in testComplexSubtraction", expectedAmount1.equals(actualAmount1));

        Money myCash2 = new Money(1, 10);
        Money yourCash2 = new Money(1,8);

        //Expected amount: $0.02
        Money expectedAmount2 = new Money(0,2);
        Money actualAmount2 = myCash2.subtract(yourCash2);

        assertTrue("Error in testComplexSubtraction", expectedAmount2.equals(actualAmount2));
    }

    @Test 
    // tests the compare to method
    public void testCompareTo() {
        Money myCash = new Money(5,30);
        Money sameCash = new Money(5,30);
        Money lessCash = new Money(4,20);
        Money moreCash = new Money(50,20);

        // Tests if both objects are the same
        assertEquals("Error in testCompareTo", 0, myCash.compareTo(sameCash));
        
        // Tests if the object calling method is greater than object in parameter
        assertEquals("Error in testCompareTo", 1, myCash.compareTo(lessCash));
        
        // Tests if the object calling method is less than object in parameter
        assertEquals("Error in testCompareTo", -1, myCash.compareTo(moreCash));

    }

}


