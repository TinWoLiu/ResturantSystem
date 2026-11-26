package Exception;

public class TooManyGuestsException extends RuntimeException{
    public TooManyGuestsException(int guests, int capacity) {
        super("The number of guests " + guests + " exceeds the table capacity of " + capacity);
    }
}
