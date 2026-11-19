package Food;

public final class Foods extends MenuItem {
    private final boolean spicy;
    private final boolean glutenFree;

    public Foods (int id, String name, double price, boolean spicy, boolean glutenFree) {
        super (id, name, price);
        this.spicy= spicy;
        this.glutenFree= glutenFree;
    }

    public boolean isSpicy() { return spicy ? "Yes" : "No"; }
    public boolean isGlutenFree() { return glutenFree ? "Yes" : "No"; }
}