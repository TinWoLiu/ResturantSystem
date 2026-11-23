package Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

//  final, cannot be subclassed or mutable, private final field
public final class Money implements Comparable<Money>{

    //initial object
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    //method for rounding, round everything that is over half up
    //example: 0.435, rounded as 0.44
    private Money(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public static Money of(String amount) { return new Money(new BigDecimal(amount)); }
    public static Money of(double amount) { return new Money(BigDecimal.valueOf(amount)); }

    public Money plus(Money m) {return new Money(amount.add(m.amount));}
    public Money minus(Money m) {return new Money(amount.subtract(m.amount));}
    public Money times(int m) {return new Money(amount.multiply(BigDecimal.valueOf(m)));}
    public Money times(double m) {return new Money(amount.multiply(BigDecimal.valueOf(m)));}
    
    public BigDecimal getAmount() {return amount;}

    @Override
    public int compareTo(Money m) { return amount.compareTo(m.amount); }

    @Override
    public boolean equals(Object o) {
        return o instanceof Money m && amount.equals(m.amount);
    }

    @Override
    public int hashCode() { return amount.hashCode(); }

    @Override
    public String toString() { return amount + " €"; }
}
