package adt.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class StudentMaxHeapTest {
	Heap<Integer> heap;

	@Before
	public void setUp() {
		// TODO Instancie seu comparator para fazer sua estrutura funcionar como
		// uma max heap aqui. Use instanciacao anonima da interface
		// Comparator!!!!
		Comparator<Integer> comparator = new ComparatorMaxHeap<Integer>();
		heap = new HeapImpl<Integer>(comparator);
	}

	@Test
	public void testBuild() {
		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34, 64, 58, 1 });
		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		verifyHeap(new Integer[] { 99, 12, 82, 6, 34, 64, 58, 1 });

		heap.buildHeap(new Integer[] {31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1});
		assertEquals(31, heap.size());
		assertFalse(heap.isEmpty());
		assertArrayEquals(new Integer[] {31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1}, heap.toArray());
		verifyHeap(new Integer[] {31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1});

		heap.buildHeap(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31});
		assertEquals(31, heap.size());
		assertFalse(heap.isEmpty());
		assertArrayEquals(new Integer[] {31,23,30,19,22,27,29,17,18,21,11,25,26,28,15,16,8,4,9,20,10,5,2,24,12,6,13,1,14,7,3}, heap.toArray());
		verifyHeap(new Integer[] {31,23,30,19,22,27,29,17,18,21,11,25,26,28,15,16,8,4,9,20,10,5,2,24,12,6,13,1,14,7,3});

		heap.buildHeap(new Integer[] {});
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertArrayEquals(new Integer[] {}, heap.toArray());
		verifyHeap(new Integer[] {});

		heap.buildHeap(new Integer[] {0,1,3});
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertArrayEquals(new Integer[] {3,1,0}, heap.toArray());
		verifyHeap(new Integer[] {3,1,0});

		Integer[] biu = new Integer[] {4, 15, 23, 25, 28, 47, 24, 75, 43, 54, 42, 19, 105, 65, 2, 10, 95, 139, 31, 111, 37, 16, 46, 26, 7, 1};
		heap.buildHeap(biu);
		assertArrayEquals(new Integer[] {139,111,105,95,54,47,65,75,43,37,46,26,23,24,2,10,15,25,31,28,4,16,42,19,7,1}, heap.toArray());
		verifyHeap(new Integer[] {139,111,105,95,54,47,65,75,43,37,46,26,23,24,2,10,15,25,31,28,4,16,42,19,7,1});

	}

	@Test
	public void testInsert() {
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);

		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 14, 8, 12, 7, 8, -5, -2, 3, -10, 0 });
	}

	@Test
	public void testRemove() {
		heap.insert(22);
		heap.insert(45);
		heap.insert(38);
		heap.insert(17);
		heap.insert(40);
		heap.insert(15);
		heap.insert(26);
		heap.insert(79);
		heap.insert(53);
		heap.insert(30);

		assertEquals(new Integer(79), heap.extractRootElement());
		assertEquals(new Integer(53), heap.extractRootElement());
		assertEquals(new Integer(45), heap.extractRootElement());
		assertEquals(new Integer(40), heap.extractRootElement());
		assertEquals(new Integer(38), heap.extractRootElement());

		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 22, 17, 15, 26, 30 });
	}

	@Test
	public void testSort() {
		assertArrayEquals(new Integer[] { 5, 6, 12, 20, 34, 43, 49, 92 },
				heap.heapsort(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());
	}

	private void verifyHeap(Integer[] expected) {
		boolean isHeap = true;

		Comparable<Integer>[] original = heap.toArray();

		Arrays.sort(expected);
		Arrays.sort(original);

		if (Arrays.equals(expected, original) == false)
			isHeap = false;

		original = heap.toArray();

		for (int i = 0; i < original.length; i++) {
			if (2 * i + 1 < original.length && original[i].compareTo((Integer) original[2 * i + 1]) < 0)
				isHeap = false;
			if (2 * i + 2 < original.length && original[i].compareTo((Integer) original[2 * i + 2]) < 0)
				isHeap = false;
		}

		assertTrue(isHeap);
	}


	// MEUS TESTES


	@Test
	public void testHeapSortRandom() {
		Random random = new Random();
		int tamanhoFinal = 100;  // Testa arrays com tamanho variando de 0 a tamanhoFinal
		int qtdArraysMesmoTamanho = 5; // Quantidade de testes de arrays diferentes com mesmo tamanho

		for (int tamanho = 0; tamanho <= tamanhoFinal ; tamanho++) {

			for (int k = 0; k < qtdArraysMesmoTamanho; k++) {

				Integer[] array = new Integer[tamanho];
				Integer[] arraySorted = new Integer[tamanho];

				for (int j = 0; j < tamanho; j++) {
					int randomNumber = random.nextInt(1000);
					array[j] = randomNumber;
					arraySorted[j] = randomNumber;
				}

				Arrays.sort(arraySorted);

				assertEquals(0, heap.size());
				assertTrue(heap.isEmpty());
				assertNull(heap.extractRootElement());
				assertNull(heap.rootElement());
				assertArrayEquals(arraySorted, heap.heapsort(array));
				assertEquals(0, heap.size());
				assertTrue(heap.isEmpty());
				assertNull(heap.extractRootElement());
				assertNull(heap.rootElement());
			}
		}
	}

	@Test
	public void testHeapSortArrayElementosIguais() {
		Integer[] array1 = new Integer[] {1,1,1,1,1,1,1,1,1};
		Integer[] array2 = new Integer[] {2,2,2,2,2,2,2,2,2};
		Integer[] array3 = new Integer[] {3,3,3,3,3,3,3,3,3};
		Integer[] array4 = new Integer[] {4,4,4,4,4,4,4,4,4};
		Integer[] array5 = new Integer[] {5,5,5,5,5,5,5,5,5};

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {1,1,1,1,1,1,1,1,1}, heap.heapsort(array1));
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {2,2,2,2,2,2,2,2,2}, heap.heapsort(array2));
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {3,3,3,3,3,3,3,3,3}, heap.heapsort(array3));
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {4,4,4,4,4,4,4,4,4}, heap.heapsort(array4));
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {5,5,5,5,5,5,5,5,5}, heap.heapsort(array5));
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.extractRootElement());
		assertNull(heap.rootElement());

	}

	@Test
	public void testInsertGeral() {
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		heap.insert(1);
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1}, heap.toArray());


		heap.insert(3);
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(3), heap.rootElement());
		assertArrayEquals(new Integer[] {3,1}, heap.toArray());

		heap.insert(5);
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(5), heap.rootElement());
		assertArrayEquals(new Integer[] {5,1,3}, heap.toArray());

		heap.insert(7);
		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(7), heap.rootElement());
		assertArrayEquals(new Integer[] {7,5,3,1}, heap.toArray());

		heap.insert(9);
		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(9), heap.rootElement());
		assertArrayEquals(new Integer[] {9,7,3,1,5}, heap.toArray());

		heap.insert(11);
		assertEquals(6, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(11), heap.rootElement());
		assertArrayEquals(new Integer[] {11,7,9,1,5,3}, heap.toArray());

		heap.insert(13);
		assertEquals(7, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(13), heap.rootElement());
		assertArrayEquals(new Integer[] {13,7,11,1,5,3,9}, heap.toArray());

		heap.insert(15);
		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(15), heap.rootElement());
		assertArrayEquals(new Integer[] {15,13,11,7,5,3,9,1}, heap.toArray());

		heap.insert(17);
		assertEquals(9, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(17), heap.rootElement());
		assertArrayEquals(new Integer[] {17,15,11,13,5,3,9,1,7}, heap.toArray());

		heap.insert(0);
		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(17), heap.rootElement());
		assertArrayEquals(new Integer[] {17,15,11,13,5,3,9,1,7,0}, heap.toArray());


		// REMOVENDO TUDO


		heap.extractRootElement();
		assertEquals(9, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(15), heap.rootElement());
		assertArrayEquals(new Integer[] {15,13,11,7,5,3,9,1,0}, heap.toArray());

		heap.extractRootElement();
		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(13), heap.rootElement());
		assertArrayEquals(new Integer[] {13,7,11,1,5,3,9,0}, heap.toArray());

		heap.extractRootElement();
		assertEquals(7, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(11), heap.rootElement());
		assertArrayEquals(new Integer[] {11,7,9,1,5,3,0}, heap.toArray());

		heap.extractRootElement();
		assertEquals(6, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(9), heap.rootElement());
		assertArrayEquals(new Integer[] {9,7,3,1,5,0}, heap.toArray());

		heap.extractRootElement();
		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(7), heap.rootElement());
		assertArrayEquals(new Integer[] {7,5,3,1,0}, heap.toArray());

		heap.extractRootElement();
		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(5), heap.rootElement());
		assertArrayEquals(new Integer[] {5,1,3,0}, heap.toArray());

		heap.extractRootElement();
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(3), heap.rootElement());
		assertArrayEquals(new Integer[] {3,1,0}, heap.toArray());


		heap.insert(null);
		heap.extractRootElement();
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,0}, heap.toArray());

		heap.insert(null);
		heap.extractRootElement();
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(0), heap.rootElement());
		assertArrayEquals(new Integer[] {0}, heap.toArray());

		heap.insert(null);
		heap.extractRootElement();
		heap.insert(null);
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {}, heap.toArray());


		Integer[] biu = new Integer[] {4, 15, 23, 25, 28, 47, 24, 75, 43, 54, 42, 19, 105, 65, 2, 10, 95, 139, 31, 111, 37, 16, 46, 26, 7, 1};

		for (int i = 0; i < biu.length; i++) {
			assertEquals(i, heap.size());
			heap.insert(biu[i]);
			assertEquals(i+1, heap.size());
			assertFalse(heap.isEmpty());
		}

		assertArrayEquals(new Integer[] {139,111,75,95,105,28,65,43,54,47,46,26,19,24,2,4,10,25,31,23,37,16,42,15,7,1}, heap.toArray());
	}
}
