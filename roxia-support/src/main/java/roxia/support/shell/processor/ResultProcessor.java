
package roxia.support.shell.processor;

import roxia.support.shell.ShellExecutor;

public interface ResultProcessor <T> {
	String supportCommond();
	ShellExecutor process(ShellExecutor<T> processorDefinition);

}
