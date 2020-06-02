package com.xls.ds.MyStack;

import com.xls.ds.LinkedList.MyLinkedList;

/**
 * @author xuls
 */
public class MyLinkedStack<E> implements MyStack<E> {
	private MyLinkedList<E> linkedList;

	public MyLinkedStack() {
		linkedList = new MyLinkedList<E>();
	}

	@Override
	public int getSize() {
		return linkedList.getSize();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	@Override
	public void push(E e) {
		linkedList.addFirst(e);
	}

	@Override
	public E pop() {
		return linkedList.removeFirst();
	}

	@Override
	public E peek() {
		return linkedList.getFirst();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("top ：");
		builder.append(linkedList.toString());
		return builder.toString();
	}
}
