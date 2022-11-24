package com.DNA.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		//System.out.println("Application started!");
		
		//String DnaSequence = "TGGACCGTTAATCCTTTTTTGAAGGACCTT";
		
		
		//CheckCompressionAndDecompression checkCompressionAndDecompression = new CheckCompressionAndDecompression();
		//checkCompressionAndDecompression.CheckLZ(DnaSequence);
		//checkCompressionAndDecompression.CheckAllSteps();
		
//		ProcessIntermidiateFiles processIntermidiateFiles = new ProcessIntermidiateFiles();
//		String[] finalStrings = processIntermidiateFiles.doAllSteps(DnaSequence);
//		FinalFiles finalFiles = new FinalFiles(finalStrings[0], finalStrings[1], finalStrings[2], finalStrings[3],
//				finalStrings[4], finalStrings[5]);
//		LZ77Compression lz77Compression = new LZ77Compression(finalFiles);
//		FinalCompressedFiles finalCompressedFiles = lz77Compression.ConvertAllFiles();
//		LZ77Decompression lz77Decompression = new LZ77Decompression();
//		FinalFiles finalFilesAfterLzDecompression = lz77Decompression
//				.ConvertFinalCompressedFilesToFinalFiles(finalCompressedFiles);
//		RegenerateOriginalDnaSequence regenerateOriginalDnaSequence = new RegenerateOriginalDnaSequence(finalFilesAfterLzDecompression);
//		String reConstructedDnaSequence = regenerateOriginalDnaSequence.ReverseAllSteps();
//		System.out.println(reConstructedDnaSequence);
		
		String x=Integer.toString(100);
		String y=Character.toString('c');
		System.out.println(x+y);
	}

}
