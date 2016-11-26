

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
			PeerInterface server = (PeerInterface) Naming.lookup(peer);
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
		if(msg != null){
			System.out.println("Received message from: " + msg.getSource() + ", clock value = " + msg.getContent().lclock);
		}
		else{
			System.out.println("Not receiving anythin...");
		}
		
	}
	
	public boolean findRemotePeer(String peer){
		try {
			//System.out.println("trying to lookup name :" + peer);
			PeerInterface sv = (PeerInterface) Naming.lookup(peer);
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
