package com.DNA.Project;

import org.springframework.stereotype.Component;

@Component
public class CheckCompressionAndDecompression {

	private FinalFiles finalStrings;
	private String dnaSequence;
	
	
	public String getDnaSequence() {
		return dnaSequence;
	}

	public void setDnaSequence(String dnaSequence) {
		this.dnaSequence = dnaSequence;
	}

	public CheckCompressionAndDecompression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void CheckLZ(String dnaSequence) {
		ProcessIntermidiateFiles processIntermidiateFiles = new ProcessIntermidiateFiles();
		String[] finalStringsBeforeLzCompression = processIntermidiateFiles.doAllSteps(dnaSequence);
		FinalFiles finalFilesBeforeLzCompression = new FinalFiles(finalStringsBeforeLzCompression[0],
				finalStringsBeforeLzCompression[1], finalStringsBeforeLzCompression[2],
				finalStringsBeforeLzCompression[3], finalStringsBeforeLzCompression[4],
				finalStringsBeforeLzCompression[5]);

		System.out.println(finalFilesBeforeLzCompression.toString());

		LZ77Compression lz77Compression = new LZ77Compression(finalFilesBeforeLzCompression);
		FinalCompressedFiles finalCompressedFiles = lz77Compression.ConvertAllFiles();

		LZ77Decompression lz77Decompression = new LZ77Decompression();
		FinalFiles finalFilesAfterLzDecompression = lz77Decompression
				.ConvertFinalCompressedFilesToFinalFiles(finalCompressedFiles);

		System.out.println(finalFilesAfterLzDecompression.toString());
		this.finalStrings=finalFilesAfterLzDecompression;
		
		this.dnaSequence=dnaSequence;
	}
	
	public void CheckAllSteps()
	{
		RegenerateOriginalDnaSequence regenerateOriginalDnaSequence = new RegenerateOriginalDnaSequence(finalStrings);
		String reConstructedDnaSequence = regenerateOriginalDnaSequence.ReverseAllSteps();
		System.out.println(dnaSequence);
		System.out.println(reConstructedDnaSequence);
	}
	
	public FinalFiles getFinalStrings() {
		return finalStrings;
	}

	public void setFinalStrings(FinalFiles finalStrings) {
		this.finalStrings = finalStrings;
	}

	@Override
	public String toString() {
		return String.format("CheckCompressionAndDecompression [finalStrings=%s, dnaSequence=%s]", finalStrings,
				dnaSequence);
	}
}
