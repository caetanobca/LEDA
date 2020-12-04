package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return (this.data == null);
	}

	@Override
	public int size() {

		if (!isEmpty() && this.next != null){
			return 1 + this.next.size();

		}else if (!isEmpty()){
			return 1;

		}
		return 0;
	}

	@Override
	public T search(T element) {
		if (element != null) {
			if (!this.isEmpty() && this.data.equals(element)) {

				return element;

			} else if (this.next != null) {
				return this.next.search(element);
			}
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.data == null) {
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<T>();

			}else if (this.next != null) {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!this.isEmpty() && this.data.equals(element)) {
				this.data = this.next.getData();
				this.next = this.next.getNext();

			} else if (this.next != null) {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];

		if (!this.isEmpty()) {
			return auxToArray(result, 0);
		}

		return result;
	}

	private T[] auxToArray(T[] array, int index){
		array[index] = this.data;
		index += 1;
		if (this.next.isEmpty()){
			return array;
		}else {
			return this.next.auxToArray(array, index);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
