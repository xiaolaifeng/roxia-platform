package roxia.support.shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roxia.support.shell.exception.ShellException;
import roxia.support.shell.processor.ResultProcessor;
import roxia.support.shell.processor.ResultProcessorConfiguration;
import roxia.support.shell.processor.ShellProcessor;

@SuppressWarnings("rawtypes")
public class ShellExecutor<T> {
	protected final transient Logger log = LoggerFactory.getLogger(getClass());

	public boolean warning = false;
	public String outputs = "";
	public String logOutPuts = "";
	private String messageSubject;
	private String shellCommond = "";
//	private List<ShellParamDefinition> inputs;
	private String shellType;
	private T shellDefenition;
	public ShellExecutor process() throws ShellException {
		return process(this.shellType);
	}

	public ShellExecutor process(String shellType) throws ShellException {
		log.debug("begin process"+getClass());
		ResultProcessor dc = ResultProcessorConfiguration.getInstance().getProcessor(shellType);
		if (dc == null) {
			throw new ShellException(shellType, "don't support this command");
		}
		return process(dc);
	}

	public ShellExecutor process(ResultProcessor processor) {
		if (0 == executeShell(this.shellCommond))
			throw new ShellException(this.shellCommond, this.outputs);
		return processor.process(this);
	}

	public ShellProcessor filterTypeIn() {
		return null;
	}


	public ShellExecutor shellCommond(String shellCommond) {
		this.shellCommond = shellCommond;
		return this;
	}

	public ShellExecutor shellType(String shellType) {
		this.shellType = shellType;
		return this;
	}

	public int executeShell(String shellCommand) {
		log.debug("beging to run shell");
		int success = 0;
		StringBuffer stringBufferLog = new StringBuffer();
		StringBuffer stringBufferShell = new StringBuffer();
		BufferedReader bufferedReader = null;
		// 格式化日期时间，记录日志时使用
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");
		try {
			stringBufferLog.append(dateFormat.format(new Date())).append("ready to run Shell ").append(shellCommand).append(" \r\n");

			Process pid = null;
			String[] cmd = { "/bin/sh", "-c", shellCommand };
			// 执行Shell命令
			pid = Runtime.getRuntime().exec(cmd);
			if (pid != null) {
				stringBufferLog.append("pid：").append(pid.toString()).append("\r\n");
				// bufferedReader用于读取Shell的输出内容
				bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
				pid.waitFor();
			}
			else {
				stringBufferLog.append("no pid\r\n");
			}
			stringBufferLog.append(dateFormat.format(new Date())).append("Run Shell to completion \r\n result：\r\n");
			String line = null;
			// 读取Shell的输出内容，并添加到stringBuffer中
			while (bufferedReader != null && (line = bufferedReader.readLine()) != null) {
				stringBufferLog.append(line).append("\r\n");
				stringBufferShell.append(line).append("\r\n");
			}
			System.out.println(stringBufferLog);
			success = 1;
		}
		catch (Exception ioe) {
			stringBufferLog.append("run Shell exception：\r\n").append(ioe.getMessage()).append("\r\n");
			ioe.printStackTrace();
			success = 0;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		this.outputs = stringBufferShell.toString();
		this.logOutPuts += stringBufferLog.toString();
		log.debug("en to run shell.result :" + success);
		return success;
	}

	public T getShellDefenition() {
		return shellDefenition;
	}

	public ShellExecutor<T> setShellDefenition(T shellDefenition) {
		this.shellDefenition = shellDefenition;
		return this;
	}
	public String getShellType(){
		return this.shellType;
	}
	
	
	public String getMessageSubject() {
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}

	public String toString() {
		log.error("this 's warning: " + this.warning);
		return this.warning ? this.outputs : "";
	}
	public String toHtml(){
		return this.warning ? this.logOutPuts : "";
 
	}
}
