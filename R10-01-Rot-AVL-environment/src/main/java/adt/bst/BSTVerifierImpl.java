package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		boolean result = true;
		if (!this.bst.isEmpty()){
			result = this.isBST(this.bst.getRoot());
		}
		return result;
	}

	private boolean isBST(BSTNode<T> node) {
		boolean result = true;

		if (!node.getLeft().isEmpty()) {
			if (node.getData().compareTo(node.getLeft().getData()) < 0) return false;
			else result = result && this.isBST((BSTNode<T>) node.getLeft());
		}
		if (!node.getRight().isEmpty()) {
			if (node.getData().compareTo(node.getRight().getData()) > 0) return false;
			else result = result && this.isBST((BSTNode<T>) node.getRight());
		}
		return result;
	}
	
}
