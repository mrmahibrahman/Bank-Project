/** 
 * A blueprint for Money objects...
 * // To create a jar file, go to projects
 * @author Mahib Rahman 
 * @version 3/25/2024
 */
public class Money
{
    // attributes = state variables
    private long totalCents;

    /**
     * Constructor: initializes all attributes (i.e. totalCents)
     * based on the given dollars and cents.
     *
     * @param theDollars the number of dollars
     * @param theCents   the number of cents
     */
    public Money(int theDollars, int theCents)
    {
        this.totalCents = theDollars * 100L + theCents;
    }

    /**
     * Constructor: initializes all attributes (i.e. totalCents)
     * based on the given total cents.
     *
     * @param theCents  the total number of cents
     */
    public Money(long theCents)
    {
        this.totalCents = theCents;
    }
    
    /**
     * getDollars:
     *
     * @return the number of dollars
     */
    public int getDollars()
    {
        return (int) (this.totalCents / 100);
    }
    
    /**
     * getCents:
     *
     * @return the number of cents (between 0 and 99, inclusive)
     */
    public int getCents()
    { 
        return (int) (this.totalCents % 100);
    }
 
    /**
     * add: adds two money values
     * 
     * Precondition: two Money amounts are created and valid
     * Postcondition: the amount in this Money object is added to the Money amount given as parameter; the result is returned.
     * Neither the calling object nor the parameter are changed.
     * 
     * @param a Money, the Money amount to add to the calling object (this)
     * @return Money, the sum
     */
    public Money add (Money theMoney)
    {
        return new Money (this.totalCents + theMoney.totalCents);
    }
    
    /**
     * toString: return String representation of this Money object
     * Precondition: this Money object is valid
     *
     * @return a String representation of this object
     */
    public String toString()
    {
        String result = "$" + this.getDollars() + "."; 
        
        if (this.getCents() < 10) {
            result += "0";
        }
        
        result += this.getCents();
        return result;
    }
    
    /**
     * equals: compare the status of two money objects.
     * 
     * @param other  a Money object
     * @return true if calling object (this) is in the same state as the Money object received as a parameter, and false otherwise.
     */
    public boolean equals (Money other)
    {
        return (this.totalCents == other.totalCents);    
    }

    /**
     * compareTo() method
     * Compares two Money objects and detmines if the money object is less than, greather than
     * or the same as the object received as a paramater. 
     * @param other
     * @return int of either 0 (if both objects are equal), -1 (if object calling method is less than the object in paramater), or 1 (if object is greater than object in param)
     */
    public int compareTo(Money other) {
        if (this.totalCents == other.totalCents) {
            return 0;
        }

        else if (this.totalCents < other.totalCents) {
            return -1;
        }

        return 1;

    }

    /**
     * subtract(): subtracts the money amount
     * * Precondition: two Money amounts are created and valid
     * Postcondition: the amount in this Money object is added to the Money amount given as parameter; the result is returned.
     * Neither the calling object nor the parameter are changed.

     * @param theMoney
     * @return Money object of updated balance after subtraction
     */
    public Money subtract(Money theMoney) {
        return new Money(this.totalCents - theMoney.totalCents);
    }
}


