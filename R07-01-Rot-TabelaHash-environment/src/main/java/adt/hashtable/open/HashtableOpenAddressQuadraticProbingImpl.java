package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (this.isFull()) throw new HashtableOverflowException();

		if (element != null && this.search(element) == null){
			int probe = 0;
			int hashElement = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElement] != null && !this.table[hashElement].equals(this.deletedElement)) {
				hashElement = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, ++probe);
				this.COLLISIONS ++;
			}
			this.table[hashElement] = element;
			this.elements ++;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty() && this.search(element) != null){
			int index = this.indexOf(element);
			int probe = 0;
			int hashElement = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElement] != null && probe < this.table.length) {
				if (this.table[hashElement].equals(element)){
					this.table[hashElement] = this.deletedElement;
					this.elements--;
				}
				hashElement = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, ++probe);
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null && !this.isEmpty()){
			int index = this.indexOf(element);
			if (index > -1) {
				result = (T) this.table[index];
			}
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;
		if (element != null && !this.isEmpty()){
			int probe = 0;
			int hashElement = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
			while (this.table[hashElement] != null && probe < this.table.length) {
				if (this.table[hashElement].equals(element)){
					result = hashElement;
					break;
				}
				hashElement = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, ++probe);
			}
		}
		return result;
	}
}
