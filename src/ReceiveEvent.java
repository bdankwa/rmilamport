import java.util.Random;


public class ReceiveEvent implements Event {
	
	private CommsImpl communications;
	private LogicalClock processClock;
	private long timeCreated;
	private int byzatineProb;
	
	public ReceiveEvent(CommsImpl comms, LogicalClock clock, int byztProb) {
		communications = comms;
		processClock = clock;
		byzatineProb = byztProb;
		timeCreated = processClock.getTime();
	}

	public void execute(){
		long randomNumber;

		Message inMsg = communications.receive();
		randomNumber = Utils.generateRandomInt(1, byzatineProb);

		if(randomNumber == byzatineProb/2){
			/***********************************************
			 * Byzantine failure - Do nothing
			 *****************************************/
		}
		else{
			processClock.tick();

			if( inMsg.isValid() && (processClock.getTime() <= inMsg.getContent().lclock)){
				processClock.setTime((inMsg.getContent().lclock) + 1);
				//cout << "Process:" << process <<"Advanced my clock"<<endl;
			}
		}
	}

	public long createdAt(){
		return timeCreated;
	}


}
