public class timeWrapper implements Comparable {

	public long time;
	public String output;
	
	public timeWrapper (long time, String output) {
		this.time = time;
		this.output = output;
	}
	
	@Override
	public int compareTo (Object other) {
		timeWrapper temp = (timeWrapper)other;
		return Long.valueOf(time).compareTo(Long.valueOf(temp.time));
	}
}
