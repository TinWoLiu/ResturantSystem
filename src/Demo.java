import Exception.*;
import Food.*;
import Table.Table;
import Payment.CardPayment;
import Payment.CashPayment;
import Payment.Payment;
import Order.Order;
import Order.OrderLine;
import Enums.TableStatus;
import Money.Money;

import java.util.List;
import java.util.function.Predicate;

public class Demo {

    private static final Restaurant restaurant = new Restaurant(5);
    
    public static void main(String[] args) {
        setUpMenu();

        story1_viewTables(restaurant);
        story2_seatParty(restaurant);
        story3_addToOrder(restaurant);
        story4_takePayment(restaurant);
    }

    private static void setUpMenu() {
        restaurant.getMenu().add(
            new Foods(1, "Salad", Money.of(8.0),false,true),
            new Foods(2, "Burger", Money.of(9.0),false,false),
            new Foods(3, "Jalapeno Popper", Money.of(10.0),true,false),
            new Foods(4, "Chips", Money.of(4.0),false,true),
            new Drinks(5, "Cola", Money.of(3),false),
            new Drinks(6, "Milkshake", Money.of(5),false),
            new Drinks(7, "Coffee", Money.of(4.5),true),
            new Desserts(8, "Ice Cream", Money.of(4.5)),
            new Desserts(9, "Pancakes", Money.of(6.5))
        );
    }

    private static void story1_viewTables(Restaurant r) {
        //features: Encapsulation & defensive copying & enum
        System.out.println("Story 1: View all Tables");
        Table[] tables = restaurant.getTables();
        System.out.println("Tables:");
        for (Table t : tables) {
                System.out.println(t.getNumber() + ". " + label(t));
        }
        System.out.println();
    }

    private static void story2_seatParty(Restaurant r) {
        //features: Encapsulation & this()/this. & enum 
        // & Method Overloading 
        // & exceptions (checked/unchecked)
        System.out.println("Story 2: Seat a party");
        try {
            r.seat(1, 2);
            r.seat(1, 9); //already occupied table, should failed
        } catch (TableOccupiedException e) { //checked exception
            System.out.println(e.getMessage());
        }

        try {
            r.seat(2, 4);
            r.seat(3, 9);
        } catch (TooManyGuestsException e) { //unchecked exception
            System.out.println(e.getMessage());
        } catch (TableOccupiedException e) {
            System.out.println(e.getMessage());
        }
        Table[] tables = restaurant.getTables();
        System.out.println("Tables:");
        for (Table t : tables) {
            System.out.println(t.getNumber() + ". " + label(t) + 
            " ppl: "+t.getGuests());  
        }
        System.out.println();
    }

    private static void story3_addToOrder(Restaurant r) {
        //features: method overloading & varargs & records & StringBuilder
        System.out.println("Story 3: Add items to an order");
        r.getTable(1).getOrder().add(r.getMenu().findById(2));
        r.getTable(1).getOrder().add(r.getMenu().findById(5),2);
        r.getTable(1).getOrder().addAll(r.getMenu().findById(4), 
        r.getMenu().findById(4));
      
        r.getTable(2).getOrder().add(r.getMenu().findById(7),1);
        System.out.println(r.getTable(1).getOrder().receipt(1));
        System.out.println();
    }

    private static void story4_takePayment(Restaurant r) {
        //features: switch case and pattern matching & sealed interface
        System.out.println("Story 4: Take payment");
        Money totalCard = r.getTable(1).getOrder().total();
        r.pay(1, new CardPayment(totalCard));
        r.pay(2, new CashPayment(r.getTable(2).getOrder().total(), 
        Money.of(4.5)));
        Table[] tables = restaurant.getTables();
        System.out.println("Tables:");
        for (Table t : tables) {
                System.out.println(t.getNumber() + ". " + label(t));
        }
        System.out.println();
    }

    private static String label(Table t) {
        return switch (t.getStatus()) {
            case FREE -> "empty";
            case OCCUPIED -> "occupied (" + t.getOrder().total() + ")";
        };
    }

}
