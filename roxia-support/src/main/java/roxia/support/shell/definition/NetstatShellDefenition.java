
package roxia.support.shell.definition;

import java.io.Serializable;
import java.util.List;

public class NetstatShellDefenition implements Serializable{
	private static final long serialVersionUID = -7826765193573424643L;
	private String id;
	private boolean warning = false;
	private int warningValue = 0;
	private int checkRow = 0;
	private String shellCommand = "";
	private String shellType;
	private String port;
	private List<ShellParamDefinition> inputs;
	public boolean isWarning() {
		return warning;
	}
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	public int getWarningValue() {
		return warningValue;
	}
	public void setWarningValue(int warningValue) {
		this.warningValue = warningValue;
	}
	public int getCheckRow() {
		return checkRow;
	}
	public void setCheckRow(int checkRow) {
		this.checkRow = checkRow;
	}
	public String getShellCommand() {
		return shellCommand;
	}
	public void setShellCommand(String shellCommand) {
		this.shellCommand = shellCommand;
	}
	public String getShellType() {
		return shellType;
	}
	public void setShellType(String shellType) {
		this.shellType = shellType;
	}
	public List<ShellParamDefinition> getInputs() {
		return inputs;
	}
	public void setInputs(List<ShellParamDefinition> inputs) {
		this.inputs = inputs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	
}
