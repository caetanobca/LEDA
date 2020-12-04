package adt.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class StudentMinHeapTest {

	Heap<Integer> heap;

	@Before
	public void setUp() {
		// TODO Instancie seu comparator para fazer sua estrutura funcionar como
		// uma min heap aqui. Use instanciacao anonima da interface
		// Comparator!!!!
		Comparator<Integer> comparator = new ComparatorMinHeap<Integer>();
		heap = new HeapImpl<Integer>(comparator);
	}

	@Test
	public void testBuild() {

		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34, 64, 58, 1 });
		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		assertArrayEquals(new Integer[] {1,6,58,12,34,64,99,82}, heap.toArray());
		verifyHeap(new Integer[] { 1, 6, 58, 12, 34, 64, 99, 82 });

		heap.buildHeap(new Integer[] {31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1});
		assertEquals(31, heap.size());
		assertFalse(heap.isEmpty());
		assertArrayEquals(new Integer[] {1,9,2,13,10,5,3,15,14,11,21,7,6,4,17,16,24,28,23,12,22,27,30,8,20,26,19,31,18,25,29}, heap.toArray());
		verifyHeap(new Integer[] {1,9,2,13,10,5,3,15,14,11,21,7,6,4,17,16,24,28,23,12,22,27,30,8,20,26,19,31,18,25,29});

		heap.buildHeap(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31});
		assertEquals(31, heap.size());
		assertFalse(heap.isEmpty());
		assertArrayEquals(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}, heap.toArray());
		verifyHeap(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31});

		heap.buildHeap(new Integer[] {});
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertArrayEquals(new Integer[] {}, heap.toArray());

		heap.buildHeap(new Integer[] {3,1,0});
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertArrayEquals(new Integer[] {0,1,3}, heap.toArray());
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

		verifyHeap(new Integer[] { -10, -5, -2, 3, 0, 8, 14, 12, 7, 8 });
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

		assertEquals(new Integer(15), heap.extractRootElement());
		assertEquals(new Integer(17), heap.extractRootElement());
		assertEquals(new Integer(22), heap.extractRootElement());
		assertEquals(new Integer(26), heap.extractRootElement());
		assertEquals(new Integer(30), heap.extractRootElement());

		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 38, 40, 79, 45, 53 });
	}

	@Test
	public void testSort() {

		assertArrayEquals(new Integer[] { 5, 6, 12, 20, 34, 43, 49, 92 },
				heap.heapsort(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());

		assertArrayEquals(new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1 },
				heap.heapsort(new Integer[] { 1, 1, 1, 1, 1, 1, 1, 1 }));

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
			if (2 * i + 1 < original.length && original[i].compareTo((Integer) original[2 * i + 1]) > 0)
				isHeap = false;
			if (2 * i + 2 < original.length && original[i].compareTo((Integer) original[2 * i + 2]) > 0)
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
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3}, heap.toArray());

		heap.insert(5);
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3,5}, heap.toArray());

		heap.insert(7);
		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3,5,7}, heap.toArray());

		heap.insert(9);
		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3,5,7,9}, heap.toArray());

		heap.insert(11);
		assertEquals(6, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3,5,7,9,11}, heap.toArray());

		heap.insert(13);
		assertEquals(7, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3,5,7,9,11,13}, heap.toArray());

		heap.insert(15);
		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3,5,7,9,11,13,15}, heap.toArray());

		heap.insert(17);
		assertEquals(9, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3,5,7,9,11,13,15,17}, heap.toArray());

		heap.insert(0);
		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(0), heap.rootElement());
		assertArrayEquals(new Integer[] {0,1,5,7,3,11,13,15,17,9}, heap.toArray());


		// REMOVENDO TUDO


		heap.extractRootElement();
		assertEquals(9, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,3,5,7,9,11,13,15,17}, heap.toArray());

		heap.extractRootElement();
		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(3), heap.rootElement());
		assertArrayEquals(new Integer[] {3,7,5,15,9,11,13,17}, heap.toArray());

		heap.extractRootElement();
		assertEquals(7, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(5), heap.rootElement());
		assertArrayEquals(new Integer[] {5,7,11,15,9,17,13}, heap.toArray());

		heap.extractRootElement();
		assertEquals(6, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(7), heap.rootElement());
		assertArrayEquals(new Integer[] {7,9,11,15,13,17}, heap.toArray());

		heap.extractRootElement();
		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(9), heap.rootElement());
		assertArrayEquals(new Integer[] {9,13,11,15,17}, heap.toArray());

		heap.extractRootElement();
		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(11), heap.rootElement());
		assertArrayEquals(new Integer[] {11,13,17,15}, heap.toArray());

		heap.extractRootElement();
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(13), heap.rootElement());
		assertArrayEquals(new Integer[] {13,15,17}, heap.toArray());

		heap.insert(0);
		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(0), heap.rootElement());
		assertArrayEquals(new Integer[] {0,13,17,15}, heap.toArray());

		heap.extractRootElement();
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(13), heap.rootElement());
		assertArrayEquals(new Integer[] {13,15,17}, heap.toArray());

		heap.extractRootElement();
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(15), heap.rootElement());
		assertArrayEquals(new Integer[] {15,17}, heap.toArray());

		heap.extractRootElement();
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(17), heap.rootElement());
		assertArrayEquals(new Integer[] {17}, heap.toArray());

		heap.extractRootElement();
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {}, heap.toArray());

		heap.extractRootElement();
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {}, heap.toArray());

		heap.insert(null);
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
		assertNull(heap.rootElement());
		assertArrayEquals(new Integer[] {}, heap.toArray());

		// ADICIONANDO

//		[4, 15, 23, 25, 28, 47, 24, 75, 43, 54, 42, 19, 105, 65, 2, 10, 95, 139, 31, 111, 37, 16, 46, 26, 7, 1]
//		[26, 7]

		heap.insert(4);
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4}, heap.toArray());

		heap.insert(15);
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15}, heap.toArray());

		heap.insert(23);
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23}, heap.toArray());

		heap.insert(25);
		assertEquals(4, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23,25}, heap.toArray());

		heap.insert(28);
		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23,25,28}, heap.toArray());

		heap.insert(47);
		assertEquals(6, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23,25,28,47}, heap.toArray());

		heap.insert(24);
		assertEquals(7, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23,25,28,47,24}, heap.toArray());

		heap.insert(75);
		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23,25,28,47,24,75}, heap.toArray());

		heap.insert(43);
		assertEquals(9, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23,25,28,47,24,75,43}, heap.toArray());

		heap.insert(54);
		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23,25,28,47,24,75,43,54}, heap.toArray());

		heap.insert(42);
		assertEquals(11, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,23,25,28,47,24,75,43,54,42}, heap.toArray());

		heap.insert(19);
		assertEquals(12, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,19,25,28,23,24,75,43,54,42,47}, heap.toArray());

		heap.insert(105);
		assertEquals(13, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,19,25,28,23,24,75,43,54,42,47,105}, heap.toArray());

		heap.insert(65);
		assertEquals(14, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(4), heap.rootElement());
		assertArrayEquals(new Integer[] {4,15,19,25,28,23,24,75,43,54,42,47,105,65}, heap.toArray());

		heap.insert(2);
		assertEquals(15, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,15,4,25,28,23,19,75,43,54,42,47,105,65,24}, heap.toArray());

		heap.insert(10);
		assertEquals(16, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,28,23,19,25,43,54,42,47,105,65,24,75}, heap.toArray());

		heap.insert(95);
		assertEquals(17, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,28,23,19,25,43,54,42,47,105,65,24,75,95}, heap.toArray());

		heap.insert(139);
		assertEquals(18, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,28,23,19,25,43,54,42,47,105,65,24,75,95,139}, heap.toArray());

		heap.insert(31);
		assertEquals(19, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,28,23,19,25,31,54,42,47,105,65,24,75,95,139,43}, heap.toArray());

		heap.insert(111);
		assertEquals(20, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,28,23,19,25,31,54,42,47,105,65,24,75,95,139,43,111}, heap.toArray());

		heap.insert(37);
		assertEquals(21, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,28,23,19,25,31,37,42,47,105,65,24,75,95,139,43,111,54}, heap.toArray());

		heap.insert(16);
		assertEquals(22, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,16,23,19,25,31,37,28,47,105,65,24,75,95,139,43,111,54,42}, heap.toArray());

		heap.insert(46);
		assertEquals(23, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,16,23,19,25,31,37,28,47,105,65,24,75,95,139,43,111,54,42,46}, heap.toArray());

		heap.insert(26);
		assertEquals(24, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,16,23,19,25,31,37,28,26,105,65,24,75,95,139,43,111,54,42,46,47}, heap.toArray());

		heap.insert(7);
		assertEquals(25, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,16,7,19,25,31,37,28,23,105,65,24,75,95,139,43,111,54,42,46,47,26}, heap.toArray());

		heap.insert(1);
		assertEquals(26, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(1), heap.rootElement());
		assertArrayEquals(new Integer[] {1,10,2,15,16,4,19,25,31,37,28,23,7,65,24,75,95,139,43,111,54,42,46,47,26,105}, heap.toArray());
		verifyHeap(new Integer[] {1,10,2,15,16,4,19,25,31,37,28,23,7,65,24,75,95,139,43,111,54,42,46,47,26,105});

		assertEquals(new Integer(1), heap.extractRootElement());
		assertEquals(25, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(2), heap.rootElement());
		assertArrayEquals(new Integer[] {2,10,4,15,16,7,19,25,31,37,28,23,105,65,24,75,95,139,43,111,54,42,46,47,26}, heap.toArray());

		assertEquals(new Integer(2), heap.extractRootElement());

		assertEquals(new Integer(4), heap.extractRootElement());
		assertEquals(23, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(new Integer(7), heap.rootElement());
		assertArrayEquals(new Integer[] {7,10,19,15,16,23,24,25,31,37,28,26,105,65,47,75,95,139,43,111,54,42,46}, heap.toArray());
		verifyHeap(new Integer[] {7,10,19,15,16,23,24,25,31,37,28,26,105,65,47,75,95,139,43,111,54,42,46});
	}

}
