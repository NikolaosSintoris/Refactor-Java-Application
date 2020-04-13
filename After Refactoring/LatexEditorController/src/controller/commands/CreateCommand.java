package controller.commands;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private LatexEditorController latexEditorController;
	
	public CreateCommand(DocumentManager documentManager, VersionsManager versionsManager, LatexEditorController latexEditorController) {
		super();
		this.documentManager = documentManager;
		this.versionsManager = versionsManager;
		this.latexEditorController = latexEditorController;
	}

	public void execute() {
		String documentType = latexEditorController.getCurrentDocument().getDocumentType();
		Document document = documentManager.createDocument(documentType);
		versionsManager.setCurrentVersion(document);
	}

}
