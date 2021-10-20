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

    private double ammount;
    private Currency currency;

    public Money(double ammount, Currency currency) {
        this.ammount = ammount;
        this.currency = currency;
    }

    @Override
    public Double aggregate(Double intermediateResult) {

        if (intermediateResult == null) {
            return ammount;
        }
        return intermediateResult + ammount;
    }

    @Override
    public Money deepClone() {
        return new Money(ammount, currency);
    }

    public double getAmmount() {
        return ammount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Money money = (Money) obj;
        // field comparison
        return Objects.equals(ammount, money.getAmmount())
                && Objects.equals(currency, money.getCurrency());
    }

    @Override
    public String toString() {
        return Double.toString(ammount) + " " + currency.toString();
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
