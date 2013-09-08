package com.dt.analyzer.entity;

public class TokenProfile {

	private String[] docs;
	private int freq;
	private int tlen;
	
	public TokenProfile(String[] docs, int freq, int tlen){
		
		this.docs = docs;
		this.freq = freq;
		this.tlen = tlen;
		
	}

	public String[] getDocs() {
		return docs;
	}

	public int getFreq() {
		return freq;
	}

	public int getTlen() {
		return tlen;
	}
	
	
}
