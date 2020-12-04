package mytests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.closed.AbstractHashtableClosedAddress;
import adt.hashtable.closed.HashtableClosedAddressImpl;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class MyTestClosedAddressDivisionMethod {

    protected AbstractHashtableClosedAddress<Integer> table1;
    protected AbstractHashtableClosedAddress<Integer> table2;

    protected final int PROPOSED_SIZE = 100;

    @Before
    public void setUp() throws Exception {
        table1 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE,
                HashFunctionClosedAddressMethod.DIVISION);

        Integer initialValue = 200;
        int increment = 5;
        while (initialValue < 600) {
            table1.insert(initialValue);
            initialValue = initialValue + increment;
        }

        table2 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE,
                HashFunctionClosedAddressMethod.DIVISION);
    }

    @Test
    public void testInsert() {
        assertEquals(0, table1.getCOLLISIONS());
        table1.insert(105); // nao produz colisao
        assertEquals(0, table1.getCOLLISIONS());
        assertEquals(4, table1.indexOf(105));
        table1.insert(110); // nao produz colisao
        assertEquals(0, table1.getCOLLISIONS());
        assertEquals(9, table1.indexOf(110));
        table1.insert(101); // produz colisao no 0
        assertEquals(1, table1.getCOLLISIONS());
        assertEquals(0, table1.indexOf(101));

        table1.insert(610); //produz uma colisao no 4, junto com 105
        assertEquals(2, table1.getCOLLISIONS());
        assertEquals(4, table1.indexOf(610));
        assertEquals(table1.indexOf(105), table1.indexOf(610)); //Verificando se dois numeros tem o mesmo hashCode

        table1.remove(110); //Removendo todos os elementos com hash 9
        table1.insert(211); // inserindo elemento com hash 9
        assertEquals(2, table1.getCOLLISIONS()); // verificando se não houve incremento na variavel collisions
        assertEquals(9, table1.indexOf(211));



        assertTrue(table2.isEmpty());
        table2.insert(103); // nao produz colisao inserindo 1 elemento na talbe vazia
        assertEquals(0, table2.getCOLLISIONS());
        assertEquals(2, table2.indexOf(103));
    }

    @Test
    public void testRemove() {
        int currentSize = table1.size();
        table1.remove(200); // elemento existente
        assertEquals(currentSize - 1, table1.size());
        assertEquals(-1, table1.indexOf(200));

        int currentCollision = table1.getCOLLISIONS();
        assertEquals(3, table1.indexOf(205));
        table1.remove(205); // elemento existente
        assertEquals(-1, table1.indexOf(205));
        assertEquals(currentCollision, table1.getCOLLISIONS());

        //Tentando remover de uma HashTable vazia
        assertEquals(0, table2.size());
        table2.remove(300);
        assertEquals(0, table2.size());
        //Tentando remover "null" em uma lista vazia
        table1.remove(null);
        assertEquals(0, table2.size());

        //Tentando remover um elemento que não existem em uma lista não vazia
        int size1 = table1.size();
        table1.remove(603);
        assertEquals(size1, table1.size());
        //Tentando remover "null" em uma lista não vazia
        table2.remove(null);
        assertEquals(size1, table1.size());

        //Removendo um elmento que colide com outro
        table1.insert(311);
        int size2 = table1.size();
        int collision2 = table1.getCOLLISIONS();
        assertEquals(table1.indexOf(210), table1.indexOf(311));
        table1.remove(311);
        assertEquals(-1, table1.indexOf(311));
        assertEquals(size2 -1, table1.size());
        assertEquals(collision2, table1.getCOLLISIONS());

    }

    @Test
    public void testSearch() {
        // busca um elemento inexistente. compara a posicao
        assertNull(table1.search(100));
        assertEquals(-1, table1.indexOf(100));

        // busca um elemento existente. compara a posicao
        assertEquals(new Integer(305), table1.search(305));
        assertEquals(2, table1.indexOf(305));

        // busca em HashTable vazia
        assertNull(table2.search(305)); //Elemento valido
        assertNull(table1.search(null)); // null

        // busca de null em HashTable povoasda
        assertNull(table1.search(null));

        // busca de elemento não adicionado que tem o mesmo hashCode que algum já cadastrado
        assertEquals(new Integer(200), table1.search(200));
        assertNull(table1.search(402));

        // busca de elemento apos remoção
        assertEquals(new Integer(200), table1.search(200));
        table1.remove(200);
        assertNull(table1.search(200));

    }

    @Test
    public void testIsEmpty() {
        assertFalse(table1.isEmpty());

        //testando HashTable que nunca teve inserção
        assertTrue(table2.isEmpty());


        //Limpeando a table1
        Integer initialValue = 200;
        int increment = 5;
        while (initialValue < 600) {
            table1.remove(initialValue);
            initialValue = initialValue + increment;
        }
        //testando HashTable depois de remover todos os elementos
        assertTrue(table1.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(table1.isFull());

        //testando HashTable que nunca teve inserção
        assertFalse(table2.isFull());
    }

    @Test
    public void testSize() {
        assertEquals(80, table1.size());

        //testando HashTable que nunca teve inserção
        assertEquals(0, table2.size());

        //testando size apos adicionar elementos na table2
        Integer valorInicial = 100;
        int incremento = 5;
        int tamanho = 0;
        while (valorInicial < 400) {
            table2.insert(valorInicial);
            assertEquals(tamanho + 1, table2.size());
            tamanho ++;
            valorInicial = valorInicial + incremento;
        }

        //testando size apos remover elemento de table1, ate que ela fique vazia
        Integer initialValue = 200;
        int increment = 5;
        int size = 80;
        while (initialValue < 600) {
            table1.remove(initialValue);
            assertEquals(size - 1, table1.size());
            size --;
            initialValue = initialValue + increment;
        }
    }
}
