package adt.heap.extended;

import adt.heap.ComparatorMaxHeap;
import adt.heap.ComparatorMinHeap;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FloorCeilHeapImplTest {
    FloorCeilHeap heapMin;
    FloorCeilHeap heapMax;

    @Before
    public void setUp() {
        Comparator<Integer> comparatorMin = new ComparatorMinHeap<Integer>();
        Comparator<Integer> comparatorMax = new ComparatorMaxHeap<Integer>();
        heapMin = new FloorCeilHeapImpl(comparatorMin);
        heapMax = new FloorCeilHeapImpl(comparatorMax);
    }

    @Test
    public void testFloor() {
        Integer[] array = new Integer[] { -10, -5, -2, 3, 0, 8, 14, 12, 7, 8 };

        assertNotNull(heapMin.floor(array, -10));
        assertNull(heapMin.floor(array, -11));
        assertNull(heapMin.floor(array, -12));
        assertNull(heapMin.floor(array, -13));
        assertNull(heapMin.floor(array, -100));
        assertNull(heapMin.floor(array, -200));

        assertEquals(new Integer(-10), heapMin.floor(array, -10));
        assertEquals(new Integer(-10), heapMin.floor(array, -9));
        assertEquals(new Integer(-10), heapMin.floor(array, -8));
        assertEquals(new Integer(-10), heapMin.floor(array, -7));
        assertEquals(new Integer(-10), heapMin.floor(array, -6));
        assertEquals(new Integer(-5), heapMin.floor(array, -5));
        assertEquals(new Integer(-5), heapMin.floor(array, -4));
        assertEquals(new Integer(-5), heapMin.floor(array, -3));
        assertEquals(new Integer(-2), heapMin.floor(array, -2));
        assertEquals(new Integer(-2), heapMin.floor(array, -1));
        assertEquals(new Integer(0), heapMin.floor(array, 0));
        assertEquals(new Integer(0), heapMin.floor(array, 1));
        assertEquals(new Integer(0), heapMin.floor(array, 2));
        assertEquals(new Integer(3), heapMin.floor(array, 3));
        assertEquals(new Integer(3), heapMin.floor(array, 4));
        assertEquals(new Integer(3), heapMin.floor(array, 5));
        assertEquals(new Integer(3), heapMin.floor(array, 6));
        assertEquals(new Integer(7), heapMin.floor(array, 7));
        assertEquals(new Integer(8), heapMin.floor(array, 8));
        assertEquals(new Integer(8), heapMin.floor(array, 9));
        assertEquals(new Integer(8), heapMin.floor(array, 10));
        assertEquals(new Integer(8), heapMin.floor(array, 11));
        assertEquals(new Integer(12), heapMin.floor(array, 12));
        assertEquals(new Integer(12), heapMin.floor(array, 13));
        assertEquals(new Integer(14), heapMin.floor(array, 14));

        assertEquals(new Integer(14), heapMin.floor(array, 15));
        assertEquals(new Integer(14), heapMin.floor(array, 16));
        assertEquals(new Integer(14), heapMin.floor(array, 17));
        assertEquals(new Integer(14), heapMin.floor(array, 18));
        assertEquals(new Integer(14), heapMin.floor(array, 19));
        assertEquals(new Integer(14), heapMin.floor(array, 50));
        assertEquals(new Integer(14), heapMin.floor(array, 100));
        assertEquals(new Integer(14), heapMin.floor(array, 1000));
    }

    @Test
    public void testFloorArrayElementosIguais() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

        assertNull(heapMin.floor(array, -100));
        assertNull(heapMin.floor(array, -1));
        assertNull(heapMin.floor(array, 0));
        assertNull(heapMin.floor(array, 1));
        assertNull(heapMin.floor(array, 2));
        assertNull(heapMin.floor(array, 3));
        assertNull(heapMin.floor(array, 4));
        assertNotNull(heapMin.floor(array, 5));

        assertEquals(new Integer(5), heapMin.floor(array, 5));
        assertEquals(new Integer(5), heapMin.floor(array, 6));
        assertEquals(new Integer(5), heapMin.floor(array, 7));
        assertEquals(new Integer(5), heapMin.floor(array, 8));
        assertEquals(new Integer(5), heapMin.floor(array, 9));
        assertEquals(new Integer(5), heapMin.floor(array, 10));
        assertEquals(new Integer(5), heapMin.floor(array, 11));
        assertEquals(new Integer(5), heapMin.floor(array, 12));
        assertEquals(new Integer(5), heapMin.floor(array, 5));
    }

    @Test
    public void testFloorUmElementoDiferenteMenor() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 3 };

        assertNull(heapMin.floor(array, -100));
        assertNull(heapMin.floor(array, -1));
        assertNull(heapMin.floor(array, 0));
        assertNull(heapMin.floor(array, 1));
        assertNull(heapMin.floor(array, 2));
        assertNotNull(heapMin.floor(array, 3));
        assertNotNull(heapMin.floor(array, 5));

        assertEquals(new Integer(3), heapMin.floor(array, 3));
        assertEquals(new Integer(3), heapMin.floor(array, 4));
        assertEquals(new Integer(5), heapMin.floor(array, 5));
        assertEquals(new Integer(5), heapMin.floor(array, 6));
        assertEquals(new Integer(5), heapMin.floor(array, 7));
        assertEquals(new Integer(5), heapMin.floor(array, 8));
        assertEquals(new Integer(5), heapMin.floor(array, 9));
        assertEquals(new Integer(5), heapMin.floor(array, 10));
        assertEquals(new Integer(5), heapMin.floor(array, 11));
        assertEquals(new Integer(5), heapMin.floor(array, 12));
        assertEquals(new Integer(5), heapMin.floor(array, 5));
    }

    @Test
    public void testFloorUmElementoDiferenteMaior() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 12 };

        assertNull(heapMin.floor(array, -100));
        assertNull(heapMin.floor(array, -1));
        assertNull(heapMin.floor(array, 0));
        assertNull(heapMin.floor(array, 1));
        assertNull(heapMin.floor(array, 2));
        assertNotNull(heapMin.floor(array, 5));

        assertEquals(new Integer(5), heapMin.floor(array, 5));
        assertEquals(new Integer(5), heapMin.floor(array, 6));
        assertEquals(new Integer(5), heapMin.floor(array, 7));
        assertEquals(new Integer(5), heapMin.floor(array, 8));
        assertEquals(new Integer(5), heapMin.floor(array, 9));
        assertEquals(new Integer(5), heapMin.floor(array, 10));
        assertEquals(new Integer(5), heapMin.floor(array, 11));
        assertEquals(new Integer(12), heapMin.floor(array, 12));
        assertEquals(new Integer(12), heapMin.floor(array, 13));
        assertEquals(new Integer(12), heapMin.floor(array, 14));
        assertEquals(new Integer(12), heapMin.floor(array, 15));
        assertEquals(new Integer(12), heapMin.floor(array, 16));
        assertEquals(new Integer(12), heapMin.floor(array, 17));
        assertEquals(new Integer(12), heapMin.floor(array, 50));
        assertEquals(new Integer(12), heapMin.floor(array, 100));
        assertEquals(new Integer(12), heapMin.floor(array, 200));
        assertEquals(new Integer(12), heapMin.floor(array, 1000));
    }

    @Test
    public void testFloorArrayVazio() {
        Integer[] array = new Integer[] {};

        assertNull(heapMin.floor(array, -1));
        assertNull(heapMin.floor(array, 0));
        assertNull(heapMin.floor(array, 1));
        assertNull(heapMin.floor(array, 10));
    }

    // ##### TESTES DO CEIL ##### \\

    @Test
    public void testCeil() {
        Integer[] array = new Integer[] { -10, -5, -2, 3, 0, 8, 14, 12, 7, 8 };

        assertEquals(new Integer(-10), heapMin.ceil(array, -200));
        assertEquals(new Integer(-10), heapMin.ceil(array, -100));
        assertEquals(new Integer(-10), heapMin.ceil(array, -50));
        assertEquals(new Integer(-10), heapMin.ceil(array, -14));
        assertEquals(new Integer(-10), heapMin.ceil(array, -13));
        assertEquals(new Integer(-10), heapMin.ceil(array, -12));
        assertEquals(new Integer(-10), heapMin.ceil(array, -11));

        assertEquals(new Integer(-10), heapMin.ceil(array, -10));
        assertEquals(new Integer(-5), heapMin.ceil(array, -9));
        assertEquals(new Integer(-5), heapMin.ceil(array, -8));
        assertEquals(new Integer(-5), heapMin.ceil(array, -7));
        assertEquals(new Integer(-5), heapMin.ceil(array, -6));
        assertEquals(new Integer(-5), heapMin.ceil(array, -5));
        assertEquals(new Integer(-2), heapMin.ceil(array, -4));
        assertEquals(new Integer(-2), heapMin.ceil(array, -3));
        assertEquals(new Integer(-2), heapMin.ceil(array, -2));
        assertEquals(new Integer(0), heapMin.ceil(array, -1));
        assertEquals(new Integer(0), heapMin.ceil(array, 0));
        assertEquals(new Integer(3), heapMin.ceil(array, 1));
        assertEquals(new Integer(3), heapMin.ceil(array, 2));
        assertEquals(new Integer(3), heapMin.ceil(array, 3));
        assertEquals(new Integer(7), heapMin.ceil(array, 4));
        assertEquals(new Integer(7), heapMin.ceil(array, 5));
        assertEquals(new Integer(7), heapMin.ceil(array, 6));
        assertEquals(new Integer(7), heapMin.ceil(array, 7));
        assertEquals(new Integer(8), heapMin.ceil(array, 8));
        assertEquals(new Integer(12), heapMin.ceil(array, 9));
        assertEquals(new Integer(12), heapMin.ceil(array, 10));
        assertEquals(new Integer(12), heapMin.ceil(array, 11));
        assertEquals(new Integer(12), heapMin.ceil(array, 12));
        assertEquals(new Integer(14), heapMin.ceil(array, 13));
        assertEquals(new Integer(14), heapMin.ceil(array, 14));

        assertNotNull(heapMin.ceil(array, 14));
        assertNull(heapMin.ceil(array, 15));
        assertNull(heapMin.ceil(array, 16));
        assertNull(heapMin.ceil(array, 17));
        assertNull(heapMin.ceil(array, 18));
        assertNull(heapMin.ceil(array, 50));
        assertNull(heapMin.ceil(array, 100));

    }

    @Test
    public void testCeilArrayElementosIguais() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

        assertEquals(new Integer(5), heapMin.ceil(array, -200));
        assertEquals(new Integer(5), heapMin.ceil(array, -100));
        assertEquals(new Integer(5), heapMin.ceil(array, -50));
        assertEquals(new Integer(5), heapMin.ceil(array, -1));
        assertEquals(new Integer(5), heapMin.ceil(array, 0));
        assertEquals(new Integer(5), heapMin.ceil(array, 1));
        assertEquals(new Integer(5), heapMin.ceil(array, 2));
        assertEquals(new Integer(5), heapMin.ceil(array, 3));
        assertEquals(new Integer(5), heapMin.ceil(array, 4));
        assertEquals(new Integer(5), heapMin.ceil(array, 5));

        assertNull(heapMin.ceil(array, 6));
        assertNull(heapMin.ceil(array, 7));
        assertNull(heapMin.ceil(array, 8));
        assertNull(heapMin.ceil(array, 9));
        assertNull(heapMin.ceil(array, 10));
        assertNull(heapMin.ceil(array, 50));
        assertNull(heapMin.ceil(array, 100));
        assertNull(heapMin.ceil(array, 200));
        assertNull(heapMin.ceil(array, 1000));
    }

    @Test
    public void testCeilUmElementoDiferenteMaior() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 12 };

        assertEquals(new Integer(5), heapMin.ceil(array, -200));
        assertEquals(new Integer(5), heapMin.ceil(array, -100));
        assertEquals(new Integer(5), heapMin.ceil(array, -50));
        assertEquals(new Integer(5), heapMin.ceil(array, -1));
        assertEquals(new Integer(5), heapMin.ceil(array, 0));
        assertEquals(new Integer(5), heapMin.ceil(array, 1));
        assertEquals(new Integer(5), heapMin.ceil(array, 2));
        assertEquals(new Integer(5), heapMin.ceil(array, 3));
        assertEquals(new Integer(5), heapMin.ceil(array, 4));
        assertEquals(new Integer(5), heapMin.ceil(array, 5));
        assertEquals(new Integer(12), heapMin.ceil(array, 6));
        assertEquals(new Integer(12), heapMin.ceil(array, 7));
        assertEquals(new Integer(12), heapMin.ceil(array, 8));
        assertEquals(new Integer(12), heapMin.ceil(array, 9));
        assertEquals(new Integer(12), heapMin.ceil(array, 10));
        assertEquals(new Integer(12), heapMin.ceil(array, 11));
        assertEquals(new Integer(12), heapMin.ceil(array, 12));

        assertNull(heapMin.ceil(array, 13));
        assertNull(heapMin.ceil(array, 14));
        assertNull(heapMin.ceil(array, 15));
        assertNull(heapMin.ceil(array, 16));
        assertNull(heapMin.ceil(array, 17));
        assertNull(heapMin.ceil(array, 50));
        assertNull(heapMin.ceil(array, 100));
        assertNull(heapMin.ceil(array, 200));
        assertNull(heapMin.ceil(array, 1000));
    }

    @Test
    public void testCeilUmElementosDiferenteMenor() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 3 };

        assertEquals(new Integer(3), heapMin.ceil(array, -200));
        assertEquals(new Integer(3), heapMin.ceil(array, -100));
        assertEquals(new Integer(3), heapMin.ceil(array, -50));
        assertEquals(new Integer(3), heapMin.ceil(array, -1));
        assertEquals(new Integer(3), heapMin.ceil(array, 0));
        assertEquals(new Integer(3), heapMin.ceil(array, 1));
        assertEquals(new Integer(3), heapMin.ceil(array, 2));
        assertEquals(new Integer(3), heapMin.ceil(array, 3));
        assertEquals(new Integer(5), heapMin.ceil(array, 4));
        assertEquals(new Integer(5), heapMin.ceil(array, 5));

        assertNull(heapMin.ceil(array, 6));
        assertNull(heapMin.ceil(array, 7));
        assertNull(heapMin.ceil(array, 8));
        assertNull(heapMin.ceil(array, 9));
        assertNull(heapMin.ceil(array, 10));
        assertNull(heapMin.ceil(array, 50));
        assertNull(heapMin.ceil(array, 100));
        assertNull(heapMin.ceil(array, 200));
        assertNull(heapMin.ceil(array, 1000));
    }

    @Test
    public void testCeilArrayVazio() {
        Integer[] array = new Integer[] {};

        assertNull(heapMin.ceil(array, -1));
        assertNull(heapMin.ceil(array, 0));
        assertNull(heapMin.ceil(array, 1));
        assertNull(heapMin.ceil(array, 10));

    }


    // TESTES DA MAX HEAP



    @Test
    public void testFloor1() {
        Integer[] array = new Integer[] { -10, -5, -2, 3, 0, 8, 14, 12, 7, 8 };

        assertNotNull(heapMax.floor(array, -10));
        assertNull(heapMax.floor(array, -11));
        assertNull(heapMax.floor(array, -12));
        assertNull(heapMax.floor(array, -13));
        assertNull(heapMax.floor(array, -100));
        assertNull(heapMax.floor(array, -200));

        assertEquals(new Integer(-10), heapMax.floor(array, -10));
        assertEquals(new Integer(-10), heapMax.floor(array, -9));
        assertEquals(new Integer(-10), heapMax.floor(array, -8));
        assertEquals(new Integer(-10), heapMax.floor(array, -7));
        assertEquals(new Integer(-10), heapMax.floor(array, -6));
        assertEquals(new Integer(-5), heapMax.floor(array, -5));
        assertEquals(new Integer(-5), heapMax.floor(array, -4));
        assertEquals(new Integer(-5), heapMax.floor(array, -3));
        assertEquals(new Integer(-2), heapMax.floor(array, -2));
        assertEquals(new Integer(-2), heapMax.floor(array, -1));
        assertEquals(new Integer(0), heapMax.floor(array, 0));
        assertEquals(new Integer(0), heapMax.floor(array, 1));
        assertEquals(new Integer(0), heapMax.floor(array, 2));
        assertEquals(new Integer(3), heapMax.floor(array, 3));
        assertEquals(new Integer(3), heapMax.floor(array, 4));
        assertEquals(new Integer(3), heapMax.floor(array, 5));
        assertEquals(new Integer(3), heapMax.floor(array, 6));
        assertEquals(new Integer(7), heapMax.floor(array, 7));
        assertEquals(new Integer(8), heapMax.floor(array, 8));
        assertEquals(new Integer(8), heapMax.floor(array, 9));
        assertEquals(new Integer(8), heapMax.floor(array, 10));
        assertEquals(new Integer(8), heapMax.floor(array, 11));
        assertEquals(new Integer(12), heapMax.floor(array, 12));
        assertEquals(new Integer(12), heapMax.floor(array, 13));
        assertEquals(new Integer(14), heapMax.floor(array, 14));

        assertEquals(new Integer(14), heapMax.floor(array, 15));
        assertEquals(new Integer(14), heapMax.floor(array, 16));
        assertEquals(new Integer(14), heapMax.floor(array, 17));
        assertEquals(new Integer(14), heapMax.floor(array, 18));
        assertEquals(new Integer(14), heapMax.floor(array, 19));
        assertEquals(new Integer(14), heapMax.floor(array, 50));
        assertEquals(new Integer(14), heapMax.floor(array, 100));
        assertEquals(new Integer(14), heapMax.floor(array, 1000));
    }

    @Test
    public void testFloorArrayElementosIguais1() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

        assertNull(heapMax.floor(array, -100));
        assertNull(heapMax.floor(array, -1));
        assertNull(heapMax.floor(array, 0));
        assertNull(heapMax.floor(array, 1));
        assertNull(heapMax.floor(array, 2));
        assertNull(heapMax.floor(array, 3));
        assertNull(heapMax.floor(array, 4));
        assertNotNull(heapMax.floor(array, 5));

        assertEquals(new Integer(5), heapMax.floor(array, 5));
        assertEquals(new Integer(5), heapMax.floor(array, 6));
        assertEquals(new Integer(5), heapMax.floor(array, 7));
        assertEquals(new Integer(5), heapMax.floor(array, 8));
        assertEquals(new Integer(5), heapMax.floor(array, 9));
        assertEquals(new Integer(5), heapMax.floor(array, 10));
        assertEquals(new Integer(5), heapMax.floor(array, 11));
        assertEquals(new Integer(5), heapMax.floor(array, 12));
        assertEquals(new Integer(5), heapMax.floor(array, 5));
    }

    @Test
    public void testFloorUmElementoDiferenteMenor1() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 3 };

        assertNull(heapMax.floor(array, -100));
        assertNull(heapMax.floor(array, -1));
        assertNull(heapMax.floor(array, 0));
        assertNull(heapMax.floor(array, 1));
        assertNull(heapMax.floor(array, 2));
        assertNotNull(heapMax.floor(array, 3));
        assertNotNull(heapMax.floor(array, 5));

        assertEquals(new Integer(3), heapMax.floor(array, 3));
        assertEquals(new Integer(3), heapMax.floor(array, 4));
        assertEquals(new Integer(5), heapMax.floor(array, 5));
        assertEquals(new Integer(5), heapMax.floor(array, 6));
        assertEquals(new Integer(5), heapMax.floor(array, 7));
        assertEquals(new Integer(5), heapMax.floor(array, 8));
        assertEquals(new Integer(5), heapMax.floor(array, 9));
        assertEquals(new Integer(5), heapMax.floor(array, 10));
        assertEquals(new Integer(5), heapMax.floor(array, 11));
        assertEquals(new Integer(5), heapMax.floor(array, 12));
        assertEquals(new Integer(5), heapMax.floor(array, 5));
    }

    @Test
    public void testFloorUmElementoDiferenteMaior1() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 12 };

        assertNull(heapMax.floor(array, -100));
        assertNull(heapMax.floor(array, -1));
        assertNull(heapMax.floor(array, 0));
        assertNull(heapMax.floor(array, 1));
        assertNull(heapMax.floor(array, 2));
        assertNotNull(heapMax.floor(array, 5));

        assertEquals(new Integer(5), heapMax.floor(array, 5));
        assertEquals(new Integer(5), heapMax.floor(array, 6));
        assertEquals(new Integer(5), heapMax.floor(array, 7));
        assertEquals(new Integer(5), heapMax.floor(array, 8));
        assertEquals(new Integer(5), heapMax.floor(array, 9));
        assertEquals(new Integer(5), heapMax.floor(array, 10));
        assertEquals(new Integer(5), heapMax.floor(array, 11));
        assertEquals(new Integer(12), heapMax.floor(array, 12));
        assertEquals(new Integer(12), heapMax.floor(array, 13));
        assertEquals(new Integer(12), heapMax.floor(array, 14));
        assertEquals(new Integer(12), heapMax.floor(array, 15));
        assertEquals(new Integer(12), heapMax.floor(array, 16));
        assertEquals(new Integer(12), heapMax.floor(array, 17));
        assertEquals(new Integer(12), heapMax.floor(array, 50));
        assertEquals(new Integer(12), heapMax.floor(array, 100));
        assertEquals(new Integer(12), heapMax.floor(array, 200));
        assertEquals(new Integer(12), heapMax.floor(array, 1000));
    }

    @Test
    public void testFloorArrayVazio1() {
        Integer[] array = new Integer[] {};

        assertNull(heapMax.floor(array, -1));
        assertNull(heapMax.floor(array, 0));
        assertNull(heapMax.floor(array, 1));
        assertNull(heapMax.floor(array, 10));
    }

    // ##### TESTES DO CEIL ##### \\

    @Test
    public void testCeil1() {
        Integer[] array = new Integer[] { -10, -5, -2, 3, 0, 8, 14, 12, 7, 8 };

        assertEquals(new Integer(-10), heapMax.ceil(array, -200));
        assertEquals(new Integer(-10), heapMax.ceil(array, -100));
        assertEquals(new Integer(-10), heapMax.ceil(array, -50));
        assertEquals(new Integer(-10), heapMax.ceil(array, -14));
        assertEquals(new Integer(-10), heapMax.ceil(array, -13));
        assertEquals(new Integer(-10), heapMax.ceil(array, -12));
        assertEquals(new Integer(-10), heapMax.ceil(array, -11));

        assertEquals(new Integer(-10), heapMax.ceil(array, -10));
        assertEquals(new Integer(-5), heapMax.ceil(array, -9));
        assertEquals(new Integer(-5), heapMax.ceil(array, -8));
        assertEquals(new Integer(-5), heapMax.ceil(array, -7));
        assertEquals(new Integer(-5), heapMax.ceil(array, -6));
        assertEquals(new Integer(-5), heapMax.ceil(array, -5));
        assertEquals(new Integer(-2), heapMax.ceil(array, -4));
        assertEquals(new Integer(-2), heapMax.ceil(array, -3));
        assertEquals(new Integer(-2), heapMax.ceil(array, -2));
        assertEquals(new Integer(0), heapMax.ceil(array, -1));
        assertEquals(new Integer(0), heapMax.ceil(array, 0));
        assertEquals(new Integer(3), heapMax.ceil(array, 1));
        assertEquals(new Integer(3), heapMax.ceil(array, 2));
        assertEquals(new Integer(3), heapMax.ceil(array, 3));
        assertEquals(new Integer(7), heapMax.ceil(array, 4));
        assertEquals(new Integer(7), heapMax.ceil(array, 5));
        assertEquals(new Integer(7), heapMax.ceil(array, 6));
        assertEquals(new Integer(7), heapMax.ceil(array, 7));
        assertEquals(new Integer(8), heapMax.ceil(array, 8));
        assertEquals(new Integer(12), heapMax.ceil(array, 9));
        assertEquals(new Integer(12), heapMax.ceil(array, 10));
        assertEquals(new Integer(12), heapMax.ceil(array, 11));
        assertEquals(new Integer(12), heapMax.ceil(array, 12));
        assertEquals(new Integer(14), heapMax.ceil(array, 13));
        assertEquals(new Integer(14), heapMax.ceil(array, 14));

        assertNotNull(heapMax.ceil(array, 14));
        assertNull(heapMax.ceil(array, 15));
        assertNull(heapMax.ceil(array, 16));
        assertNull(heapMax.ceil(array, 17));
        assertNull(heapMax.ceil(array, 18));
        assertNull(heapMax.ceil(array, 50));
        assertNull(heapMax.ceil(array, 100));

    }

    @Test
    public void testCeilArrayElementosIguais1() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

        assertEquals(new Integer(5), heapMax.ceil(array, -200));
        assertEquals(new Integer(5), heapMax.ceil(array, -100));
        assertEquals(new Integer(5), heapMax.ceil(array, -50));
        assertEquals(new Integer(5), heapMax.ceil(array, -1));
        assertEquals(new Integer(5), heapMax.ceil(array, 0));
        assertEquals(new Integer(5), heapMax.ceil(array, 1));
        assertEquals(new Integer(5), heapMax.ceil(array, 2));
        assertEquals(new Integer(5), heapMax.ceil(array, 3));
        assertEquals(new Integer(5), heapMax.ceil(array, 4));
        assertEquals(new Integer(5), heapMax.ceil(array, 5));

        assertNull(heapMax.ceil(array, 6));
        assertNull(heapMax.ceil(array, 7));
        assertNull(heapMax.ceil(array, 8));
        assertNull(heapMax.ceil(array, 9));
        assertNull(heapMax.ceil(array, 10));
        assertNull(heapMax.ceil(array, 50));
        assertNull(heapMax.ceil(array, 100));
        assertNull(heapMax.ceil(array, 200));
        assertNull(heapMax.ceil(array, 1000));
    }

    @Test
    public void testCeilUmElementoDiferenteMaior1() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 12 };

        assertEquals(new Integer(5), heapMax.ceil(array, -200));
        assertEquals(new Integer(5), heapMax.ceil(array, -100));
        assertEquals(new Integer(5), heapMax.ceil(array, -50));
        assertEquals(new Integer(5), heapMax.ceil(array, -1));
        assertEquals(new Integer(5), heapMax.ceil(array, 0));
        assertEquals(new Integer(5), heapMax.ceil(array, 1));
        assertEquals(new Integer(5), heapMax.ceil(array, 2));
        assertEquals(new Integer(5), heapMax.ceil(array, 3));
        assertEquals(new Integer(5), heapMax.ceil(array, 4));
        assertEquals(new Integer(5), heapMax.ceil(array, 5));
        assertEquals(new Integer(12), heapMax.ceil(array, 6));
        assertEquals(new Integer(12), heapMax.ceil(array, 7));
        assertEquals(new Integer(12), heapMax.ceil(array, 8));
        assertEquals(new Integer(12), heapMax.ceil(array, 9));
        assertEquals(new Integer(12), heapMax.ceil(array, 10));
        assertEquals(new Integer(12), heapMax.ceil(array, 11));
        assertEquals(new Integer(12), heapMax.ceil(array, 12));

        assertNull(heapMax.ceil(array, 13));
        assertNull(heapMax.ceil(array, 14));
        assertNull(heapMax.ceil(array, 15));
        assertNull(heapMax.ceil(array, 16));
        assertNull(heapMax.ceil(array, 17));
        assertNull(heapMax.ceil(array, 50));
        assertNull(heapMax.ceil(array, 100));
        assertNull(heapMax.ceil(array, 200));
        assertNull(heapMax.ceil(array, 1000));
    }

    @Test
    public void testCeilUmElementosDiferenteMenor1() {
        Integer[] array = new Integer[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 3 };
        assertEquals(new Integer(3), heapMax.ceil(array, -200));
        assertEquals(new Integer(3), heapMax.ceil(array, -100));
        assertEquals(new Integer(3), heapMax.ceil(array, -50));
        assertEquals(new Integer(3), heapMax.ceil(array, -1));
        assertEquals(new Integer(3), heapMax.ceil(array, 0));
        assertEquals(new Integer(3), heapMax.ceil(array, 1));
        assertEquals(new Integer(3), heapMax.ceil(array, 2));
        assertEquals(new Integer(3), heapMax.ceil(array, 3));
        assertEquals(new Integer(5), heapMax.ceil(array, 4));
        assertEquals(new Integer(5), heapMax.ceil(array, 5));

        assertNull(heapMax.ceil(array, 6));
        assertNull(heapMax.ceil(array, 7));
        assertNull(heapMax.ceil(array, 8));
        assertNull(heapMax.ceil(array, 9));
        assertNull(heapMax.ceil(array, 10));
        assertNull(heapMax.ceil(array, 50));
        assertNull(heapMax.ceil(array, 100));
        assertNull(heapMax.ceil(array, 200));
        assertNull(heapMax.ceil(array, 1000));
    }

    @Test
    public void testCeilArrayVazio1() {
        Integer[] array = new Integer[] {};

        assertNull(heapMax.ceil(array, -1));
        assertNull(heapMax.ceil(array, 0));
        assertNull(heapMax.ceil(array, 1));
        assertNull(heapMax.ceil(array, 10));

    }
}