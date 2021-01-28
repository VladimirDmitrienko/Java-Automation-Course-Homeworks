package collections;

import exceptions.EmptyQueueException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Iterator;
import utilities.MyQueue;

import java.util.NoSuchElementException;

public class MyQueueTest {

    private MyQueue<Integer> testingQueue;

    @BeforeMethod
    public void initQueue() {
        testingQueue = new MyQueue<>();
    }

    @AfterMethod
    public void destructQueue() {
        testingQueue = null;
    }

    @Test
    public void testInitialSize() {
        Assert.assertEquals(testingQueue.size(), 0);
    }

    @Test
    public void testEnqueue() {
        testingQueue.enqueue(3);
        Assert.assertEquals(testingQueue.peek().intValue(), 3);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testEnqueueNull() {
        testingQueue.enqueue(null);
    }

    @Test
    public void testDequeue() {
        testingQueue.enqueue(3);
        int element = testingQueue.dequeue();
        Assert.assertTrue(element == 3 && testingQueue.isEmpty());
    }

    @Test(expectedExceptions = EmptyQueueException.class)
    public void testDequeueWithEmptyQueue() {
        testingQueue.dequeue();
    }

    @Test
    public void testPeek() {
        testingQueue.enqueue(2);
        int element = testingQueue.peek();
        Assert.assertTrue(element == 2 && !testingQueue.isEmpty());
    }

    @Test(expectedExceptions = EmptyQueueException.class)
    public void testPeekWithEmptyQueue() {
        testingQueue.peek();
    }

    @Test
    public void testDequeueAll() {
        testingQueue.enqueue(1);
        testingQueue.enqueue(2);
        testingQueue.dequeueAll();
        Assert.assertTrue(testingQueue.isEmpty());
    }

    @Test
    public void testIsEmptyTrue() {
        Assert.assertTrue(testingQueue.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        testingQueue.enqueue(2);
        Assert.assertFalse(testingQueue.isEmpty());
    }

    @Test
    public void testIteratorHasNextFalse() {
        Iterator<Integer> iterator = testingQueue.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextTrue() {
        testingQueue.enqueue(10);
        Iterator<Integer> iterator = testingQueue.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        testingQueue.enqueue(10);
        Iterator<Integer> iterator = testingQueue.iterator();
        Assert.assertEquals(iterator.next().intValue(), 10);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testIteratorNoSuchElement() {
        Iterator<Integer> iterator = testingQueue.iterator();
        iterator.next();
    }

    @Test
    public void testEqualsTrue() {
        testingQueue.enqueue(10);
        testingQueue.enqueue(20);
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        Assert.assertTrue(testingQueue.equals(myQueue));
    }

    @Test
    public void testEqualsFalseWithDifferentElements() {
        testingQueue.enqueue(10);
        testingQueue.enqueue(20);
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        Assert.assertFalse(testingQueue.equals(myQueue));
    }

    @Test
    public void testEqualsFalseWithDifferentSize() {
        testingQueue.enqueue(10);
        testingQueue.enqueue(20);
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(10);
        Assert.assertFalse(testingQueue.equals(myQueue));
    }

    @Test
    public void testToArrayWithoutArgument() {
        Object[] ints = {1, 2};
        testingQueue.enqueue(1);
        testingQueue.enqueue(2);
        Assert.assertEquals(ints, testingQueue.toArray());
    }

    @Test
    public void testToArrayWithArgument() {
        Integer[] ints = new Integer[2];
        testingQueue.enqueue(1);
        testingQueue.enqueue(2);
        testingQueue.toArray(ints);
        Assert.assertEquals(ints, testingQueue.toArray());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testToArrayWithNullArgument() {
        testingQueue.enqueue(1);
        testingQueue.toArray(null);
    }

    @Test
    public void testIsFull() {
        Assert.assertFalse(testingQueue.isFull());
    }

    @Test
    public void testSize() {
        for (int i = 0; i < 5; i++) {
            testingQueue.enqueue(i);
        }
        Assert.assertEquals(testingQueue.size(), 5);
    }
}