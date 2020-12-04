package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	private Util myUtil = new Util();

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		this.MERGESORT_APPLICATIONS = 0;
		this.INSERTIONSORT_APPLICATIONS = 0;

		this.hybridMergeInsertionSort(array, leftIndex, rightIndex);
	}

	private void hybridMergeInsertionSort(T[] array, int leftIndex, int rightIndex) {
		if ((rightIndex - leftIndex + 1) > this.SIZE_LIMIT){
			int middle = (leftIndex + rightIndex)/2;

			this.hybridMergeInsertionSort(array, leftIndex, middle);
			this.hybridMergeInsertionSort(array,middle + 1, rightIndex);

			merge(array, leftIndex, middle, rightIndex);

			this.MERGESORT_APPLICATIONS += 1;
		}else {
			this.insertion(array, leftIndex, rightIndex);
			this.INSERTIONSORT_APPLICATIONS += 1;
		}
	}

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		T[]aux = (T[]) new Comparable[array.length];
		for (int i = leftIndex; i <= rightIndex; i++ ){
			aux[i] = array[i];
		}
		int i = leftIndex;
		int j = leftIndex;
		int k = middle + 1;

		while (i <= middle && k <= rightIndex){
			if (aux[i].compareTo(aux[k])<0){
				array[j] = aux[i];
				i++;
			}else {
				array[j] = aux[k];
				k++;
			}
			j++;
		}

		while (i <= middle){
			array[j] = aux[i];
			i++;
			j++;
		}
		while (k <= rightIndex){
			array[j] = aux[k];
			k++;
			j++;
		}
	}

	private void insertion(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 1; i <= rightIndex; i++){

			int j =  i;

			while (j > leftIndex && array[j].compareTo(array[j-1]) < 0){
				myUtil.swap(array, j-1, j);
				j--;
			}
		}
	}
}
