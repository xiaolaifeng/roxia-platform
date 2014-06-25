package roxia.support.shell.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import roxia.support.shell.ShellExecutor;
import cn.baozun.bh.task.model.EmailInfo;
import cn.baozun.bh.task.model.TaskMessageBean;

@Component("shellRouterProcessor")
public class DefaultShellRouterProcessor implements Processor {
	protected final transient Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings({ "rawtypes" })
	public void process(Exchange exchange) throws Exception {
		log.debug("begin run ScheduleShellProcessor");
		ShellExecutor shellDefinition = exchange.getIn().getHeader("shellExecutor", ShellExecutor.class);
		shellDefinition.process();
		String messageContent = null;
		if (shellDefinition.warning) {
			messageContent = shellDefinition.toHtml();
			exchange.getOut().setHeader("warning", "true");
		}
		else {
			exchange.getOut().setHeader("warning", "false");
			return;
		}

		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setSubject(shellDefinition.getMessageSubject());
		emailInfo.setTextString(messageContent);
		TaskMessageBean messageBean = new TaskMessageBean();
		messageBean.setPlatformCode("monitor");
		messageBean.setInterfaceCode(shellDefinition.getShellType());
		messageBean.setMessageType(shellDefinition.getShellType());
		messageBean.setEmailInfo(emailInfo);
		messageBean.setSendMail(Boolean.TRUE);

		exchange.getOut().setBody(messageBean);
	}

}