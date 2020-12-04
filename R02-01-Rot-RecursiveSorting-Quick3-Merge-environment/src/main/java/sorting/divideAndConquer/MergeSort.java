package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex){
			int middle = (leftIndex + rightIndex)/2;

			this.sort(array, leftIndex, middle);
			this.sort(array,middle + 1, rightIndex);

			merge(array, leftIndex, middle, rightIndex);
		}

	}

	public void merge(T[] array, int leftIndex, int middle, int rightIndex){
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
}
