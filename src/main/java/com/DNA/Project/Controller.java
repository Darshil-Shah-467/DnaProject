package com.DNA.Project;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class Controller {

	// Phase-1 of compression
	@GetMapping("/compress/requestIntermidiateFiles/{dnaSequence}")
	public FinalFiles CompressDnaSequenceWithoutLz77(@PathVariable String dnaSequence) {
		ProcessIntermidiateFiles processIntermidiateFiles = new ProcessIntermidiateFiles();
		String[] finalStrings = processIntermidiateFiles.doAllSteps(dnaSequence);
		FinalFiles finalFiles = new FinalFiles(finalStrings[0], finalStrings[1], finalStrings[2], finalStrings[3],
				finalStrings[4], finalStrings[5]);
		return finalFiles;
	}
	
	

	// Complete Compression
	@GetMapping("/compress/{dnaSequence}")
	public FinalCompressedFiles CompressDnaSequenceWithLz77(@PathVariable String dnaSequence) {
		System.out.println("Compressing...");
		ProcessIntermidiateFiles processIntermidiateFiles = new ProcessIntermidiateFiles();
		String[] finalStrings = processIntermidiateFiles.doAllSteps(dnaSequence);
		FinalFiles finalFiles = new FinalFiles(finalStrings[0], finalStrings[1], finalStrings[2], finalStrings[3],
				finalStrings[4], finalStrings[5]);
		/************************/

		ModifyF0 modifyF0 = new ModifyF0();
		finalFiles.setF0(modifyF0.ModifyForCompression(finalFiles.getF0()));
		System.out.printf("f0 === %s \n", finalFiles.getF0());

		/************************/
		LZ77Compression lz77Compression = new LZ77Compression(finalFiles);
		FinalCompressedFiles finalCompressedFiles = lz77Compression.ConvertAllFiles();
		return finalCompressedFiles;
	}
	
	@GetMapping("test/compress/{dnaSequence}")
	public String TestCompressDnaSequenceWithLz77(@PathVariable String dnaSequence) {
		System.out.println("Compressing...");
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
		//System.out.println(ans);
		return ans;
	}

	// Compression Metric
	@GetMapping("/requestCompressionRatio/{dnaSequence}")
	public CompressionRatio GetCompressionRatio(@PathVariable String dnaSequence) {
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
		String output = oneString.ConvertForCompression(finalCompressedFiles);
		
		double outputLength = output.length();
		double inputLength = dnaSequence.length();
		
		CompressionRatio compressionRatio = new CompressionRatio();
		compressionRatio.setCompressionRatio(1.0-outputLength/inputLength);
		compressionRatio.setInputFileSize(inputLength);
		compressionRatio.setOutputFileSize(outputLength);
		return compressionRatio;
	}

	// Complete Decompression
	@PostMapping("/decompress")
	public String DecompressDnaSequenceWithLz77(@RequestBody FinalCompressedFiles finalCompressedFiles) {
		System.out.println("Decompressing...");
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
		return reConstructedDnaSequence;
	}

	// Phase-2 of compression
	@GetMapping("/decompress/requestDnaSequence/{finalStrings}")
	public String RetriveOriginalDnaSequence(@PathVariable FinalFiles finalStrings) {
		RegenerateOriginalDnaSequence regenerateOriginalDnaSequence = new RegenerateOriginalDnaSequence(finalStrings);
		String reConstructedDnaSequence = regenerateOriginalDnaSequence.ReverseAllSteps();
		return reConstructedDnaSequence;
	}
	
	@PostMapping("/test/decompress")
	public String TestDecompressDnaSequenceWithLz77(@RequestBody String inputString) {
		System.out.printf("Decompressing.... \n");
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
		return reConstructedDnaSequence;
	}
	
	
}
