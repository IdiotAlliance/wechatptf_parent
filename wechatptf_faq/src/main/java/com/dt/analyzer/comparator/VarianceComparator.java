package com.dt.analyzer.comparator;

import java.util.Comparator;

import com.dt.analyzer.entity.Chunk;

public class VarianceComparator<T> implements Comparator<T> {

	public int compare(T o1, T o2) {
		if(o1 == o2)
			return 0;
		float v1 = ((Chunk) o1).getVariance();
		float v2 = ((Chunk) o2).getVariance();
		return v1 > v2 ? 1 : (v1 == v2 ? 0 : -1);
	}

}
