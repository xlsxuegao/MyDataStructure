package com.xls.ds.LinkedList;

/**
 * @author xuls
 */
public class MyLinkedList<E> {

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

	//head指向头元素
	private Node dummyHead;
	//size说明个数
	private int size;

	public MyLinkedList() {
		dummyHead = new Node(null, null);
		size = 0;
	}

	//getSize获得链表的元素个数
	public int getSize() {
		return size;
	}

	//isEmpty判空
	public boolean isEmpty() {
		return size == 0;
	}

	//add在链表任意位置插入元素
	public void add(int index, E e) {
		if (index < 0 || index > size)
			throw new IllegalArgumentException("add failed ,index is not valid");
		Node pre = dummyHead;
		for (int i = 0; i < index; i++) {
			pre = pre.next;
		}
//			Node node = new Node(e);
//			node.next=pre.next;
//			pre.next=node;

		pre.next = new Node(e, pre.next);
		size++;


	}

	//addFirst在表头添加新的元素e
	public void addFirst(E e) {
//		Node node = new Node(e);
//		node.next=head;
//		head = node;
		add(0, e);
	}

	//addLast在链表末尾插入元素
	public void addLast(E e) {
		add(size, e);
	}

	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("get failed ,index is valid");
		Node cur = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.e;
	}

	public E getFirst() {
		return get(0);
	}

	public E getLast() {
		return get(size - 1);
	}

	//修改链表中index位置的元素
	public void set(int index, E e) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("set failed ,index is valid");
		Node cur = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		cur.e = e;
	}

	//查找链表中是否有元素e
	public boolean contains(E e) {
		Node cur = dummyHead.next;
		while (cur != null) {
			if (e.equals(cur.e)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("remove failed , index is valid");
		Node pre = dummyHead;
		for (int i = 0; i < index; i++) {
			pre = pre.next;
		}
		Node delNode = pre.next;
		pre.next = delNode.next;
		delNode.next = null;
		size--;
		return delNode.e;
	}

	public E removeFirst() {
		return remove(0);
	}

	public E removeLast() {
		return remove(size - 1);
	}

	public void removeElement(E e) {
		Node prev = dummyHead;
		while (prev.next != null) {
			if (prev.next.e.equals(e)) {
				break;
			}
			prev = prev.next;
		}
		if (prev.next != null) {
			Node delNode = prev.next;
			prev.next = delNode.next;
			delNode.next = null;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node cur = dummyHead.next;
		while (cur != null) {
			builder.append(cur.e + "——>");
			cur = cur.next;
		}
		builder.append("NULL");
		return builder.toString();
	}
}
