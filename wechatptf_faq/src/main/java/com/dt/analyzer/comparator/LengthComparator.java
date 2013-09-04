package com.dt.analyzer.comparator;

import java.util.Comparator;

import com.dt.analyzer.entity.Chunk;

public class LengthComparator<T> implements Comparator<T> {

	public int compare(T arg0, T arg1) {
		if(arg0 == arg1)
			return 0;
		int l1 = ((Chunk) arg0).getLength();
		int l2 = ((Chunk) arg1).getLength();
		return l1 > l2 ? 1 : (l1 == l2 ? 0 : -1);
	}

}
