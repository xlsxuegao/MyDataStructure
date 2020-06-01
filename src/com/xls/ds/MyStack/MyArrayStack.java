package com.xls.ds.MyStack;

import com.xls.ds.MyArray.MyArray;

/**
 * @author auas
 */
public class MyArrayStack<E> implements MyStack<E> {

	private MyArray<E> array;

	public MyArrayStack() {
		array = new MyArray<E>();
	}

	public MyArrayStack(int capacity) {
		array = new MyArray<E>(capacity);
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
	public void push(E e) {
		array.addLast(e);
	}

	@Override
	public E pop() {
		return array.removeLast();
	}

	@Override
	public E peek() {
		return array.getLast();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyStack");
		builder.append("[");
		for (int i = 0; i < array.getSize(); i++) {
			builder.append(array.get(i));
			if (i != array.getSize() - 1) {
				builder.append("，");
			}
		}
		builder.append("] top");
		return builder.toString();
	}
}
