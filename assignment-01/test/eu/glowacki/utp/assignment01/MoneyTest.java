package eu.glowacki.utp.assignment01;

import eu.glowacki.utp.assignment01.solution.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {

    private Money money;
    private double amount = 100d;

    @Before
    public void before() {
        money = new Money(amount, Money.Currency.USD);
    }

    @Test
    public void aggregate() {
        Assert.assertEquals(money.getAmount(), money.aggregate(null));
        final double intermidiateResult = 100d;
        Assert.assertEquals((long)(amount + intermidiateResult), money.aggregate(intermidiateResult).longValue());
    }

    @Test
    public void deepClone() {
        Money clone = money.deepClone();
        Assert.assertEquals(money.getAmount(), clone.getAmount());
        Assert.assertEquals(money.getCurrency(), clone.getCurrency());
        Assert.assertEquals(money, clone);
        Assert.assertNotSame(money, clone);
    }

}
