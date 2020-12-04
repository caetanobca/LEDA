package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			int maior = Math.abs(array[leftIndex]);
			for (int a = leftIndex + 1; a <= rightIndex; a++) {
				if (maior < Math.abs(array[a])) {
					maior = Math.abs(array[a]);
				}
			}

			int[] contadores = new int[maior * 2 + 1];

			for (int i = leftIndex; i <= rightIndex; i++) {
				contadores[array[i] + maior] += 1;
			}

			for (int j = 1; j < contadores.length; j++) {
				contadores[j] += contadores[j - 1];
			}

			Integer[] aux = new Integer[rightIndex - leftIndex + 1];
			for (int k = rightIndex; k >= leftIndex; k--) {
				aux[contadores[array[k] + maior] - 1] = array[k];
				contadores[array[k] + maior] -= 1;
			}

			for (int l = 0; l < aux.length; l++) {
				array[l + leftIndex] = aux[l];
			}
		}
	}

}
