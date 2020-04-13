package controller.commands;

import controller.LatexEditorController;

public class SaveCommand implements Command {
	private LatexEditorController latexEditorController;
	
	public SaveCommand(LatexEditorController latexEditorController) {
		this.latexEditorController = latexEditorController;
	}

	public void execute() {
		latexEditorController.getCurrentDocument().save(latexEditorController.getFilename());
	}

}
