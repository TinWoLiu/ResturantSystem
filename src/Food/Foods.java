package Food;

import Money.Money;

public final class Foods extends MenuItem {
    private final boolean spicy;
    private final boolean glutenFree;

    public Foods (int id, String name, Money price, boolean spicy, boolean glutenFree) {
        super (id, name, price);
        this.spicy= spicy;
        this.glutenFree= glutenFree;
    }

    public boolean isSpicy() { return spicy;}
    public boolean isGlutenFree() { return glutenFree; }

    @Override // for polymorphism and super.
    public String toString() { 
        return super.toString() + (spicy ? "(Spicy)" : "") + (glutenFree ? "(Gluten Free)" : ""); 
    }
}