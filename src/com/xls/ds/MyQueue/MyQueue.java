package com.xls.ds.MyQueue;

/**
 * @author auas
 */
public interface MyQueue<E> {
	void enqueue(E e);

	E dequeue();

	E getFront();

	int getSize();

	boolean isEmpty();
}
