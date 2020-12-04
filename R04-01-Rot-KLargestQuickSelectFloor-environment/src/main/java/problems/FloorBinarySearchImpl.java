package problems;

import util.Util;


public class FloorBinarySearchImpl implements Floor {

	Util myUtil = new Util();


	@Override
	public Integer floor(Integer[] array, Integer x) {
		this.sort(array);

		int indiceFloor = this.buscaBinaria(array, x, 0, array.length - 1);
		if (indiceFloor >= 0){
			return array[indiceFloor];
		}else {
			return null;
		}
	}

	private Integer buscaBinaria(Integer[] array, Integer x, int left, int right) {
		if (left <= right) {
			int meio = (left + right) / 2;
			if (x == array[meio]) {
				return meio;

			} else if (x < array[meio] && meio > 0) {
				if (x > array[meio - 1]) {
					return meio - 1;
				}

				return buscaBinaria(array, x, left, meio - 1);

			} else {
				if (meio == array.length - 1) {
					return meio;
				}

				return buscaBinaria(array, x, meio + 1, right);
			}
		}

		return -1;
	}

	/**
	 * Metodo que ordena o array a partir do algoritmo heapSort que tem custo de O(n.log n) e é in-place
	 * @param array
	 */
	private void sort(Integer[] array) {
		int meio = array.length / 2;

		for (int i = meio - 1; i >= 0; i--)
			heapify(array, array.length, i);

		for (int j = array.length - 1; j > 0; j--){
			myUtil.swap(array, 0, j);

			heapify(array, j, 0);
		}
	}

	void heapify(Integer[] array, int n, int i) {
		int indiceMaior = i;
		int folhaEsquerda = 2 * i + 1;
		int folhaDireita = 2 * i + 2;

		if (folhaEsquerda < n && array[folhaEsquerda] > array[indiceMaior]){
			indiceMaior = folhaEsquerda;
		}

		if (folhaDireita < n && array[folhaDireita] > array[indiceMaior]){
			indiceMaior = folhaDireita;
		}

		myUtil.swap(array, i, indiceMaior);
		if (indiceMaior != i){
			heapify(array, n, indiceMaior);
		}
	}
}
