package controller.commands;

import model.VersionsManager;

public class DisableVersionsManagementCommand implements Command {

	private VersionsManager versionsManager;
	
	public DisableVersionsManagementCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	public void execute() {
		versionsManager.disable();
	}

}
