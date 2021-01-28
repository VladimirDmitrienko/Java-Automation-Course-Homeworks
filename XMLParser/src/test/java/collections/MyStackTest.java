package collections;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Iterator;
import utilities.MyStack;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStackTest {

    private MyStack<Integer> testingStack;

    @BeforeMethod
    public void initStack() {
        testingStack = new MyStack<>();
    }

    @AfterMethod
    public void destructStack() {
        testingStack = null;
    }

    @Test
    public void testInitialSize() {
        Assert.assertEquals(testingStack.size(), 0);
    }

    @Test
    public void testPush() {
        testingStack.push(2);
        Assert.assertTrue(testingStack.contains(2));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testPushNull() {
        testingStack.push(null);
    }

    @Test
    public void testPop() {
        testingStack.push(2);
        int element = testingStack.pop();
        Assert.assertTrue(element == 2 && testingStack.isEmpty());
    }

    @Test(expectedExceptions = EmptyStackException.class)
    public void testPopWithEmptyStack() {
        testingStack.pop();
    }

    @Test
    public void testPeek() {
        testingStack.push(2);
        int element = testingStack.peek();
        Assert.assertTrue(element == 2 && testingStack.contains(2));
    }

    @Test(expectedExceptions = EmptyStackException.class)
    public void testPeekWithEmptyStack() {
        testingStack.peek();
    }

    @Test
    public void testClear() {
        testingStack.push(2);
        testingStack.clear();
        Assert.assertTrue(testingStack.isEmpty() && testingStack.size() == 0);
    }

    @Test
    public void testIsEmptyTrue() {
        Assert.assertTrue(testingStack.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        testingStack.push(2);
        Assert.assertFalse(testingStack.isEmpty());
    }

    @Test
    public void testToArrayWithoutArgument() {
        Object[] ints = {1, 2};
        testingStack.push(2);
        testingStack.push(1);
        Assert.assertEquals(ints, testingStack.toArray());
    }

    @Test
    public void testToArrayWithArgument() {
        Integer[] ints = new Integer[2];
        testingStack.push(2);
        testingStack.push(1);
        testingStack.toArray(ints);
        Assert.assertEquals(ints, testingStack.toArray());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testToArrayWithNullArgument() {
        testingStack.push(1);
        testingStack.toArray(null);
    }

    @Test
    public void testContainsTrue() {
        testingStack.push(10);
        Assert.assertTrue(testingStack.contains(10));
    }

    @Test
    public void testContainsFalse() {
        Assert.assertFalse(testingStack.contains(10));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testContainsWithNullArgument() {
        testingStack.contains(null);
    }

    @Test
    public void testSearch() {
        testingStack.push(10);
        testingStack.push(20);
        Assert.assertEquals(testingStack.search(20), 1);
    }

    @Test
    public void testEqualsTrue() {
        testingStack.push(10);
        testingStack.push(20);
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(10);
        myStack.push(20);
        Assert.assertTrue(testingStack.equals(myStack));
    }

    @Test
    public void testEqualsFalseWithDifferentElements() {
        testingStack.push(10);
        testingStack.push(20);
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(1);
        myStack.push(2);
        Assert.assertFalse(testingStack.equals(myStack));
    }

    @Test
    public void testEqualsFalseWithDifferentSize() {
        testingStack.push(10);
        testingStack.push(20);
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(10);
        Assert.assertFalse(testingStack.equals(myStack));
    }

    @Test
    public void testSize() {
        for (int i = 0; i < 5; i++) {
            testingStack.push(i);
        }
        Assert.assertEquals(testingStack.size(), 5);
    }

    @Test
    public void testIteratorHasNextFalse() {
        Iterator<Integer> iterator = testingStack.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextTrue() {
        testingStack.push(10);
        Iterator<Integer> iterator = testingStack.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        testingStack.push(10);
        Iterator<Integer> iterator = testingStack.iterator();
        Assert.assertEquals(iterator.next().intValue(), 10);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testIteratorNoSuchElement() {
        Iterator<Integer> iterator = testingStack.iterator();
        iterator.next();
    }
}