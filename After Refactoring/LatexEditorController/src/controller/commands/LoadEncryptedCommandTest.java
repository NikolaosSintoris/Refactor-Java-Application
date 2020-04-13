package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

class LoadEncryptedCommandTest {
	private LatexEditorController latexEditorController;
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private CreateCommand createCommand;
	private LoadEncryptedCommand loadEncryptedCommand;
	private LatexEditorView latexEditorView;
	private SaveEncryptedCommand saveEncryptedCommand;

	public LoadEncryptedCommandTest() throws IOException {
		latexEditorController = new LatexEditorController();
		latexEditorView = new LatexEditorView(latexEditorController);
		documentManager = new DocumentManager();
		versionsManager = latexEditorController.getVersionsManager();
		createCommand = new CreateCommand(documentManager, versionsManager, latexEditorController);
		loadEncryptedCommand  = new LoadEncryptedCommand(latexEditorController);
		saveEncryptedCommand =  new SaveEncryptedCommand(latexEditorController);
	}
	
	@Test
	void loadAtbashFile() {
		latexEditorController.getCurrentDocument().setDocumentType("emptyTemplate");
		createCommand.execute();
		latexEditorController.getCurrentDocument().setContents("nikos");
		
		String actualContents = "nikos\n";
		latexEditorController.setFilename("test.tex");
		latexEditorView.setEncryptionKey(25);
		
		saveEncryptedCommand.execute();
		
		loadEncryptedCommand.execute();
		String fileContents = latexEditorController.getCurrentDocument().getContents();
		assertEquals(fileContents, actualContents);
	}
	
	void loadRot13File() {
		latexEditorController.getCurrentDocument().setDocumentType("emptyTemplate");
		createCommand.execute();
		latexEditorController.getCurrentDocument().setContents("nikos");
		
		String actualContents = "nikos\n";
		latexEditorController.setFilename("test.tex");
		latexEditorView.setEncryptionKey(13);
		
		saveEncryptedCommand.execute();
		
		loadEncryptedCommand.execute();
		String fileContents = latexEditorController.getCurrentDocument().getContents();
		assertEquals(fileContents, actualContents);
	}

}
