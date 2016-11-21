

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerEntry {
	
	private static Registry registry;	
	static volatile CommsImpl communications;
	
	public static void main(String args[]){
		System.setSecurityManager(new SecurityManager());
		
		String port = args[0];
		String serverName = args[1];
		
		int port_num = Integer.parseInt(port);
		String AppName = "/ServerEntry";
		String name = "//"+ serverName + ":"+ port + AppName;
		int delay = 10000;
		
		System.out.println("Starting rmiregistry on port" + port_num +"..." );
		try {
			registry = LocateRegistry.createRegistry(port_num);
			communications = new CommsImpl();
			RMIServer server = new RMIServer(name, communications);
			Thread t = new Thread(server);
			t.start();
			
			while(!communications.bounded){
				;
			}
			
			//Do client stuff
			Message txmsg = new Message(1234);
			communications.send(txmsg, name);
			System.out.println(" transmitted message id :" + txmsg.getid());
			
			Message rxmsg = communications.receive();
			
			System.out.println(" received message id :" + rxmsg.getid());
			
		} catch (RemoteException e) {		
			e.printStackTrace();
		}
		
	}

}
