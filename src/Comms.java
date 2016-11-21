

public interface Comms {
	
	public void send(Message msg, String peer);
	public Message receive();

}
