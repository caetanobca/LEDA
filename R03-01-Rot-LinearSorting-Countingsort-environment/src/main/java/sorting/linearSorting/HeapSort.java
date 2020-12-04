package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

public class HeapSort  extends AbstractSorting<Integer> {
    private Util myUtil;

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
        this.sorting(array);

    }


    public void sorting(Integer[] array){
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
        int folhaEsquerda = 2*i + 1;
        int folhaDireita = 2*i + 2;

        if (array[folhaEsquerda] > array[indiceMaior] && folhaEsquerda < n){
            indiceMaior = folhaEsquerda;
        }

        if (array[folhaDireita] > array[indiceMaior] && folhaDireita < n){
            indiceMaior = folhaDireita;
        }

        myUtil.swap(array, i, indiceMaior);
        if (indiceMaior != i){
            heapify(array, n, indiceMaior);
        }
    }
}
