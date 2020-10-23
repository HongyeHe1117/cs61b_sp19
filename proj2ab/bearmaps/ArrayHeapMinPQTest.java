package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<String> tester = new ArrayHeapMinPQ<>();
        tester.add("Test1", 3);
        tester.add("Test2", 1);
        tester.add("Test3", 2);
        tester.add("Test4", 0);
        tester.add("Test5", 4);
        assertEquals("Test4", tester.removeSmallest());
    }
    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<String> tester = new ArrayHeapMinPQ<>();
        tester.add("Test1", 3);
        tester.add("Test2", 1);
        tester.add("Test3", 2);
        tester.add("Test4", 0);
        tester.add("Test5", 3);
        assertEquals("Test4", tester.getSmallest());
    }
    @Test
    public void TestAdd() {
        ArrayHeapMinPQ<String> tester = new ArrayHeapMinPQ<>();
        tester.add("Test1", 5);
        tester.add("Test2", 2);
        tester.add("Test3", 1);
        tester.add("Test4", 0);
        tester.add("Test5", 3);
        assertEquals(5, tester.size());
    }
    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> tester = new ArrayHeapMinPQ<>();
        tester.add("Test1", 3);
        tester.add("Test2", 1);
        tester.add("Test3", 4);
        tester.add("Test4", 0);
        tester.add("Test5", 2);
        tester.changePriority("Test3", 0);
        tester.changePriority("Test4", 5);
        assertEquals("Test3", tester.getSmallest());
    }
    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> tester = new ArrayHeapMinPQ<>();
        for (int i = 1; i <= 1000; i++) {
            tester.add(i, i);
        }
        for (int i = 1; i <= 1000; i++) {
            assertEquals(tester.contains(i), true);
        }
    }
    @Test
    public void tesSize() {
        ArrayHeapMinPQ<Integer> tester = new ArrayHeapMinPQ<>();
        for (int i = 1; i <= 1000; i++) {
            tester.add(i, i);
        }
        assertEquals(tester.size(), 1000);
    }
    @Test
    public void timeTestRemoveSmallest() {
        ArrayHeapMinPQ<Integer> tester = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naiveTester = new NaiveMinPQ<>();
        for (int i = 0; i < 100000; i++) {
            tester.add(i, i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            int x = tester.getSmallest();
            int y = tester.removeSmallest();
            assertEquals(x, y);
            assertEquals(x, i);
            assertEquals(y, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 100000.0 +  " seconds.");
        for (int i = 0; i < 100000; i++) {
            naiveTester.add(i, i);
        }
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            int x = naiveTester.getSmallest();
            int y = naiveTester.removeSmallest();
            assertEquals(x, y);
            assertEquals(x, i);
            assertEquals(y, i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 100000.0 +  " seconds.");
    }
    @Test
    public void timeTestChangePriority() {
        ArrayHeapMinPQ<Integer> tester = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> tester2 = new NaiveMinPQ<>();
        for (int i = 0; i < 100000; i++) {
            tester.add(i, i);
            tester2.add(i, i);
        }
        long start = System.currentTimeMillis();
        for (int i = 100000 - 1; i >= 0; i--) {
            tester.changePriority(i, tester.getSmallest());
            tester2.changePriority(i, tester2.getSmallest());
            int x = tester.getSmallest();
            int y = tester2.getSmallest();
            assertEquals(x, y);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 100000.0 +  " seconds.");
    }
    @Test
    public void timeTestTwoChangePriority() {
        ArrayHeapMinPQ<Integer> tester = new ArrayHeapMinPQ<>();
        int[] items = new int[100000];
        for (int i = 0; i < 100000; i++) {
            tester.add(i, i);
            items[i] = i;
        }
        long start = System.currentTimeMillis();
        int priority = 0;
        for (int i = 100000 - 1; i > 0; i--) {
            tester.changePriority(items[i], priority);
            priority++;
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 100000.0 +  " seconds.");
    }
}
