package com.xls.ds.MyQueue;

import com.xls.ds.LinkedList.MyLinkedList;

import javax.print.attribute.standard.PrinterURI;

/**
 * @author xuls
 */
public class MyLinkedQueue<E> implements MyQueue<E> {
	private Node head, tail;
	private int size;

	public MyLinkedQueue() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public void enqueue(E e) {
		if (tail == null) {
			tail = new Node(e);
			head = tail;
		} else {
			tail.next = new Node(e);
			tail = tail.next;
		}
		size++;
	}

	@Override
	public E dequeue() {

		if (isEmpty()) {
			throw new IllegalArgumentException("stack is empty");
		}
		Node delNode = head;
		head = head.next;
		size--;
		delNode.next = null;//别忘了置空

		if (head == null) {
			tail = null;
		}
		return delNode.e;
	}

	@Override
	public E getFront() {
		if (isEmpty()) {
			throw new IllegalArgumentException("stack is empty");
		}
		return head.e;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Queue head：");
		Node cur = head;
		while (cur != null) {
			builder.append(cur.e + "——>");
			cur = cur.next;
		}
		builder.append("NULL tail");
		return builder.toString();
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private class Node {
		public E e;
		public Node next;

		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}

		public Node(E e) {
			this(e, null);
		}

		public Node() {
			this(null, null);
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}
}
