package com.DNA.Project;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class ProcessIntermidiateFiles {
	private IntermidiateFiles intermidiateFiles = new IntermidiateFiles();
	String f1aa = "", f1bb = "", f2aa = "", f2bb = "", f3 = "", f0 = "";
	String addIfRemaiming;
	public ProcessIntermidiateFiles() {
		// super();
		// TODO Auto-generated constructor stub
	}
	
	
	public IntermidiateFiles getIntermidiateFiles() {
		return intermidiateFiles;
	}

	public void setIntermidiateFiles(IntermidiateFiles intermidiateFiles) {
		this.intermidiateFiles = intermidiateFiles;
	}

	public ProcessIntermidiateFiles(IntermidiateFiles intermidiateFiles) {
		super();
		this.intermidiateFiles = intermidiateFiles;
	}
	
	public void printStep(int step)
	{
//		System.out.println("");
//		System.out.printf("Step %d : \n",step);
//		System.out.printf("order = %s \n", intermidiateFiles.getOrder());
//		System.out.printf("f0 : %s  \n",f0);
//		System.out.printf("f1 : %s  \n",intermidiateFiles.getF1());
//		System.out.printf("fr1a : %s  \n",intermidiateFiles.getFr1a());
//		System.out.printf("fr1b : %s  \n",intermidiateFiles.getFr1b());
//		System.out.printf("f2 : %s  \n",intermidiateFiles.getF2());
//		System.out.printf("fr2a : %s  \n",intermidiateFiles.getFr2a());
//		System.out.printf("fr2b : %s  \n",intermidiateFiles.getFr2b());
//		System.out.printf("fr3 : %s  \n",intermidiateFiles.getFr3());
//		System.out.printf("f1aa : %s  \n",f1aa);
//		System.out.printf("f1bb : %s  \n",f1bb);
//		System.out.printf("f2aa : %s  \n",f2aa);
//		System.out.printf("f2bb : %s  \n",f2bb);
//		System.out.printf("f3 : %s  \n",f3);
//		System.out.println("");
	}
	
	public void Step1() {
		String DnaSequence = intermidiateFiles.getDnaSequence();
		int A = 0, C = 0, G = 0, T = 0;
		int i;

		for (i = 0; i < DnaSequence.length(); i++) {
			if (DnaSequence.charAt(i) == 'A') {
				A++;
			} else if (DnaSequence.charAt(i) == 'C') {
				C++;
			} else if (DnaSequence.charAt(i) == 'G') {
				G++;
			} else {
				T++;
			}
		}
		int maxFreqCharCount = 0;
		maxFreqCharCount = Math.max(A, maxFreqCharCount);
		maxFreqCharCount = Math.max(C, maxFreqCharCount);
		maxFreqCharCount = Math.max(G, maxFreqCharCount);
		maxFreqCharCount = Math.max(T, maxFreqCharCount);
		if (A == maxFreqCharCount) {
			intermidiateFiles.setMaxFreqChar('A');
		} else if (C == maxFreqCharCount) {
			intermidiateFiles.setMaxFreqChar('C');
		} else if (G == maxFreqCharCount) {
			intermidiateFiles.setMaxFreqChar('G');
		} else {
			intermidiateFiles.setMaxFreqChar('T');
		}

		PairCharAndFreq arr[] = { new PairCharAndFreq(A, 'A'), new PairCharAndFreq(C, 'C'), new PairCharAndFreq(G, 'G'),
				new PairCharAndFreq(T, 'T') };
		Arrays.sort(arr, new SortByFreq());
		String order = "";
		// System.out.printf("values : %d %d %d %d \n",A,C,G,T);
		for (i = 3; i >= 0; i--) {
			order += arr[i].getGenome();
		}
		// System.out.println(order);
		intermidiateFiles.setOrder(order);
		//System.out.println(order);
		intermidiateFiles.setMaxFreqChar(order.charAt(0));
		//System.out.printf("ProcessIntermidiate files , order = ");
		//System.out.println(order);
		printStep(1);
	}

	public void Step2() {
		String DnaSequence = intermidiateFiles.getDnaSequence();
		char maxFreqChar = intermidiateFiles.getMaxFreqChar();
		//System.out.println(maxFreqChar);
		String fr1 = "", f1 = "";
		int i;
		for (i = 0; i < DnaSequence.length(); i++) {
			if (DnaSequence.charAt(i) == maxFreqChar) {
				fr1 += '1';
			} else {
				fr1 += '0';
				f1 += DnaSequence.charAt(i);
			}
		}
		intermidiateFiles.setFr1(fr1);
		intermidiateFiles.setF1(f1);
		
		//System.out.printf("ProcessIntermidiate files , fr1 = ");
		//System.out.println(fr1);
		//System.out.printf("ProcessIntermidiate files , f1 = ");
		//System.out.println(f1);
		
		//System.out.println("----STEP 2 DONE----");
		printStep(2);
	}

	public void Step3() {
		String fr1 = intermidiateFiles.getFr1(), fr1a = "", fr1b = "";
		int countConsecutiveZeros = 0, addZeros;
		int i, j;
		for (i = 0; i < fr1.length(); i++) {
			if (fr1.charAt(i) == '0') {
				countConsecutiveZeros++;
			} else {
				if (countConsecutiveZeros == 0) {
					// last number was one , do nothing
				} else if (countConsecutiveZeros % 2 == 0) {
					addZeros = countConsecutiveZeros / 2;
					for (j = 0; j < addZeros; j++) {
						fr1a += '0';
					}
					fr1b += '0';
				} else {
					addZeros = (countConsecutiveZeros + 1) / 2;
					for (j = 0; j < addZeros; j++) {
						fr1a += '0';
					}
					fr1b += '1';
				}
				countConsecutiveZeros = 0;
				fr1a += '1';
			}
		}
		if (countConsecutiveZeros == 0) {
			// last number was one , do nothing
		} else if (countConsecutiveZeros % 2 == 0) {
			addZeros = countConsecutiveZeros / 2;
			for (j = 0; j < addZeros; j++) {
				fr1a += '0';
			}
			fr1b += '0';
		} else {
			addZeros = (countConsecutiveZeros + 1) / 2;
			for (j = 0; j < addZeros; j++) {
				fr1a += '0';
			}
			fr1b += '1';
		}
		intermidiateFiles.setFr1a(fr1a);
		intermidiateFiles.setFr1b(fr1b);
		
		
		//System.out.printf("ProcessIntermidiate files , fr1a = ");
		//System.out.println(fr1a);
		//System.out.printf("ProcessIntermidiate files , fr1b = ");
		//System.out.println(fr1b);
		printStep(3);
		//System.out.println("----STEP 3 DONE----");
	}

	@Override
	public String toString() {
		return String.format(
				"ProcessIntermidiateFiles [intermidiateFiles=%s, f1aa=%s, f1bb=%s, f2aa=%s, f2bb=%s, f3=%s, f0=%s, addIfRemaiming=%s]",
				intermidiateFiles, f1aa, f1bb, f2aa, f2bb, f3, f0, addIfRemaiming);
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

	public void Step4() {
		int i;
		String key, value;
		String str = intermidiateFiles.getOrder().substring(1, 4);
		//System.out.println(str);
		Map<String, String> map = new HashMap<String, String>();
		for (i = 0; i < 3; i++) {
			key = "";
			key += str.charAt(i);
			key += str.charAt((i + 1) % 3);
			value = "10";
			value += str.charAt((i + 2) % 3);
			map.put(key, value);
			key = "";
			key += str.charAt((i + 1) % 3);
			key += str.charAt(i);
			value = "11";
			value += str.charAt((i + 2) % 3);
			map.put(key, value);
		}
		map.put("AA", "0A");
		map.put("CC", "0C");
		map.put("GG", "0G");
		map.put("TT", "0T");
		// System.out.println("Hey there! - put ends");
		intermidiateFiles.setMapOrder(map);
		String f1 = intermidiateFiles.getF1();
		//System.out.println(f1);
		// System.out.println(f1);
		String fr2a = "", fr2b = "", f2 = "";
		for (i = 0; i < f1.length(); i += 2) {
			if(i+2>f1.length())
			{
				break;
			}
			key = f1.substring(i, i + 2);
			value = map.get(key);
			if (value.length() == 2) {
				fr2a += value.charAt(0);
				f2 += value.charAt(1);
			} else {
				fr2a += value.charAt(0);
				fr2b += value.charAt(1);
				f2 += value.charAt(2);
			}
		}
		intermidiateFiles.setFr2a(fr2a);
		intermidiateFiles.setFr2b(fr2b);
		intermidiateFiles.setF2(f2);
		//System.out.printf("ProcessIntermidiate files , f2 = ");
		//System.out.println(f2);
		addIfRemaiming="";
		if(f1.length()%2==1)
		{
			addIfRemaiming+=f1.charAt(f1.length()-1);
		}
		addIfRemaiming+='#';
		//System.out.println("----STEP 4 DONE----");
		printStep(4);
	}

	public void Step5() {
		String f2 = intermidiateFiles.getF2(), fr3 = "";
		String order = intermidiateFiles.getOrder();
		//System.out.println("----STEP 5.1 ----");
		int i;
		Map<Character, String> map = new HashMap<Character, String>();
		map.put(order.charAt(1), "0");
		map.put(order.charAt(2), "10");
		map.put(order.charAt(3), "11");
		//System.out.println("----STEP 5.2 ----");
		//System.out.println(f2);
		for (i = 0; i < f2.length(); i++) {
			fr3 = fr3.concat(map.get(f2.charAt(i)));
		}
		//System.out.println("----STEP 5.3 ----");
		intermidiateFiles.setFr3(fr3);
		//System.out.printf("ProcessIntermidiate files , fr3 = ");
		//System.out.println(fr3);
		//System.out.println("----STEP 5 DONE----");
		printStep(5);
	}

	public void Step6() {
		String fr1a = intermidiateFiles.getFr1a(), fr1b = intermidiateFiles.getFr1b(),
				fr2a = intermidiateFiles.getFr2a(), fr2b = intermidiateFiles.getFr2b(),
				fr3 = intermidiateFiles.getFr3();

		BinaryToAscii binaryToAscii = new BinaryToAscii();
		f1aa = binaryToAscii.Convert(fr1a);
		f0="";
		f0 = f0.concat(binaryToAscii.AddSuffix(fr1a));
		f0 = f0.concat("#");
		f1bb = binaryToAscii.Convert(fr1b);
		f0 = f0.concat(binaryToAscii.AddSuffix(fr1b));
		f0 = f0.concat("#");
		f2aa = binaryToAscii.Convert(fr2a);
		f0 = f0.concat(binaryToAscii.AddSuffix(fr2a));
		f0 = f0.concat("#");
		f2bb = binaryToAscii.Convert(fr2b);
		f0 = f0.concat(binaryToAscii.AddSuffix(fr2b));
		f0 = f0.concat("#");
		f3 = binaryToAscii.Convert(fr3);
		f0 = f0.concat(binaryToAscii.AddSuffix(fr3));
		f0 = f0.concat("#");
		f0 = f0.concat(intermidiateFiles.getOrder());
		f0 = f0.concat("#");
		f0 = f0.concat(addIfRemaiming);
		//System.out.println("----STEP 6 DONE----");
		printStep(6);
	}

	public String[] doAllSteps(String dnaSequence) {
		intermidiateFiles.setDnaSequence(dnaSequence);
		this.Step1();
		this.Step2();
		this.Step3();
		this.Step4();
		this.Step5();
		this.Step6();
		//System.out.println(this.toString());
		return this.getAllFinalStrings();
	}

	public String[] getAllFinalStrings() {

		String[] finalStrings = { f1aa, f1bb, f2aa, f2bb, f3, f0 };
		return finalStrings;
	}
	
	
}
