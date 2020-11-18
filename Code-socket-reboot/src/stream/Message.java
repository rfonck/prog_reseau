package stream;

public class Message {
    private String message;
    private String username;
	public String getMessage() {
		return message;
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
	}
	@Override
	public String toString() {
		return "Message [message=" + message + ", username=" + username + "]";
	}
	
    
    
}
