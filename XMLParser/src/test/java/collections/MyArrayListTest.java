package collections;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Iterator;
import utilities.MyArrayList;

import java.util.NoSuchElementException;

public class MyArrayListTest {

    private MyArrayList<Integer> testingArray;

    @BeforeMethod
    public void initArray() {
        testingArray = new MyArrayList<>();
    }

    @AfterMethod
    public void destructArray() { testingArray = null; }

    @Test
    public void testInitialSizeWithDefaultConstructor() {
        Assert.assertEquals(testingArray.size(), 0);
    }

    @Test
    public void testInitialSizeWithCapacityArgumentConstructor() {
        testingArray = new MyArrayList<>(10);
        Assert.assertEquals(testingArray.size(), 0);
    }

    @Test
    public void testSize() {
        for (int i = 0; i < 5; i++) {
            testingArray.add(i);
        }
        Assert.assertEquals(testingArray.size(), 5);
    }

    @Test
    public void testClear() {
        testingArray.add(10);
        testingArray.clear();
        Assert.assertTrue(testingArray.isEmpty() && testingArray.size() == 0);
    }

    @Test
    public void testAddWithIndex() {
        testingArray.add(10);
        testingArray.add(0, 20);
        Assert.assertEquals(testingArray.get(0).intValue(), 20);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAddNullWithIndex() {
        testingArray.add(1);
        testingArray.add(0, null);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testAddElementOutOfBounds() {
        testingArray.add(10, 5);
    }

    @Test
    public void testAddWithoutIndex() {
        testingArray.add(10);
        Assert.assertEquals(testingArray.get(0).intValue(), 10);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAddNullWithoutIndex() {
        testingArray.add(null);
    }

    @Test
    public void testAddAll() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        testingArray.addAll(myArrayList);
        Assert.assertEquals(testingArray.get(0).intValue(), 1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAddAllWithNullArgument() {
        testingArray.addAll(null);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        testingArray.get(10);
    }

    @Test
    public void testRemoveWithIndex() {
        testingArray.add(1);
        testingArray.add(2);
        testingArray.remove(0);
        Assert.assertEquals(testingArray.get(0).intValue(), 2);
    }

    @Test
    public void testRemoveWithoutIndex() {
        testingArray.add(2);
        testingArray.add(3);
        testingArray.remove(new Integer(2));
        Assert.assertEquals(testingArray.get(0).intValue(), 3);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBounds() {
        testingArray.remove(10);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRemoveWithNullArgument() {
        testingArray.remove(null);
    }

    @Test
    public void testSet() {
        testingArray.add(10);
        testingArray.set(0, 5);
        Assert.assertEquals(testingArray.get(0).intValue(), 5);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSetWithNullArgument() {
        testingArray.add(1);
        testingArray.set(0, null);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testSetOutOfBounds() {
        testingArray.set(1, 1);
    }

    @Test
    public void testIsEmptyTrue() {
        Assert.assertTrue(testingArray.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        testingArray.add(1);
        Assert.assertFalse(testingArray.isEmpty());
    }

    @Test
    public void testContainsTrue() {
        testingArray.add(10);
        Assert.assertTrue(testingArray.contains(10));
    }

    @Test
    public void testContainsFalse() {
        Assert.assertFalse(testingArray.contains(10));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testContainsWithNullArgument() {
        testingArray.contains(null);
    }

    @Test
    public void testToArrayWithArgument() {
        Integer[] ints = new Integer[2];
        testingArray.add(1);
        testingArray.add(2);
        testingArray.toArray(ints);
        Assert.assertEquals(ints, testingArray.toArray());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testToArrayWithNullArgument() {
        testingArray.add(1);
        testingArray.toArray(null);
    }

    @Test
    public void testToArrayWithoutArgument() {
        Object[] ints = {1, 2};
        testingArray.add(1);
        testingArray.add(2);
        Assert.assertEquals(ints, testingArray.toArray());
    }

    @Test
    public void testIteratorHasNextFalse() {
        Iterator<Integer> iterator = testingArray.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextTrue() {
        testingArray.add(10);
        Iterator<Integer> iterator = testingArray.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        testingArray.add(10);
        Iterator<Integer> iterator = testingArray.iterator();
        Assert.assertEquals(iterator.next().intValue(), 10);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testIteratorNoSuchElement() {
        Iterator<Integer> iterator = testingArray.iterator();
        iterator.next();
    }
}