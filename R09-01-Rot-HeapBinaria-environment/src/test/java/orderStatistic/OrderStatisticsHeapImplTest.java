package orderStatistic;

import adt.heap.ComparatorMinHeap;
import adt.heap.extended.FloorCeilHeapImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.*;

public class OrderStatisticsHeapImplTest {


    OrderStatistics<Integer> os;

    @Before
    public void setUp() {
        os = new OrderStatisticsHeapImpl<>();
    }

    @Test
    public void testOrderStatistic() {

        assertNull(os.getOrderStatistics(new Integer[] {1,2,3,4}, -50));
        assertNull(os.getOrderStatistics(new Integer[] {1,2,3,4}, -2));
        assertNull(os.getOrderStatistics(new Integer[] {1,2,3,4}, -1));
        assertNull(os.getOrderStatistics(new Integer[] {1,2,3,4}, 0));
        assertEquals(new Integer(1), os.getOrderStatistics(new Integer[] {1,2,3,4}, 1));
        assertEquals(new Integer(2), os.getOrderStatistics(new Integer[] {1,2,3,4}, 2));
        assertEquals(new Integer(3), os.getOrderStatistics(new Integer[] {1,2,3,4}, 3));
        assertEquals(new Integer(4), os.getOrderStatistics(new Integer[] {1,2,3,4}, 4));
        assertNull(os.getOrderStatistics(new Integer[] {1,2,3,4}, 5));
        assertNull(os.getOrderStatistics(new Integer[] {1,2,3,4}, 6));
        assertNull(os.getOrderStatistics(new Integer[] {1,2,3,4}, 7));
        assertNull(os.getOrderStatistics(new Integer[] {1,2,3,4}, 50));


        assertEquals(new Integer(1), os.getOrderStatistics(new Integer[] {1,2,3,4}, 1));
        assertEquals(new Integer(1), os.getOrderStatistics(new Integer[] {4,3,2,1}, 1));
        assertEquals(new Integer(3), os.getOrderStatistics(new Integer[] {1,2,3,4}, 3));
        assertEquals(new Integer(3), os.getOrderStatistics(new Integer[] {4,3,2,1}, 3));
        assertEquals(new Integer(4), os.getOrderStatistics(new Integer[] {1,2,3,4}, 4));
        assertEquals(new Integer(4), os.getOrderStatistics(new Integer[] {4,3,2,1}, 4));
        assertEquals(new Integer(1), os.getOrderStatistics(new Integer[] {1}, 1));

        assertNull(os.getOrderStatistics(new Integer[] {1}, 0));
        assertNull(os.getOrderStatistics(new Integer[] {4,3,2,1}, 0));
        assertNull(os.getOrderStatistics(new Integer[] {1}, 2));
    }

    @Test
    public void testOrderStatisticArrayVazio() {

        assertNull(os.getOrderStatistics(new Integer[] {}, -2));
        assertNull(os.getOrderStatistics(new Integer[] {}, -1));
        assertNull(os.getOrderStatistics(new Integer[] {}, 0));
        assertNull(os.getOrderStatistics(new Integer[] {}, 1));
        assertNull(os.getOrderStatistics(new Integer[] {}, 2));


    }


    @Test
    public void testOrderStatisticRandom() {
        Random random = new Random();

        boolean comentarios = true;
        int casosDeTestes = 10;


        for (int j = 0; j < casosDeTestes; j++) {

            Integer[] arrayAux = new Integer[100];
            Integer[] arraySorted = new Integer[100];
            int randomNumber;


            for (int i = 0; i < 100; i++) {
                randomNumber = random.nextInt(1000);
                arrayAux[i] = randomNumber;
                arraySorted[i] = randomNumber;
            }

            Arrays.sort(arraySorted);
            if (comentarios) {
                System.out.println("Teste " + (j+1));
                System.out.println(Arrays.toString(arraySorted));
                System.out.println();
            }


            for (int i = 0; i < 500; i++) {

                randomNumber = random.nextInt(100);
                assertEquals(arraySorted[randomNumber], os.getOrderStatistics(arrayAux, randomNumber + 1));

                if (comentarios)
                    System.out.println("OK! " + arraySorted[randomNumber]  + " is the " + (randomNumber + 1) + " Order Statistic of array");
            }


            if (comentarios) {
                System.out.println();
                System.out.println("####################################################################################");
                System.out.println();
            }

        }
    }

}