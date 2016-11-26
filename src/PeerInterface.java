

import java.rmi.Remote;

public interface PeerInterface extends Remote {
	
	public void transmit(Message msg) throws java.rmi.RemoteException;

}
