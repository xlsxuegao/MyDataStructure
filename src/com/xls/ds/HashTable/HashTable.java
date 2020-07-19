package com.xls.ds.HashTable;

import java.util.TreeMap;

/**
 * @author xuls
 */
public class HashTable<K, V> {
	private TreeMap<K, V>[] hashTable;
	private int M;
	private int size;
	private static final int UPPER_TOL = 10;  //上界  超出做扩容操作
	private static final int LOWER_TOL = 2;  //下界  超出做缩容操作
	private static final int INIT_CAPACITY = 7; //初始容量

	public HashTable(int M) {
		this.M = M;
		size = 0;
		hashTable = new TreeMap[M];
		for (int i = 0; i < M; i++) {
			hashTable[i] = new TreeMap<>();
		}
	}

	public HashTable() {
		this(INIT_CAPACITY);
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public int getSize() {
		return size;
	}

	public void add(K key, V value) {
		TreeMap<K, V> map = hashTable[hash(key)];
		if (map.containsKey(key)) {
			map.put(key, value);
		} else {
			map.put(key, value);
			size++;

			if (size >= UPPER_TOL * M) {
				resize(2 * M);
			}
		}
	}

	public V remove(K key) {

		TreeMap<K, V> map = hashTable[hash(key)];
		V ret = null;
		if (map.containsKey(key)) {
			ret = map.remove(key);
			size--;
			if (size < LOWER_TOL * M && M / 2 >= INIT_CAPACITY) {
				resize(M / 2);
			}
		}
		return ret;
	}

	public void set(K key, V value) {
		TreeMap<K, V> map = hashTable[hash(key)];
		if (!map.containsKey(key)) {
			throw new IllegalArgumentException(key + "doesn't exist");
		}
		map.put(key, value);
	}

	public boolean contains(K key) {
		return hashTable[hash(key)].containsKey(key);
	}

	public V get(K key) {
		return hashTable[hash(key)].get(key);
	}

	private void resize(int newM) {
		TreeMap<K, V>[] treeMaps = new TreeMap[newM];
		for (int i = 0; i < newM; i++) {
			treeMaps[i] = new TreeMap<>();
		}
		int oldM = M;
		this.M = newM;
		for (int i = 0; i < oldM; i++) {
			TreeMap<K, V> map = hashTable[i];
			for (K key : map.keySet()) {
				treeMaps[hash(key)].put(key, map.get(key));
			}
		}
	}
}
