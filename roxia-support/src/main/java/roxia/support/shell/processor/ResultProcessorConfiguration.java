
package roxia.support.shell.processor;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class ResultProcessorConfiguration {

	public static ResultProcessorConfiguration instance;
	private Map<String, ResultProcessor> processorMap = new HashMap<String, ResultProcessor>();
	

	private ResultProcessorConfiguration(){
		registerResultProcessor(new NetstatProcessor());
	}
	public void registerResultProcessor(ResultProcessor dc){
		processorMap.put(dc.supportCommond(), dc);
	}
	
	public  ResultProcessor getProcessor(String command){
		return (ResultProcessor)processorMap.get(command);
	}
	
	
	public static ResultProcessorConfiguration getInstance(){
		if(instance == null)
			instance = new ResultProcessorConfiguration();
		return instance;
	}
	
}
