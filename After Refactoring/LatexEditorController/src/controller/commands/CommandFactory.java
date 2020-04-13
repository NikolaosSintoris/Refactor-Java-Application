package controller.commands;

import java.io.IOException;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;

public class CommandFactory {
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private LatexEditorController latexEditorController;
	
	public CommandFactory(VersionsManager versionsManager, LatexEditorController latexEditorController) throws IOException {
		this.latexEditorController = latexEditorController;
		this.versionsManager = versionsManager;
		documentManager = new DocumentManager();
	}


	public Command createCommand(String type) {
		if(type.equals("addLatex")) {
			return new AddLatexCommand(latexEditorController);
		}
		if(type.equals("changeVersionsStrategy")) {
			return new ChangeVersionsStrategyCommand(versionsManager);
		}
		if(type.equals("create")) {
			return new CreateCommand(documentManager, versionsManager, latexEditorController);
		}
		if(type.equals("disableVersionsManagement")) {
			return new DisableVersionsManagementCommand(versionsManager);
		}
		if(type.equals("edit")) {
			return new EditCommand(latexEditorController);
		}
		if(type.equals("enableVersionsManagement")) {
			return new EnableVersionsManagementCommand(versionsManager);
		}
		if(type.equals("load")) {
			return new LoadCommand(versionsManager, latexEditorController);
		}
		if(type.equals("rollbackToPreviousVersion")) {
			return new RollbackToPreviousVersionCommand(versionsManager);
		}
		if(type.equals("save")) {
			return new SaveCommand(latexEditorController);
		}
		if(type.equals("saveEncrypted")) {
			return new SaveEncryptedCommand(latexEditorController);
		}
		if(type.equals("loadEncrypted")) {
			return new LoadEncryptedCommand(latexEditorController);
		}
		return null;
	}
}
