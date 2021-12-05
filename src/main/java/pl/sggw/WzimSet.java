package pl.sggw;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class WzimSet<T> implements Set<T>{
    //imituje hashset

    WzimList set;
    float loadFactor = 0.75f;

    public WzimSet(){
        set = new WzimList<T>();
        set.ensureCapacity(16);
    }

    public WzimSet(int initialCapacity){
        set = new WzimList<T>();
        set.ensureCapacity(initialCapacity);
    }

    public WzimSet(int initialCapacity, float loadFactor){
        set = new WzimList<T>();
        set.ensureCapacity(initialCapacity);
        this.loadFactor = loadFactor;
    }

    public WzimSet(Collection<? extends T> c){
        set = new WzimList<T>(c);
        set.ensureCapacity(16);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return set.listIterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) set.toArray(a);
    }

    @Override
    public boolean add(T t) {
        if(!set.contains(t))
            return set.add(t);
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.addAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }

    public Object clone(){
        WzimSet<T> ws = new WzimSet<T>(set.maxCapacity,loadFactor);
        ws.addAll((WzimList<T>)set.clone());
        return ws;
    }
}
