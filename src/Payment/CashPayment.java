package Payment;

import Money.Money;

public record CashPayment(Money amount, Money tendered) implements Payment {

    public CashPayment {
        if (tendered.minus(amount).isNegative()) {
            throw new IllegalArgumentException("Still owed: " + amount.minus(tendered));
        }
    }

    public Money change() {
        return tendered.minus(amount);
    }
}
