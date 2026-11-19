package Food;

public sealed abstract class MenuItem permits Foods, Drinks, Desserts {
    private final int id;
    private final String name;
    private final double price;
    
    protected MenuItem (int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {return id;}
    public String getName() { return name;}
    public double getPrice() { return price;}


}