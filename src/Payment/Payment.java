package Payment;

import Money.Money;

public sealed interface Payment permits CashPayment, CardPayment{
    Money amount();
}
