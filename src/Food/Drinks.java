package Food;

public final class Drinks extends MenuItem {
    private final boolean hot;

    public Drinks (int id, String name, double price, boolean hot) {
        super (id, name, price);
        this.hot = hot;
    }

    public boolean isHot() {return hot ? "Yes" : "No"; }
}