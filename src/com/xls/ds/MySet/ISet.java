package com.xls.ds.MySet;

/**
 * @author xuls
 */
public interface ISet<E> {
	void add(E e);

	boolean contains(E e);

	void remove(E e);

	int getSize();

	boolean isEmpty();

}
