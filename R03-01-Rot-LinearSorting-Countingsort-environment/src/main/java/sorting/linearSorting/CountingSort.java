package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			int maior = array[leftIndex];
			for (int a = leftIndex + 1; a <= rightIndex; a++) {
				if (maior < array[a]) {
					maior = array[a];
				}
			}

			int[] contadores = new int[maior + 1];

			for (int i = leftIndex; i <= rightIndex; i++) {
				contadores[array[i]] += 1;
			}

			for (int j = 1; j <= maior; j++) {
				contadores[j] += contadores[j - 1];
			}

			Integer[] aux = new Integer[rightIndex - leftIndex + 1];
			for (int k = rightIndex; k >= leftIndex; k--) {
				aux[contadores[array[k]] - 1] = array[k];
				contadores[array[k]] -= 1;
			}

			for (int l = 0; l < aux.length; l++) {
				array[l + leftIndex] = aux[l];
			}
		}

	}

}
