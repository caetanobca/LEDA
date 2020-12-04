package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		boolean isEquals = false;
		if (tree1.size() != tree2.size()) isEquals = false;
		else if (!tree1.isEmpty() && !tree2.isEmpty()) {
			isEquals = equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
		}else if (tree1.isEmpty() && tree2.isEmpty()) isEquals = true;

		return isEquals;
	}

	private boolean equals(BSTNode<T> nodeTree1, BSTNode<T> nodeTree2) {
		boolean result = false;

		result = nodeTree1.equals(nodeTree2);
		if (result) {
			if (!nodeTree1.getLeft().isEmpty() && !nodeTree2.getLeft().isEmpty()) {
				result = equals((BSTNode<T>) nodeTree1.getLeft(), (BSTNode<T>) nodeTree2.getLeft());
			}
			if (!nodeTree1.getRight().isEmpty() && !nodeTree2.getRight().isEmpty()) {
				result = equals((BSTNode<T>) nodeTree1.getRight(), (BSTNode<T>) nodeTree2.getRight());
			}
		}

		return result;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		boolean isSimilar = false;
		if (tree1.size() != tree2.size()) isSimilar = false;
		else if (!tree1.isEmpty() && !tree2.isEmpty()) {
			isSimilar = isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
		}else if (tree1.isEmpty() && tree2.isEmpty()) isSimilar = true;

		return isSimilar;
	}

	private boolean isSimilar(BSTNode<T> nodeTree1, BSTNode<T> nodeTree2) {
		boolean result = false;

		result = nodeTree1.isLeaf() && nodeTree2.isLeaf();
		if (!result) {
			boolean resultLeft = false;
			boolean resultRight = false;
			if (!nodeTree1.getLeft().isEmpty() && !nodeTree2.getLeft().isEmpty()) {
				resultLeft = isSimilar((BSTNode<T>) nodeTree1.getLeft(), (BSTNode<T>) nodeTree2.getLeft());
			}else if (nodeTree1.getLeft().isEmpty() && nodeTree2.getLeft().isEmpty()) resultLeft = true;

			if (!nodeTree1.getRight().isEmpty() && !nodeTree2.getRight().isEmpty()) {
				resultRight = isSimilar((BSTNode<T>) nodeTree1.getRight(), (BSTNode<T>) nodeTree2.getRight());
			}else if (nodeTree1.getRight().isEmpty() && nodeTree2.getRight().isEmpty()) resultRight = true;

			result = resultLeft && resultRight;
		}

		return result;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T result = null;
		if (!tree.isEmpty() && k > 0 && k <= tree.size()){
			result = orderStatistic(tree, k-1, tree.minimum());
		}
		return result;
	}

	private T orderStatistic(BST<T> tree, int k, BSTNode<T> currentNode) {
		T result;

		if (k == 0) result = currentNode.getData();
		else result = this.orderStatistic(tree, k-1, tree.sucessor(currentNode.getData()));

		return result;
	}

}
