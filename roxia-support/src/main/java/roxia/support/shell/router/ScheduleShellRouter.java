package roxia.support.shell.router;

import java.util.Calendar;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import roxia.support.constants.ShellConstants;
import roxia.support.shell.ShellExecutor;
import roxia.support.shell.definition.NetstatShellDefenition;
import roxia.support.shell.processor.DefaultShellRouterProcessor;

public class ScheduleShellRouter extends RouteBuilder {
	protected final transient Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("shellRouterProcessor")
	private DefaultShellRouterProcessor shellProcessor;
	@Autowired
	@Qualifier("monitorMq")
	private NetstatShellDefenition shellMqDefinition;
	@Autowired
	@Qualifier("monitorSftp")
	private NetstatShellDefenition shellSftpDefinition;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void configure() {
		ShellExecutor shellExecutor1 = new ShellExecutor();
		shellExecutor1
			.shellType(shellMqDefinition.getShellType())
			.setShellDefenition(shellMqDefinition)
			.shellCommond(shellMqDefinition.getShellCommand());
		
		log.debug("begin run ScheduleShellRouter");
		from("quartz://roxia/monitor_mq_netstat_shell?cron=0+20+*+*+*+?")
				.routeId(shellMqDefinition.getId())
				.onException(Exception.class).handled(true)
				.log(LoggingLevel.ERROR, "##  run shell error.")
				.end()
				.setHeader("shellExecutor", constant(shellExecutor1))
				.process(shellProcessor)
				.choice()
				.when(header("warning").isEqualTo("true"))
				.marshal().json(JsonLibrary.Jackson)
				.convertBodyTo(String.class)
				.to(ShellConstants.MONITOR_MEG2JMS)
				.log(LoggingLevel.DEBUG, "##  ## end run-shell. time." + Calendar.getInstance().getTime())
				.otherwise()
				.end();
		
		ShellExecutor shellExecutor2 = new ShellExecutor();
		shellExecutor2
			.shellType(shellSftpDefinition.getShellType())
			.setShellDefenition(shellSftpDefinition)
			.shellCommond(shellSftpDefinition.getShellCommand());
		from("quartz://roxia/monitor_sftp_netstat_shell?cron=0+20+*+*+*+?")
				.routeId(shellSftpDefinition.getId())
				.onException(Exception.class).handled(true)
				.log(LoggingLevel.ERROR, "##  run shell error.")
				.end()
				.setHeader("shellExecutor", constant(shellExecutor2))
				.process(shellProcessor)
				.choice()
				.when(header("warning").isEqualTo("true"))
				.marshal().json(JsonLibrary.Jackson)
				.convertBodyTo(String.class)
				.to(ShellConstants.MONITOR_MEG2JMS)
				.log(LoggingLevel.DEBUG, "##  ## end run-shell. time." + Calendar.getInstance().getTime())
				.otherwise()
				.end();
	}
}
