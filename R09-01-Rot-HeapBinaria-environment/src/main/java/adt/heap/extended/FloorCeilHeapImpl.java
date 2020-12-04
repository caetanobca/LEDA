package adt.heap.extended;

import java.util.Arrays;
import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer floor = null;
		if (array.length > 0) {
			for (Integer num : array) {
				this.insert(num);
			}

			if (this.comparator.compare(1, 2) > 0) {
				while (!this.isEmpty()) {
					if (this.rootElement() <= numero) {
						if (floor == null) floor = this.rootElement();
						else if (this.rootElement() >= floor) floor = this.rootElement();
					}
					this.extractRootElement();
				}

			}else{
				while (!this.isEmpty()) {
					if (this.rootElement() <= numero && floor == null) floor = this.rootElement();

					this.extractRootElement();
				}
			}
		}
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer ceil = null;
		if (array.length > 0) {
			for (Integer num : array) {
				this.insert(num);
			}

			if (this.comparator.compare(1, 2) > 0) {
				while (!this.isEmpty()) {
					if (this.rootElement() >= numero && ceil == null) ceil = this.rootElement();

					this.extractRootElement();
				}

			}else{
				while (!this.isEmpty()) {
					if (this.rootElement() >= numero) {
						if (ceil == null) ceil = this.rootElement();
						else if (this.rootElement() < ceil) ceil = this.rootElement();
					}
					this.extractRootElement();
				}
			}
		}
		return ceil;
	}

}
