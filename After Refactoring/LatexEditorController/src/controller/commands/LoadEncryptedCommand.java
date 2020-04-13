package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import controller.LatexEditorController;
import model.Document;

public class LoadEncryptedCommand extends CodecCommand implements Command{
	static final int ATBASH_KEY = 25;
	static final int ROT_13_KEY = 13;
	static final int DECRYPTION = -1;
	private LatexEditorController latexEditorController;
	private HashMap<Integer, String> encryptionModeMap;

	public LoadEncryptedCommand(LatexEditorController latexEditorController) {
		this.latexEditorController = latexEditorController;
		encryptionModeMap = new HashMap<>();
		encryptionModeMap.put(ATBASH_KEY, "#");
		encryptionModeMap.put(ROT_13_KEY, "*");
	}
	
	public void execute() {
		
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(latexEditorController.getFilename()));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		char[] contentsCharArray = fileContents.toCharArray(); 
		
		if(contentsCharArray[0] == '#') {
			contentsCharArray = atbashCodec(contentsCharArray);	
		}else {
			contentsCharArray = rot13Codec(contentsCharArray, DECRYPTION);
		}
		contentsCharArray = Arrays.copyOfRange(contentsCharArray, 1, contentsCharArray.length);
		fileContents =  String.copyValueOf(contentsCharArray);
		
		Document newDocument = new Document();
		newDocument.setContents(fileContents);
		latexEditorController.setCurrentDocument(newDocument);
		
		String type = "emptyTemplate";
		fileContents = fileContents.trim();
		if(fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
			type = "articleTemplate";
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
			type = "bookTemplate";
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
			type = "reportTemplate";
		}
		else if(fileContents.startsWith("\\documentclass{letter}")) {
			type = "letterTemplate";
		}
		latexEditorController.getCurrentDocument().setDocumentType(type);
	}

}
