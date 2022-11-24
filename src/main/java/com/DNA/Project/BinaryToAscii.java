package com.DNA.Project;

public class BinaryToAscii {

	public BinaryToAscii() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String Convert(String binaryString) {
		String str, ascii = "";
		int i;
		for (i = 0; i < binaryString.length(); i += 6) {
			if (i + 6 > binaryString.length()) {
				break;
			}
			str = binaryString.substring(i, i + 6);
			int num = Integer.parseInt(str);
			int base = 1;
			int dec_value = 0;
			int temp = num;
			while (temp > 0) {
				int last_digit = temp % 10;
				temp = temp / 10;

				dec_value += last_digit * base;

				base = base * 2;
			}
			dec_value += 60;
			char ch = (char) dec_value;
			ascii += ch;
		}
		return ascii;
	}

	public String AddSuffix(String binaryString) {
		int len = binaryString.length();
		if (len % 6 == 0) {
			return "";
		}
		int i = len / 6;
		String str = binaryString.substring(6 * i, len);
		return str;
	}

}
