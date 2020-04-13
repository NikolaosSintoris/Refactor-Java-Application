package controller.commands;

import controller.LatexEditorController;

public class EditCommand extends EditContentsCommand implements Command {
	
	public EditCommand(LatexEditorController latexEditorController) {
		super(latexEditorController);
	}

	public void execute() {
		updateCurrentDocumentContents();
	}
}
