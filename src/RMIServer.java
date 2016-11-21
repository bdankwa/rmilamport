

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
		
		try {
			RPOInterface server = new RMIComms(comms);
			Naming.rebind(name, server);
			comms.bounded = true;
			System.out.println("server bounded name :" + name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
