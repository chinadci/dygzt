package software.dygzt.service.research;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResearchVariableFactory {
	@Autowired
	private Map<String,ResearchVariableService> map;
	
	public ResearchVariableService getServiceByName(String key){
		return map.get(key);
	}

	public void setMap(Map<String, ResearchVariableService> map) {
		this.map = map;
	}
	
}
