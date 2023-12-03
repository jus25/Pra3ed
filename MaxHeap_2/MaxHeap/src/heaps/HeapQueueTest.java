package heaps;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class HeapQueueTest {
    private static HeapQueue<Integer, Integer> arbreTest;

    static void setup() {
        arbreTest = new HeapQueue<>();
        arbreTest.add(16, 16);
        arbreTest.add(14, 14);
        arbreTest.add(10, 10);
        arbreTest.add(8, 8);
        arbreTest.add(7, 7);
        arbreTest.add(9, 9);
        arbreTest.add(3, 3);
        arbreTest.add(2, 2);
        arbreTest.add(4, 4);
        arbreTest.add(1, 1);
    }

    @Test
    void testTrees() {
        HeapQueue<Integer, Integer> arbre1 = new HeapQueue<>();
        arbre1.add(18, 18);
        arbre1.add(20, 20);
        arbre1.add(25, 25);
        arbre1.add(5, 5);
        arbre1.add(12, 12);
        arbre1.add(23, 23);
        arbre1.add(21, 21);
        arbre1.add(24, 24);

        // Comprobar el estado original
        assertEquals(25, arbre1.element());
        assertEquals(18, arbre1.get(3));
        assertEquals(21, arbre1.get(6));

        // Eliminar un elemento
        arbre1.remove();

        // Comprobar el nuevo estado después de la eliminación
        assertEquals(24, arbre1.element());  // El nuevo elemento máximo después de la eliminación
        assertEquals(23, arbre1.get(2));      // El elemento en el índice 2 después de la eliminación
        assertEquals(20, arbre1.get(5));     // El elemento en el índice 5 después de la eliminación
    }


    @Test
    void testAddBigNumber() {
        setup();
        arbreTest.add(18, 18);
        assertEquals(18, arbreTest.element());
        assertEquals(16, arbreTest.get(1));
        assertEquals(14, arbreTest.get(4));
        assertEquals(7, arbreTest.get(10));
        assertEquals(11, arbreTest.size());
    }


    @Test
    void testAddNumber() {
        setup();
        arbreTest.add(12, 12);
        assertEquals(12, arbreTest.get(4));
        assertEquals(7, arbreTest.get(10));
        assertEquals(11, arbreTest.size());
    }

    @Test
    void testAddExistingPriority() {
        setup();
        arbreTest.add(7, 7);
        assertEquals(7, arbreTest.get(10));
        assertEquals(7, arbreTest.get(4));
    }

    @Test
    void testVoidTree() {
        HeapQueue<Integer, Integer> ArbreBuit = new HeapQueue<>();
        assertThrows(NoSuchElementException.class, () -> {
            ArbreBuit.remove();
        });
    }

    @Test
    void testTreeVoidElement() {
        HeapQueue<Integer, Integer> ArbreBuit = new HeapQueue<>();
        assertThrows(NoSuchElementException.class, () -> {
            ArbreBuit.element();
        });
    }

    @Test
    void testIndexMayor() {
        setup();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            arbreTest.get(15);
        });
    }

    @Test
    void testRemove() {
        setup();
        assertEquals(16, arbreTest.element());
        arbreTest.remove();
        assertEquals(14, arbreTest.element());
        assertEquals(1, arbreTest.get(8));
        assertEquals(8, arbreTest.get(1));
        assertEquals(4, arbreTest.get(3));
        assertEquals(9, arbreTest.size());
    }
}
