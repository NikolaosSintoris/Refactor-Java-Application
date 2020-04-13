package model;

import javax.swing.JOptionPane;

import controller.LatexEditorController;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

public class VersionsManager {
	private boolean enabled;
	private VersionsStrategy versionsStrategy;
	private LatexEditorController latexEditorController;

	
	public VersionsManager(LatexEditorController latexEditorController) {
		this.versionsStrategy = new VolatileVersionsStrategy();
		this.latexEditorController = latexEditorController;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void disable() {
		enabled = false;
	}
	
	public void setStrategy(VersionsStrategy versionsStrategy) {
		this.versionsStrategy = versionsStrategy;
	}
	
	public void setCurrentVersion(Document document) {
		latexEditorController.setCurrentDocument(document);
	}

	public void enableStrategy() {
		enabled = true;
	}

	public void changeStrategy() {
		String versionsStrategyType = latexEditorController.getLatexEditorView().getStrategy();
		if(versionsStrategyType.equals("stable") && versionsStrategy instanceof VolatileVersionsStrategy) {
			VersionsStrategy newVersionsStrategy = new StableVersionsStrategy();
			newVersionsStrategy.setEntireHistory(versionsStrategy.getEntireHistory());
			versionsStrategy = newVersionsStrategy;
			enableStrategy();
		}
		else if(versionsStrategyType.equals("volatile") && versionsStrategy instanceof StableVersionsStrategy) {
			VersionsStrategy newVersionsStrategy = new VolatileVersionsStrategy();
			newVersionsStrategy.setEntireHistory(versionsStrategy.getEntireHistory());
			versionsStrategy = newVersionsStrategy;
			enableStrategy();
		}
	}

	public void rollback() {
		if(enabled == false) {
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			Document document = versionsStrategy.getVersion();
			if(document == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				versionsStrategy.removeVersion();
				latexEditorController.setCurrentDocument(document);
			}
		}
	}

	public VersionsStrategy getStrategy() {
		return versionsStrategy;
	}
}
