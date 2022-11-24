package com.DNA.Project;

import org.springframework.stereotype.Component;

@Component
public class PairIntAndChar 
{
	
	private int prefix;
	private char suffix;
	public PairIntAndChar() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PairIntAndChar(int prefix, char suffix) {
		super();
		this.prefix = prefix;
		this.suffix = suffix;
	}
	public int getPrefix() {
		return prefix;
	}
	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}
	public char getSuffix() {
		return suffix;
	}
	public void setSuffix(char suffix) {
		this.suffix = suffix;
	}
	@Override
	public String toString() {
		return String.format("PairIntAndChar [prefix=%s, suffix=%s]", prefix, suffix);
	}
	
}
