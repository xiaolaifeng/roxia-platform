
package roxia.support.shell.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import roxia.support.shell.ShellExecutor;
import roxia.support.shell.definition.NetstatShellDefenition;
import roxia.support.shell.definition.ShellCommand;
import roxia.support.shell.model.MonitorMessage;
import cn.baozun.bh.util.ReloadablePropertiesUtil;
@Component
public class NetstatProcessor implements ResultProcessor<NetstatShellDefenition>{
    protected final transient Logger log = LoggerFactory.getLogger(getClass());
	private VelocityEngine velocityEngine;
	private ReloadablePropertiesUtil monitorConfigService;
	public String supportCommond() {
		return ShellCommand.COMMAND_NETSTAT;
	}

	
	@SuppressWarnings("rawtypes")
	public ShellExecutor process(ShellExecutor<NetstatShellDefenition> processorDefinition) {
		velocityEngine=(VelocityEngine)applicationContext.getBean("velocityEngine");
		monitorConfigService=(ReloadablePropertiesUtil)applicationContext.getBean("monitorConfigService");
		String[] str = processorDefinition.outputs.split("\n");
		Collection<MonitorMessage> monitorMessages = new ArrayList<MonitorMessage>();
		for(String checkValue : str){
			String[] checkValueStr= checkValue.trim().split(" ");
			MonitorMessage monitorCell = new MonitorMessage();
			Integer value = Integer.valueOf(checkValueStr[processorDefinition.getShellDefenition().getCheckRow()]);
			monitorCell.setCount(value);
			monitorCell.setTargetIp(checkValueStr[1]);
			if(value>processorDefinition.getShellDefenition().getWarningValue()){
				if(!processorDefinition.warning)
				processorDefinition.warning=true;
				log.error("Dangerous! Please check below ips . ");
				monitorCell.setLevel(1);
			}else{
				monitorCell.setLevel(0);
			}
			monitorMessages.add(monitorCell);
		}
		
		String mailTemplatePath = "/shell/netstat-mail-template.vm";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("monitorMsgs", monitorMessages);
		map.put("targetIp",  monitorConfigService.loadString("hostIp"));
		map.put("targetPort",processorDefinition.getShellDefenition().getPort());
		map.put("velocityEngine",velocityEngine);
		String email = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, mailTemplatePath, "UTF-8", map);
		processorDefinition.logOutPuts = email;
		return processorDefinition;
	}
	
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		NetstatProcessor.applicationContext = applicationContext;
	}
}
