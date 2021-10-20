package eu.glowacki.utp.assignment01.solution;

import eu.glowacki.utp.assignment01.IAggregable;
import eu.glowacki.utp.assignment01.IDeeplyCloneable;

import java.util.Objects;

public class Money implements IAggregable<Money, Double>, IDeeplyCloneable<Money> {

    public enum Currency {
        PLN,
        EUR,
        USD,
        UAH
    }

    private Double amount;
    private Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public Double aggregate(Double intermediateResult) {

        if (intermediateResult == null) {
            return amount;
        }
        return intermediateResult + amount;
    }

    @Override
    public Money deepClone() {
        return new Money(amount, currency);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Money money = (Money) obj;
        // field comparison
        return Objects.equals(amount, money.getAmount())
                && Objects.equals(currency, money.getCurrency());
    }

    @Override
    public String toString() {
        return Double.toString(amount) + " " + currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
