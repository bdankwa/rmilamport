

import java.rmi.Naming;
/****************************************************
 * Server thread
 * 
 * This thread listens for remote calls
 * @author bdankwa
 *
 */

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
			PeerInterface server = new PeerRemote(comms);
			Naming.rebind(name, server);
			System.out.println("SEVER RUNNING, bound to :" + name);

		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

}
