package com.xls.ds.MyArray;


import java.util.Arrays;

/**
 * 学习数据结构数组
 *
 * @author auas
 */
public class MyArray<E> {
	//存储数组
	private E[] data;
	//有效长度
	private int size;

	//构造函数，传入初始数组的容量
	public MyArray(int capacity) {
		this.data = (E[]) new Object[capacity];//new E[]语法上行不通
		this.size = 0;
	}

	//构造函数，默认容量为10
	public MyArray() {
		this(10);
	}

	//获取有效长度
	public int getSize() {
		return size;
	}

	//判断数组是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	//获取数组容量
	public int capacity() {
		return data.length;
	}

	//增加数组元素
	/*public void addLast(int e){
		//0.容量充足
		if (size<this.data.length){
			//数组为空增加元素
			if(this.isEmpty()){
				this.data[0]=e;
				size++;
			}else{
				this.data[size]=e;
				size++;
			}
		}else {
			//容量不足 要扩充数组容量  把之前的数组进行复制
			int[] newArray = Arrays.copyOf(this.data, this.data.length + this.data.length / 2);
			this.data=newArray;
			this.data[size]=e;
			size++;
		}
	}*/
	//插入元素到最后
	public void addLast(E e) {
		this.add(size, e);
	}

	//插入元素到前面
	public void addFirst(E e) {
		this.add(0, e);
	}

	//插入元素到数组指定位置
	public void add(int index, E e) {

		if (index < 0 || index > size) {
			throw new IllegalArgumentException("add filed index>=0 or index <= size");
		}
		if (size == data.length) {
			reSize(2 * data.length);
		}
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = e;
		size++;
	}

	//获取指定index的元素
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("get filed , index is illegal");
		}
		return data[index];
	}

	//修改index的元素
	public void set(int index, E e) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("set filed , index is illegal");
		}
		data[index] = e;
	}

	//是否包含某元素
	public boolean contains(E e) {
		for (int i = 0; i < size; i++) {
			if (e.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	//查找某元素在数组中的索引
	public int find(E e) {
		for (int i = 0; i < size; i++) {
			if (e.equals(data[i])) {
				return i;
			}
		}
		return -1;
	}

	//删除数组的指定索引的元素
	public E remove(int index) {
		E result = data[index];
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("delete failed ,index illegal");
		}
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		data[size] = null;
		size--;
		if (size == data.length / 4 && data.length / 2 != 0) {//采用lazy的方式 减少复杂度的震荡
			reSize(data.length / 2);
		}
		return result;
	}

	//删除首位元素
	public E removeFirst() {
		return remove(0);
	}

	//删除末位元素
	public E removeLast() {
		return remove(size - 1);
	}

	//删除数组中包含的某个元素
	public void removeElement(E e) {
		int res = find(e);
		if (res != -1) {
			remove(res);
		}
	}

	//重新定义data数组大小
	private void reSize(int newCapacity) {
//		E[] newData=(E[])new Object[newCapacity];
		data = Arrays.copyOf(this.data, newCapacity);
	}

	//展示数组中的数据
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("array size=%d,capacity=%d\n", size, data.length));
		builder.append("[");
		for (int i = 0; i < this.size; i++) {
			builder.append(data[i]);
			if (i != size - 1) {
				builder.append(",");
			}
		}
		builder.append("]");
		return builder.toString();
	}

}
