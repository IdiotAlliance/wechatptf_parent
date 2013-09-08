package com.dt.wechatptf.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/***
 * 非线程安全的最小堆实现
 * @author lvxiang
 *
 * @param <T>
 */
public class MinHeap<T>{

	private int size;
	private int cursor = -1;
	private List<T> heap;
	private Comparator<T> cp = null;
	
	public MinHeap(int size){
		this(size, null);
	}
	
	public MinHeap(int size, Comparator<T> cp){
		if(size <= 0)
			throw new IllegalArgumentException("Heap size must be greater than 0");

		this.size = size;
		this.heap = new LinkedList<T>();
		this.cp   = cp;
	}
	
	public void push(T o){
		// the heap is not full
		if(cursor + 1 < size){
			cursor ++;
			heap.add(cursor, o);
			filtUp();
			return;
		}
		// the heap is full
		throw new IndexOutOfBoundsException();
	}
	
	public T pop(){
		if(cursor < 0)
			throw new IndexOutOfBoundsException("The heap is empty!");
		T result = heap.remove(0);
		cursor --;
		if(cursor >= 0){
			heap.add(0, heap.remove(cursor));
			filtDown();
		}
		return result;
	}
	
	public T min(){
		if(cursor < 0)
			return null;
		return heap.get(0);
	}
	
	public boolean isFull(){
		return this.cursor == size - 1;
	}
	
	private void filtDown(){
		int i = 0;
		while(i < cursor){
			int left = 2 * i + 1;
			if(left <= cursor && compare(i, left) > 0){
				Collections.swap(heap, i, left);
				i = left;
				continue;
			}
			int right = 2 * (i + 1);
			if(right <= cursor && compare(i, right) > 0){
				Collections.swap(heap, i, right);
				i = right;
				continue;
			}
			break;
		}
	}
	
	private void filtUp(){
		int i = cursor;
		while(i != 0){
			int parent = i / 2;
			if(parent < i && compare(i, parent) < 0){
				Collections.swap(heap, i, parent);
				i = parent;
				continue;
			}
			break;
		}
	}
	
	@SuppressWarnings("unchecked")
	private int compare(int i1, int i2){
		if(cp != null)
			return cp.compare(heap.get(i1), heap.get(i2));
		else
			return ((Comparable)heap.get(i1)).compareTo(heap.get(i2));
	}
}
