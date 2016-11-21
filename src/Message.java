

public class Message implements java.io.Serializable {
	
	private static final long serialVersionUID = 782557L;
	
	private int id;
	
	public Message(int id){
		this.id = id;		
	}
	
	public int getid(){
		return id;
	}

}
