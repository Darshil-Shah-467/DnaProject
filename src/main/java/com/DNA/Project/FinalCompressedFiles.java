package com.DNA.Project;

import java.util.List;

public class FinalCompressedFiles {
	
	//@JsonIgnore
	private List<PairIntAndChar> f1aa, f1bb, f2aa, f2bb, f3, f0;
	
	public FinalCompressedFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinalCompressedFiles(List<PairIntAndChar> f1aa, List<PairIntAndChar> f1bb, List<PairIntAndChar> f2aa,
			List<PairIntAndChar> f2bb, List<PairIntAndChar> f3, List<PairIntAndChar> f0) {
		super();
		this.f1aa = f1aa;
		this.f1bb = f1bb;
		this.f2aa = f2aa;
		this.f2bb = f2bb;
		this.f3 = f3;
		this.f0 = f0;
	}

	public List<PairIntAndChar> getF1aa() {
		return f1aa;
	}

	public void setF1aa(List<PairIntAndChar> f1aa) {
		this.f1aa = f1aa;
	}

	public List<PairIntAndChar> getF1bb() {
		return f1bb;
	}

	public void setF1bb(List<PairIntAndChar> f1bb) {
		this.f1bb = f1bb;
	}

	public List<PairIntAndChar> getF2aa() {
		return f2aa;
	}

	public void setF2aa(List<PairIntAndChar> f2aa) {
		this.f2aa = f2aa;
	}

	public List<PairIntAndChar> getF2bb() {
		return f2bb;
	}

	public void setF2bb(List<PairIntAndChar> f2bb) {
		this.f2bb = f2bb;
	}

	public List<PairIntAndChar> getF3() {
		return f3;
	}

	public void setF3(List<PairIntAndChar> f3) {
		this.f3 = f3;
	}

	public List<PairIntAndChar> getF0() {
		return f0;
	}

	public void setF0(List<PairIntAndChar> f0) {
		this.f0 = f0;
	}

	@Override
	public String toString() {
		return String.format("FinalCompressedFiles [f1aa=%s, f1bb=%s, f2aa=%s, f2bb=%s, f3=%s, f0=%s]", f1aa, f1bb,
				f2aa, f2bb, f3, f0);
	}

	
	
}
