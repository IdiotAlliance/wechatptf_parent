package com.dt.analyzer.entity;

/***
 * 中文分词策略
 * @author lvxiang
 *
 */
public enum Strategy {
	
	/***
	 * 简单策略，只采用最大匹配算法
	 */
	SIMPLE, 
	
	/***
	 * 复杂策略
	 */
	COMPLEX;
}
