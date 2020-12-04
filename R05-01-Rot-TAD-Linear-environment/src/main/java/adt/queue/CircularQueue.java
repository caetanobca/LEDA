package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()){
			throw new QueueOverflowException();
		}
		if (element != null){
			if (isEmpty()){
				head = 0;
				tail = 0;
			}else {
				tail = (tail + 1) % array.length;
			}
				array[tail] = element;
				elements += 1;
		}
	}


//	Se head == tail, então só temos um elemento na fila, logo quando retirarmos ele, devemos "resetar" os apontadores
	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()){
			throw new QueueUnderflowException();
		}
		T element = array[head];
		if (head == tail){
			head = -1;
			tail = -1;
		}else{
			head = (head + 1) % array.length;
		}
		elements -= 1;

		return element;
	}

	@Override
	public T head() {
		if (isEmpty()){
			return null;
		}
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;
	}
}
