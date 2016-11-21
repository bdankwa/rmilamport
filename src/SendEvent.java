
public class SendEvent implements Event {
	
	private long timeCreated;
	private CommsImpl communications;
	String destination;
	LogicalClock processClock;
	
	public SendEvent(CommsImpl comms, String dest, LogicalClock clock) {
		processClock = clock;
		communications = comms;
		destination = dest;
		timeCreated = processClock.getTime();
	}

	public void execute(){
		/************************************************
		 * Create a message, write current clock value and
		 * send to destination.
		 ************************************************/
		Message outMsg = new Message(1);
		processClock.tick();
		outMsg.setCreatedTime(processClock.getTime());
		outMsg.setValid(true);
		communications.send(outMsg, destination);

	}

	public long createdAt(){
		return timeCreated;
	}

}
