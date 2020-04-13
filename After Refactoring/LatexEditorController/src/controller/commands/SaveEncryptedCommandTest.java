package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

class SaveEncryptedCommandTest {
	private LatexEditorController latexEditorController;
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private CreateCommand createCommand;
	private SaveEncryptedCommand saveEncryptedCommand;
	private LoadCommand loadCommand;
	private LatexEditorView latexEditorView;
	
	public SaveEncryptedCommandTest() throws IOException {
		latexEditorController = new LatexEditorController();
		latexEditorView = new LatexEditorView(latexEditorController);
		documentManager = new DocumentManager();
		versionsManager = latexEditorController.getVersionsManager();
		createCommand = new CreateCommand(documentManager, versionsManager, latexEditorController);
		saveEncryptedCommand =  new SaveEncryptedCommand(latexEditorController);
		loadCommand = new LoadCommand(versionsManager, latexEditorController);
	}
	@Test
	void testAtbashEncryption() {
		latexEditorController.getCurrentDocument().setDocumentType("emptyTemplate");
		createCommand.execute();
		latexEditorController.getCurrentDocument().setContents("nikos");
	
		String actualContents = "#mrplh\n";
		latexEditorController.setFilename("test.tex");
		latexEditorView.setEncryptionKey(25);
		
		saveEncryptedCommand.execute();
		
		loadCommand.execute();
		String fileContents = latexEditorController.getCurrentDocument().getContents();
		assertEquals(fileContents, actualContents);
	}
	
	@Test
	void testRot13Encryption() {
		latexEditorController.getCurrentDocument().setDocumentType("emptyTemplate");
		createCommand.execute();
		latexEditorController.getCurrentDocument().setContents("nikos");
	
		String actualContents = "*avxbf\n";
		latexEditorController.setFilename("test.tex");
		latexEditorView.setEncryptionKey(13);
		
		saveEncryptedCommand.execute();
		
		loadCommand.execute();
		String fileContents = latexEditorController.getCurrentDocument().getContents();
		assertEquals(fileContents, actualContents);
	}

}
