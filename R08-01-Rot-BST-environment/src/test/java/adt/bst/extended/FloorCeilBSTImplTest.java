package adt.bst.extended;

import org.junit.Test;

import static org.junit.Assert.*;

public class FloorCeilBSTImplTest {


    FloorCeilBST test = new FloorCeilBSTImpl();
    Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };

    @Test
    public void testFloor() {
        assertEquals(test.floor(array, 7), new Integer(6));
        assertNull(test.floor(array,-100));
        assertEquals(test.floor(array,232), new Integer(232));
        assertEquals(test.floor(array,250), new Integer(232));
        assertEquals(test.floor(array,-40), new Integer(-40));
    }

    @Test
    public void testCeil() {
        assertEquals(test.ceil(array, 7), new Integer(9));
        assertNull(test.ceil(array,250));
        assertEquals(test.ceil(array,232), new Integer(232));
        assertEquals(test.ceil(array,-50), new Integer(-40));
        assertEquals(test.ceil(array,-40), new Integer(-40));

    }

}