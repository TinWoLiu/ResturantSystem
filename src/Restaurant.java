import Exception.*;
import Money.Money;
import Payment.*;
import Table.Table;
import Food.Menu;

public class Restaurant {
    
    private final Table[] tables;
    private final Menu menu = new Menu();

    private Money totalRevenue = Money.ZERO;

    public Restaurant(int numberOfTables) {
        tables = new Table[numberOfTables];
        for (int i=0; i < tables.length; i++) {
            tables[i] = new Table(i + 1);
        }
    }

    public Table getTable(int number) {
        if (number < 1 || number > tables.length) {
            throw new IllegalArgumentException("Table " + number + " does not exist!");
        } else {
            return tables[number-1];
        }
    }

    //defensive copying
    public Table[] getTables() {
        return tables.clone();
    }

    public Menu getMenu() {
        return menu;
    }

    public void seat(int tableNumber, int guests) throws TableOccupiedException {
        getTable(tableNumber).seat(guests);
    }

    public String pay(int tableNumber, Payment payment) {
        Table table = getTable(tableNumber);

        totalRevenue = totalRevenue.plus(payment.amount());
        table.clear();
        return switch (payment) {
            case CashPayment c -> "Paid cash. Change: " + c.change();
            case CardPayment c -> "Paid card";
        };
    }

    public Money getTotalRevenue() {return totalRevenue; }
}
