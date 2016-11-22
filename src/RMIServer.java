

import java.rmi.Naming;

public class RMIServer implements Runnable{	

	private String name;
	private CommsImpl comms;
	
	public RMIServer(String name, CommsImpl comms){
		super();
		this.name = name;
		this.comms = comms;
	}
	
	public void run(){
		//System.setSecurityManager(new SecurityManager());
		System.out.println("STARTING SERVER ON " + name);
		
		try {
			RPOInterface server = new RMIComms(comms);
			Naming.rebind(name, server);
			System.out.println("SEVER RUNNING, bound to :" + name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("Exiting...");
		
		
	}

}
