package Table;

import Enums.TableStatus;
import Exception.TableOccupiedException;
import Exception.TooManyGuestsException;
import Order.Order;

import java.time.LocalDateTime;
import java.time.Duration;

public class Table {
    private static final int DEFAULT_CAPACITY = 4;

    private final int number;
    private final int capacity;
    private int guests;
    private TableStatus status = TableStatus.FREE;
    private LocalDateTime seatedAt;
    private Order order;

    //this(), for invoking a constructor within the same class
    public Table(int number) {
        this(number, DEFAULT_CAPACITY);
    }

    //this. 
    //method overloading, same method name, accept different parameters
    public Table(int number, int capacity) {
        //this.number, this.capacity refers to the class variables
        this.number = number;
        this.capacity= capacity;
    }

    public void seat(int guests) throws TableOccupiedException {
            if (status == TableStatus.OCCUPIED) {
                throw new TableOccupiedException(number);
            }
            if (guests <= 0) {
                throw new IllegalArgumentException("Guests must be at least 1");
            }
            if (guests > capacity) {
                throw new TooManyGuestsException(guests, capacity);
            }
        
        this.guests = guests;
        status = TableStatus.OCCUPIED;
        seatedAt = LocalDateTime.now();
        order = new Order();
    }

    public void clear() {
        this.guests = 0;
        status = TableStatus.FREE;
        seatedAt = null;
        order = null;
    }

    public Duration timeSeated() {
        return Duration.between(seatedAt, LocalDateTime.now());
    }

    //Setter
    public void setOrder(Order order) {this.order = order;}

    //Getters (Encapsulation)
    public int getNumber()          { return number; }
    public int getCapacity()        { return capacity; }
    public int getGuests()          { return guests; }
    public TableStatus getStatus()  { return status; }
    public Order getOrder()         { return order;}

    @Override 
    public String toString() {
        return "Table" + number + " [" + status + ", " + guests + "/" + capacity + "]";
    }
}
