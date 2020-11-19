package stream;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	
    private String message;
    private String username;
    private Date date;

	public Date getDate() {
		return date;
	}
	public String getMessage() {
		return message;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	} 
	public Message(String message, String username) {
		super();
		this.message = message;
		this.username = username;
		this.date = new Date();
	}
	@Override
	public String toString() {
		return  "\n" + username + " : "+ date.toString() + "\n" + message ;
	}
	
    
    
}
