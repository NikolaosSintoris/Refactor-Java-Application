package controller.commands;

import model.VersionsManager;

public class EnableVersionsManagementCommand implements Command {
	private VersionsManager versionsManager;
	
	public EnableVersionsManagementCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	public void execute() {
		versionsManager.enableStrategy();
	}

}
