package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;

class DisableVersionsManagementCommandTest {
	private LatexEditorController latexEditorController;
	private LatexEditorView latexEditorView;
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private CreateCommand createCommand;
	private EditCommand editCommand;
	private DisableVersionsManagementCommand disableCommand;

	public DisableVersionsManagementCommandTest() throws IOException {
		latexEditorController = new LatexEditorController();
		latexEditorView = new LatexEditorView(latexEditorController);
		documentManager = new DocumentManager();
		versionsManager = new VersionsManager(latexEditorController);
		createCommand = new CreateCommand(documentManager, versionsManager, latexEditorController);
		editCommand = new EditCommand(latexEditorController);
		disableCommand = new DisableVersionsManagementCommand(versionsManager);
	}
	@Test
	void testVolatile() {
		VersionsStrategy strategy = new VolatileVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		latexEditorController.getCurrentDocument().setDocumentType("articleTemplate");
		createCommand.execute();
		latexEditorView.setStrategy("volatile");
		disableCommand.execute();
		latexEditorView.setTypedText("test edit contents\n");
		editCommand.execute();
		
		assertEquals(versionsManager.isEnabled(), false);
		assertEquals(strategy.getEntireHistory().size(), 0);
	}
}
