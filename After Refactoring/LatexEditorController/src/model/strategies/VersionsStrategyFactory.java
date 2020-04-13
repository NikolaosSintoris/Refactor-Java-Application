package model.strategies;

import java.util.HashMap;

public class VersionsStrategyFactory {
	private HashMap<String, VersionsStrategy> versionsStrategiesMap;
	
	public VersionsStrategyFactory() {
		versionsStrategiesMap = new HashMap<String, VersionsStrategy>();
		versionsStrategiesMap.put("volatileStrategy", new VolatileVersionsStrategy());
		versionsStrategiesMap.put("stableStrategy", new StableVersionsStrategy());
	}
	
	public VersionsStrategy createStrategy(String type) {
		return versionsStrategiesMap.get(type);
	}
}
