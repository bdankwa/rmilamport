

import java.rmi.Naming;

public class CommsImpl implements Comms {
	
	private Message receivedMsg;
	public boolean bounded;
	
	public CommsImpl(){
		System.setSecurityManager(new SecurityManager());
		bounded = false;
	}
	
	public void send(Message msg, String peer){
		try {
			System.out.println("trying to lookup name :" + peer);
			RPOInterface server = (RPOInterface) Naming.lookup(peer);
			server.transmit(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setReceivedMsg(Message msg){
		receivedMsg = msg;
	}
	
	public Message receive(){
		return receivedMsg;
	}

}
