package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import view.LatexEditorView;

class RollbackToPreviousVersionCommandTest {
	private LatexEditorController latexEditorController;
	private LatexEditorView latexEditorView;
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private CreateCommand createCommand;
	private EditCommand editCommand;
	private EnableVersionsManagementCommand enableCommand;
	private RollbackToPreviousVersionCommand rollback;

	public RollbackToPreviousVersionCommandTest() throws IOException {
		latexEditorController = new LatexEditorController();
		latexEditorView = new LatexEditorView(latexEditorController);
		documentManager = new DocumentManager();
		versionsManager = latexEditorController.getVersionsManager();
		createCommand = new CreateCommand(documentManager, versionsManager, latexEditorController);
		editCommand = new EditCommand(latexEditorController);
		enableCommand = new EnableVersionsManagementCommand(versionsManager);
		rollback = new RollbackToPreviousVersionCommand(versionsManager);
	}
	
	@Test
	void testStable() {
		VersionsStrategy strategy = new StableVersionsStrategy();
		versionsManager.setStrategy(strategy);		
		latexEditorController.getCurrentDocument().setDocumentType("articleTemplate");
		createCommand.execute();
		String actualContents = latexEditorController.getCurrentDocument().getContents();
		latexEditorView.setStrategy("stable");
		enableCommand.execute();
		latexEditorView.setTypedText(latexEditorController.getCurrentDocument().getContents());
		editCommand.execute();
		latexEditorView.setTypedText("test edit contents\n");
		latexEditorController.getCurrentDocument().setContents(latexEditorController.getLatexEditorView().getTypedText());
		rollback.execute();
		String contents = latexEditorController.getCurrentDocument().getContents();
		
		assertEquals(contents, actualContents);
	}
}
