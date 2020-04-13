package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

class EditCommandTest {
	private LatexEditorController latexEditorController;
	private EditCommand editCommand;
	private CreateCommand createCommand;
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView; 
	
	public EditCommandTest() throws IOException {
		latexEditorController = new LatexEditorController();
		latexEditorView = new  LatexEditorView(latexEditorController);
		editCommand = new EditCommand(latexEditorController);
		versionsManager = new VersionsManager(latexEditorController);
		documentManager = new DocumentManager();
		createCommand = new CreateCommand(documentManager, versionsManager, latexEditorController);
		
		
	}
	@Test
	void test() {
		latexEditorController.getCurrentDocument().setDocumentType("articleTemplate");
		createCommand.execute();
		latexEditorView.setTypedText("test edit contents\n");
		editCommand.execute();
		String actualContents = latexEditorController.getCurrentDocument().getContents();
		
		assertEquals("test edit contents\n", actualContents);
	}

}
