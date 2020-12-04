package orderStatistic;

import util.Util;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 *
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 *
 * @author Adalberto
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	private Util myUtil;

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os daods em duas partes baseado no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso redua a completixade de O(n.log n) para O(n).
	 *
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 *
	 *
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento.
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 */
	public T quickSelect(T[] array, int k) {
		if (array.length < k || k < 0){
			return null;
		}

		int inicio = 0;
		int fim = array.length - 1;
		int pivotIndex = -1;
		while (pivotIndex + 1 != k && inicio >= 0 && fim <= array.length){
			pivotIndex = this.partition(array, inicio, fim);

			if (pivotIndex + 1 < k){
				inicio = pivotIndex + 1;
			}else {
				fim = pivotIndex - 1;
			}
		}

		return array[pivotIndex];


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
		int pivotIndex = leftIndex;
		T pivot = array[leftIndex];

		for (int i = leftIndex + 1; i <= rightIndex; i++){
			if (pivot.compareTo(array[i]) >= 0){
				pivotIndex ++;
				this.myUtil.swap(array, pivotIndex, i);
			}
		}

		this.myUtil.swap(array, leftIndex, pivotIndex);

		return pivotIndex;
	}
}
