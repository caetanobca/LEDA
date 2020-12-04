package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null) {
			Arrays.sort(array);
			List<T> lista = Arrays.asList(array);
			fillWithoutRebalance(lista, 0, lista.size() - 1);
		}
	}

	private void fillWithoutRebalance(List<T> lista, int leftIndex, int rightIndex){
		if (leftIndex <= rightIndex) {
			int middle = (rightIndex + leftIndex)/2;
			if (!this.isEmpty()){
				insertWithoutRebalance(this.root, lista.get(middle));
			}else {
				insert(lista.get(middle));
			}
			this.fillWithoutRebalance(lista, leftIndex, middle-1);
			this.fillWithoutRebalance(lista, middle+1, rightIndex);
		}
	}

	private void insertWithoutRebalance(BSTNode<T> node, T element){
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
					this.insertWithoutRebalance((BSTNode<T>) node.getLeft(), element);
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
					this.insertWithoutRebalance((BSTNode<T>) node.getRight(), element);
				}
			}
		}
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);
		if (balance > 1){
			BSTNode<T> aux;
			if (this.calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
				Util.leftRotation((BSTNode<T>) node.getLeft());
				this.LRcounter += 1;
			}else this.LLcounter += 1;

			aux = Util.rightRotation(node);

			if(aux.getParent().isEmpty()) super.root = aux;

		}else if (balance < -1){
			BSTNode<T> aux;
			if (this.calculateBalance((BSTNode<T>) node.getRight()) > 0){
				Util.rightRotation((BSTNode<T>) node.getRight());
				this.RLcounter += 1;
			}else this.RRcounter += 1;

			aux = Util.leftRotation(node);

			if(aux.getParent().isEmpty()) super.root = aux;
		}
	}

}
