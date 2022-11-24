package com.DNA.Project;

import java.util.Comparator;

public class SortByFreq implements Comparator<PairCharAndFreq> {

	public SortByFreq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int compare(PairCharAndFreq p1, PairCharAndFreq p2) {
		return (p1.getFreq() - p2.getFreq());

	}
}
