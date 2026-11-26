package Payment;

import Money.Money;

public record CardPayment(Money amount) implements Payment {
    //record, no need to create constructor and getters
}
