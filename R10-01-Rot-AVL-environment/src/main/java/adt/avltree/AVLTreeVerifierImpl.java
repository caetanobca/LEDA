package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		boolean isAVL = true;

		if (!this.avlTree.isEmpty()){
			isAVL = this.isAVLTree(this.avlTree.getRoot());
		}

		return isBST() && isAVL;
	}

	private boolean isAVLTree(BSTNode<T> node){
		boolean isAVL = true;

		if (Math.abs(this.avlTree.calculateBalance(node)) > 1){
			return false;
		}
		else{
			isAVL = isAVL && this.isAVLTree((BSTNode<T>) node.getLeft()) && this.isAVLTree((BSTNode<T>) node.getRight());
		}

		return isAVL;
	}

}
