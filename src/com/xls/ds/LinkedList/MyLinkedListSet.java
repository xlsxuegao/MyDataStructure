package com.xls.ds.LinkedList;

import com.xls.ds.MySet.ISet;

/**
 * @author xuls
 */
public class MyLinkedListSet<E> implements ISet<E> {
	private MyLinkedList myLinkedList;

	public MyLinkedListSet() {
		this.myLinkedList = new MyLinkedList<E>();
	}

	@Override
	public void add(E e) {
		if (!myLinkedList.contains(e)) {
			myLinkedList.addLast(e);
		}
	}

	@Override
	public boolean contains(E e) {
		return myLinkedList.contains(e);
	}

	@Override
	public void remove(E e) {
		myLinkedList.removeElement(e);
	}

	@Override
	public int getSize() {
		return myLinkedList.getSize();
	}

	@Override
	public boolean isEmpty() {
		return myLinkedList.isEmpty();
	}
}
