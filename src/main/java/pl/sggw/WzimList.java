package pl.sggw;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class WzimList<T> implements java.util.List<T> {

    T[] arr;
    int maxCapacity = -1;

    public WzimList(){
        arr = (T[]) new Object[0];
    }

    public WzimList(Collection<? extends T> c){
        this();
        for(T t : c)
            add(t);
    }

    public WzimList(int initialCapacity){
        this();
        maxCapacity = initialCapacity;
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public boolean isEmpty() {
        return arr.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T t: arr) {
            if(t.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public T[] toArray() {
        return (T[]) arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) arr;
    }

    @Override
    public boolean add(T t) {
        try{
            add(arr.length, t);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;
        if(contains(o))
            result = true;
        remove(indexOf(o));
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o: c) {
            if(!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(arr.length,c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        try{
            int i = 0;
            for(T t: c){
                add(index+i++,t);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for(Object o: c) {
            while (contains(o)) {
                if(remove(o) && !result)
                    result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for(T t: arr){
            while(!c.contains(t) && contains(t)){
                if(remove(t) && !result)
                    result = true;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        arr = (T[]) new Object[0];
    }

    @Override
    public T get(int index) {
        return arr[index];
    }

    @Override
    public T set(int index, T element) {
        return arr[index] = element;
    }

    @Override
    public void add(int index, T element) {
        if(maxCapacity != -1 && arr.length < maxCapacity)
        {
            T[] temp = (T[]) new Object[arr.length+1];
            int j = 0;
            for (int i = 0; i < temp.length; i++) {
                if(i == index)
                    temp[i] = element;
                else
                    temp[i] = arr[j++];
            }
        }
    }

    @Override
    public T remove(int index){
        T[] temp = (T[]) new Object[arr.length-1];
        int j = 0;
        int i = 0;
        while (i<temp.length) {
            if(j == index)
                temp[i++] = arr[j];
            j++;
        }
        T ret = arr[index];
        arr = temp;
        return ret;
    }

    @Override
    public int indexOf(Object o) {
        T temp = (T) o;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(temp))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        T temp = (T) o;
        int last = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(temp))
                last = i;
        }
        return last;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            int curr = index;

            @Override
            public boolean hasNext() {
                return arr.length-1 > curr;
            }

            @Override
            public T next() {
                if(hasNext())
                    return arr[++curr];
                else
                    throw new NoSuchElementException();
            }

            @Override
            public boolean hasPrevious() {
                return curr>0;
            }

            @Override
            public T previous() {
                if(hasPrevious())
                    return arr[--curr];
                else
                    throw new NoSuchElementException();
            }

            @Override
            public int nextIndex() {
                return curr+1;
            }

            @Override
            public int previousIndex() {
                return curr-1;
            }

            @Override
            public void remove() {
                WzimList.this.remove(curr);
            }

            @Override
            public void set(T t) {
                WzimList.this.set(curr,t);
            }

            @Override
            public void add(T t) {
                WzimList.this.add(curr,t);
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        WzimList result = new WzimList();
        for (int i = fromIndex; i < toIndex; i++) {
            result.add(arr[i]);
        }
        return result;
    }


    //wyjątkowe dla ArrayList
    public Object clone(){
        WzimList wz = new WzimList();
        wz.addAll(this);
        return wz;
    }

    public void ensureCapacity(int minCapacity){
        if(minCapacity > arr.length){
            maxCapacity = minCapacity;
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void trimToSize(){
        maxCapacity = size();
    }
}