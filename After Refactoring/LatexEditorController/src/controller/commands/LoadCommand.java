package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;


public class LoadCommand implements Command {
	private VersionsManager versionsManager;
	private LatexEditorController latexEditorController;
	
	public LoadCommand(VersionsManager versionsManager, LatexEditorController latexEditorController) {
		super();
		this.versionsManager = versionsManager;
		this.latexEditorController = latexEditorController;
	}

	public VersionsManager getVersionsManager() {
		return versionsManager;
	}

	public void setVersionsManager(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
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
		Document newDocument = new Document();
		newDocument.setContents(fileContents);
		latexEditorController.setCurrentDocument(newDocument);
		
		String documentType = "emptyTemplate";
		fileContents = fileContents.trim();
		if(fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
			documentType = "articleTemplate";
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
			documentType = "bookTemplate";
		}
		else if(fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
			documentType = "reportTemplate";
		}
		else if(fileContents.startsWith("\\documentclass{letter}")) {
			documentType = "letterTemplate";
		}
		latexEditorController.getCurrentDocument().setDocumentType(documentType);
	}

}
