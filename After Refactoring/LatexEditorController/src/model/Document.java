package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Document {
	private String author;
	private String date;
	private String copyright;
	private String versionID = "0";
	private String contents;
	private String documentType;
	
	public Document(String author, String date, String copyright, String versionID, String contents, String documentType) {
		this.author = author;
		this.date = date;
		this.copyright = copyright;
		this.versionID = versionID;
		this.contents = contents;
		this.documentType = documentType;
	}
	
	public Document() {
		this.contents = "";
		this.documentType = "";
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public void save(String filename) {
		try {
			PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename));
			printWriter.write(contents);
			printWriter.close();
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
	}
	
	public Document clone() {
		return new Document(author, date, copyright, versionID, contents, documentType);
	}

	public void changeVersion() {
		int oldVersion = Integer.parseInt(versionID);
		versionID = (oldVersion + 1) + "";
	}

	public String getVersionID() {
		return versionID;
	}
}
