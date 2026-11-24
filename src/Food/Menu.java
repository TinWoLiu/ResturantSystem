package Food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Menu {

    private final List<MenuItem> items = new ArrayList<>();

    //varargs
    public void add(MenuItem... newItems) {
        for (MenuItem item : newItems) {
            items.add(item);
        }
    }

    //defensive copying 
    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
        //return List.copyOf(items);
    }

    //Predicate, accept a single input argument, returns either true or false
    public List<MenuItem> filter(Predicate<MenuItem> condition) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : items) {
            if (condition.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    //switch case
    public static String details(MenuItem item) {
        return switch (item) {
            case Foods f -> "Spicy: " + yesNo(f.isSpicy())+ ", Gluten-free: " + yesNo(f.isGlutenFree());
            case Drinks d -> "Hot: " + yesNo(d.isHot());
            case Desserts ds -> "No extra details";
        };
    }

    private static String yesNo(boolean b) {
        return b ? "Yes" : "No";
    }
}
