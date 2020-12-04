package orderStatisticAndBinarySearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import orderStatistic.KLargestOrderStatisticsImpl;
import orderStatistic.QuickSelect;
import problems.FloorBinarySearchImpl;


public class StudentSortingTest {
    private Integer[] vetorTamPar;
    private Integer[] vetorTamImpar;
    private Integer[] vetorVazio = {};
    private Integer[] vetorValoresRepetidos;
    private Integer[] vetorDecrescente;

    public QuickSelect<Integer> implementation01;
    public KLargestOrderStatisticsImpl<Integer> implementation02;
    public FloorBinarySearchImpl floorBinarySearch;
    @Before
    public void setUp() {
        populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
                31 });
        populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
                11, 18, 36 });
        populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
        populaVetorDescrescente(new Integer[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });

        getimplementation01();
    }

    // // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
    /**
     * Método que inicializa a implementação a ser testada com a implementação
     * do aluno
     */
    private void getimplementation01() {
        this.implementation01 = new QuickSelect<>();
        this.implementation02 = new KLargestOrderStatisticsImpl<>();
        this.floorBinarySearch = new FloorBinarySearchImpl();
    }

    public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
        this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
    }

    public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
        this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
    }

    public void populaVetorRepetido(Integer[] arrayPadrao) {
        this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
                arrayPadrao.length);
    }

    public void populaVetorDescrescente(Integer[] arrayPadrao) {
        this.vetorDecrescente = Arrays
                .copyOf(arrayPadrao, arrayPadrao.length);
    }

    // FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

    // MÉTODOS DE TESTE QUICK SELECT

    public void genericTest01(Integer[] array, int k) {
        Integer numberByOrder = implementation01.quickSelect(array, k);

        Arrays.sort(array);

        assertEquals(numberByOrder, array[k-1]);
    }

    // TESTES PARA VETOR TAMANHO PAR

    @Test
    public void testQuickSelectEvenArray01() {
        genericTest01(vetorTamPar, 1);
    }

    @Test
    public void testQuickSelectEvenArray02() {
        genericTest01(vetorTamPar, 2);
    }

    @Test
    public void testQuickSelecEvenArray03() {
        genericTest01(vetorTamPar, 3);
    }

    @Test
    public void testQuickSelectEvenArray04() {
        genericTest01(vetorTamPar, 8);
    }

    @Test
    public void testQuickSelectEvenArray05() {
        genericTest01(vetorTamPar, 9);
    }

    @Test
    public void testQuickSelectEvenArray06() {
        genericTest01(vetorTamPar, 10);
    }

    // TESTES PARA VETOR TAMANHO IMPAR

    @Test
    public void testQuickSelectOddArray01() {
        genericTest01(vetorTamImpar, 1);
    }

    @Test
    public void testQuickSelectOddArray02() {
        genericTest01(vetorTamImpar, 2);
    }

    @Test
    public void testQuickSelectOddArray03() {
        genericTest01(vetorTamImpar, 3);
    }

    @Test
    public void testQuickSelectOddArray04() {
        genericTest01(vetorTamImpar, 9);
    }

    @Test
    public void testQuickSelectOddArray05() {
        genericTest01(vetorTamImpar, 10);
    }

    @Test
    public void testQuickSelectOddArray06() {
        genericTest01(vetorTamImpar, 11);
    }

    // TESTES PARA VETOR COM VALORES REPETIDOS

    @Test
    public void testQuickRepeatedValuesArray01() {
        genericTest01(vetorValoresRepetidos, 4);
    }

    @Test
    public void testQuickRepeatedValuesArray02() {
        genericTest01(vetorValoresRepetidos, 5);
    }

    @Test
    public void testQuickRepeatedValuesArray03() {
        genericTest01(vetorValoresRepetidos, 6);
    }

    // TESTES PARA VETOR PIOR CASO ORDENADO DE FORMA DESCRESCENTE

    @Test
    public void testQuickSelectDescendingArray01() {
        genericTest01(vetorDecrescente, 1);
    }

    @Test
    public void testQuickSelectDescendingArray02() {
        genericTest01(vetorDecrescente, 2);
    }

    @Test
    public void testQuickSelectDescendingArray03() {
        genericTest01(vetorDecrescente, 3);
    }

    @Test
    public void testQuickSelectDescendingArray04() {
        genericTest01(vetorDecrescente, 9);
    }

    @Test
    public void testQuickSelectDescendingArray05() {
        genericTest01(vetorDecrescente, 10);
    }

    @Test
    public void testQuickSelectDescendingArray06() {
        genericTest01(vetorDecrescente, 11);
    }

    // TESTES PARA VETOR VAZIO

    @Test
    public void testQuickSelecEmptyArray01() {
        Integer numberByOrder = implementation01.quickSelect(vetorVazio, 5);

        assertEquals(numberByOrder, null);
    }

    // METODOS DE TESTE DO KLARGESTORDER

    public void genericTest02(Integer[] array, int k) {
        Integer[] arrayCopy = Arrays.copyOf(array, array.length);
        Arrays.sort(arrayCopy);
        Integer[] copyOfAnswer = new Integer[k];


        int j = 0;

        if(array.length > 0){
            copyOfAnswer = Arrays.copyOfRange(arrayCopy, arrayCopy.length-k, arrayCopy.length);
        }

        System.out.println(Arrays.toString(implementation02.getKLargest(array, k)));
        System.out.println(Arrays.toString(copyOfAnswer));
        assertArrayEquals(copyOfAnswer, implementation02.getKLargest(array, k));
    }

    //TESTES PARA O VETOR DE TAMANHO IMPAR

    @Test
    public void testGetKLargestOddArray01() {
        genericTest02(vetorTamImpar, 1);
    }

    @Test
    public void testGetKLargestOddArray02() {
        genericTest02(vetorTamImpar, 2);
    }

    @Test
    public void testGetKLargestOddArray03() {
        genericTest02(vetorTamImpar, 3);
    }

    @Test
    public void testGetKLargestOddArray04() {
        genericTest02(vetorTamImpar, vetorTamImpar.length);
    }

    //TESTES PARA VETOR DE TAMANHO PAR

    @Test
    public void testGetKLargestEvenArray01() {
        genericTest02(vetorTamPar, 1);
    }

    @Test
    public void testGetKLargestEvenArray02() {
        genericTest02(vetorTamPar, 2);
    }

    @Test
    public void testGetKLargestEvenArray03() {
        genericTest02(vetorTamPar, 3);
    }

    @Test
    public void testGetKLargestEvenArray04() {
        genericTest02(vetorTamPar, vetorTamPar.length);
    }

    // TESTES PARA VETOR COM VALORES REPETIDOS

    @Test
    public void testGetKLargestRepeatedValuesArray01() {
        genericTest01(vetorValoresRepetidos, 6);
    }

    @Test
    public void testGetKLargestRepeatedValuesArray02() {
        genericTest01(vetorValoresRepetidos, 3);
    }

    @Test
    public void testGetKLargestRepeatedValuesArray03() {
        genericTest01(vetorValoresRepetidos, vetorValoresRepetidos.length);
    }

    // TESTES PARA VETOR PIOR CASO ORDENADO DE FORMA DESCRESCENTE

    @Test
    public void testGetKLargestDescendingArray01() {
        genericTest01(vetorDecrescente, 1);
    }

    @Test
    public void testGetKLargestDescendingArray02() {
        genericTest01(vetorDecrescente, 2);
    }

    @Test
    public void testGetKLargestDescendingArray03() {
        genericTest01(vetorDecrescente, 3);
    }

    @Test
    public void testGetKLargestDescendingArray04() {
        genericTest01(vetorDecrescente, vetorDecrescente.length);
    }

    @Test
    public void test_1() {
        Integer[] array = {2,6,1,0,9};
        assertEquals(1, (int) this.floorBinarySearch.floor(array, 1));
    }

    @Test
    public void test_2() {
        Integer[] array = {2,6,1,0,9};
        assertEquals(2, (int) this.floorBinarySearch.floor(array, 3));
    }


    @Test
    public void test_3() {
        Integer[] array = {2,6,1,0,9};
        assertEquals(9, (int) this.floorBinarySearch.floor(array, 200));
    }

    @Test
    public void test_4() {
        Integer[] array = {2,6,1,0,9, 10, 12, 13, 15, 20};
        assertEquals(10, (int) this.floorBinarySearch.floor(array, 10));
    }

    @Test
    public void test_7() {
        Integer[] array = {2,6,1,9};
        assertEquals(null, this.floorBinarySearch.floor(array, 0));
    }
}
