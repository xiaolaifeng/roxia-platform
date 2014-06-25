//
//package roxia.support.shell;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//
//public class TestRoxiaApplicationManager   {
//	private ApplicationContext applicationContext;
//	private static TestRoxiaApplicationManager instance;
//	public TestRoxiaApplicationManager() {
//	}
//	public static TestRoxiaApplicationManager getInstance() {
//		if (instance == null) {
//			instance = new TestRoxiaApplicationManager();
//		}
//		return instance;
//	}
//	boolean stop = false;
//
//	public void stop() {
//		this.stop = true;
//	}
//	public void run() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:test-roxia-spring.xml");
//		while (!stop) {
//			try {
//				Thread.sleep(30000);
//			} catch (InterruptedException e) {
//				// swollowed
//			}
//		}
//	}
//
//	public static void main(String[] args)   {
//		TestRoxiaApplicationManager.getInstance().run();
//
//		TestRoxiaApplicationManager applicationManager = new TestRoxiaApplicationManager();
////		ShellUtilTest shell=new ShellUtilTest();
//		try {
////			shell.executeShell("netstat -n|grep :61616|awk  '{print $5}'|awk -F ':' '{print$1}'|sort|uniq -c");
////			ShellProcessorDefinition shellA = new ShellProcessorDefinition();
////			System.out.println(shellA.shellType(ShellType.SIMPLE_SHEET).warningValue(40).shellCommond("netstat -n|grep :61616|awk  '{print $5}'|awk -F ':' '{print$1}'|sort|uniq -c").process());
//		}
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
