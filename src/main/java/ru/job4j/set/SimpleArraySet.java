package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean rev = !contains(value);
        if (rev) {
            set.add(value);
        }
        return rev;
    }

    @Override
    public boolean contains(T value) {
        boolean rev = false;
        for (T element : set) {
            if (Objects.equals(element, value)) {
                rev = true;
                break;
            }
        }
        return rev;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}