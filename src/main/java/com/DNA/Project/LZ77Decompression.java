package com.DNA.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class LZ77Decompression {
	

	public LZ77Decompression() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String Decompress(List<PairIntAndChar> inputList) {
		String output = "";
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "");
		int i;
		for(i=0;i<inputList.size();i++)
		{
			//System.out.println(inputList.get(i).toString());
			String currentString=map.get(inputList.get(i).getPrefix());
			currentString+=inputList.get(i).getSuffix();
			//System.out.println(currentString);
			map.put(i+1, currentString);
			output=output.concat(currentString);
		}
		//System.out.println(output);
		return output;
	}
	
	public FinalFiles ConvertFinalCompressedFilesToFinalFiles(FinalCompressedFiles finalCompressedFiles)
	{
		FinalFiles finalFiles = new FinalFiles();
		String str;
		str=Decompress(finalCompressedFiles.getF0());
		str=str.substring(0, str.length()-1);
		finalFiles.setF0(str);
		str=Decompress(finalCompressedFiles.getF1aa());
		str=str.substring(0, str.length()-1);
		finalFiles.setF1aa(str);
		str=Decompress(finalCompressedFiles.getF1bb());
		str=str.substring(0, str.length()-1);
		finalFiles.setF1bb(str);
		str=Decompress(finalCompressedFiles.getF2aa());
		str=str.substring(0, str.length()-1);
		finalFiles.setF2aa(str);
		str=Decompress(finalCompressedFiles.getF2bb());
		str=str.substring(0, str.length()-1);
		finalFiles.setF2bb(str);
		str=Decompress(finalCompressedFiles.getF3());
		str=str.substring(0, str.length()-1);
		finalFiles.setF3(str);
		return finalFiles;
	}
	

}
