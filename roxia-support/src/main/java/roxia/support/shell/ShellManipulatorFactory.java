package roxia.support.shell;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import roxia.support.shell.definition.NetstatShellDefenition;

public class ShellManipulatorFactory {
	static final Logger logger = LoggerFactory.getLogger(ShellManipulatorFactory.class);
	
	public static final String RULE_FILE = "roxia/support/shell/netstatshell-definition-rule.xml";
	
	private Map<String, NetstatShellDefenition> shellDefinitions = new HashMap<String, NetstatShellDefenition>();	
	
	@SuppressWarnings("unchecked")
	public void setConfig(String... configurations){
		for(String config: configurations){
			Digester digester = DigesterLoader.createDigester(
					new InputSource(Thread.currentThread().getContextClassLoader()
							.getResourceAsStream(RULE_FILE)));
			digester.setValidating(false);
			try {
				List<NetstatShellDefenition> list =
					(List<NetstatShellDefenition>)digester.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(config));
				for(NetstatShellDefenition es: list)
					shellDefinitions.put(es.getId(), es);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Read excel config failed.");
			} catch (SAXException e) {
				e.printStackTrace();
				throw new RuntimeException("Read excel config failed.");
			}
		}
	}
	
	public NetstatShellDefenition createNetstatShellDefenition(String defenitions){
		NetstatShellDefenition shellReader = shellDefinitions.get(defenitions);
		
		return cloneDefenition( shellReader);
	}
	
	
	private NetstatShellDefenition cloneDefenition(NetstatShellDefenition sheet){
		NetstatShellDefenition shell = new NetstatShellDefenition();
		shell.setCheckRow(sheet.getCheckRow());
		shell.setId(sheet.getId());
		shell.setShellCommand(sheet.getShellCommand());
		shell.setShellType(sheet.getShellType());
		shell.setWarningValue(sheet.getWarningValue());
		return shell;
	}
	
	private Class<?> loadClass(String className, Class<?> callingClass) throws ClassNotFoundException {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ex) {
                try {
                    return ShellManipulatorFactory.class.getClassLoader().loadClass(className);
                } catch (ClassNotFoundException exc) {
                    return callingClass.getClassLoader().loadClass(className);
                }
            }
        }
    }
}
