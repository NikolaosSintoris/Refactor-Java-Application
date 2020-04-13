package model.strategies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Document;

public class StableVersionsStrategy implements VersionsStrategy{
	private String versionID = "";

	public void putVersion(Document document) {
		String filename = document.getVersionID() + ".tex";
		document.save(filename);
		versionID = document.getVersionID();
	}

	public Document getVersion() {
		if(versionID.equals(""))
			return null;
		
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(versionID + ".tex"));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Document document = new Document();
		document.setContents(fileContents);
		return document;
	}

	public void setEntireHistory(List<Document> documentList) {
		for(int i = 0; i < documentList.size(); i++) {
			Document document = documentList.get(i);
			document.save(document.getVersionID() +".tex");
		}
		if(documentList.size() > 0)
			versionID = documentList.get(documentList.size()-1).getVersionID();
		else
			versionID = "";
	}

	public List<Document> getEntireHistory() {
		List<Document> documents = new ArrayList<Document>();
		if(versionID.equals(""))
			return documents;
		int version = Integer.parseInt(versionID);
		for(int i = 0; i <= version; i++) {
			String fileContents = "";
			try {
				Scanner scanner = new Scanner(new FileInputStream(i + ".tex"));
				while(scanner.hasNextLine()) {
					fileContents = fileContents + scanner.nextLine() + "\n";
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Document document = new Document();
			document.setContents(fileContents);
			documents.add(document);
		}
		return documents;
	}

	public void removeVersion() {
		int version = Integer.parseInt(versionID);
		if(version== 0)
			versionID = "";
		else
			versionID = (version-1) + "";
	}
}
