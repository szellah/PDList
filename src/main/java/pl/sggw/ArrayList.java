package pl.sggw;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface ArrayList<T> {
    //zgodny z ArrayList

    boolean add(T t);

    void add(int index, T element);

    boolean	addAll(Collection<? extends T> c);

    boolean	addAll(int index, Collection<? extends T> c);

    void clear();

    Object clone();

    boolean contains(Object o);

    void ensureCapacity(int minCapacity);

    void forEach(Consumer<? super T> action);

    T get(int index);

    int	indexOf(Object o);

    boolean	isEmpty();

    Iterator<T> iterator();

    int	lastIndexOf(Object o);

    ListIterator<T> listIterator();

    ListIterator<T>	listIterator(int index);

    T remove(int index);

    boolean	remove(Object o);

    boolean	removeAll(Collection<?> c);

    boolean	removeIf(Predicate<? super T> filter);

    void removeRange(int fromIndex, int toIndex);

    void replaceAll(UnaryOperator<T> operator);

    boolean	retainAll(Collection<?> c);

    T set(int index, T element);

    int	size();

    void sort(Comparator<? super T> c);

    Spliterator<T> spliterator();

    //ArrayList<T> subList(int fromIndex, int toIndex);

    <T> T[]	toArray(T[] a);

    void trimToSize();
}
