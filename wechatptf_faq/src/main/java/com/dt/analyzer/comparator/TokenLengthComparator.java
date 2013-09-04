package com.dt.analyzer.comparator;

import java.util.Comparator;

import com.dt.analyzer.entity.Token;

public class TokenLengthComparator<T> implements Comparator<T>{

	public int compare(T arg0, T arg1) {
		if(arg0 == arg1)
			return 0;
		int l1 = ((Token) arg0).getLen();
		int l2 = ((Token) arg1).getLen();
		return l1 > l2 ? 1 : (l1 == l2 ? 0 : -1);
	}
	
}
