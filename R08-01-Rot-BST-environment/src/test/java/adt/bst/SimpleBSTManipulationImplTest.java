package adt.bst;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SimpleBSTManipulationImplTest extends TestCase {

    private BST<Integer> tree1 = new BSTImpl<Integer>();
    private BST<Integer> tree2 = new BSTImpl<Integer>();
    private BST<Integer> tree3 = new BSTImpl<Integer>();
    private BST<Integer> tree4 = new BSTImpl<Integer>();
    private BST<Integer> tree5 = new BSTImpl<Integer>();
    private SimpleBSTManipulation test = new SimpleBSTManipulationImpl();

    private void fillTree(BST<Integer> tree) {
        Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
        for (int i : array) {
            tree.insert(i);
        }
    }

    private void fillTreeDifferent(BST<Integer> tree) {
        Integer[] array = {23, 5, 9, 0, 6, 76, 12, 2, 67, -34, 232, -40 };
        for (int i : array) {
            tree.insert(i);
        }
    }

    private void fillTreeLastDifferent(BST<Integer> tree) {
        Integer[] array = {23, 5, 9, 0, 6, 76, 12, 2, 67, -34, 232, -4 };
        for (int i : array) {
            tree.insert(i);
        }
    }

    private void fillTreeRootDifferent(BST<Integer> tree) {
        Integer[] array = {99, 5, 9, 0, 6, 76, 12, 2, 67, -34, 232, -4 };
        for (int i : array) {
            tree.insert(i);
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
        fillTree(tree1);
        fillTree(tree2);
        fillTreeDifferent(tree3);

    }

    @Test
    public void testEquals() {
        System.out.println(Arrays.toString(tree1.preOrder()));
        System.out.println(Arrays.toString(tree2.preOrder()));
        System.out.println(Arrays.toString(tree3.preOrder()));
        assertTrue(test.equals(tree1,tree2));

        assertFalse(test.equals(tree1,tree3));
        fillTreeLastDifferent(tree2);
        System.out.println(Arrays.toString(tree2.preOrder()));
        assertFalse(test.equals(tree1,tree2));
    }

    @Test
    public void testeIsSimilar() {
        //Arvores iguias
        assertTrue(test.isSimilar(tree1, tree2));

        //Uma Arvore preenchida e outra vazia
        assertFalse(test.isSimilar(tree1, tree4));

        //Arvores preenchidas diferentes
        assertFalse(test.isSimilar(tree1, tree3));

        //Duas arvores vazias
        assertTrue(test.isSimilar(tree4, tree5));

        this.fillTreeLastDifferent(tree4);
        this.fillTreeRootDifferent(tree5);

        //removendo um elemento e comparando com mesma arvore
        BST<Integer> treeTest = new BSTImpl<Integer>();
        this.fillTreeLastDifferent(treeTest);
        treeTest.remove(0);
        assertFalse(test.isSimilar(tree4, treeTest));



    }

    @Test
    public void testOrderStatistic() {
        //k invalido
        assertNull(test.orderStatistic(tree1, 0));
        assertNull(test.orderStatistic(tree1, 13));

        //arvore vazia
        assertNull(test.orderStatistic(tree4, 1));

        //arvore 1
        assertEquals(test.orderStatistic(tree1, 1), -40);
        assertEquals(test.orderStatistic(tree1, 7), 9);
        assertEquals(test.orderStatistic(tree1, 12), 232);
        assertEquals(test.orderStatistic(tree1, 6), 6);

    }
}