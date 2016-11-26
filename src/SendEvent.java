
public class SendEvent implements Event {
	
	private long timeCreated;
	private CommsImpl communications;
	String destination;
	String source;
	LogicalClock processClock;
	
	public SendEvent(CommsImpl comms, String source, String destination, LogicalClock clock) {
		processClock = clock;
		communications = comms;
		this.source = source;
		this.destination = destination;
		timeCreated = processClock.getTime();
	}

	public void execute(){
		/************************************************
		 * Create a message, write current clock value and
		 * send to destination.
		 ************************************************/
		Message outMsg = new Message(1, source, destination);
		processClock.tick();
		outMsg.setCreatedTime(processClock.getTime());
		outMsg.setValid(true);
		communications.send(outMsg, destination);

	}

	public long createdAt(){
		return timeCreated;
	}

}
