package controller.commands;

import java.util.HashMap;

import controller.LatexEditorController;

public class SaveEncryptedCommand extends CodecCommand implements Command{
	static final int ATBASH_KEY = 25;
	static final int ROT_13_KEY = 13;
	static final int ENCRYPTION = 1;
	private LatexEditorController latexEditorController;
	private HashMap<Integer, String> encryptionModeMap;

	public SaveEncryptedCommand(LatexEditorController latexEditorController) {
		this.latexEditorController = latexEditorController;
		encryptionModeMap = new HashMap<>();
		encryptionModeMap.put(ATBASH_KEY, "#");
		encryptionModeMap.put(ROT_13_KEY, "*");
	}


	public void execute() {
		String contents = latexEditorController.getCurrentDocument().getContents();
		char[] contentsCharArray = contents.toCharArray(); 
		int encryptionKey = latexEditorController.getLatexEditorView().getEncryptionKey();
		
		if(encryptionKey == ATBASH_KEY) {
			contentsCharArray = atbashCodec(contentsCharArray);
		}else {
			contentsCharArray = rot13Codec(contentsCharArray, ENCRYPTION);
		}
		contents = encryptionModeMap.get(encryptionKey) + String.copyValueOf(contentsCharArray);
		latexEditorController.getCurrentDocument().setContents(contents);
		latexEditorController.getCurrentDocument().save(latexEditorController.getFilename());
	}
}
