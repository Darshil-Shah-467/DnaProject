package com.DNA.Project;

import org.springframework.stereotype.Component;

@Component
public class CompressionRatio {
	private double inputFileSize,outputFileSize,compressionRatio;

	public CompressionRatio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompressionRatio(double inputFileSize, double outputFileSize, double compressionRatio) {
		super();
		this.inputFileSize = inputFileSize;
		this.outputFileSize = outputFileSize;
		this.compressionRatio = compressionRatio;
	}

	public double getInputFileSize() {
		return inputFileSize;
	}

	public void setInputFileSize(double inputFileSize) {
		this.inputFileSize = inputFileSize;
	}

	public double getOutputFileSize() {
		return outputFileSize;
	}

	public void setOutputFileSize(double outputFileSize) {
		this.outputFileSize = outputFileSize;
	}

	public double getCompressionRatio() {
		return compressionRatio;
	}

	public void setCompressionRatio(double compressionRatio) {
		this.compressionRatio = compressionRatio;
	}
	
	public CompressionRatio findCompression(String dnaSequence)
	{
		ProcessIntermidiateFiles processIntermidiateFiles = new ProcessIntermidiateFiles();
		String[] finalStrings = processIntermidiateFiles.doAllSteps(dnaSequence);
		FinalFiles finalFiles = new FinalFiles(finalStrings[0], finalStrings[1], finalStrings[2], finalStrings[3],
				finalStrings[4], finalStrings[5]);
		LZ77Compression lz77Compression = new LZ77Compression(finalFiles);
		FinalCompressedFiles finalCompressedFiles = lz77Compression.ConvertAllFiles();
		outputFileSize=0;
		outputFileSize+=finalCompressedFiles.getF0().size()*6;
		outputFileSize+=finalCompressedFiles.getF1aa().size()*6;
		outputFileSize+=finalCompressedFiles.getF1bb().size()*6;
		outputFileSize+=finalCompressedFiles.getF2aa().size()*6;
		outputFileSize+=finalCompressedFiles.getF2bb().size()*6;
		outputFileSize+=finalCompressedFiles.getF3().size()*6;
		inputFileSize=2*dnaSequence.length();
		compressionRatio=outputFileSize/inputFileSize;
		return new CompressionRatio(inputFileSize,outputFileSize,compressionRatio);
	}
	
}
