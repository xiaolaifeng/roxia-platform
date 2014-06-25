
package roxia.support.shell.processor;

import roxia.support.shell.ShellExecutor;

public interface ShellProcessor {
	String getShellType();
	ShellExecutor process(ShellExecutor processorDefinition);
}
