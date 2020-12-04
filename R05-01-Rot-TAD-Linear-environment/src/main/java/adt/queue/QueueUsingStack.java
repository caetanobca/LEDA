package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()){
		    throw new QueueOverflowException();
        }else {
            if (element != null) {
                try {
                    stack1.push(element);
                } catch (StackOverflowException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
	    if (isEmpty()){
	        throw new QueueUnderflowException();
        }

        while (true) {
            try {
                stack2.push(stack1.pop());
            } catch (StackOverflowException e) {
                e.printStackTrace();
            } catch (StackUnderflowException e) {
                break;
            }
        }

        T result = null;
        try {
            result = stack2.pop();
        } catch (StackUnderflowException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                stack1.push(stack2.pop());
            } catch (StackOverflowException e) {
                e.printStackTrace();
            } catch (StackUnderflowException e) {
                break;
            }
        }

        return result;
	}

	@Override
	public T head() {
	    T result = null;
	    if (!isEmpty()){
            while (true) {
                try {
                    stack2.push(stack1.pop());
                } catch (StackOverflowException e) {
                    e.printStackTrace();
                } catch (StackUnderflowException e) {
                    break;
                }
            }

            result = stack2.top();

            while (true) {
                try {
                    stack1.push(stack2.pop());
                } catch (StackOverflowException e) {
                    e.printStackTrace();
                } catch (StackUnderflowException e) {
                    break;
                }
            }
        }
        return result;

    }

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}
