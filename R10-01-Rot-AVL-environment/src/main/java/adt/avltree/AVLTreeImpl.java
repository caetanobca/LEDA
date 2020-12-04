package adt.avltree;

import adt.bst.BST;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 *
 * Implementacao de uma arvore AVL
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
	public void insert(T element) {
		if(element != null && this.search(element).isEmpty()) {
			if (!this.isEmpty()) {
				this.insert(this.root, element);
			} else {
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.left(new BSTNode<T>())
						.right(new BSTNode<T>())
						.parent(new BSTNode<T>())
						.data(element)
						.build();
				this.root = newNode;
			}
		}
	}

	protected void insert(BSTNode<T> node, T element){
		if(element != null) {
			if (element.compareTo(node.getData()) < 0) {
				if (node.getLeft().isEmpty()) {
					BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
							.left(new BSTNode<T>())
							.right(new BSTNode<T>())
							.parent(node)
							.data(element)
							.build();
					node.setLeft(newNode);
				}else{
					this.insert((BSTNode<T>) node.getLeft(), element);
				}
			} else if (element.compareTo(node.getData()) > 0) {
				if (node.getRight().isEmpty()){
					BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
							.left(new BSTNode<T>())
							.right(new BSTNode<T>())
							.parent(node)
							.data(element)
							.build();
					node.setRight(newNode);
				}else{
					this.insert((BSTNode<T>) node.getRight(), element);
				}
			}
			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty() && !search(element).isEmpty()){
			BSTNode<T> node = super.search(element);
			super.remove(element);
			this.rebalanceUp(node);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int balance = 0;

		if (node != null && !node.isEmpty()) balance = this.height((BSTNode<T>) node.getLeft()) - this.height((BSTNode<T>) node.getRight());

		return balance;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);
		if (balance > 1){
			BSTNode<T> aux;
			if (this.calculateBalance((BSTNode<T>) node.getLeft()) < 0) Util.leftRotation((BSTNode<T>) node.getLeft());

			aux = Util.rightRotation(node);

			if(aux.getParent().isEmpty()) super.root = aux;

		}else if (balance < -1){
			BSTNode<T> aux;
			if (this.calculateBalance((BSTNode<T>) node.getRight()) > 0) Util.rightRotation((BSTNode<T>) node.getRight());

			aux = Util.leftRotation(node);

			if(aux.getParent().isEmpty()) super.root = aux;
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while (!parent.isEmpty()){
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
}
