package com.DNA.Project;



public class PairCharAndFreq {
	private int freq;
	private char genome;
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public char getGenome() {
		return genome;
	}
	public void setGenome(char c) {
		this.genome = c;
	}
	public PairCharAndFreq(int freq, char c) {
		super();
		this.freq = freq;
		this.genome = c;
	}
	@Override
	public String toString() {
		return String.format("PairCharAndFreq [freq=%s, c=%s]", freq, genome);
	}
	
	
}


