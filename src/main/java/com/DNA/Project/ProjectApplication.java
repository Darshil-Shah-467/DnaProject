package com.DNA.Project;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

@SpringBootApplication
public class ProjectApplication implements ActionListener{
	
	private static JTextField dnaSequence, asciiString;
	private static JButton transformButton, revTransformButton, copy2cb;
	private static String toCopy="";
	
	public static void main(String[] args) {
		//SpringApplication.run(ProjectApplication.class, args);
		
		new ProjectApplication();
//		System.out.println("Press 0 to exit");
//		System.out.println("Press 1 to compress");
//		System.out.println("Press 2 to decompress");
		
		//for console
		//takeInput();
		
	}

//	uncomment for console
//	private static void takeInput() {
//		System.out.println("Enter your choice :");
//		Scanner myObj = new Scanner(System.in);
//		int choice = myObj.nextInt();
//		
//		if(choice==1)
//		{
//			System.out.println("Enter DNA string to compress:");
//			Scanner myObj2 = new Scanner(System.in);
//			String str = myObj2.nextLine(); 
////			System.out.print("You have entered: ");
////			System.out.println(str);			
//			doCompression(str);
//			takeInput();
//		}
//		else if(choice == 2)
//		{
//			System.out.println("Enter ASCII string to decompress:");
//			Scanner myObj2 = new Scanner(System.in);
//			String str = myObj2.nextLine(); 
////			System.out.print("You have entered: ");
////			System.out.println(str);
//			doDecompression(str);
//			takeInput();
//		}
//		else
//		{
//			//exit the application...
//			System.out.println("Thank you for using the application....");
//			System.out.println("Exiting Application....");
//		}
//	}
	
	
	static aes_crypt aes;
	private static void init_aes() {
		try {
			aes = new aes_crypt();
			aes.init();
		} catch (Exception e) {
			System.out.println("Unable to initialize AES engine!");
			e.printStackTrace();
		}
	}

	
	private static String doDecompression(String inputString) {
		// TODO Auto-generated method stub
		System.out.printf("Decompressing... \n");
		OneString oneString = new OneString();
		FinalCompressedFiles finalCompressedFiles = oneString.ConvertForDecompression(inputString);
		LZ77Decompression lz77Decompression = new LZ77Decompression();
		FinalFiles finalFilesAfterLzDecompression = lz77Decompression
				.ConvertFinalCompressedFilesToFinalFiles(finalCompressedFiles);
		/************************/

		ModifyF0 modifyF0 = new ModifyF0();
		finalFilesAfterLzDecompression.setF0(modifyF0.ModifyForDecompression(finalFilesAfterLzDecompression.getF0()));
		//System.out.printf("f0 === %s \n", finalFilesAfterLzDecompression.getF0());

		/************************/
		RegenerateOriginalDnaSequence regenerateOriginalDnaSequence = new RegenerateOriginalDnaSequence(
				finalFilesAfterLzDecompression);
		String reConstructedDnaSequence = regenerateOriginalDnaSequence.ReverseAllSteps();
		System.out.println("Decompressed String: "+reConstructedDnaSequence);
		return reConstructedDnaSequence;
	}

	
	public static String doCompression(String dnaSequence) {
		System.out.printf("Compressing... \n");
		ProcessIntermidiateFiles processIntermidiateFiles = new ProcessIntermidiateFiles();
		String[] finalStrings = processIntermidiateFiles.doAllSteps(dnaSequence);
		FinalFiles finalFiles = new FinalFiles(finalStrings[0], finalStrings[1], finalStrings[2], finalStrings[3],
				finalStrings[4], finalStrings[5]);
		/************************/

		ModifyF0 modifyF0 = new ModifyF0();
		finalFiles.setF0(modifyF0.ModifyForCompression(finalFiles.getF0()));
		//System.out.printf("f0 === %s \n", finalFiles.getF0());

		/************************/
		LZ77Compression lz77Compression = new LZ77Compression(finalFiles);
		FinalCompressedFiles finalCompressedFiles = lz77Compression.ConvertAllFiles();
		OneString oneString = new OneString();
		String ans = oneString.ConvertForCompression(finalCompressedFiles);
		System.out.println("Compressed String: "+ans);
		return ans;
	}
	
	
	
	
	public ProjectApplication()
	{
		JFrame frame  = new JFrame("DNA Compressor/Decompressor");
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		frame.setSize(400,250);
//		frame.setDefaultCloseOperation(JFrame.dispose());
		frame.add(panel);
		panel.setLayout(null);
		
		init_aes();
		
		JLabel compress = new JLabel("Input: ");
		compress.setBounds(50, 20, 100, 25);
		panel.add(compress);
		dnaSequence = new JTextField(20);
		dnaSequence.setBounds(150, 20, 165, 25);
		
		panel.add(dnaSequence);
		transformButton = new JButton("Transform");
		transformButton.setBounds(50, 50, 265, 20);
		transformButton.addActionListener(this);
		panel.add(transformButton);
		
		
		JLabel decompress = new JLabel("Input: ");
		decompress.setBounds(50, 100, 100, 25);
		panel.add(decompress);
		asciiString = new JTextField(20);
		asciiString.setBounds(150, 100, 165, 25);
		panel.add(asciiString);
		revTransformButton = new JButton("Reverse Transform");
		revTransformButton.setBounds(50, 130, 265, 20);
		revTransformButton.addActionListener(this);
		panel.add(revTransformButton);
		
		frame.setVisible(true);
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s1 = dnaSequence.getText();
		String s2 = asciiString.getText();
//		String toCopy = "";
		
		String ans="";
		
		if(e.getSource()==transformButton)
		{
			// Compress
			String compressed = doCompression(s1);
			
			// Compression Rate
			double outputLength = compressed.length();
			double inputLength = s1.length();
			double compression = (1.0-outputLength/inputLength);
			int x=(int) (compression*10000);
			compression = ((double)x)/100.00;
			System.out.printf("in_length = %f\tout_len = %f\tCompression = %f\n",inputLength,outputLength,compression);
			
			// Encrypt
			try {
				ans = aes.encrypt(compressed);
			} catch (Exception e1) {
				System.out.println("Encryption failed!");
				e1.printStackTrace();
			}
			System.out.println("Encrypted String: "+ans+"\n");
			toCopy = ans;
			
			JFrame frameOutput  = new JFrame("Transformation Result");
			JPanel panelOutput = new JPanel();
			panelOutput.setLayout(null);
			
			JLabel compression_out = new JLabel("Compression output: "+compressed);
			panelOutput.add(compression_out);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			
			JLabel compressionRatio = new JLabel("Compression: "+Double.toString(compression)+"%");
			panelOutput.add(compressionRatio);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 60, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			
			JLabel transformation_out = new JLabel("Compression+Encryption Output: "+ans);
			panelOutput.add(transformation_out);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 90, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			
			copy2cb = new JButton("Copy output to Clipboard");
			copy2cb.setBounds(30, 150, 265, 20);
			copy2cb.addActionListener(this);
			panelOutput.add(copy2cb);
			
			
			frameOutput.setSize(500,200);
//			frameOutput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frameOutput.add(panelOutput);
			frameOutput.setVisible(true);
			
		}
		
		else if(e.getSource()==revTransformButton)
		{
			// decrypt
			String decrypted="";
			try {
				decrypted = aes.decrypt(s2);
			} catch (Exception e1) {
				System.out.println("Decryption failed!");
				e1.printStackTrace();
			}
			System.out.println("Decrypted String: "+decrypted);
			
			// decompress
			ans = doDecompression(decrypted);
			
	
			JFrame frameOutput  = new JFrame("Reverse Transformation Result");
			JPanel panelOutput = new JPanel();
			panelOutput.setLayout(null);
			
			JLabel decrypted_out = new JLabel("Decryption output: "+decrypted);
			panelOutput.add(decrypted_out);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			
			JLabel answer = new JLabel("Decryption+Decompression Output: "+ans);
			panelOutput.add(answer);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 60, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			frameOutput.setSize(500,200);
//			frameOutput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			copy2cb = new JButton("Copy output to Clipboard");
			copy2cb.setBounds(30, 170, 265, 20);
			copy2cb.addActionListener(this);
			panelOutput.add(copy2cb);
			
			frameOutput.add(panelOutput);
			frameOutput.setVisible(true);
		}
		
		else if(e.getSource()==copy2cb) {
			StringSelection stringSelection = new StringSelection(toCopy);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			
			JFrame frameOutput  = new JFrame("Copy to Clipboard");
			JPanel panelOutput = new JPanel();
			panelOutput.setLayout(null);
			
			JLabel copy2cb_status = new JLabel("Output successfully copied to Clipboard.");
			panelOutput.add(copy2cb_status);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 120, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			
			frameOutput.setSize(500,100);
//			frameOutput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frameOutput.add(panelOutput);
			frameOutput.setVisible(true);
		}
	}

}


// Cross closes all windows
// Have to type input for re-transform manually