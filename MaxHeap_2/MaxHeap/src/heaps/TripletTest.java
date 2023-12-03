package heaps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TripletTest {
    @Test
    void testSomeCompare() {
        var triplet1 = new HeapQueue.Triplet<Integer, Object>(
                1, 1L, new Object());
        var triplet2 = new HeapQueue.Triplet<Integer, Object>(
                2, 2L, new Object());
        assertTrue(triplet2.compareTo(triplet1) > 0);
    }
    @Test
    void testSomeMoreCompare() {
        var triplet1 = new HeapQueue.Triplet<Integer, Object>(
                1, 2L, new Object());
        var triplet2 = new HeapQueue.Triplet<Integer, Object>(
                1, 1L, new Object());
        assertTrue(triplet2.compareTo(triplet1) < 0);
    }
    @Test
    void testSomeMoreCompare2() {
        var triplet1 = new HeapQueue.Triplet<Integer, Object>(
                1, 2L, new Object());
        var triplet2 = new HeapQueue.Triplet<Integer, Object>(
                1, 1L, new Object());
        assertTrue(triplet2.compareTo(triplet1) < 0);
        var triplet3 = new HeapQueue.Triplet<Integer, Object>(
                1, 1L, new Object());
        assertEquals(0, triplet3.compareTo(triplet2));
        assertTrue(triplet1.compareTo(triplet3) > 0);
    }
}