

import java.rmi.Remote;

public interface RPOInterface extends Remote {
	
	public void transmit(Message msg) throws java.rmi.RemoteException;

}
