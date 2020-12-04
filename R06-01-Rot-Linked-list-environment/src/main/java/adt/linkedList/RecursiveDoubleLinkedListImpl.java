package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<T>();
                ((RecursiveDoubleLinkedListImpl<T>)this.next).setPrevious(this);

			}else if (this.next != null) {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null){
		    if (this.isEmpty()){
		        this.data = element;
		        this.next = new RecursiveDoubleLinkedListImpl<T>();
		        this.previous = new RecursiveDoubleLinkedListImpl<T>();

            }else {
                RecursiveDoubleLinkedListImpl<T> newSecond= new RecursiveDoubleLinkedListImpl<>();
                newSecond.setPrevious(this);
                newSecond.setData(this.data);
                newSecond.setNext(this.next);
                ((RecursiveDoubleLinkedListImpl<T>)newSecond.getNext()).setPrevious(newSecond);

                this.data = element;
                this.next = newSecond;
            }
        }
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!this.isEmpty() && this.data.equals(element)) {
				this.data = this.next.getData();

				if (this.next.getNext() != null) {
					this.next = this.next.getNext();
				}else {
					this.next = new RecursiveDoubleLinkedListImpl<T>();
				}
				((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);

			} else if (this.next != null) {
				this.next.remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
	    if (!this.isEmpty()){
	        this.data = this.next.getData();

			if (this.next.getNext() != null) {
				this.next = this.next.getNext();
			}else {
				this.next = new RecursiveDoubleLinkedListImpl<T>();
			}
            ((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
        }
	}

	@Override
	public void removeLast() {
		if (this.next != null && this.next.isEmpty()){
			this.data = null;
			this.next = null;
		}else if (this.next != null){
			((RecursiveDoubleLinkedListImpl<T>)this.next).removeLast();
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
