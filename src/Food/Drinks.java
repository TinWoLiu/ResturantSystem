package Food;

public final class Drinks extends MenuItem {
    private final boolean hot;

    public Drinks (int id, String name, double price, boolean hot) {
        super (id, name, price);
        this.hot = hot;
    }

    public Boolean isHot() {return hot; }

    @Override // for polymorphism and super. 
    public String toString() { return super.toString() + (hot ? " (hot)" : "");}
}