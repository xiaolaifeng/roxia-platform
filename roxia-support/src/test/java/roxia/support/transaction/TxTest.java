
package roxia.support.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TxTest {
	 public static void main(final String[] args) throws Exception {
	        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-tx.xml", TxTest.class);
//			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:test-roxia-spring.xml");

	        FooService fooService = (FooService) ctx.getBean("fooService");
	        fooService.insertFoo (new Foo());
	    }
}
