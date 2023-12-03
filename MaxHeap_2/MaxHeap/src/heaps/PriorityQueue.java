package heaps;

public interface PriorityQueue<
        P extends Comparable<? super P>, V> {
    V get(int index);

    void add(P priority, V value);
    V remove();
    V element();
    int size();
}