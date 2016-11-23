

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerEntry {
	
	private static Registry registry;	
	static volatile CommsImpl communications;
	
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
		
		String us[] = {myhostname, peer1, peer2, peer3};
		
		int port_num = Integer.parseInt(port);
		String AppName = "/ServerEntry";
		String me = "//"+ myhostname + ":"+ port + AppName;
		
		System.out.println("Starting rmiregistry on port" + port_num +"..." );
		try {
			registry = LocateRegistry.createRegistry(port_num);
			communications = new CommsImpl();
			RMIServer server = new RMIServer(me, communications);
			Thread t = new Thread(server);
			t.start();
			
			while(!communications.bounded){
				;
			}
			
			//Do client stuff
			/*Message txmsg = new Message(1234);
			communications.send(txmsg, name);
			System.out.println(" transmitted message id :" + txmsg.getid());
			
			Message rxmsg = communications.receive();
			
			System.out.println(" received message id :" + rxmsg.getid()); */
			Process  proc = new Process(communications, us, Integer.parseInt(iterations),
					Integer.parseInt(eventProb), Integer.parseInt(byzntProb));
			
			proc.run();
			
		} catch (RemoteException e) {		
			e.printStackTrace();
		}
		
	}

}
