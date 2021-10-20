package eu.glowacki.utp.assignment01.solution;

import eu.glowacki.utp.assignment01.IAggregable;
import eu.glowacki.utp.assignment01.IContainer;
import eu.glowacki.utp.assignment01.IDeeplyCloneable;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MyContainer<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>, TAggregateResult> implements IContainer<TElement, TAggregateResult> {


    private List<TElement> elementList;

    public MyContainer() {
        elementList = new LinkedList<>();
    }

    public MyContainer(List<TElement> list) {
        this.elementList = new LinkedList<>(list);
    }

    public MyContainer(TElement ...elements) {
        this(Arrays.asList(elements));
    }


    @Override
    public List<TElement> elements() {
        return elementList;
    }

    @Override
    public TAggregateResult aggregateAllElements() {
        TAggregateResult result = null;
        for (TElement tElement : elementList) {
            result = tElement.aggregate(result);
        }
        return result;

    }

    @Override
    public TElement cloneElementAtIndex(int index) {
        return elementList.get(index).deepClone();
    }

    public void add(TElement tElement) {
        elementList.add(tElement);
    }

    public TElement get(int index) {
        return elementList.get(index);
    }
}


