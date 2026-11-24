package Order;

import java.util.List;

import Money.Money;

public interface Billable {
    
    //abstract
    Money total();

    //private, can only be used within the same interface
    private Money addPercent (Money amount, double rate) {
        return amount.plus(amount.times(rate));
    }

    default Money totalWithTip(double tipRate) {
        return addPercent (total(), tipRate);
    }


    static Money sum(List<? extends Billable> bills) {
    //List< ? extends Billable>, every list that implements billable
        Money result = Money.ZERO;
            for (Billable b : bills) {
                result = result.plus(b.total());
            }
        return result;
    }

}

