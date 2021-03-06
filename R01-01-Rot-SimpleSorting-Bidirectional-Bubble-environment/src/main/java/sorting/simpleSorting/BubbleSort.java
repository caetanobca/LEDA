package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Util myutil = new Util();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i <= rightIndex; i++){
			boolean trocou = false;
			for (int j = leftIndex; j < rightIndex - i; j++){
				if (array[j].compareTo(array[j+1]) > 0){
					myutil.swap(array, j, j+1);
					trocou = true;
				}
			}
			if (trocou == false){
				return;
			}
		}
	}
}
