//package roxia.support.shell;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import roxia.support.shell.definition.NetstatShellDefenition;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:roxia-spring.xml" })
//public class ShellTest   {
//
//	@Resource
//	private ApplicationContext ac;
//	
//	@Autowired
//	@Qualifier("monitor1Shell")
//	private NetstatShellDefenition report1ExcelReader;
//	@Test
//	public void testRead1() throws Exception{
//	        Map<String, Object> beans = new HashMap<String, Object>();
//
//         
//		System.out.println(report1ExcelReader.getCheckRow());
//	}
//	
//}
// 