package Exception;

public class TableOccupiedException extends Exception{
    
    public TableOccupiedException(int tableNumber) {
        super("table "+ tableNumber + " is already occupied");
    }
}
