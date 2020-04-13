package controller.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controller.LatexEditorController;

public class AddLatexCommand extends EditContentsCommand implements Command  {
	private LatexEditorController latexEditorController;
	private String workingDirectory = System.getProperty("user.dir");
	
	public AddLatexCommand(LatexEditorController latexEditorController) {
		super(latexEditorController);
		this.latexEditorController = latexEditorController;
	}

	public void execute() throws IOException {
		String contents = latexEditorController.getLatexEditorView().getEditorPane().getText();
		String contentsBeforeCursor = contents.substring(0, latexEditorController.getLatexEditorView().getEditorPane().getCaretPosition());
		String contentsAfterCursor = contents.substring(latexEditorController.getLatexEditorView().getEditorPane().getCaretPosition());
		
		String selectedCommandName = latexEditorController.getLatexEditorView().getSelectedCommandName();
		
		File file = new File(workingDirectory + File.separator + "Resourses" 
				  + File.separator + selectedCommandName + ".txt"); 

		String addCommandContents = ""; 
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); 
			String line = "";
			while((line = bufferedReader.readLine()) != null){
				addCommandContents = addCommandContents + line + '\n';
			}
			bufferedReader.close();
		}catch(FileNotFoundException exception){
			System.out.println(exception);
		}
		contents = contentsBeforeCursor + "\n" + addCommandContents + contentsAfterCursor;
		latexEditorController.getLatexEditorView().setTypedText(contents);
		latexEditorController.getLatexEditorView().getEditorPane().setText(contents);
		updateCurrentDocumentContents();
	}
	
}
