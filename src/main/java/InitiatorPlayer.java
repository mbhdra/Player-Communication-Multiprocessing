// class for initiator player
// holds counts for received messages additionally because it determines end of communication after 10 message sent and received

import java.net.Socket;

public class InitiatorPlayer extends Player {

	private int receivedMessageCount;
	
	public InitiatorPlayer(Socket socket) {
		super(socket);
		this.receivedMessageCount = 0;
	}

	public int getReceivedMessageCount() {
		return receivedMessageCount;
	}

	public void setReceivedMessageCount(int receivedMessageCount) {
		this.receivedMessageCount = receivedMessageCount;
	}
}