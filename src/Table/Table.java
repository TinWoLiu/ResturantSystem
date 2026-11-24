package Table;

import Enums.TableStatus;
import Exception.TableOccupiedException;
import Exception.TooManyGuestsException;

import java.time.LocalDateTime;
import java.time.Duration;

public class Table {
    private static final int DEFAULT_CAPACITY = 4;

    private final int number;
    private final int capacity;
    private int guests;
    private TableStatus status = TableStatus.FREE;
    private LocalDateTime seatedAt;

    //this(), for invoking a constructor within the same class
    public Table(int number) {
        this(number, DEFAULT_CAPACITY);
    }

    //this. 
    public Table(int number, int capacity) {
        //this.number, this.capacity refers to the class variables
        this.number = number;
        this.capacity= capacity;
    }

    public void seat(int guests) throws TableOccupiedException, TooManyGuestsException{
        try {
        if (status == TableStatus.OCCUPIED) {
            throw new TableOccupiedException(number);
        }
        if (guests <= 0) {
            throw new IllegalArgumentException("Guests must be at least 1");
        }
        if (guests > capacity) {
            throw new TooManyGuestsException(guests, capacity);
        }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        } 

        this.guests = guests;
        status = TableStatus.OCCUPIED;
        seatedAt = LocalDateTime.now();
    }

    public void clear() {
        this.guests = 0;
        status = TableStatus.FREE;
    }

    public Duration timeSeated() {
        return Duration.between(seatedAt, LocalDateTime.now()).ZERO;
    }

    public int getNumber()          { return number; }
    public int getCapacity()        { return capacity; }
    public int getGuests()          { return guests; }
    public TableStatus getStatus()  { return status; }

    @Override 
    public String toString() {
        return "Table" + number + " [" + status + ", " + guests + "/" + capacity + "]";
    }
}
