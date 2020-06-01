package com.xls.ds.MyQueue;

import com.xls.ds.MyArray.MyArray;

/**
 * @author xls
 */
public class MyArrayQueue<E> implements MyQueue<E> {
	private MyArray<E> array;

	public MyArrayQueue(int capacity) {
		array = new MyArray<E>(capacity);
	}

	public MyArrayQueue() {
		array = new MyArray<E>();
	}


	@Override
	public void enqueue(E e) {
		array.addLast(e);
	}

	@Override
	public E dequeue() {
		return array.removeFirst();
	}

	@Override
	public E getFront() {
		return array.getFirst();
	}

	@Override
	public int getSize() {
		return array.getSize();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	public int getCapacity() {
		return array.getCapacity();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyQueue：");
		builder.append("front [");
		for (int i = 0; i < array.getSize(); i++) {
			builder.append(array.get(i));
			if (i != array.getSize() - 1) {
				builder.append("，");
			}
		}
		builder.append("] tail");
		return builder.toString();
	}
}
