package Order;

import Food.MenuItem;
import Money.Money;

//record, immutable data classes that only needs type and name of field
//regular class requires you to create constructor and getters, record does it automatically
public record OrderLine(MenuItem item, int quantity) {

    public OrderLine {
        if (quantity <= 0 ) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }
    
    public Money lineTotal() {
        return item.getPrice().times(quantity);
    }

    @Override 
    public String toString() {
        return quantity + " x " +item.getName() + " - "+ lineTotal();
    }
}
