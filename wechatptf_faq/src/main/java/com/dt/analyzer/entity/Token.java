package com.dt.analyzer.entity;

public class Token {

	public static final int MAX_LENGTH = 4;
	
	private String token;
	private int len;
	private boolean stopword;
	private long hash;
	
	public Token(String token, boolean stopword){
		if(token == null)
			throw new NullPointerException();
		
		this.token = token;
		this.len = calcLen(token);
		if(len > MAX_LENGTH)
			throw new IllegalArgumentException("非法串：" + token);
		
		this.stopword = stopword;
		this.hash = hash(token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
		setLen(token.length());
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public boolean isStopword() {
		return stopword;
	}

	public void setStopword(boolean stopword) {
		this.stopword = stopword;
	}
	
	public boolean equalTo(String str){
		return this.hash == hash(str);
	}
	
	public long getHash(){
		return hash;
	}
	
	/***
	 * 对字符串进行求哈希码，默认的词典中最长的词的长度为4和字符，因此可将哈希码
	 * 保存在一个64位的长整形数中。
	 * @param token
	 * @return
	 */
	private long hash(String token){
		long result = 0;
		for(int i = 0; i < token.length(); i++){
			result = result << 16 | token.charAt(i);
		}
		return result;
	}
	
	private int calcLen(String token){
		return token.replaceAll("[a-zA-Z]+", " ").length();
	}
}
