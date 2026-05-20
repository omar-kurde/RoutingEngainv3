package org.example.util.utilObjects;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ExtendingList<E> extends AbstractList<E> {
    private final List<E> firstPart ;
    private final List<E> secondPart ;
    public ExtendingList(List<E> firstPart) {
        this.firstPart = firstPart;
        this.secondPart = new ArrayList<>() ;
    }
    @Override
    public int size() {
        return firstPart.size() + secondPart.size();
    }


    @Override
    public boolean add(E e) {
        return secondPart.add(e);
    }

    @Override
    public E get(int index) {
        if (index < firstPart.size()) {
            return firstPart.get(index);
        }
        return secondPart.get(index-firstPart.size());
    }
}
