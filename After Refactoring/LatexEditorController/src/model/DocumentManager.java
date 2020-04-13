package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DocumentManager {
	private HashMap<String, Document> documentTemplatesMap;
	private String workingDirectory = System.getProperty("user.dir");
	
	public DocumentManager() throws IOException {
		documentTemplatesMap = new HashMap<String, Document>();
		File file = new File(workingDirectory + File.separator + "Resourses" 
				+ File.separator + "DocumentTemplateNames.txt"); 
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); 
			String line; 
			line = bufferedReader.readLine();
			String[] documentTemplateNamesArray = line.split(",");
			for (int i = 0; i < documentTemplateNamesArray.length; i++) {
				Document document = new Document();
				document.setContents(getTemplateContents(documentTemplateNamesArray[i]));
				document.setDocumentType(documentTemplateNamesArray[i]);
				documentTemplatesMap.put(documentTemplateNamesArray[i], document);
			}
			bufferedReader.close();
		}catch(FileNotFoundException exception){
			System.out.println(exception);
		}
	}
	
	public Document createDocument(String type) {
		return documentTemplatesMap.get(type).clone();
	}
	
	public String getTemplateContents(String templateType) throws IOException {
		File file = new File(workingDirectory + File.separator + "Resourses" 
											  + File.separator + templateType + ".txt"); 

		String templateContents = ""; 
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); 
			String line = "";
			while((line = bufferedReader.readLine()) != null){
				templateContents = templateContents + line + "\n";
			}
			bufferedReader.close();
		}catch(FileNotFoundException exception){
			System.out.println(exception);
		}
		return templateContents;
	}
}
