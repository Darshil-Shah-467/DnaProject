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

@SpringBootApplication
public class ProjectApplication implements ActionListener{
	
	private static JTextField dnaSequence,asciiString;
	private static JButton compressButton,decompressButton;
	private static JLabel output;
	
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

	private static String doDecompression(String inputString) {
		// TODO Auto-generated method stub
		System.out.printf("Decompressing .... \n");
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
		System.out.println("Decompressed String :");
		System.out.println(reConstructedDnaSequence);
		return reConstructedDnaSequence;
	}

	public static String doCompression(String dnaSequence) {
		System.out.printf("Compressing .... \n");
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
		System.out.println("Compressed String :");
		//System.out.println(ans);
		return ans;
	}
	
	public ProjectApplication()
	{
		JFrame frame  = new JFrame("DNA Compressor/Decompressor");
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		frame.setSize(400,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		
		
		JLabel compress = new JLabel("Compress : ");
		compress.setBounds(50, 20, 100, 25);
		panel.add(compress);
		dnaSequence = new JTextField(20);
		dnaSequence.setBounds(150, 20, 165, 25);
		
		panel.add(dnaSequence);
		compressButton = new JButton("compress");
		compressButton.setBounds(50, 50, 265, 20);
		compressButton.addActionListener(this);
		panel.add(compressButton);
		
		
		JLabel decompress = new JLabel("Decompress : ");
		decompress.setBounds(50, 100, 100, 25);
		panel.add(decompress);
		asciiString = new JTextField(20);
		asciiString.setBounds(150, 100, 165, 25);
		panel.add(asciiString);
		decompressButton = new JButton("decompress");
		decompressButton.setBounds(50, 130, 265, 20);
		decompressButton.addActionListener(this);
		panel.add(decompressButton);
		
		output = new JLabel("");
		output.setBounds(100, 160, 165, 25);
		panel.add(output);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s1=dnaSequence.getText(); 
		System.out.println(s1);
		String s2="";
		s2=asciiString.getText();
		System.out.println(s2);
		
		String ans="";
		
		if(e.getSource()==compressButton)
		{
			// decompress
			ans=doCompression(s1);
			
			double outputLength = ans.length();
			double inputLength = s1.length();
			double compression = (1.0-outputLength/inputLength);
			int x=(int) (compression*10000);
			compression = ((double)x)/100.00;
			System.out.printf("Input : %f Output : %f Compression : %f \n",inputLength,outputLength,compression);
			
			JFrame frameOutput  = new JFrame("Compression Result");
			JPanel panelOutput = new JPanel();
			panelOutput.setLayout(null);
			JLabel answer = new JLabel("Output : "+ans);
			panelOutput.add(answer);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			
			JLabel compressionRatio = new JLabel("Compression : "+Double.toString(compression)+"%");
			panelOutput.add(compressionRatio);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 60, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			
			frameOutput.setSize(500,200);
			frameOutput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frameOutput.add(panelOutput);
			frameOutput.setVisible(true);
			
		}
		else
		{
			//compress
			ans=doDecompression(s2);
			JFrame frameOutput  = new JFrame("Decompression Result");
			JPanel panelOutput = new JPanel();
			panelOutput.setLayout(null);
			JLabel answer = new JLabel("Output : "+ans);
			//answer.setBounds(10, 100, 100, 25);
			panelOutput.add(answer);
			panelOutput.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
			panelOutput.setLayout(new GridLayout(0,1));
			frameOutput.setSize(500,100);
			frameOutput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frameOutput.add(panelOutput);
			frameOutput.setVisible(true);
		}
		
		System.out.println(ans);
		
		
	}
}
