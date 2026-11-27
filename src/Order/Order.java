package Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import Money.Money;
import Food.MenuItem;

public class Order implements Billable{

    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm");

    private final List<OrderLine> lines = new ArrayList<>();
    
    private final LocalDateTime openedAt;

    //new order
    public Order() {
        this.openedAt = LocalDateTime.now();
    }

    //overloading methods
    public Order(Order o) {
        this.openedAt = o.openedAt;
        this.lines.addAll(o.lines);
    }

    public void add(MenuItem item) {
        add(item, 1);
    }

    //for edit order, edit already exisiting item instead of adding a new
    //line of the same item
    public void add(MenuItem item, int quantity) {
        if (quantity <= 0) {
        throw new IllegalArgumentException("Quantity must be positive");
        }
        //check exisiting item in the List
        for (int i = 0; i < lines.size(); i ++) {
            //check the id of the List
            OrderLine line = lines.get(i);
            //if the id of the adding item matches exist id in the List
            if (line.item().getId() == item.getId()) {
                //modify the quantity in the same position
                lines.set(i, new OrderLine(item, line.quantity()+ quantity));
                return;
            }
        }
        lines.add(new OrderLine(item, quantity));
    }

    // vararg
    public void addAll(MenuItem... items){
        for (MenuItem item : items) {
            add(item);
        }
    }

    public boolean isEmpty() {
        return lines.isEmpty();
    }

    public List<OrderLine> getLines() {
        return new ArrayList<>(lines);
    }

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    @Override 
    public Money total() {
        Money total = Money.ZERO;
        for (OrderLine line : lines){
            total = total.plus(line.lineTotal());
        }
        return total;
    }

    //StringBuilder, instead of primative print
    public String receipt (int tableNumber) {
        var sb = new StringBuilder();
        sb.append("Table ").append(tableNumber).append(" Order")
            .append(" (opened ").append(openedAt.format(TIME)).append(")\n");
        //loop through the lines List
        for (OrderLine line : lines) {
            sb.append(line).append('\n');
        }
        sb.append('\n').append("Total -").append(total());
        return sb.toString();
    }
}
