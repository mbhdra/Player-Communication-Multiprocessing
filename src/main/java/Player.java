// base class of players
// Socket object is here because it is used by both players
// sent message count is common for both player types

import java.net.Socket;

public abstract class Player {
	
	protected int sentMessageCount;
	protected String message;
	protected Socket socket;
	
	public Player() {
		super();
		this.sentMessageCount = 0;
		this.message = "";
	}

	public Player(Socket socket) {
		super();
		this.socket = socket;
		this.sentMessageCount = 0;
		this.message = "";
	}

	public int getSentMessageCount() {
		return sentMessageCount;
	}

	public void setSentMessageCount(int sentMessageCount) {
		this.sentMessageCount = sentMessageCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}