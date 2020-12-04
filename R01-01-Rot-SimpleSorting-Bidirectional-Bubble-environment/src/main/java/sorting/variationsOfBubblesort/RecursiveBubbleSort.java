package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RecursiveBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	Util myutil = new Util();

	/**
	 * Implementação recursiva do bubble sort. Você deve implementar apenas esse
	 * método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex > leftIndex){
			boolean trocou = false;
			for (int i = leftIndex; i < rightIndex; i++){
				if (array[i].compareTo(array[i+1]) > 0){
					this.myutil.swap(array, i, i+1);
					trocou = true;
				}
			}
			if (trocou == false){
				return;
			}
			sort(array, leftIndex, rightIndex - 1);
		}
	}

}
