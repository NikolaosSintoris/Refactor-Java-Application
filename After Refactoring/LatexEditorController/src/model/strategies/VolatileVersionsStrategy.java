package model.strategies;

import java.util.ArrayList;
import java.util.List;
import model.Document;

public class VolatileVersionsStrategy implements VersionsStrategy {
	private ArrayList<Document> versionsStrategyHistoryArrayList;
	
	public VolatileVersionsStrategy() {
		super();
		versionsStrategyHistoryArrayList = new ArrayList<Document>();
	}

	public void putVersion(Document document) {
		Document newDocument = document.clone();
		versionsStrategyHistoryArrayList.add(newDocument);
	}

	public Document getVersion() {
		if(versionsStrategyHistoryArrayList.size() == 0) {	
			return null;
		}
		return versionsStrategyHistoryArrayList.get(versionsStrategyHistoryArrayList.size() - 1);
	}

	public void setEntireHistory(List<Document> documentsList) {
		versionsStrategyHistoryArrayList.clear();
		versionsStrategyHistoryArrayList.addAll(documentsList);
	}

	public List<Document> getEntireHistory() {
		return versionsStrategyHistoryArrayList;
	}

	public void removeVersion() {
		versionsStrategyHistoryArrayList.remove(versionsStrategyHistoryArrayList.size() - 1);
	}
}
