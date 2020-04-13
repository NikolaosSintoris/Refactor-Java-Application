package controller;

import java.io.*;
import java.util.HashMap;
import controller.commands.Command;
import controller.commands.CommandFactory;
import model.Document;
import model.VersionsManager;
import view.LatexEditorView;

public class LatexEditorController{
	private HashMap<String, Command> commandsMap;
	private String workingDirectory = System.getProperty("user.dir");
	private Document currentDocument;
	private LatexEditorView latexEditorView;
	private String filename;
	private VersionsManager versionsManager;
	
	public LatexEditorController() throws IOException {
		currentDocument = new Document("", "", "", "", "", "");
		this.versionsManager = new VersionsManager(this);
		versionsManager.enableStrategy();
		CommandFactory commandFactory = new CommandFactory(versionsManager, this);
		commandsMap = new HashMap<String, Command>();

		File file = new File(workingDirectory + File.separator + "Resourses" 
										+ File.separator + "CommandObjects.txt"); 
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); 
			String line; 
			line = bufferedReader.readLine();
			String[] commandObjectsArray = line.split(",");
			for (int i = 0; i < commandObjectsArray.length; i++) {
				commandsMap.put(commandObjectsArray[i], commandFactory.createCommand(commandObjectsArray[i]));
			}
			bufferedReader.close();
		}catch(FileNotFoundException exception){
			System.out.println(exception);
		}
	}
	
	public void enact(String command) throws IOException {
		commandsMap.get(command).execute();
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public Document getCurrentDocument() {
		return currentDocument;
	}
	
	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}
	
	public VersionsManager getVersionsManager(){
		return versionsManager;
	}
	
	public void setVersionsManager(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}
	
	public LatexEditorView getLatexEditorView(){
		return latexEditorView;
	}

	public void setLatexEditorView(LatexEditorView latexEditorView) {
		this.latexEditorView = latexEditorView;
	}
}	
