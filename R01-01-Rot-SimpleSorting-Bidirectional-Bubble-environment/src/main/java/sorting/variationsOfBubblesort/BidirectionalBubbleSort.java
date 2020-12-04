package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

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
			for (int k = rightIndex; k > leftIndex + i; k--){
				if (array[k].compareTo(array[k-1]) < 0){
					myutil.swap(array, k, k-1);
					trocou = true;
				}
			}
			if (trocou == false){
				return;
			}
		}
	}
}
