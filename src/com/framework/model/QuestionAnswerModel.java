package com.framework.model;

import java.io.Serializable;

public class QuestionAnswerModel implements Serializable{
	
	private int id;
	private String question;
	private String answer;
	public int getId() {
		return id;
	}
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	

}
