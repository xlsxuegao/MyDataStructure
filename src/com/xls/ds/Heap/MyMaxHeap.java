package com.xls.ds.Heap;

import com.xls.ds.MyArray.MyArray;

import java.util.logging.Level;

/**
 * @author xuls
 * 最大堆的实现
 */
public class MyMaxHeap<E extends Comparable<E>> {

	private MyArray<E> data;

	public MyMaxHeap(int capacity) {
		data = new MyArray<E>(capacity);
	}

	public MyMaxHeap() {
		data = new MyArray<E>();
	}

	public MyMaxHeap(E[] arr) {
		data = new MyArray<E>(arr);
		for (int i = getPatent(arr.length - 1); i >= 0; i--) {
			siftDown(i);
		}
	}

	public int getSize() {
		return data.getSize();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	//返回该节点的根节点的索引值
	private int getPatent(int index) {
		if (index == 0)
			throw new IllegalArgumentException("index-0 doesn't have parent");
		return (index - 1) / 2;
	}

	//返回该节点的左孩子节点
	private int getLeftChild(int index) {
		return (index * 2 + 1);
	}

	private int getRightChild(int index) {
		return (index * 2 + 2);
	}

	//向堆中添加元素
	public void add(E e) {
		data.addLast(e);
		siftUp(data.getSize() - 1);
	}

	//元素上浮
	private void siftUp(int k) {
		//防止元素上浮到索引为0的时候，还继续比较，所以K>0
		while (k > 0 && data.get(getPatent(k)).compareTo(data.get(k)) < 0) {
			data.swap(k, getPatent(k));
			k = getPatent(k);
		}
	}

	//取出堆中的最大值
	public E findMax() {
		if (isEmpty()) {
			throw new IllegalArgumentException("heap is empty");
		}
		return data.get(0);
	}

	//从堆中取出元素
	public E extractMax() {
		E ret = findMax();
		data.swap(0, data.getSize() - 1);
		data.removeLast();
		siftDown(0);
		return ret;
	}

	//元素下沉
	private void siftDown(int k) {
		while (getLeftChild(k) < data.getSize()) {
			int j = getLeftChild(k);
			//找出左右节点中最大的结点索引
			if (getRightChild(k) < data.getSize() && data.get(j).compareTo(data.get(j + 1)) < 0) {
				j = getRightChild(k);
			}
			//k与左右节点中最大值进行比较
			if (data.get(k).compareTo(data.get(j)) >= 0) {
				break;
			}
			data.swap(k, j);
			k = j;
		}
	}

	//取出最大的元素，并放入一个新的元素
	public E replace(E e) {
		E ret = findMax();
		data.set(0, e);
		siftDown(0);
		return ret;
	}

}
