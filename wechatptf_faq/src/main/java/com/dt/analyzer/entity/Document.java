package com.dt.analyzer.entity;

public class Document implements Comparable<Document>{

	private String question;
	private String answer;
	private String cid;
	private double rank = 0.0;
	
	public Document(String q,  String a, String cid){
		this.question = q;
		this.answer = a;
		this.cid = cid;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public String getCid() {
		return cid;
	}
	
	public double getRank(){
		return rank;
	}
	
	public void addRank(double inc){
		this.rank += inc;
	}

	public int compareTo(Document arg0) {
		if(this == arg0)
			return 0;
		return this.rank > arg0.rank ? -1 : (this.rank == arg0.rank ? 0 : 1);
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		return false;
	}
	
}
