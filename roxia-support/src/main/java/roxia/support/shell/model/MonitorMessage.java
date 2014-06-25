
package roxia.support.shell.model;



public class MonitorMessage implements Comparable<MonitorMessage>{
	
	private String targetIp;
	private Integer count;
	private Integer level;

	public String getTargetIp() {
		return targetIp;
	}

	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @author robin.jiao
	 *         此方法是为了在Collections.sort的时候可以首先按照count排序，这样经过Collections.sort之后的List<MonitorMessage>在展示的时候不至于过于凌乱
	 */
	public int compareTo(MonitorMessage paramT) {
		int r1 = 0;
		r1 = paramT.count>this.count?0:1;
		return r1;
	}


}
