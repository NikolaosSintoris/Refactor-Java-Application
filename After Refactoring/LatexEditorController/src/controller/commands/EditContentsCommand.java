package controller.commands;

import controller.LatexEditorController;

public abstract class EditContentsCommand implements Command{
	private LatexEditorController latexEditorController;
	
	public EditContentsCommand(LatexEditorController latexEditorController) {
		super();
		this.latexEditorController = latexEditorController;
	}
	
	public void updateCurrentDocumentContents() {
		if(latexEditorController.getVersionsManager().isEnabled()) {
			latexEditorController.getCurrentDocument().setContents(latexEditorController.getLatexEditorView().getTypedText());
			latexEditorController.getVersionsManager().getStrategy().putVersion(latexEditorController.getCurrentDocument());
			latexEditorController.getCurrentDocument().changeVersion();
		}
	}
}
