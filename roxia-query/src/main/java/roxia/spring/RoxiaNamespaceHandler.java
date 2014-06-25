package roxia.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class RoxiaNamespaceHandler extends NamespaceHandlerSupport {

	public void init() {
		registerBeanDefinitionParser("dao", new GenericDaoBeanDefinitionParser());
		registerBeanDefinitionParser("dao-config", new GenericDaoConfigBeanDefinitionParser());
	}

}
