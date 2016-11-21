
public class InternalEvent implements Event {
	private long currentTime;
	private LogicalClock processClock;
	
	public InternalEvent(LogicalClock clock) {
		processClock = clock;
		currentTime = 0;
	}

	public void execute(){
		/**********************************
		 * Internal event - only advance clock
		 *************************************/
		processClock.tick();
		currentTime = processClock.getTime();

	}

	public long createdAt(){
		return currentTime;

	}

}
