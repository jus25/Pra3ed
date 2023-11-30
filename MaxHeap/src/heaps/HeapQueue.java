package heaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HeapQueue<P extends Comparable<? super P>, V>
        implements PriorityQueue<P, V> {
    private final ArrayList<Triplet<P, V>> triplets;
    private long nextTimeStamp = 0L;
    static class Triplet<P extends Comparable<? super P>, V>
            implements Comparable<Triplet<P, V>> {
        private final P priority;
        private final long timeStamp;
        private final V value;
        Triplet(P priority, long timeStamp, V value) {
            this.value = value;
            this.priority = priority;
            this.timeStamp = timeStamp;
        }
        @Override
        public int compareTo(Triplet<P, V> other) {
            int comparePriority = this.priority.compareTo(other.priority);

            if (comparePriority != 0) {
                // Si las prioridades son diferentes, devolver el resultado de la comparación por prioridad
                return comparePriority;
            } else {
                // Si las prioridades son iguales, comparar por timestamp
                if (this.timeStamp < other.timeStamp) {
                    return -1;
                } else if (this.timeStamp > other.timeStamp) {
                    return 1;
                } else {
                    // Si las prioridades y timestamps son iguales, las instancias son consideradas iguales
                    return 0;
                }
            }
        }
    }
    public HeapQueue() {
        triplets = new ArrayList<Triplet<P,V>>();
        nextTimeStamp += 1;
    }
    @Override
    public void add(P priority, V value) {throw new NoSuchElementException("a");}
    @Override
    public V remove() {throw new NoSuchElementException("b");}
    @Override
    public V element() { throw new NoSuchElementException("c"); }
    @Override
    public int size() { throw new NoSuchElementException("d"); }

}