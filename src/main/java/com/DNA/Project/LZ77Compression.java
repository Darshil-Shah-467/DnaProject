package com.DNA.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class LZ77Compression {
	private FinalFiles oldFiles;

	public LZ77Compression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LZ77Compression(FinalFiles oldFiles) {
		super();
		this.oldFiles = oldFiles;
	}

	public List<PairIntAndChar> ConvertUsingLzAlgo(String oldString) {
		oldString+=';';
		List<PairIntAndChar> lzList = new ArrayList<PairIntAndChar>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		String prefix = "";
		map.put("", 0);
		int count = 0, position,i;
		for (i = 0; i < oldString.length(); i++) {
			prefix += oldString.charAt(i);
			if (map.containsKey(prefix) == false) {
				String key = prefix.substring(0, prefix.length() - 1);
				position = map.get(key);
				PairIntAndChar pairIntAndChar = new PairIntAndChar(position, oldString.charAt(i));
				lzList.add(pairIntAndChar);
				map.put(prefix, ++count);
				prefix = "";
			}
		}
		//System.out.println(prefix);
		/*
		if (map.containsKey(prefix) == false) {
			String key = prefix.substring(0, prefix.length() - 1);
			position = map.get(key);
			PairIntAndChar pairIntAndChar = new PairIntAndChar(position, oldString.charAt(i));
			lzList.add(pairIntAndChar);
			map.put(prefix, ++count);
			prefix = "";
		}
		*/
		return lzList;
	}

	public FinalCompressedFiles ConvertAllFiles() {
		String f1aa = oldFiles.getF1aa(), f1bb = oldFiles.getF1bb(), f2aa = oldFiles.getF2aa(),
				f2bb = oldFiles.getF2bb(), f3 = oldFiles.getF3(), f0 = oldFiles.getF0();
		FinalCompressedFiles finalCompressedFiles = new FinalCompressedFiles();
		finalCompressedFiles.setF0(this.ConvertUsingLzAlgo(f0));
		finalCompressedFiles.setF1aa(this.ConvertUsingLzAlgo(f1aa));
		finalCompressedFiles.setF1bb(this.ConvertUsingLzAlgo(f1bb));
		finalCompressedFiles.setF2aa(this.ConvertUsingLzAlgo(f2aa));
		finalCompressedFiles.setF2bb(this.ConvertUsingLzAlgo(f2bb));
		finalCompressedFiles.setF3(this.ConvertUsingLzAlgo(f3));
		printFile(finalCompressedFiles);
		return finalCompressedFiles;
	}

	private void printFile(FinalCompressedFiles finalCompressedFiles) {
		// TODO Auto-generated method stub
		System.out.println("Step 7 :");
		System.out.printf("f0 : ");
		for(int i=0;i<finalCompressedFiles.getF0().size();i++)
		{
			int i1 = finalCompressedFiles.getF0().get(i).getPrefix();
			char c1 = finalCompressedFiles.getF0().get(i).getSuffix();
			System.out.printf("{%d , %c} ", i1,c1);
		}
		System.out.println("");
		System.out.printf("f1aa : ");
		for(int i=0;i<finalCompressedFiles.getF1aa().size();i++)
		{
			int i1 = finalCompressedFiles.getF1aa().get(i).getPrefix();
			char c1 = finalCompressedFiles.getF1aa().get(i).getSuffix();
			System.out.printf("{%d , %c} ", i1,c1);
		}
		System.out.println("");
		System.out.printf("f1bb : ");
		for(int i=0;i<finalCompressedFiles.getF1bb().size();i++)
		{
			int i1 = finalCompressedFiles.getF1bb().get(i).getPrefix();
			char c1 = finalCompressedFiles.getF1bb().get(i).getSuffix();
			System.out.printf("{%d , %c} ", i1,c1);
		}
		System.out.println("");
		System.out.printf("f2aa : ");
		for(int i=0;i<finalCompressedFiles.getF2aa().size();i++)
		{
			int i1 = finalCompressedFiles.getF2aa().get(i).getPrefix();
			char c1 = finalCompressedFiles.getF2aa().get(i).getSuffix();
			System.out.printf("{%d , %c} ", i1,c1);
		}
		System.out.println("");
		System.out.printf("f2bb : ");
		for(int i=0;i<finalCompressedFiles.getF2bb().size();i++)
		{
			int i1 = finalCompressedFiles.getF2bb().get(i).getPrefix();
			char c1 = finalCompressedFiles.getF2bb().get(i).getSuffix();
			System.out.printf("{%d , %c} ", i1,c1);
		}
		System.out.println("");
		System.out.printf("f3 : ");
		for(int i=0;i<finalCompressedFiles.getF3().size();i++)
		{
			int i1 = finalCompressedFiles.getF3().get(i).getPrefix();
			char c1 = finalCompressedFiles.getF3().get(i).getSuffix();
			System.out.printf("{%d , %c} ", i1,c1);
		}
		System.out.println("");
	}

}
