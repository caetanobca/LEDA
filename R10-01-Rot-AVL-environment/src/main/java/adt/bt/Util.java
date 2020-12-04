package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		BSTNode<T> leftPivot = (BSTNode<T>) pivot.getLeft();
		BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

		if (!parentNode.isEmpty()) {
			if (parentNode.getLeft().equals(node)) parentNode.setLeft(pivot);
			else parentNode.setRight(pivot);
		}

		pivot.setParent(parentNode);
		pivot.setLeft(node);
		node.setParent(pivot);
		node.setRight(leftPivot);
		leftPivot.setParent(node);

		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		BSTNode<T> rightPivot = (BSTNode<T>) pivot.getRight();
		BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

		if (!parentNode.isEmpty()) {
			if (parentNode.getLeft().equals(node)) parentNode.setLeft(pivot);
			else parentNode.setRight(pivot);
		}

		pivot.setParent(parentNode);
		node.setParent(pivot);
		pivot.setRight(node);
		node.setLeft(rightPivot);
		rightPivot.setParent(node);

		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
