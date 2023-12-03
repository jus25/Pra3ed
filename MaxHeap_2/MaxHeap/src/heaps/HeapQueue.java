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
        triplets = new ArrayList<>();
    }
    @Override
    public V get(int index){
        if (triplets.isEmpty()) {
            throw new NoSuchElementException("La cola de prioridad está vacía");
        }
        if (triplets.size() < index){
            throw new ArrayIndexOutOfBoundsException("El index es mayor que el tamaño de la cola");
        }
        return triplets.get(index).value;
    }
    @Override
    public void add(P priority, V value) {
        Triplet<P, V> newTriplet = new Triplet<>(priority, nextTimeStamp++, value);
        triplets.add(newTriplet);
        heapifyUp(triplets.size() - 1);
    }

    @Override
    public V remove() {
        if (triplets.isEmpty()) {
            throw new NoSuchElementException("La cola de prioridad está vacía");
        }

        // Obtener y eliminar el elemento de mayor prioridad (raíz)
        Triplet<P, V> removedTriplet = triplets.get(0);


        // Colocar el último elemento en la raíz
        Triplet<P, V> lastTriplet = triplets.remove(triplets.size() - 1);
        if (!triplets.isEmpty()) {
            triplets.set(0, lastTriplet);
            // Reorganizar el heap hacia abajo
            heapifyDown(0);
        }

        return removedTriplet.value;
    }

    @Override
    public V element() {
        if (triplets.isEmpty()) {
            throw new NoSuchElementException("La cola de prioridad está vacía");
        }
        return triplets.get(0).value;
    }

    @Override
    public int size() {
        return triplets.size();
    }

    //Operaciones auxiliares para hacer el add y el remove.
    private void heapifyUp(int index) {
        Triplet<P, V> insertTriplet = triplets.get(index);

        while (index > 0) {
            int parentIndex = parentIndex(index);
            Triplet<P, V> parentTriplet = triplets.get(parentIndex);

            if (parentTriplet.compareTo(insertTriplet) < 0) {
                triplets.set(parentIndex, insertTriplet);
                triplets.set(index, parentTriplet);
                index = parentIndex;  // Actualizar el índice después del intercambio
            } else {
                break;
            }
        }

    }

    private void heapifyDown(int index) {
        int leftChildIndex, rightChildIndex, maxIndex;

        while (true) {
            leftChildIndex = leftIndex(index);
            rightChildIndex = rightIndex(index);

            // Verificar si los índices de los hijos existen en la lista
            boolean hasLeftChild = exists(leftChildIndex);
            boolean hasRightChild = exists(rightChildIndex);

            // Salir del bucle si ambos índices no existen
            if (!hasLeftChild && !hasRightChild) {
                break;
            }

            maxIndex = index;

            // Comparar con el hijo izquierdo si existe
            if (hasLeftChild && triplets.get(leftChildIndex).compareTo(triplets.get(maxIndex)) > 0) {
                maxIndex = leftChildIndex;
            }

            // Comparar con el hijo derecho si existe
            if (hasRightChild && triplets.get(rightChildIndex).compareTo(triplets.get(maxIndex)) > 0) {
                maxIndex = rightChildIndex;
            }

            // Si el índice máximo no es el actual, intercambiar y seguir bajando
            if (maxIndex != index) {
                swap(index, maxIndex);
                index = maxIndex;
            } else {
                // Si no se hizo ningún intercambio, el heap está equilibrado
                break;
            }
        }
    }


    private void swap(int i, int j) {
        Triplet<P, V> temp = triplets.get(i);
        triplets.set(i, triplets.get(j));
        triplets.set(j, temp);
    }

    // Operaciones para manipular índices en forma de árbol
    static int parentIndex(int i) {
        return (i - 1) / 2;
    }

    static int leftIndex(int i) {
        return 2 * i + 1;
    }

    static int rightIndex(int i) {
        return 2 * i + 2;
    }

    boolean exists(int index) {
        return 0 <= index && index < size();
    }

}