package adt.bst;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(this.root);
	}
	private int height(BSTNode<T> node){
		int result = -1;
		if (!node.isEmpty()) {
			result = 1 + Math.max(height((BSTNode<T>) node.getLeft()),
					height((BSTNode<T>) node.getRight()));
			}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> result = new BSTNode<T>();
		if(element != null && !this.isEmpty()){
			result = search(this.root, element);
		}
		return result;
	}

	private BSTNode<T> search(BSTNode<T> node, T element){
		if (!node.isEmpty()) {
			if (node.getData().equals(element)) return node;
			else if (element.compareTo(node.getData()) < 0) return search((BSTNode<T>) node.getLeft(), element);
			else if (element.compareTo(node.getData()) > 0) return search((BSTNode<T>) node.getRight(), element);
		}
		return new BSTNode<T>();
	}


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

	private void insert(BSTNode<T> node, T element){
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
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (!this.isEmpty()){
			return maximum(this.root);
		}
		return null;
	}
	private BSTNode<T> maximum(BSTNode<T> node){
		if (node.getRight().isEmpty()){
			return node;
		}
		return maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		if (!this.isEmpty()){
			return minimum(this.root);
		}
		return null;
	}

	private BSTNode<T> minimum(BSTNode<T> node){
		if (node.getLeft().isEmpty()){
			return node;
		}
		return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result= null;
		if(element != null && !this.isEmpty() && !this.search(element).isEmpty()){
			BSTNode<T> node = this.search(element);
			if (node.getRight().isEmpty()) result = sucessor(node, element);
			else result = this.minimum((BSTNode<T>) node.getRight());
		}
		return result;
	}

	private BSTNode<T> sucessor(BSTNode<T> node, T element){
		if (node.getParent().isEmpty()) return null;
		else if (node.getParent().getData().compareTo(element) > 0) return (BSTNode<T>) node.getParent();
		else return sucessor((BSTNode<T>) node.getParent(), element);
	}

	@Override
	public BSTNode<T> predecessor(T element) {
        BSTNode<T> result= null;
        if(element != null && !this.isEmpty() && !this.search(element).isEmpty()){
            BSTNode<T> node = this.search(element);
            if (node.getLeft().isEmpty()) result = predecessor(node, element);
            else result = this.maximum((BSTNode<T>) node.getLeft());
        }
        return result;
	}

    private BSTNode<T> predecessor(BSTNode<T> node, T element){
        if (node.getParent().isEmpty()) return null;
        else if (node.getParent().getData().compareTo(element) < 0) return (BSTNode<T>) node.getParent();
        else return predecessor((BSTNode<T>) node.getParent(), element);
    }

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty() && !this.search(element).isEmpty()){
            BSTNode<T> node = this.search(element);
            this.remove(node, element);
		}
	}

	private void remove(BSTNode<T> node, T element){
		if (node.isLeaf()){
			if (node.equals(this.root)) this.root = new BSTNode<T>();
			else if (node.getParent().getLeft().equals(node)) node.getParent().setLeft(new BSTNode<T>());
			else if (node.getParent().getRight().equals(node)) node.getParent().setRight(new BSTNode<T>());

		}else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()){
			node.getLeft().setParent(node.getParent());

			if (this.root.equals(node)) this.root = (BSTNode<T>) node.getLeft();
			else if (node.getParent().getData().compareTo(node.getLeft().getData()) > 0) node.getParent().setLeft(node.getLeft());
			else if (node.getParent().getData().compareTo(node.getLeft().getData()) < 0) node.getParent().setRight(node.getLeft());

		}else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()){
			node.getRight().setParent(node.getParent());

			if (this.root.equals(node)) this.root = (BSTNode<T>) node.getRight();
			else if (node.getParent().getData().compareTo(node.getRight().getData()) > 0) node.getParent().setLeft(node.getRight());
			else if (node.getParent().getData().compareTo(node.getRight().getData()) < 0) node.getParent().setRight(node.getRight());
		}else if (!node.getLeft().isEmpty() && !node.getLeft().isEmpty()){
			BSTNode<T> sucessorNode = this.sucessor(element);
			node.setData(sucessorNode.getData());
			sucessorNode.setData(element);

			this.remove(sucessorNode, element);
		}
	}


	@Override
	public T[] preOrder() {
		T[] result = (T[])new Comparable[this.size()];
		if (!this.isEmpty()) {
			ArrayList<T> lista = new ArrayList<T>();
			preOrder(this.root, lista).toArray(result);
		}
		return result;
	}

	private ArrayList<T> preOrder(BSTNode<T> node, ArrayList<T> list){
		list.add(node.getData());
		if (!node.getLeft().isEmpty()) preOrder((BSTNode<T>)node.getLeft(), list);
		if (!node.getRight().isEmpty()) preOrder((BSTNode<T>)node.getRight(), list);
		return list;
	}

	@Override
	public T[] order() {
		T[] result = (T[])new Comparable[this.size()];
		if (!this.isEmpty()) {
			ArrayList<T> lista = new ArrayList<T>();
			order(this.root, lista).toArray(result);
		}
		return result;
	}

	private ArrayList<T> order(BSTNode<T> node, ArrayList<T> list){
		if (!node.getLeft().isEmpty()) this.order((BSTNode<T>) node.getLeft(), list);
		list.add(node.getData());
		if (!node.getRight().isEmpty()) this.order((BSTNode<T>) node.getRight(), list);
		return list;
	}

	@Override
	public T[] postOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		if (!this.isEmpty()) {
			ArrayList<T> lista = new ArrayList<T>();
			postOrder(this.root, lista).toArray(result);
		}
		return result;
	}

	private ArrayList<T> postOrder(BSTNode<T> node, ArrayList<T> list){
		if (!node.getLeft().isEmpty()) this.postOrder((BSTNode<T>) node.getLeft(), list);
		if (!node.getRight().isEmpty()) this.postOrder((BSTNode<T>) node.getRight(), list);
		list.add(node.getData());
		return list;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
