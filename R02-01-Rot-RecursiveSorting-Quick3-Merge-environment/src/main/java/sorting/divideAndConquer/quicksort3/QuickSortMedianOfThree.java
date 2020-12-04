package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	private Util myUtil = new Util();

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex){
			this.medianOfThree(array, leftIndex, rightIndex);

			int pivotIndex = this.partition(array, leftIndex, rightIndex);

			this.sort(array, leftIndex, pivotIndex - 1);
			this.sort(array, pivotIndex + 1, rightIndex);
		}
	}

	/**
	 * Esse metodo é responsavel por analisar o elemento mais a esquerda, o do meio e o mais a direita. Ao final desse
	 * metodo o array deve ter no seu espaco mais a esquerda o menor desses tres elementos, no espaco mais a direita o
	 * maior e na posicao "direita - 1" o valor mediano
	 * @param array - array a ser ordenado
	 * @param leftIndex - primeiro indice que devera ser ordenado
	 * @param rightIndex - ultimo indice que devera ser ordenado
	 */
	private void medianOfThree(T[] array, int leftIndex, int rightIndex) {
		int middle = (rightIndex + leftIndex)/2;

		if (array[leftIndex].compareTo(array[rightIndex]) > 0 && array[leftIndex].compareTo(array[middle]) > 0){
			myUtil.swap(array, leftIndex, rightIndex);
		}else if(array[middle].compareTo(array[leftIndex]) > 0 && array[middle].compareTo(array[rightIndex]) > 0){
			myUtil.swap(array, middle, rightIndex);
		}
		if (array[leftIndex].compareTo(array[middle]) > 0){
			myUtil.swap(array, middle, leftIndex);
		}
		myUtil.swap(array, middle, rightIndex-1);
	}

	/**
	 * Esse metodo é responsavel por alocar todos os elementos menores q o pivot a sua esquerda e todos os maiores a
	 * sua direita
	 * @param array - array a ser ordenado
	 * @param leftIndex - primeiro indice que devera ser ordenado
	 * @param rightIndex - ultimo indice que devera ser ordenado
	 * @return indice da posicao em que o pivot ficou no final do metodo
	 */
	public int partition(T[] array, int leftIndex, int rightIndex){
		int pivotIndex = rightIndex - 1;
		T pivot = array[pivotIndex];

		for (int i = rightIndex - 2; i >= leftIndex; i--){
			if (pivot.compareTo(array[i]) <= 0){
				pivotIndex --;
				this.myUtil.swap(array, pivotIndex, i);
			}
		}

		this.myUtil.swap(array, rightIndex - 1, pivotIndex);

		return pivotIndex;
	}
}
