

public class Message implements java.io.Serializable {
	
	private static final long serialVersionUID = 782557L;
	private static final long VALID_DATA = 0xDEADBEEF;
	
	private int id;
	private Packet data;
	
	public Message(int id){
		this.id = id;
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

}
