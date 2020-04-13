package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;

class ChangeVersionsStrategyCommandTest {
	private VersionsManager versionsManager;
	private LatexEditorController latexEditorController;
	private ChangeVersionsStrategyCommand changeCommand;
	private LatexEditorView latexEditorView;

	
	public ChangeVersionsStrategyCommandTest() throws IOException{
		latexEditorController = new LatexEditorController();
		latexEditorView = new LatexEditorView(latexEditorController);
		versionsManager = new VersionsManager(latexEditorController);
		changeCommand = new ChangeVersionsStrategyCommand(versionsManager);
		
	}
	@Test
	void testVolatile() {
		VersionsStrategy strategy = new VolatileVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		
		latexEditorController.getCurrentDocument().setDocumentType("articleTemplate");
		//latexEditorView.setVersionsManager(versionsManager);
		latexEditorView.setStrategy("stable");
		changeCommand.execute();
		String test = "test ok";
		if(versionsManager.getStrategy() instanceof VolatileVersionsStrategy)
			test = "not ok";
		
		assertEquals("test ok", test);
	}
	
	@Test
	void testStable() {
		VersionsStrategy strategy = new StableVersionsStrategy();
		versionsManager.setStrategy(strategy);
		
		latexEditorController.getCurrentDocument().setDocumentType("articleTemplate");
		//latexEditorView.setVersionsManager(versionsManager);
		latexEditorView.setStrategy("volatile");
		changeCommand.execute();
		
		String test = "test ok";
		if(versionsManager.getStrategy() instanceof StableVersionsStrategy)
			test = "not ok";
		
		assertEquals("test ok", test);
	}
		
}
