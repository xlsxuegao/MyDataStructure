package com.xls.ds.MyQueue;

/**
 * @author xuls
 */
public class MyLoopQueue<E> implements MyQueue<E> {
	private E[] data;
	private int front, tail;
	private int size;

	public MyLoopQueue(int capacity) {
		data = (E[]) new Object[capacity + 1];
		front = 0;
		tail = 0;
		size = 0;
	}

	public MyLoopQueue() {
		this(10);
	}

	public int getCapacity() {
		return data.length - 1;
	}

	@Override
	public void enqueue(E e) {
		//判断是否满了
		if ((tail + 1) % data.length == front) {
			//进行扩容操作 resize
			reSize(getCapacity() * 2);
		}
		data[tail] = e;
		tail = (tail + 1) % data.length;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Queue is empty");
		}
		E res = data[front];
		data[front] = null;
		front = (front + 1) % data.length;
		size--;
		if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
			reSize(getCapacity() / 2);
		}
		return res;
	}

	@Override
	public E getFront() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Queue is empty");
		}
		return data[front];
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return front == tail;
	}

	private void reSize(int newCapacity) {
		E[] newData = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newData[i] = data[(front + i) % data.length];
		}
		front = 0;
		tail = size;
		data = newData;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("LoopQueue : size=%d , capacity=%d \n", size, getCapacity()));
		builder.append("front [");
		for (int i = front; i < tail; i = (i + 1) % data.length) {
			builder.append(data[i]);
			if (i + 1 != tail % data.length) {
				builder.append("，");
			}
		}
		builder.append("] tail");
		return builder.toString();
	}
}
