package com.DNA.Project;

import org.springframework.stereotype.Component;

@Component
public class AsciiToBinary {

	public AsciiToBinary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String ReverseString(String inputString)
	{
		String outputString="";
		for(int i=inputString.length()-1;i>=0;i--)
		{
			outputString+=inputString.charAt(i);
		}
		return outputString;
	}
	public String Convert(String asciiString)
	{
		String currentBinaryString="",binaryString="";
		int decimalValue;
		for(int i=0;i<asciiString.length();i++)
		{
			currentBinaryString="";
			decimalValue=asciiString.charAt(i)-'<';
			while(decimalValue>0)
			{
				if(decimalValue%2==0)
				{
					currentBinaryString+='0';
				}
				else
				{
					currentBinaryString+='1';
				}
				decimalValue/=2;
			}
			
			while(currentBinaryString.length()<6)
			{
				currentBinaryString+='0';
			}
			
			currentBinaryString=ReverseString(currentBinaryString);
			
			//System.out.printf("AsciiToBinary File, currentBinaryString: %s \n" ,currentBinaryString);			
			binaryString=binaryString.concat(currentBinaryString);
		}
		return binaryString;
	}
}
