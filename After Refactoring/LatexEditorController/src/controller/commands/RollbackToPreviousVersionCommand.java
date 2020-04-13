package controller.commands;

import model.VersionsManager;

public class RollbackToPreviousVersionCommand implements Command {
	private VersionsManager versionsManager;
	
	public RollbackToPreviousVersionCommand(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}

	public void execute() {
		versionsManager.rollback();
	}
}