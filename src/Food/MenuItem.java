package Food;

import Money.Money;

// permits = sealed classes, only allows certain classes to extend from this class
public sealed abstract class MenuItem permits Foods, Drinks, Desserts {
    private final int id;
    private final String name;
    private final Money price;
    
    protected MenuItem (int id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {return id;}
    public String getName() { return name;}
    public Money getPrice() { return price;}

    @Override 
    public String toString() { return getName() + " - " + getPrice();}
}