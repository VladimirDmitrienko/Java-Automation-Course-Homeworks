package collections;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Iterator;
import utilities.MyArrayList;
import utilities.MyDLL;

import java.util.NoSuchElementException;

public class MyDLLTest {

    private MyDLL<Integer> testingDLL;

    @BeforeMethod
    public void initDLL() {
        testingDLL = new MyDLL<>();
    }

    @AfterMethod
    public void destructDLL() {
        testingDLL = null;
    }

    @Test
    public void testInitialSize() {
        Assert.assertEquals(testingDLL.size(), 0);
    }

    @Test
    public void testIsEmptyTrue() {
        Assert.assertTrue(testingDLL.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        testingDLL.add(2);
        Assert.assertFalse(testingDLL.isEmpty());
    }

    @Test
    public void testToArrayWithoutArgument() {
        Object[] ints = {1, 2};
        testingDLL.add(1);
        testingDLL.add(2);
        Assert.assertEquals(ints, testingDLL.toArray());
    }

    @Test
    public void testToArrayWithArgument() {
        Integer[] ints = new Integer[2];
        testingDLL.add(1);
        testingDLL.add(2);
        testingDLL.toArray(ints);
        Assert.assertEquals(ints, testingDLL.toArray());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testToArrayWithNullArgument() {
        testingDLL.add(1);
        testingDLL.toArray(null);
    }

    @Test
    public void testContainsTrue() {
        testingDLL.add(10);
        Assert.assertTrue(testingDLL.contains(10));
    }

    @Test
    public void testContainsFalse() {
        Assert.assertFalse(testingDLL.contains(10));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testContainsWithNullArgument() {
        testingDLL.contains(null);
    }

    @Test
    public void testIteratorHasNextFalse() {
        Iterator<Integer> iterator = testingDLL.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextTrue() {
        testingDLL.add(10);
        Iterator<Integer> iterator = testingDLL.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        testingDLL.add(10);
        Iterator<Integer> iterator = testingDLL.iterator();
        Assert.assertEquals(iterator.next().intValue(), 10);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testIteratorNoSuchElement() {
        Iterator<Integer> iterator = testingDLL.iterator();
        iterator.next();
    }

    @Test
    public void testAddWithoutIndex() {
        testingDLL.add(10);
        Assert.assertEquals(testingDLL.get(0).intValue(), 10);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAddNullWithoutIndex() {
        testingDLL.add(null);
    }

    @Test
    public void testAddWithIndex() {
        testingDLL.add(10);
        testingDLL.add(0, 20);
        Assert.assertEquals(testingDLL.get(0).intValue(), 20);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testAddElementOutOfBounds() {
        testingDLL.add(10, 5);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAddNullWithIndex() {
        testingDLL.add(1);
        testingDLL.add(0, null);
    }

    @Test
    public void testAddAll() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        testingDLL.addAll(myArrayList);
        Assert.assertEquals(testingDLL.get(0).intValue(), 1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAddAllWithNullArgument() {
        testingDLL.addAll(null);
    }

    @Test
    public void testRemoveWithIndex() {
        testingDLL.add(1);
        testingDLL.add(2);
        testingDLL.remove(0);
        Assert.assertEquals(testingDLL.get(0).intValue(), 2);
    }

    @Test
    public void testRemoveWithoutIndex() {
        testingDLL.add(2);
        testingDLL.add(3);
        testingDLL.remove(new Integer(2));
        Assert.assertEquals(testingDLL.get(0).intValue(), 3);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRemoveWithNullArgument() {
        testingDLL.remove(null);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBounds() {
        testingDLL.remove(10);
    }

    @Test
    public void testClear() {
        testingDLL.add(2);
        testingDLL.clear();
        Assert.assertTrue(testingDLL.isEmpty() && testingDLL.size() == 0);
    }

    @Test
    public void testSize() {
        for (int i = 0; i < 5; i++) {
            testingDLL.add(i);
        }
        Assert.assertEquals(testingDLL.size(), 5);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        testingDLL.get(10);
    }

    @Test
    public void testSet() {
        testingDLL.add(10);
        testingDLL.set(0, 5);
        Assert.assertEquals(testingDLL.get(0).intValue(), 5);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSetWithNullArgument() {
        testingDLL.add(1);
        testingDLL.set(0, null);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testSetOutOfBounds() {
        testingDLL.set(1, 1);
    }
}