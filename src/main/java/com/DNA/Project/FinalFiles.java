package com.DNA.Project;

import org.springframework.stereotype.Component;

@Component
public class FinalFiles {
	private String f1aa, f1bb, f2aa, f2bb, f3, f0;

	public FinalFiles(String f1aa, String f1bb, String f2aa, String f2bb, String f3, String f0) {
		super();
		this.f1aa = f1aa;
		this.f1bb = f1bb;
		this.f2aa = f2aa;
		this.f2bb = f2bb;
		this.f3 = f3;
		this.f0 = f0;
	}

	public FinalFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("FinalFiles [f1aa=%s, f1bb=%s, f2aa=%s, f2bb=%s, f3=%s, f0=%s]", f1aa, f1bb, f2aa, f2bb,
				f3, f0);
	}

	public String getF1aa() {
		return f1aa;
	}

	public void setF1aa(String f1aa) {
		this.f1aa = f1aa;
	}

	public String getF1bb() {
		return f1bb;
	}

	public void setF1bb(String f1bb) {
		this.f1bb = f1bb;
	}

	public String getF2aa() {
		return f2aa;
	}

	public void setF2aa(String f2aa) {
		this.f2aa = f2aa;
	}

	public String getF2bb() {
		return f2bb;
	}

	public void setF2bb(String f2bb) {
		this.f2bb = f2bb;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF0() {
		return f0;
	}

	public void setF0(String f0) {
		this.f0 = f0;
	}
	
	
}
