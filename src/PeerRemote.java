

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PeerRemote extends UnicastRemoteObject implements PeerInterface {
	
	private static final long serialVersionUID = 78837L;	
	private CommsImpl comms;
	
	public PeerRemote(CommsImpl comms) throws RemoteException{
		super();
		this.comms = comms;
	}
	
	public synchronized void transmit(Message msg){	
		if(comms != null){
			comms.setReceivedMsg(msg);
		}		
	}
}
