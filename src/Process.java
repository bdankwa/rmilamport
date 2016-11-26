import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;


public class Process {
	
	private static final int NUM_OF_PROC = 4;
	
	private LogicalClock clock;
	private int proc_id;
	private int num_processes;
	private int eventProb;
	private int byztProb;
	private long iterations;
	private String logFile;
	private String me;
	private CommsImpl communications;
	private Formatter fmtFile;
	private String nodes[];
	
	public Process(CommsImpl comms, String[] peers, String localName, int iterations, int eventProb, int byzntProb) {

		clock = new LogicalClock();
		communications = comms;
		this.iterations = iterations; //args[0];
		this.eventProb = eventProb; //args[1];
		this.byztProb = byzntProb; //args[2];
		this.nodes = peers;
		this.me = localName;
		
		logFile = "node_" + me + ".dat";
		try {
			fmtFile = new Formatter(new FileOutputStream(logFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		int i;
		//int randomNumber;
		Event rcvEvent = null;
		Event randomEvent = null;
		long prev_time = 0;
		//FILE* fd;

		/*if((fd = fopen(logFile, "a")) == NULL){
			perror("Cannot open output file \n");
			exit(EXIT_FAILURE);
		}*/

		//cout << "Process: " << proc_id << endl;
		for(i=1; i< nodes.length; i++){
			while(!communications.findRemotePeer(nodes[i]));			
		}

		for(i=0; i<iterations; i++){

			/*randomNumber = generateRandomInt(1, byztProb);

			if(randomNumber == byztProb/2){
				//Byzantine event
			}
			else{
				rcvEvent = new ReceiveEvent(communications, clock, proc_id);
				if(rcvEvent != NULL){
					rcvEvent->execute();
				}
			}*/
			rcvEvent = new ReceiveEvent(communications, clock, byztProb);
			if(rcvEvent != null){
				rcvEvent.execute();
			}

			randomEvent = createRandomEvent();
			if(randomEvent != null){
				randomEvent.execute();
			}

			
			fmtFile.format("%d\t%d\t%f\t%f\t%d\t%d\n", 0, i, (eventProb/10.0), (1.0/byztProb), 
					clock.getTime(), (clock.getTime() - prev_time));

			prev_time = clock.getTime();

		}

		//fclose(fd);
		fmtFile.close();
		System.out.println("SIMULATION COMPLETE! runs = " + iterations);
		System.out.println("Data is saved in " + logFile);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error! Unable to sleep");
		}
		//System.exit(0);
	}

	Event createRandomEvent() {

		Event event = null;

		int selection = Utils.generateRandomInt(1, 10);
		int destinationID = Utils.generateRandomInt(1, NUM_OF_PROC-1);
		String randomDestination = nodes[destinationID];

		switch (eventProb) {
		case 1 : // 0.1 probability
			if(selection == 1){
				event = new InternalEvent(clock);
			}
			if(selection == 10){
				event = new SendEvent(communications, me, randomDestination, clock);
			}
			break;
		case 2 : // 0.2 probability
			if(selection <= 2){
				event = new InternalEvent(clock);
			}
			if(selection >= 9){
				event = new SendEvent(communications, me, randomDestination, clock);
			}
			break;
		case 3: // 0.3 probability
			if(selection <= 3){
				event =  new InternalEvent(clock);
			}
			if(selection >= 8){
				event =  new SendEvent(communications, me, randomDestination, clock);
			}
			break;
		case 4: // 0.4 probability
			if(selection <= 4){
				event =  new InternalEvent(clock);
			}
			if(selection >= 7){
				event =  new SendEvent(communications, me,randomDestination, clock);
			}
			break;
		case 5: // 0.5 probability
			if(selection <= 5){
				event =  new InternalEvent(clock);
			}
			if(selection >= 6){
				event =  new SendEvent(communications, me, randomDestination, clock);
			}
			break;
		default:
			event = null;
			break;
		}

		return event;
	}

}
