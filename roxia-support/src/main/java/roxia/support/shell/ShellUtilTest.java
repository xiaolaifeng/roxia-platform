package roxia.support.shell;


public class ShellUtilTest {
	public static void main(String[] args){
		ShellExecutor shell = new ShellExecutor();
//		System.out.println(
//				shell
//				.shellType(ShellCommand.COMMAND_NETSTAT)
//				.warningValue(40)
//				.shellCommond("netstat -n|grep :61616|awk  '{print $5}'|awk -F ':' '{print$1}'|sort|uniq -c")
//				.process());
	}		

	
	
	
//	// 基本路径
//	private static final String basePath = "/home/jmuser/BusinessHub/";
//
//	// 记录Shell执行状况的日志文件的位置(绝对路径)
//	private static final String executeShellLogFile = basePath + "executeShell.log";
//
//	// 发送文件到Kondor系统的Shell的文件名(绝对路径)
//	private static final String sendKondorShellName = basePath + "sendKondorFile.sh";
//
//	public int executeShell(String shellCommand) throws IOException {
//		int success = 0;
//		StringBuffer stringBuffer = new StringBuffer();
//		BufferedReader bufferedReader = null;
//		// 格式化日期时间，记录日志时使用
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");
//		try {
//			stringBuffer.append(dateFormat.format(new Date())).append("ready to run Shell ").append(shellCommand).append(" \r\n");
//
//			Process pid = null;
//			String[] cmd = { "/bin/sh", "-c", shellCommand };
//			// 执行Shell命令
//			pid = Runtime.getRuntime().exec(cmd);
//			if (pid != null) {
//				stringBuffer.append("pid：").append(pid.toString()).append("\r\n");
////				 bufferedReader用于读取Shell的输出内容 
//				bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
//				pid.waitFor();
//			}
//			else {
//				stringBuffer.append("no pid\r\n");
//			}
//			stringBuffer.append(dateFormat.format(new Date())).append("Run Shell to completion \r\n result：\r\n");
//			String line = null;
//			// 读取Shell的输出内容，并添加到stringBuffer中
//			while (bufferedReader != null && (line = bufferedReader.readLine()) != null) {
//				stringBuffer.append(line).append("\r\n");
//			}
//			System.out.println(stringBuffer);
//			System.out.println(basePath);
//		}
//		catch (Exception ioe) {
//			stringBuffer.append("run Shell exception：\r\n").append(ioe.getMessage()).append("\r\n");
//		} finally {
//			if (bufferedReader != null) {
//				OutputStreamWriter outputStreamWriter = null;
//				try {
//					System.out.println(executeShellLogFile);
//					bufferedReader.close();
//					// 将Shell的执行情况输出到日志文件中
//					OutputStream outputStream = new FileOutputStream(executeShellLogFile);
//					outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
//					outputStreamWriter.write(stringBuffer.toString());
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					outputStreamWriter.close();
//				}
//			}
//			success = 1;
//		}
//		return success;
//	}
}
