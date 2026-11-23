package Exception;

public class TooManyGuestsException extends Exception{
    public TooManyGuestsException(int guests, int capacity) {
        super("The number of guests " + guests + " exceeds the table capacity of " + capacity);
    }
}
