package com.DNA.Project;

import org.springframework.stereotype.Component;

@Component
public class ModifyF0 {
	
	public ModifyF0() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String ModifyForCompression(String f0)
	{
		f0=f0.replace('0', '+');
		f0=f0.replace('1', '*');
		//System.out.println(f0);
		return f0;
	}
	
	public String ModifyForDecompression(String f0)
	{
		f0=f0.replace('+', '0');
		f0=f0.replace('*', '1');
		return f0;
	}

}
