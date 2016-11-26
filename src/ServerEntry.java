

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerEntry {
	
	private static Registry registry;	
	static volatile CommsImpl communications;
	
	/**************************************************
	 * Main entry
	 * @param args
	 *************************************************/
	
	public static void main(String args[]){
		System.setSecurityManager(new SecurityManager());
		
		String port = args[0];
		String myhostname = args[1];
		String peer1 = args[2];
		String peer2 = args[3];
		String peer3 = args[4];
		String iterations = args[5];
		String eventProb = args[6];
		String byzntProb = args[7];
		
		String hostnames[] = {myhostname, peer1, peer2, peer3};
		//String hostnames[] = {myhostname, peer1};
		
		int port_num = Integer.parseInt(port);
		String AppName = "/ServerEntry";
		String me = "//"+ myhostname + ":"+ port + AppName;
		String peerNames[] = {null, null, null, null};
		
		for(int i =0; i<hostnames.length; i++){
			peerNames[i] = "//"+ hostnames[i] + ":"+ port + AppName;
		}
		
		System.out.println("Starting rmiregistry on port " + port_num +"..." );
		try {
			//Start RMI registry
			registry = LocateRegistry.createRegistry(port_num);
			
			communications = new CommsImpl();
			RMIServer server = new RMIServer(me, communications);
			Thread t = new Thread(server);
			//Start server thread
			t.start();
			
			//Run process object - simulation
			Process  proc = new Process(communications, peerNames, hostnames[0], Integer.parseInt(iterations),
					Integer.parseInt(eventProb), Integer.parseInt(byzntProb));
			
			proc.run();
			
		} catch (RemoteException e) {		
			e.printStackTrace();
		}
		
	}

}
