package controller.commands;

import model.VersionsManager;

public class ChangeVersionsStrategyCommand implements Command {
	private VersionsManager versionsManager;
	
	public ChangeVersionsStrategyCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	public void execute() {
		versionsManager.changeStrategy();
	}
}
