package com.DNA.Project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OneString {

	private int itr;
	public OneString() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String ConvertForCompression(FinalCompressedFiles finalfiles)
	{
		String str="";
		for(int i=0;i<finalfiles.getF0().size();i++)
		{
			str=str.concat(Integer.toString(finalfiles.getF0().get(i).getPrefix()));
			str=str.concat(Character.toString(finalfiles.getF0().get(i).getSuffix()));
		}
		str+='|';
		for(int i=0;i<finalfiles.getF1aa().size();i++)
		{
			str=str.concat(Integer.toString(finalfiles.getF1aa().get(i).getPrefix()));
			str=str.concat(Character.toString(finalfiles.getF1aa().get(i).getSuffix()));
		}
		str+='|';
		for(int i=0;i<finalfiles.getF1bb().size();i++)
		{
			str=str.concat(Integer.toString(finalfiles.getF1bb().get(i).getPrefix()));
			str=str.concat(Character.toString(finalfiles.getF1bb().get(i).getSuffix()));
		}
		str+='|';
		for(int i=0;i<finalfiles.getF2aa().size();i++)
		{
			str=str.concat(Integer.toString(finalfiles.getF2aa().get(i).getPrefix()));
			str=str.concat(Character.toString(finalfiles.getF2aa().get(i).getSuffix()));
		}
		str+='|';
		for(int i=0;i<finalfiles.getF2bb().size();i++)
		{
			str=str.concat(Integer.toString(finalfiles.getF2bb().get(i).getPrefix()));
			str=str.concat(Character.toString(finalfiles.getF2bb().get(i).getSuffix()));
		}
		str+='|';
		for(int i=0;i<finalfiles.getF3().size();i++)
		{
			str=str.concat(Integer.toString(finalfiles.getF3().get(i).getPrefix()));
			str=str.concat(Character.toString(finalfiles.getF3().get(i).getSuffix()));
		}
		str+='|';
		return str;
	}
	
	public FinalCompressedFiles ConvertForDecompression(String str)
	{
		FinalCompressedFiles finalCompressedFiles = new FinalCompressedFiles();
		itr=0;
		finalCompressedFiles.setF0(Helper(str, itr));
		System.out.println("F0 set....");
		finalCompressedFiles.setF1aa(Helper(str, itr));
		System.out.println("F1aa set....");
		finalCompressedFiles.setF1bb(Helper(str, itr));
		System.out.println("F1bb set....");
		finalCompressedFiles.setF2aa(Helper(str, itr));
		System.out.println("F2aa set....");
		finalCompressedFiles.setF2bb(Helper(str, itr));
		System.out.println("F2bb set....");
		finalCompressedFiles.setF3(Helper(str, itr));
		System.out.println("F3 set....");
		return finalCompressedFiles;
	}
	
	public List<PairIntAndChar> Helper(String str,int i)
	{
		String s="";
		while(str.charAt(i)!='|')
		{
			s+=str.charAt(i);
			i++;
		}
		System.out.println(s);
		List<PairIntAndChar> lzList = new ArrayList<PairIntAndChar>();
		int j=0;
		while(j<s.length())
		{
			String num="";
			while(j<s.length() && s.charAt(j)>='0' && s.charAt(j)<='9')
			{
				num+=s.charAt(j);
				j++;
			}
			PairIntAndChar pairIntAndChar = new PairIntAndChar(Integer.valueOf(num),s.charAt(j));
			lzList.add(pairIntAndChar);
			j++;
		}
		i++;
		itr=i;
		return lzList;
	}
}
