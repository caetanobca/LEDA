package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 *
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1].
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 *
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as
 * estatisticas de ordem maiores que k.
 *
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala
 *   para calcular estatisticas de ordem.
 *
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	private Util myUtil;

	@Override
	public T[] getKLargest(T[] array, int k) {

		T[] result = (T[]) new Comparable[k];

		for (int i = 0; i < k; i++){
			result[i] = this.orderStatistics(array, array.length - i);
		}

		return result;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista
	 * a k-esima estatistica de ordem, entao retorna null.
	 *
	 * Obs: o valor de k deve ser entre 1 e N.
	 *
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
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
