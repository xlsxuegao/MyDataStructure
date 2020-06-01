package com.xls.ds.MyStack;

/**
 * @author auas
 */
public interface MyStack<E> {
	int getSize();

	boolean isEmpty();

	void push(E e);

	E pop();

	E peek();
}
