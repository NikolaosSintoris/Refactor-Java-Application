package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;

class SaveCommandTest {
	private LatexEditorController latexEditorController;
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private CreateCommand createCommand;
	private SaveCommand saveCommand;
	
	public SaveCommandTest() throws IOException {
		latexEditorController = new LatexEditorController();
		documentManager = new DocumentManager();
		versionsManager = latexEditorController.getVersionsManager();
		createCommand = new CreateCommand(documentManager, versionsManager, latexEditorController);
		saveCommand = new SaveCommand(latexEditorController);
	}
	
	@Test
	void test() {
		latexEditorController.getCurrentDocument().setDocumentType("articleTemplate");
		createCommand.execute();
		latexEditorController.setFilename("test.tex");
		saveCommand.execute();
		
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream("test.tex"));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String actualContents = latexEditorController.getCurrentDocument().getContents();
		
		assertEquals(fileContents, actualContents);
	}

}
