package com.dt.analyzer.comparator;

import java.util.Comparator;

import com.dt.analyzer.entity.Chunk;

public class AvgComparator<T> implements Comparator<T> {

	public int compare(T o1, T o2) {
		if(o1 == o2)
			return 0;
		float avg1 = ((Chunk) o1).getAverage();
		float avg2 = ((Chunk) o2).getAverage();
		return avg1 > avg2 ? 1 : (avg1 == avg2 ? 0 : -1);
	}

}
