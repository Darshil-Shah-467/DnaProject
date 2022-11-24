package com.DNA.Project;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class IntermidiateFiles {
	private String f1="", fr1="", fr1a="", fr1b="", fr2a="", fr2b="", f2="", fr3="";
	private char maxFreqChar;
	private String order;
	private Map<String,String> mapOrder;
	private String dnaSequence;
	
	public Map<String, String> getMapOrder() {
		return mapOrder;
	}

	public void setMapOrder(Map<String, String> mapOrder) {
		this.mapOrder = mapOrder;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public IntermidiateFiles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IntermidiateFiles(String dnaSequence) {
		this.dnaSequence=dnaSequence;
	}

	public String getDnaSequence() {
		return dnaSequence;
	}

	public void setDnaSequence(String dnaSequence) {
		this.dnaSequence = dnaSequence;
	}

	public char getMaxFreqChar() {
		return maxFreqChar;
	}

	public void setMaxFreqChar(char maxFreqChar) {
		this.maxFreqChar = maxFreqChar;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getFr1() {
		return fr1;
	}

	public void setFr1(String fr1) {
		this.fr1 = fr1;
	}

	public String getFr1a() {
		return fr1a;
	}

	public void setFr1a(String fr1a) {
		this.fr1a = fr1a;
	}

	public String getFr1b() {
		return fr1b;
	}

	public void setFr1b(String fr1b) {
		this.fr1b = fr1b;
	}

	public String getFr2a() {
		return fr2a;
	}

	public void setFr2a(String fr2a) {
		this.fr2a = fr2a;
	}

	public String getFr2b() {
		return fr2b;
	}

	public void setFr2b(String fr2b) {
		this.fr2b = fr2b;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getFr3() {
		return fr3;
	}

	public void setFr3(String fr3) {
		this.fr3 = fr3;
	}

	@Override
	public String toString() {
		return String.format(
				"IntermidiateFiles [f1=%s, fr1=%s, fr1a=%s, fr1b=%s, fr2a=%s, fr2b=%s, f2=%s, fr3=%s, maxFreqChar=%s, order=%s, mapOrder=%s, dnaSequence=%s]",
				f1, fr1, fr1a, fr1b, fr2a, fr2b, f2, fr3, maxFreqChar, order, mapOrder, dnaSequence);
	}

}
