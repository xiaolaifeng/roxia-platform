
package roxia.support.shell.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellException extends RuntimeException{
    protected final transient Logger log = LoggerFactory.getLogger(getClass());

	public ShellException(){};
	public ShellException(String shellCommond){
		log.error("run shell error. script:"+shellCommond);
	};
	public ShellException(String shellCommond,String msg){
		log.error("run shell error. script:"+shellCommond);
		log.error(msg);
		System.out.println(msg);
	};

}
