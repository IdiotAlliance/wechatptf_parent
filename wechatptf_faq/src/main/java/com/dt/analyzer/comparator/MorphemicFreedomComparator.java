package com.dt.analyzer.comparator;

import java.util.Comparator;

import com.dt.analyzer.entity.Chunk;

public class MorphemicFreedomComparator<T> implements Comparator<T> {

	public int compare(T o1, T o2) {
		if(o1 == o2)
			return 0;
		float f1 = ((Chunk) o1).getFreedom();
		float f2 = ((Chunk) o2).getFreedom();
		return f1 > f2 ? 1 : (f1 == f2 ? 0 : -1);
	}

}
