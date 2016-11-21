
public class LogicalClock {
	private static long time;
	
	public LogicalClock(){
		time = 0;
	}
	
	public void tick(){
	    time++;
	}

	public void setTime(long t){
		time = t;
	}

	public long getTime(){
		return time;
	}

	public void reset(){
		time = 0;
	}

}
