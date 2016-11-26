

public class Message implements java.io.Serializable {
	
	private static final long serialVersionUID = 782557L;
	private static final long VALID_DATA = 0xDEADBEEF;
	
	private int id;
	private Packet data;
	private String source;
	private String destination;
	
	public Message(int id, String source, String destination){
		this.id = id;
		this.source = source;
		this.destination = destination;
		data = new Packet();
		data.lclock = 0;
		data.validityCode = 0;
	}
	
	public Message(Packet data){
		this.data = data;
	}
	
	public Packet getContent(){
		return data;
	}
	
	public void setValid(boolean setValid){
		if(setValid){
			data.validityCode = VALID_DATA;
		}
		else{
			data.validityCode = 0;
		}
	}
	
	public boolean isValid(){

		if(data.validityCode == VALID_DATA){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setCreatedTime(long time){

		data.lclock = time;
	}
	
	public int getid(){
		return id;
	}
	
	public String getSource(){
		return source;
	}
	
	public String getDestination(){
		return destination;
	}

}
