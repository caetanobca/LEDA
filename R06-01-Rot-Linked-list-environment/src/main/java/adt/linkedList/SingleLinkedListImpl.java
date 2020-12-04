package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;

		if (!this.isEmpty()) {

			SingleLinkedListNode<T> element = this.head;
			while (!element.isNIL()) {
				size += 1;
				element = element.getNext();
			}
		}

		return size;
	}

	@Override
	public T search(T element) {
		T result = null;

		if (element != null) {

			SingleLinkedListNode<T> search = this.head;
			while (!search.isNIL()) {
				if (search.getData().equals(element)) {
					result = search.getData();
				}

				search = search.getNext();
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {

			if (this.isEmpty()) {

				this.head.setData(element);
				this.head.setNext(new SingleLinkedListNode<T>());

			} else {

				SingleLinkedListNode<T> nextNode = this.head;
				while (!nextNode.isNIL()) {
					nextNode = nextNode.getNext();
				}

				nextNode.setData(element);
				nextNode.setNext(new SingleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {

			if (element.equals(this.head.getData())) {
				this.head = this.head.getNext();
			} else {

				SingleLinkedListNode<T> search = this.head;
				while (!search.getNext().isNIL()) {
					if (search.getNext().getData().equals(element)) {
						search.setNext(search.getNext().getNext());
						return;
					}
					search = search.getNext();
				}

			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];

		int index = 0;
		SingleLinkedListNode<T> element = this.head;
		while (!element.isNIL()){
			result[index] = element.getData();
			index += 1;
			element = element.getNext();
		}

		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
