

import java.rmi.Naming;
import java.rmi.RemoteException;

public class CommsImpl implements Comms {
	
	private Message receivedMsg;
	
	public CommsImpl(){
		System.setSecurityManager(new SecurityManager());
	}
	
	public void send(Message msg, String peer){
		try {
			//System.out.println("trying to lookup name :" + peer);
			RPOInterface server = (RPOInterface) Naming.lookup(peer);
			server.transmit(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Message receive(){
		return receivedMsg;
	}
	
	public void setReceivedMsg(Message msg){
		receivedMsg = msg;
	}
	
	public boolean findRemotePeer(String peer){
		try {
			//System.out.println("trying to lookup name :" + peer);
			RPOInterface sv = (RPOInterface) Naming.lookup(peer);
		}catch (RemoteException e){
			//System.out.println("Waiting for remote peer " + peer + " to come up!");
			return false;

		}catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		System.out.println("SYNCHRONIZED WITH " + peer + "!");
		return true;
	}

}
