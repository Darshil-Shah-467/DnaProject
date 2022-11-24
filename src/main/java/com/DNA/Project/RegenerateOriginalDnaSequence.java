package com.DNA.Project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RegenerateOriginalDnaSequence {
	private String dnaSequence;
	private String f1aa, f1bb, f2aa, f2bb, f3, f0;
	private String order, extraChar;
	private String fr1, f1, f2;
	private int f0Iterator;

	public RegenerateOriginalDnaSequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegenerateOriginalDnaSequence(FinalFiles finalFiles) {
		super();
		// TODO Auto-generated constructor stub
		this.f0 = finalFiles.getF0();
		this.f1aa = finalFiles.getF1aa();
		this.f1bb = finalFiles.getF1bb();
		this.f2aa = finalFiles.getF2aa();
		this.f2bb = finalFiles.getF2bb();
		this.f3 = finalFiles.getF3();
		//System.out.println(finalFiles.toString());
	}

	public void ReverseStep6() {
		//System.out.println("Reverse Step 6 called");
		f0Iterator = 0;
		AsciiToBinary asciiToBinary = new AsciiToBinary();
		f1aa = asciiToBinary.Convert(f1aa);
		//System.out.printf("Regeneration File, f1aa: %s \n" ,f1aa);
		while (f0.charAt(f0Iterator) != '#') {
			f1aa += f0.charAt(f0Iterator);
			f0Iterator++;
		}
		//System.out.printf("Regenerate file, f1aa = %s \n",f1aa);
		f1bb = asciiToBinary.Convert(f1bb);
		f0Iterator++;
		while (f0.charAt(f0Iterator) != '#') {
			f1bb += f0.charAt(f0Iterator);
			f0Iterator++;
		}
		f2aa = asciiToBinary.Convert(f2aa);
		f0Iterator++;
		while (f0.charAt(f0Iterator) != '#') {
			f2aa += f0.charAt(f0Iterator);
			f0Iterator++;
		}
		f2bb = asciiToBinary.Convert(f2bb);
		f0Iterator++;
		while (f0.charAt(f0Iterator) != '#') {
			f2bb += f0.charAt(f0Iterator);
			f0Iterator++;
		}
		f3 = asciiToBinary.Convert(f3);
		f0Iterator++;
		while (f0.charAt(f0Iterator) != '#') {
			f3 += f0.charAt(f0Iterator);
			f0Iterator++;
		}
		order = "";
		f0Iterator++;
		while (f0.charAt(f0Iterator) != '#') {
			order += f0.charAt(f0Iterator);
			f0Iterator++;
		}
		extraChar = "";
		f0Iterator++;
		while (f0.charAt(f0Iterator) != '#') {
			extraChar += f0.charAt(f0Iterator);
			f0Iterator++;
		}
		
		//System.out.println(order);
		//System.out.println(f1aa);
		//System.out.println(f1bb);
		//System.out.println(f2aa);
		//System.out.println(f2bb);
		//System.out.println(f3);
		
		//System.out.println("Reverse Step 6 ends");
	}

	public void ReverseStep5() {
		//System.out.println("Reverse Step 5 called");
		Map<String, Character> map = new HashMap<String, Character>();
		map.put("0", order.charAt(1));
		map.put("10", order.charAt(2));
		map.put("11", order.charAt(3));

		f2 = "";
		for (int i = 0; i < f3.length(); i++) {
			if (f3.charAt(i) == '0') {
				f2 += map.get("0");
			} else {
				i++;
				if (f3.charAt(i) == '0') {
					f2 += map.get("10");
				} else {
					f2 += map.get("11");
				}
			}
		}
		//System.out.printf("Regeneration File, f2: %s \n" ,f2);
		//System.out.println("Reverse Step 5 ends");
	}

	public void ReverseStep4() {
		
		//System.out.println("Reverse Step 4 called");
		
		f1 = "";

		Map<Character, Integer> mapCharToInt = new HashMap<Character, Integer>();
		mapCharToInt.put(order.charAt(1), 0);
		mapCharToInt.put(order.charAt(2), 1);
		mapCharToInt.put(order.charAt(3), 2);
		Map<Integer, Character> mapIntToChar = new HashMap<Integer, Character>();
		mapIntToChar.put(0, order.charAt(1));
		mapIntToChar.put(1, order.charAt(2));
		mapIntToChar.put(2, order.charAt(3));

		int i, f2bbIterator = 0;
		for (i = 0; i < f2aa.length(); i++) {
			//System.out.println(i);
			if (f2aa.charAt(i) == '0') {
				f1 += f2.charAt(i);
				f1 += f2.charAt(i);
			} else {

				if (f2bb.charAt(f2bbIterator) == '0') {
					f1 += mapIntToChar.get((mapCharToInt.get(f2.charAt(i)) - 2 + 3) % 3);
					f1 += mapIntToChar.get((mapCharToInt.get(f2.charAt(i)) - 1 + 3) % 3);
				} else {
					f1 += mapIntToChar.get((mapCharToInt.get(f2.charAt(i)) + 2 + 3) % 3);
					f1 += mapIntToChar.get((mapCharToInt.get(f2.charAt(i)) + 1 + 3) % 3);
				}
				f2bbIterator++;
			}
			//System.out.println(f2bb.length()-f2bbIterator);
			//System.out.println(f1);
		}
		f1 += extraChar;
		
		//System.out.printf("Regeneration File, f1: %s \n" ,f1);
		//System.out.println("Reverse Step 4 ends");
	}

	public void ReverseStep3() {
		
		//System.out.println("Reverse Step 3 called");
		
		fr1 = "";
		//System.out.println(f1aa);
		//System.out.println(f1bb);
		int i, f1bbIterator = 0, countZeros;
		for (i = 0; i < f1aa.length(); i++) {
			if (f1aa.charAt(i) == '1') {
				fr1 += '1';
			} else {
				countZeros = 0;
				while (i<f1aa.length() && f1aa.charAt(i) == '0') {
					countZeros++;
					i++;
				}
				countZeros *= 2;
				i--;
				if (f1bb.charAt(f1bbIterator) == '1') {
					countZeros--;
				}
				f1bbIterator++;
				while (countZeros > 0) {
					fr1 += '0';
					countZeros--;
				}
			}
			//System.out.println(fr1);
		}
		//System.out.printf("Regeneration File, fr1: %s \n" ,fr1);
		//System.out.println("Reverse Step 3 ends");
	}

	public void ReverseStep2() {
		int i, f1Iterator = 0;
		dnaSequence="";
		//System.out.printf("fr1 : %s \n",fr1);
		for (i = 0; i < fr1.length(); i++) {
			if (fr1.charAt(i) == '1') {
				dnaSequence += order.charAt(0);
			} else {
				dnaSequence += this.f1.charAt(f1Iterator);
				f1Iterator++;
			}
		}
		//System.out.printf("final dna sequence : %s \n",dnaSequence);
	}

	public void ReverseStep1() {
		// this step is already done , it computes order
	}

	public String ReverseAllSteps() {
		this.ReverseStep6();
		this.ReverseStep5();
		this.ReverseStep4();
		this.ReverseStep3();
		this.ReverseStep2();
		this.ReverseStep1();
		return this.dnaSequence;
	}

	public String getDnaSequence() {
		return dnaSequence;
	}

	public void setDnaSequence(String dnaSequence) {
		this.dnaSequence = dnaSequence;
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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getExtraChar() {
		return extraChar;
	}

	public void setExtraChar(String extraChar) {
		this.extraChar = extraChar;
	}

	public String getFr1() {
		return fr1;
	}

	public void setFr1(String fr1) {
		this.fr1 = fr1;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public int getF0Iterator() {
		return f0Iterator;
	}

	public void setF0Iterator(int f0Iterator) {
		this.f0Iterator = f0Iterator;
	}

	@Override
	public String toString() {
		return String.format(
				"RegenerateOriginalDnaSequence [dnaSequence=%s, f1aa=%s, f1bb=%s, f2aa=%s, f2bb=%s, f3=%s, f0=%s, order=%s, extraChar=%s, fr1=%s, f1=%s, f2=%s, f0Iterator=%s]",
				dnaSequence, f1aa, f1bb, f2aa, f2bb, f3, f0, order, extraChar, fr1, f1, f2, f0Iterator);
	}

}
