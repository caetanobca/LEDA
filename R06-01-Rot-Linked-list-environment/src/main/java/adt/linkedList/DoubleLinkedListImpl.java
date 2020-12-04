package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl(){
		this.setHead(new DoubleLinkedListNode<>());
		this.setLast((DoubleLinkedListNode<T>) this.head);
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {

			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>();
			newHead.setData(element);
			newHead.setNext(this.head);
			newHead.setPrevious(new DoubleLinkedListNode<T>());

			((DoubleLinkedListNode<T>) this.head).setPrevious(newHead);

			if (this.isEmpty()) {
				this.last = newHead;
			}
			this.head = newHead;
		}

	}

	@Override
	public void insert(T element) {
		if (element != null){

			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>();
			newLast.setData(element);
			newLast.setPrevious(this.last);

			this.last.setNext(newLast);
			this.last = newLast;
			this.last.setNext(new DoubleLinkedListNode<T>());

			if (this.isEmpty()){
				this.head = newLast;
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {

			this.head = this.head.getNext();
			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());

			if (this.isEmpty()) {
				this.last = new DoubleLinkedListNode<T>();
			}
		}
	}


	@Override
	public void removeLast() {
		if (!this.isEmpty()) {

			this.last = this.last.getPrevious();
			this.last.setNext(new DoubleLinkedListNode<T>());

			if (this.last.isNIL()) {
				this.head = new DoubleLinkedListNode<T>();
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {

			if (element.equals(this.head.getData())) {
				this.removeFirst();

			} else if (element.equals(this.last.getData())){
				this.removeLast();

			} else {

				DoubleLinkedListNode<T> search = (DoubleLinkedListNode<T>) this.head;
				while (!search.getNext().isNIL()) {
					if (search.getNext().getData().equals(element)) {
						search.setNext(search.getNext().getNext());
						((DoubleLinkedListNode<T>) search.getNext()).setPrevious(search);
						return;
					}

					search = ((DoubleLinkedListNode<T>) search.getNext());
				}
			}
		}
	}



	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
